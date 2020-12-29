package ru.ifmo.se.databases.asurkis.coursework

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@SpringBootApplication
class CourseworkApplication {
    @Bean
    fun corsConfigurer() = object : WebMvcConfigurer {
        override fun addCorsMappings(registry: CorsRegistry) {
            registry.addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOrigins("http://localhost:8081")
        }
    }
}

fun main(args: Array<String>) {
    runApplication<CourseworkApplication>(*args)
}
