package atmjavaconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ATMjavaconfig {
    @Bean
    public DataSource dataSource() {
        return new DataSource("customers.txt");
    }
    @Bean Bank bank() {
        return new Bank(dataSource());
    }
    @Bean
    public ATM atm() {
        return new ATM(bank());
    }
    @Bean
    public ATMSimulator atmSimulator() {
        return new ATMSimulator(atm());
    }
}