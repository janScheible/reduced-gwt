package com.scheible.gwt.reduced.server;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.scheible.gwt.reduced.shared.framework.collection.Array;
import java.io.IOException;
import java.nio.charset.Charset;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.util.ContentCachingResponseWrapper;

/**
 *
 * @author sj
 */
@SpringBootApplication
public class ReducedGwtApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ReducedGwtApplication.class, args);
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        SimpleModule frameworkModule = new SimpleModule();
        frameworkModule.addSerializer(Array.class, new JsonSerializer<Array>() {
            @Override
            public void serialize(Array value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
                gen.writeStartArray();
                for (int i = 0; i < value.getLength(); i++) {
                    gen.writeObject(value.get(i));
                }
                gen.writeEndArray();
            }
        });
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(frameworkModule);

        return new MappingJackson2HttpMessageConverter(objectMapper);
    }

    @Bean
    public Filter javaScriptFilter() {
        return new Filter() {
            @Override
            public void init(FilterConfig fc) throws ServletException {
            }

            @Override
            public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
                if (servletRequest instanceof HttpServletRequest) {
                    HttpServletRequest request = (HttpServletRequest) servletRequest;
                    boolean isJQuery = request.getRequestURI().toLowerCase().contains("/webjars/jquery/");
                    boolean isReducedGwt = request.getRequestURI().toLowerCase().contains("reduced-gwt");
                    if (request.getRequestURI().toLowerCase().endsWith(".js")
                            && (isJQuery || isReducedGwt)) {
                        HttpServletResponse response = (HttpServletResponse) servletResponse;
                        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

                        chain.doFilter(servletRequest, responseWrapper);

                        ServletOutputStream responseOutputStream = response.getOutputStream();
                        
                        if(isJQuery) {
                            byte[] jQuery = responseWrapper.getContentAsByteArray();
                            byte[] noConflictAndEs6Export = ("\n"
                                    + "const noConflictJQuery = window.jQuery.noConflict(true);\n"
                                    + "export { noConflictJQuery as jQuery };").getBytes(Charset.forName("UTF-8"));

                            response.setContentLength(jQuery.length + noConflictAndEs6Export.length);

                            responseOutputStream.write(jQuery);
                            responseOutputStream.write(noConflictAndEs6Export);                        
                        } else if(isReducedGwt) {
                            String customElementsScript = new String(responseWrapper.getContentAsByteArray(), Charset.forName("UTF-8"));
                            customElementsScript = customElementsScript.replace("$application-package$", getClass().getPackage().getName().substring(0, getClass().getPackage().getName().lastIndexOf(".")));

                            byte[] customElements = customElementsScript.getBytes(Charset.forName("UTF-8"));
                            response.setContentLength(customElements.length);
                            responseOutputStream.write(customElements);
                        }

                        return;
                    }
                }

                chain.doFilter(servletRequest, servletResponse);
            }

            @Override
            public void destroy() {
            }
        };
    }
}
