package com.forezp.sericefeign.impl;

import com.forezp.sericefeign.hystric.FeignRibbonServiceHystric;
import com.forezp.sericefeign.hystric.FeignServiceHystric;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 定义一个feign接口，通过@ FeignClient（“服务名”），来指定调用哪个服务。比如在代码中调用了servic
 * 0e-hi服务的“/hi”接口，代码如下：
 *
 * 基于service-feign工程进行改造，只需要在FeignClient的SchedualServiceHi接口的注解中加上fallback的指定类就行了
 */
@FeignClient(value = "service-ribbon",fallback = FeignRibbonServiceHystric.class)
public interface FeignRibbonService {
    @RequestMapping(value = "/ribbon", method = RequestMethod.GET)
    String ribbon(@RequestParam(value = "name") String name);
}
