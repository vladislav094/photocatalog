package guru.qa.photocatalog.service;

import guru.qa.photocatalog.controller.error.ApiError;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

public class PhotocatalogErrorAttributes extends DefaultErrorAttributes {

    private final String apiVersion;

    public PhotocatalogErrorAttributes(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> defaultMap = super.getErrorAttributes(webRequest, options);
        ApiError apiError = ApiError.fromAttributeMap(apiVersion, defaultMap);
        return apiError.toAttributeMaps();
    }
}
