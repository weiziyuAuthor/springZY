package cn.hello.ws251017;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @Author ziyu.wei
 * <p>
 * 2025/10/17 21:55
 */
@WebService
public interface JobListService {

    @WebMethod
    String getList(@WebParam(name="userId") String userId,
                   @WebParam(name="agentNum") int num);
}
