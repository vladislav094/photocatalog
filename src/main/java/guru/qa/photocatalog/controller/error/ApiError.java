package guru.qa.photocatalog.controller.error;

import java.util.List;
import java.util.Map;

public class ApiError {

    private final String apiVersion;
    private final Error error;

    public ApiError(String apiVersion, Error error) {
        this.apiVersion = apiVersion;
        this.error = error;
    }

    public ApiError(String apiVersion,
                    String code,
                    String message,
                    String domain,
                    String reason) {
        this.apiVersion = apiVersion;
        this.error = new Error(
                code,
                message,
                List.of(
                        new ErrorItem(
                                domain,
                                reason,
                                message
                        )
                )
        );
    }

    public static ApiError fromAttributeMap(String apiVersion, Map<String, Object> attributes) {
        return new ApiError(
                apiVersion,
                ((Integer) attributes.get("status")).toString(),
                ((String) attributes.getOrDefault("error", "No message found")),
                ((String) attributes.getOrDefault("path", "No path found")),
                ((String) attributes.getOrDefault("reason", "No message found"))
        );
    }

    public Map<String, Object> toAttributeMaps() {
        return Map.of(
                "apiVersion", apiVersion,
                "error", error
        );
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public Error getError() {
        return error;
    }

    private record Error(String code, String message, List<ErrorItem> errors) {
    }

    private record ErrorItem(String code, String reason, String message) {
    }
}
