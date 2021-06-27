package onesec.onesecapp;

import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "onesec.onesecapp")
public class MainApp {
	public static void main(String[] args) {
		SpringApplication.run(MainApp.class, args);
	}

}
