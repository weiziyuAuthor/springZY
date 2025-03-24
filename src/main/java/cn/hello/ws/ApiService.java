package cn.hello.ws;
 
import javax.jws.WebService;
 
/**
 * @Description  这里主要是想要在暴露方法的时候能够统一，方便管理
 *
 */
@WebService(targetNamespace = "http://service.spi.bjca.cn/")
//@WebService(targetNamespace = "http://ws.hello.cn/")
public interface ApiService {

}