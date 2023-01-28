package pe.com.cronos.core.security.actuator;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/actuator")
public class CoreCustomActuator {

    @GetMapping("live")
    public ResponseEntity<String> live() {
        return ResponseEntity.ok("Live!");
    }

    @GetMapping("ready")
    public ResponseEntity<String> ready() {
        return ResponseEntity.ok("Ready!");
    }
}

