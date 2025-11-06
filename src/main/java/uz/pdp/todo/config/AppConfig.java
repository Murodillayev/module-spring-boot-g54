package uz.pdp.todo.config;


import com.zaxxer.hikari.util.DriverDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Properties;

@Configuration
public class AppConfig {

    @Bean
    @Profile("dev")
    public TaskExecutor taskExecutorDev() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(4);
        executor.setQueueCapacity(10);
        executor.setThreadNamePrefix("dev-");
        executor.initialize();
        return executor;
    }

    @Bean
    @Profile("prod")
    public TaskExecutor taskExecutorProd() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(4);
        executor.setQueueCapacity(10);
        executor.setThreadNamePrefix("prod-");
        executor.initialize();
        return executor;
    }


//    @Bean
//    @Profile("dev")
//    public DataSource getDataSource1() {
//        return new DriverDataSource(
//                "jdbc:postgresql://localhost:5432/test",
//                "org.postgresql.Driver",
//                new Properties(),
//                "macbookpro",
//                "19801980"
//        );
//    }
//
//    @Bean
//    @Profile("prod")
//    public DataSource getDataSource2() {
//        return new DriverDataSource(
//                "jdbc:postgresql://localhost:5432/hub",
//                "org.postgresql.Driver",
//                new Properties(),
//                "macbookpro",
//                "19801980"
//        );
//    }

}
