package net.java.Springbt_restapi.config;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import net.java.Springbt_restapi.APIVersioning.DepreciatedApiInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AllArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private DepreciatedApiInterceptor depreciatedApiInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(depreciatedApiInterceptor);
    }
}
