package org.savindu.annotation.swaggerLogger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
@Slf4j
public class SwaggerUrlLogger implements ApplicationListener<ApplicationReadyEvent> {
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        int port = event.getApplicationContext().getEnvironment().getProperty("local.server.port", Integer.class);
        log.info("Swagger UI is available at: http://localhost:" + port + "/swagger-ui/index.html");

    }
}

