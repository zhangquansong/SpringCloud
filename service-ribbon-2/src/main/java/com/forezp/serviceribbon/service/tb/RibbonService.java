package com.forezp.serviceribbon.service.tb;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 写一个测试类HelloService，通过之前注入ioc容器的restTemplate来消费service-hi服务的“/hi”接口，在这里我们直接用的程序名替代了具体的url地址，
 * 在ribbon中它会根据服务名来选择具体的服务实例，根据服务实例在请求的时候会用具体的url替换掉服务名
 *
 * 改造HelloService类，在hiService方法上加上@HystrixCommand注解。该注解对该方法创建了熔断器的功能，并指定了fallbackMethod熔断方法，
 * 熔断方法直接返回了一个字符串，字符串为”hi,”+name+”,sorry,error!”，代码如下：
 */
@Service
public class RibbonService {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "hiError")
    public String ribbonService(String name) {
//        restTemplate.getForObject("http://localhost:8989/hi?name=" + name, String.class);
        return restTemplate.getForObject("http://SERVICE-1/simple/listPage?name=" + name, String.class);
        // return restTemplate.getForObject("http://SERVICE-ZUUL/api-b/hi?name="+name+"&token="+123,String.class);

    }
    public String hiError(String name) {
        return "hi,"+name+",sorry,error!";
    }
}
