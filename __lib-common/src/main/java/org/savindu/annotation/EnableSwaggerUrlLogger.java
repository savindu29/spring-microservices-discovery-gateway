package org.savindu.annotation;

import org.savindu.annotation.swaggerLogger.SwaggerUrlLoggerRegistrar;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(SwaggerUrlLoggerRegistrar.class)
public @interface EnableSwaggerUrlLogger {
}

