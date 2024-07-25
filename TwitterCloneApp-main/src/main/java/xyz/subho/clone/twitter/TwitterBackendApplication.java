
package xyz.subho.clone.twitter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class TwitterBackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(TwitterBackendApplication.class, args);
  }
}
