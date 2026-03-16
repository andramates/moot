package ro.andramates.moot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class MootBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MootBackendApplication.class, args);
    }
}