package io.hayk.demo.note.rsocket;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
class RsocketConfiguration {

    @Bean
    Executor dbCallBoundExecutor(
            @Value("${dbCallBoundExecutor.corePoolSize:4}") final int corePoolSize,
            @Value("${dbCallBoundExecutor.maxPoolSize:64}") final int maxPoolSize,
            @Value("${dbCallBoundExecutor.queueCapacity:4096}") final int queueCapacity
    ) {
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        return executor;
    }
}
