package ca.ulaval.glo4002.vet;

import java.util.function.Predicate;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import springfox.documentation.builders.*;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@Import({SpringDataRestConfiguration.class})
@EntityScan(
        basePackageClasses = {VetSpringApplication.class, Jsr310JpaConverters.class}
)
@EnableSwagger2
public class VetSpringApplication {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
		        .apiInfo(getApiInfo())
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ca.ulaval.glo4002.vet.controllers"))
                .paths(documentedPaths())
                .build();
    }

    private Predicate<String> documentedPaths() {
        return PathSelectors.regex("/(error|profile).*").negate();
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .contact(new Contact("The GLO-4002 team", "http://projet2021.qualitelogicielle.ca",
                        "aide@qualitelogicielle.ca"))
                .title("External service API")
                .description("External service API")
                .version("1.0")
                .build();
    }
}
