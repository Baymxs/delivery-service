package ru.nsu.bayramov.deliveryservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Обеспечивает работу swagger документации.
 *
 * @apiNote После старта приложения документация доступна по host:port/swagger-ui.html
 * @see <a href="https://springfox.github.io/springfox/">springfox project</a>
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private static final String SWAGGER_API_VERSION = "0.0.1";
    private static final String LICENSE = "Apache License";
    private static final String TITLE = "DELIVERY SERVICE REST API";
    private static final String DESCRIPTION = "RESTful API for delivery service";

    @Bean
    public Docket teamCftApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ru.nsu.bayramov.deliveryservice"))
                .paths(regex("/api.*"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(TITLE)
                .description(DESCRIPTION)
                .license(LICENSE)
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0.txt")
                .version(SWAGGER_API_VERSION)
                .build();
    }
}
