package teun.stout.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.accept.SemanticApiVersionParser;
import org.springframework.web.servlet.config.annotation.ApiVersionConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void configureApiVersioning(ApiVersionConfigurer configurer) {
        configurer.useRequestHeader("API-Version");
        // Need default functions for e.g. swagger
        // I like to set this to be explicit with my versions
        configurer.setDefaultVersion("1");
        // takes first segment from path e.g. "/v1/sour" takes "v1"
        configurer.setVersionParser(new SemanticApiVersionParser());
    }
}
