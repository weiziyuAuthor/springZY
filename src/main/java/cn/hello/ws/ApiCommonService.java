package cn.hello.ws;
 
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Map;

/**
 * @ClassName ApiService
 * @Description
 * @Date 2024/6/14 16:48
 * @Version 1.0
 */
@WebService(targetNamespace = "http://service.spi.bjca.cn/")
public interface ApiCommonService {
    @WebMethod(operationName = "commonApi")
    ReponseBean commonApi(@WebParam(name="param1") String param1, @WebParam(name = "param2") String param2);
}