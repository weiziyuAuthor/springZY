package cn.hello.ws;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.xml.ws.Endpoint;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName WebServicePublishConfig
 * @Description
 * @Date 2024/6/14 16:57
 * @Version 1.0
 */
@Configuration
public class WebServicePublishConfig {
    @Bean
    public ServletRegistrationBean wsServlet(){
        return new ServletRegistrationBean(new CXFServlet(),"/ws/*");
    }
 
    @Resource
    private TaskMgrControl apiCommonService;
    @Resource
    private List<TaskMgrControl> apiServices;
    @Resource
    @Qualifier(Bus.DEFAULT_BUS_ID)
    private SpringBus bus;
 
    @Bean
    public Endpoint[] endPoint(){
        List<Endpoint> endpointList = new ArrayList<>(apiServices.size());
        EndpointImpl endpoint = new EndpointImpl(bus, apiCommonService);
        String simpleName = apiCommonService.getClass().getSimpleName();
        //如果有Impl结尾的类名，去掉Impl
        if (simpleName.endsWith("Impl")) {
            simpleName = simpleName.substring(0, simpleName.length() - 4);
        }
        endpoint.publish("/"+simpleName);
        endpointList.add(endpoint);
        return endpointList.toArray(new Endpoint[0]);
    }
 
}