package net.java.Springbt_restapi.APIVersioning;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class DepreciatedApiInterceptor implements HandlerInterceptor {
    private static final String SUNSET_DATE = "Fri, 01 Aug 2026 00:00:00 GMT";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String uri = request.getRequestURI();
        if (uri.startsWith("/api/") && !uri.startsWith("/api/v1/")) {
            response.setHeader("Deprecation", "true");
            response.setHeader("Sunset", SUNSET_DATE);
            response.setHeader("Link", "</api/v1" + uri.substring(4) + ">; rel=\"successor-version\"");
        }
        return true;
    }
}
