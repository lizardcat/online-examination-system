package xss.it.backend;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;


@Log4j2
@Configuration
@EnableJpaAuditing
public class Config {

    /**
     * Creates and configures a DataSource bean for the application.
     *
     * @return The configured DataSource bean.
     */
    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url(String.format("jdbc:sqlite:%s", "./test.db"))//Read this from some settings OK
                .build();
    }

    /**
     * Provides a BCryptPasswordEncoder bean for password encoding.
     *
     * @return An instance of BCryptPasswordEncoder for secure password hashing.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
