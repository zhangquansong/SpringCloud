package com.zqs.api.service;

import com.github.pagehelper.Page;
import com.zqs.api.entity.PageInfo;
import com.zqs.api.entity.User;

public interface IUserService {
    Page<User> listPage(PageInfo page);
}
