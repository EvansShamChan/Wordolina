package wordolina;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import wordolina.config.RootConfig;
import wordolina.config.WebConfig;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

    @Nullable
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    @Nullable
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
