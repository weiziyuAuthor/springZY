package cn.hello.ws251017;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
public class PublishWebService {
 
	// 如果这个地方的名字是 dispatcherServlet 则其他的请求也会被 webservice 拦截，访问不了
	@Bean
	public ServletRegistrationBean disServlet() {
		return new ServletRegistrationBean(new CXFServlet(), "/services/*");
	}
	
	@Bean(name = Bus.DEFAULT_BUS_ID)
	public SpringBus springBus() {
		return new SpringBus();
	}
	
	@Bean
	public JobListService jobListService() {
		return new JobListServiceImpl();
	}
	@Bean
	public Endpoint endpoint() {
		EndpointImpl endpoint = new EndpointImpl(springBus(), jobListService());
		endpoint.publish("/jobListService");
		return endpoint;
	}
}