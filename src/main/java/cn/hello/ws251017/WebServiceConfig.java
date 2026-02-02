//package cn.hello.ws251017;
//
//import javax.xml.ws.Endpoint;
//
//import org.apache.cxf.Bus;
//import org.apache.cxf.bus.spring.SpringBus;
//import org.apache.cxf.jaxws.EndpointImpl;
//import org.apache.cxf.transport.servlet.CXFServlet;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class WebServiceConfig {
//	@Autowired
//    private JobListService jobListService;
//
//	/**
//     * 注入servlet  bean name不能dispatcherServlet 否则会覆盖dispatcherServlet
//     * @return
//     */
//    @Bean(name = "cxfServlet")
//    public ServletRegistrationBean cxfServlet() {
//        return new ServletRegistrationBean(new CXFServlet(),"/services/*");
//    }
//
//    @Bean(name = Bus.DEFAULT_BUS_ID)
//    public SpringBus springBus() {
//        return new SpringBus();
//    }
//
//
//    @Bean
//    public Endpoint endpoint() {
//        EndpointImpl endpoint = new EndpointImpl(springBus(), jobListService);
//        endpoint.publish("/jobListService");
//        return endpoint;
//    }
//}