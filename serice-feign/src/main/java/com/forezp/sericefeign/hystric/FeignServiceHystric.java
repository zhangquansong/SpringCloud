package com.forezp.sericefeign.hystric;

import com.forezp.sericefeign.impl.FeignService;
import org.springframework.stereotype.Component;

/**
 * SchedualServiceHiHystric需要实现SchedualServiceHi 接口，并注入到Ioc容器中，代码如下：
 */
@Component
public class FeignServiceHystric implements FeignService {
    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry " + name;
    }
}