package com.zqs.api.dao;

import com.github.pagehelper.Page;
import com.zqs.api.entity.PageInfo;
import com.zqs.api.entity.User;

public interface UserDao {

    Page<User> listPage(PageInfo page);
}
