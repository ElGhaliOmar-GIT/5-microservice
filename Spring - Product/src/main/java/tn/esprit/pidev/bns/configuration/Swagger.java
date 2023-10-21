package tn.esprit.pidev.bns.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

/*
    Link SWAGGER :
    http://localhost:9000/bns/swagger-ui/index.html
*/
@Configuration
public class Swagger {
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(infoAPI());
    }

    public Info infoAPI() {
        return new Info().title("BnS App's Documentation")
                .description("All the functions and tests are here")
                .contact(contactAPI());
    }

    public Contact contactAPI() {
        Contact contact = new Contact().name("RevoluTech")
                .email("noEmail@email.com")
                .url("https://www.noLink.com");
        return contact;
    }
}
