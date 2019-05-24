package ch.bfh.red;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
