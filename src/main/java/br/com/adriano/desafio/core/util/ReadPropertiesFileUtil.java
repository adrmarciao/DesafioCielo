package br.com.adriano.desafio.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadPropertiesFileUtil {
    public static Properties readPropertiesFile(String fileName) throws IOException {
        InputStream fis = null;
        Properties prop;
        try {
            fis = ReadPropertiesFileUtil.class.getClassLoader().getResourceAsStream(fileName);
            prop = new Properties();
            prop.load(fis);
        } finally {
            fis.close();
        }
        return prop;
    }
}
