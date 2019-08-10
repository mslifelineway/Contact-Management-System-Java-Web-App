
package com.oyedost.contactapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 *
 * @author janeman
 */

@Configuration
@ComponentScan(basePackages = {"com.oyedost"})
@EnableWebMvc
public class SpringWebConfig extends WebMvcConfigurerAdapter{
    //--- to handle the static resource we need to create a method such as addResourceHandlers
    //--- static resources means : css, bootstrap, js etc file. they must be added here...
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
       registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    
    }
    
    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver vr = new InternalResourceViewResolver();
         vr.setViewClass(JstlView.class);
         vr.setPrefix("/WEB-INF/view/");
        vr.setSuffix(".jsp");
        return vr;
        
    }
    
    
}
