package com.zqs.api.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zqs.api.dao.UserDao;
import com.zqs.api.entity.PageInfo;
import com.zqs.api.entity.User;
import com.zqs.api.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserDao uDao;

    @Override
    public Page<User> listPage(PageInfo page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        return uDao.listPage(page);
    }
}
