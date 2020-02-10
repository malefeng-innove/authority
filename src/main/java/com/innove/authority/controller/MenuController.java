package com.innove.authority.controller;

import com.innove.authority.bean.dto.response.Response;
import com.innove.authority.bean.entity.MenuEntity;
import com.innove.authority.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
@Api(value = "menu", tags = { "菜单管理" })
public class MenuController {

    @Autowired
    private MenuService service;

    @ApiOperation(value = "菜单列表信息查询", notes = "菜单列表信息查询")
    @GetMapping("/list")
    public Response<MenuEntity> list(){
        return new Response(service.list());
    }

}
