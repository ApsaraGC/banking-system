package bank.banking_system.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.awt.SystemColor.info;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI bankingOpenAPI(){
        return new OpenAPI()
           .info(new Info()
                .title("Banking System API")
                .description("APIs for Accounts, Transactions, Deposit, Withdraw, Transfer")
                .version("1.0.0"));
    }
}
