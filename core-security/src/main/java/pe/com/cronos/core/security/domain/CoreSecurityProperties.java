package pe.com.cronos.core.security.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CoreSecurityProperties {
    private List<String> excludedPaths;
}
