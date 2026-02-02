package cn.hello.ws251017;

import org.springframework.stereotype.Component;

import javax.jws.WebService;

/**
 * @Author ziyu.wei
 * <p>
 * 2025/10/17 21:57
 */
@WebService(targetNamespace = "http://webservice.test.fc.com/",
        endpointInterface = "cn.hello.ws251017.JobListService")
//@Component
public class JobListServiceImpl implements  JobListService{

    @Override
    public String getList(String userId, int num) {
        return "req success.";
    }
}
