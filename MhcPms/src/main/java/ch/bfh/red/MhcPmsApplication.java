package ch.bfh.red;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import ch.bfh.red.backend.seeders.DbSeeder;

@SpringBootApplication
public class MhcPmsApplication {
	
	public static void main(String[] args) {
		ApplicationContext context = start(args);
		DbSeeder dbSeeder = context.getBean(DbSeeder.class);
		seed(dbSeeder);
		browse("http://localhost:8080");
	}
	
	public static ApplicationContext start(String[] args) {
		return SpringApplication.run(MhcPmsApplication.class, args);
	}
	
	public static void seed(DbSeeder dbSeeder) {
		dbSeeder.seed(150);
	}
	
	private static void browse(String url) {
		if (Desktop.isDesktopSupported()) {
			Desktop desktop = Desktop.getDesktop();
			try {
				desktop.browse(new URI(url));
			} catch (IOException | URISyntaxException e) {
				e.printStackTrace();
			}
		} else {
			Runtime runtime = Runtime.getRuntime();
			try {
				runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
