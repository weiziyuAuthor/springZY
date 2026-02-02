package cn.hello.ws;
 
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @ClassName ApiService
 * @Description
 * @Date 2024/6/14 16:48
 * @Version 1.0
 */
@WebService(targetNamespace = "http://com/meritit/portal/uniontask/webserviceLocal")
public interface TaskMgrControl {
    @WebMethod(operationName = "addTaskNew")
    String commonApi(@WebParam(name="appID") String appId,
                          @WebParam(name="taskName") String taskName,
                          @WebParam(name="appTaskId") String appTaskId,
                          @WebParam(name = "taskType") String taskType);
}