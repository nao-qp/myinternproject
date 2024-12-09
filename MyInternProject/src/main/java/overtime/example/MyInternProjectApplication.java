package overtime.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class MyInternProjectApplication extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		SpringApplication.run(MyInternProjectApplication.class, args);
	}

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MyInternProjectApplication.class);  // 外部サーバー用設定
    }
	
}
