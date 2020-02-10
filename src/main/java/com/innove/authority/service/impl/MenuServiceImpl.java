package com.innove.authority.service.impl;

import com.innove.authority.dao.mapper.MenuDao;
import com.innove.authority.bean.entity.MenuEntity;
import com.innove.authority.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao dao;

    @GetMapping("/list")
    @Override
    public List<MenuEntity> list() {
        return dao.selectAll();
    }
}
