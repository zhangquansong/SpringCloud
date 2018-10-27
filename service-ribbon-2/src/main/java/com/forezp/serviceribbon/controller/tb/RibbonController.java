package com.forezp.serviceribbon.controller.tb;

import com.forezp.serviceribbon.service.tb.RibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RibbonController {
    @Autowired
    RibbonService ribbonService;

    @RequestMapping(value = "/ribbon")
    public String hi(@RequestParam String name) {
        return ribbonService.ribbonService(name);
    }
}
