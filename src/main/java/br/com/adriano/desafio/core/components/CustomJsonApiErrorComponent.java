package br.com.adriano.desafio.core.components;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class CustomJsonApiErrorComponent extends DefaultErrorAttributes {

    public static final String STATUS = "status";
    public static final String TITLE = "title";
    public static final String DETAIL = "detail";
    public static final String ERRORS = "errors";

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        final Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);
        final Map<String, Object> jsonApiErrorAttributes = new LinkedHashMap<>();
        jsonApiErrorAttributes.put(STATUS, errorAttributes.get("status"));
        jsonApiErrorAttributes.put(TITLE, errorAttributes.get("error"));
        jsonApiErrorAttributes.put(DETAIL, errorAttributes.get("message"));

        return Map.of(ERRORS, new Map[] {jsonApiErrorAttributes});
    }
}
