package com.example.demo;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.*;
import org.springframework.context.annotation.*;

@Configuration
public class SwaggerConfig {
  @Bean
  public OpenAPI customOpenAPI() {
    // http://localhost:8080/swagger-ui/index.html
    // https://mini03.onrender.com/swagger-ui/index.html
    return new OpenAPI().components(new Components()).info(info());
  }

  private Info info() {
    return new Info().title("MINI03 API").description("MINI03 API 레퍼런스 문서화").version("1.0");
  }
}
