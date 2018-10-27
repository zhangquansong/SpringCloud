package com.zqs.api.controller;

import com.github.pagehelper.Page;
import com.zqs.api.entity.PageInfo;
import com.zqs.api.entity.User;
import com.zqs.api.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private IUserService iUserService;

    @GetMapping("/simple/listPage")
    public PageInfo<User> list(PageInfo page) {
        System.out.println("listPage");
        Page<User> users = this.iUserService.listPage(page);
        PageInfo<User> pageInfo = new PageInfo<>(users);
        return pageInfo;
    }
}
