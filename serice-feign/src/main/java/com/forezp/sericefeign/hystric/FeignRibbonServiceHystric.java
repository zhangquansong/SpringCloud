package com.forezp.sericefeign.hystric;

import com.forezp.sericefeign.impl.FeignRibbonService;
import com.forezp.sericefeign.impl.FeignService;
import org.springframework.stereotype.Component;

/**
 * SchedualServiceHiHystric需要实现SchedualServiceHi 接口，并注入到Ioc容器中，代码如下：
 */
@Component
public class FeignRibbonServiceHystric implements FeignRibbonService {

    @Override
    public String ribbon(String name) {
        return "sorry,ribbon " + name;
    }
}
