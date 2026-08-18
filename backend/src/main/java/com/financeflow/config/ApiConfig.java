package com.financeflow.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.*;
@Configuration
public class ApiConfig implements WebMvcConfigurer {
  @Bean OpenAPI financeFlowOpenApi(){return new OpenAPI().info(new Info().title("FinanceFlow API").version("1.0").description("API REST para gestão financeira pessoal."));}
  @Override public void addCorsMappings(CorsRegistry registry){registry.addMapping("/api/**").allowedOrigins("http://localhost:5173").allowedMethods("GET","POST","PUT","DELETE");}
}

