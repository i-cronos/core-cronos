package pe.com.cronos.core.exceptions.domain;

public record ErrorInfo(
        Group group,
        String code,
        String systemMessage,
        String userMessage) {
}
