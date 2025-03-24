package cn.hello.ws.impl;
 
import cn.hello.ws.ApiCommonService;
import cn.hello.ws.ReponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
 
import javax.jws.WebService;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ApiServiceImpl
 * @Description  测试地址：http://127.0.0.1:8099/ws/ApiCommonService?wsdl
 * @Date 2024/6/14 16:52
 * @Version 1.0
 */
@Component
@WebService(name = "ApiCommonService",
        targetNamespace = "http://service.spi.bjca.cn/",
        endpointInterface = "cn.hello.ws.ApiCommonService",
        portName = "10000")
@Slf4j
public class ApiCommonServiceImpl implements ApiCommonService {
 
    @Override
    public ReponseBean commonApi(String param1, String param2) {
        log.info("调用的入参为："+param1+","+param2);
        Map<String, String> map= new HashMap<>();
        map.put("pm", "成功. " + "p1: " + param1 + "  p2: " + param2);
        ReponseBean reponseBean = new ReponseBean();
        reponseBean.setOut("成功. " + "p1: " + param1 + "  p2: " + param2);
        return reponseBean;
    }
}