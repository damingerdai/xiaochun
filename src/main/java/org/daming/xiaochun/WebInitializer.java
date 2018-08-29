package org.daming.xiaochun;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.daming.xiaochun.config.AppMcvConfig;
import org.daming.xiaochun.filter.ForumFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

/** 
* 
* @author: aming
* @date: 2018-08-22 ����4:49:23
* @version: 1.0
*/
public class WebInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(AppMcvConfig.class);
        ctx.setServletContext(servletContext); //2
        Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx)); //3
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
        servlet.setAsyncSupported(true);//1
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        servletContext.addFilter("encodingFilter", characterEncodingFilter);
        javax.servlet.FilterRegistration.Dynamic forumFilter =
                servletContext.addFilter("forumFilter", ForumFilter.class);
        forumFilter.addMappingForUrlPatterns(null, false, "*.jsp");
        forumFilter.addMappingForUrlPatterns(null, false, "*.html");
	}
	
	

}
