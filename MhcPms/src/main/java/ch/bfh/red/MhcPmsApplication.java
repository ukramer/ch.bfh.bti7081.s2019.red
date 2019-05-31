package ch.bfh.red;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//import ch.bfh.red.backend.test.PatientCrudTest;

@SpringBootApplication
public class MhcPmsApplication {
	
//	public static ApplicationContext APPLICATION_CONTEXT;
	
	public static ApplicationContext applicationContext;
	
	public static void main(String[] args) {
		start(args);
		
//		PatientCrudTest test = new PatientCrudTest();
//		test.before();
//		try {
//			test.create();
//			test.read();
//			test.update();
//			test.delete();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
//    	ApplicationContextProvider.getApplicationContext();
		
		browse("http://localhost:8080");
	}
	
	public static ApplicationContext start(String[] args) {
		return SpringApplication.run(MhcPmsApplication.class, args);
	}
	
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
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
