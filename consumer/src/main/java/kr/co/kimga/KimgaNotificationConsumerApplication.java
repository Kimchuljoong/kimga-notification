package kr.co.kimga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@SpringBootApplication(
		scanBasePackages = "kr.co.kimga"
)
public class KimgaNotificationConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KimgaNotificationConsumerApplication.class, args);
	}

}
