package br.com.adriano.desafio.migration;

import br.com.adriano.desafio.banco.entidade.Banco;
import br.com.adriano.desafio.banco.service.BancoService;
import br.com.adriano.desafio.lancamento.entidade.Lancamento;
import br.com.adriano.desafio.lancamento.entidade.LancamentoStatus;
import br.com.adriano.desafio.migration.dto.ControleLancamentoDTO;
import br.com.adriano.desafio.migration.dto.DadosDomicilioBancario;
import br.com.adriano.desafio.migration.dto.LacamentoConteudoDTO;
import br.com.adriano.desafio.migration.dto.LancamentoContaCorrenteClienteDTO;
import br.com.adriano.desafio.core.util.HibernateUtil;
import br.com.adriano.desafio.remessa.entidade.Remessa;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import liquibase.change.custom.CustomTaskChange;
import liquibase.database.Database;
import liquibase.exception.CustomChangeException;
import liquibase.exception.SetupException;
import liquibase.exception.ValidationErrors;
import liquibase.resource.ResourceAccessor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class LegacyMigration implements CustomTaskChange {

    private static final String PATH_DADOS_LEGADO = "legacy/lancamento-conta-legado.json";
    public static final String PARAMETRO_QUERY_CONTA = "conta";

    @Autowired
    private BancoService bancoService;

    private Session session = null;
    private Transaction transaction = null;

    @Override
    public void execute(Database database) throws CustomChangeException {
        final ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        final File file = new File(classLoader.getResource(PATH_DADOS_LEGADO).getFile());
        try {
            this.session = HibernateUtil.getSessionFactory(Banco.class, Remessa.class, Lancamento.class).openSession();
            this.transaction = this.session.beginTransaction();

            final ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

            final LacamentoConteudoDTO dto = objectMapper.readValue(file, LacamentoConteudoDTO.class);
            for (ControleLancamentoDTO controleLancamentoDTO : dto.getListaControleLancamento()) {
                parserFromLegacy(controleLancamentoDTO);
            }
            this.transaction.commit();
        } catch (IOException e) {
            this.transaction.rollback();
            throw new CustomChangeException(e);
        } finally {
            if (session != null) {
                this.session.close();;
            }
            HibernateUtil.shutdown();
        }
    }

    private void parserFromLegacy(ControleLancamentoDTO controleLancamentoDTO) {
        final Remessa remessa = cadastrarNovaRemessa(controleLancamentoDTO);

        LancamentoContaCorrenteClienteDTO correnteClienteDTO = controleLancamentoDTO.getLancamentoContaCorrenteCliente();
        DadosDomicilioBancario dadosDomicilioBancario = correnteClienteDTO.getDadosDomicilioBancario();
        Query<Banco> query = this.session.createQuery("from Banco where numeroContaCorrente = :conta");
        query.setParameter(PARAMETRO_QUERY_CONTA, String.valueOf(dadosDomicilioBancario.getNumeroContaCorrente()));
        List<Banco> bancos = query.getResultList();
        Banco result = null;
        if (bancos.isEmpty()) {
            result = criarBancoNovo(dadosDomicilioBancario);
        } else {
            result = bancos.get(0);
        }
        criarLancamento(controleLancamentoDTO, remessa, correnteClienteDTO, result);
    }

    private void criarLancamento(ControleLancamentoDTO controleLancamentoDTO, Remessa remessa,
                                 LancamentoContaCorrenteClienteDTO correnteClienteDTO, Banco banco) {
        final Lancamento lancamento = new Lancamento();
        lancamento.setNumeroRemessaBanco(correnteClienteDTO.getNumeroRemessaBanco());
        lancamento.setNomeSituacaoRemessa(correnteClienteDTO.getNomeSituacaoRemessa().equals("Pago") ?
                LancamentoStatus.PAGO : LancamentoStatus.PENDENTE);
        lancamento.setDateLancamentoContaCorrenteCliente(controleLancamentoDTO.getDateLancamentoContaCorrenteCliente());
        lancamento.setRemessa(remessa);
        lancamento.setBanco(banco);
        this.session.persist(lancamento);
    }

    private Remessa cadastrarNovaRemessa(ControleLancamentoDTO controleLancamentoDTO) {
        final Remessa remessa = new Remessa();
        remessa.setDateEfetivaLancamento(controleLancamentoDTO.getDateEfetivaLancamento());
        remessa.setNumeroEvento(controleLancamentoDTO.getNumeroEvento());
        remessa.setDescricaoGrupoPagamento(controleLancamentoDTO.getDescricaoGrupoPagamento());
        remessa.setCodigoIdentificadorUnico(controleLancamentoDTO.getCodigoIdentificadorUnico());
        remessa.setNomeBanco(controleLancamentoDTO.getNomeBanco());
        remessa.setQuantidadeLancamentoRemessa(controleLancamentoDTO.getQuantidadeLancamentoRemessa());
        remessa.setNumeroRaizCNPJ(controleLancamentoDTO.getNumeroRaizCNPJ());
        remessa.setNumeroSufixoCNPJ(controleLancamentoDTO.getNumeroSufixoCNPJ());
        remessa.setValorLancamentoRemessa(controleLancamentoDTO.getValorLancamentoRemessa());
        this.session.persist(remessa);
        return remessa;
    }

    private Banco criarBancoNovo(DadosDomicilioBancario dadosDomicilioBancario) {
        Banco banco = new Banco();
        banco.setNumeroContaCorrente(String.valueOf(dadosDomicilioBancario.getNumeroContaCorrente()));
        banco.setNumeroAgencia(dadosDomicilioBancario.getNumeroAgencia());
        banco.setCodigoBanco(dadosDomicilioBancario.getCodigoBanco());
        this.session.persist(banco);
        return banco;
    }

    @Override
    public String getConfirmationMessage() {
        return "Migracao banco legado finalizado.";
    }

    @Override
    public void setUp() throws SetupException {

    }

    @Override
    public void setFileOpener(ResourceAccessor resourceAccessor) {

    }

    @Override
    public ValidationErrors validate(Database database) {
        return new ValidationErrors();
    }
}
