package ch.bfh.red;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import ch.bfh.red.MhcPmsApplication;

/**
 * Used for SpringRunnerTest in StartupTest
 */
@SpringBootApplication
public class TestApplication {

    public static void main(String[] args) {
    	SpringApplication.run(MhcPmsApplication.class, args);
    }

}
