package com.innove.authority.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.innove.authority.dao.mapper.UserDao;
import com.innove.authority.bean.dto.request.UserListRequest;
import com.innove.authority.bean.dto.request.UserSaveRequest;
import com.innove.authority.bean.dto.response.Response;
import com.innove.authority.bean.dto.response.UserDetailResponse;
import com.innove.authority.bean.entity.UserEntity;
import com.innove.authority.bean.enums.ResponseCodeEnum;
import com.innove.authority.service.UserService;
import com.innove.authority.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.beans.Transient;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    @Override
    @PostMapping("/list")
    public PageInfo list(@RequestBody UserListRequest request) {
        PageHelper.startPage(request.getPageNo(),request.getPageCount());
        return new PageInfo(dao.list(request));
    }

    @GetMapping("/{userCode}")
    @Override
    public UserDetailResponse detail(@PathVariable("userCode")String userCode) {
        return dao.detail(userCode);
    }

    @PostMapping
    @Override
    public Response save(@RequestBody UserSaveRequest request) throws Exception {
        //校验账号唯一性
        if(!checkUniq(request.getUserCode(),request.getId())){
            return new Response(false).error(ResponseCodeEnum.FAILED.getKey(),"账号重复");
        }
        String tel = request.getUserTel();
        String newPsw = "";
        if(StringUtil.isNotBlank(request.getPassword())){
            newPsw = request.getPassword();
        }else if(StringUtil.isNotBlank(tel)&&tel.length()>=6){
            newPsw = tel.substring(tel.length()-6);
        }
        if(StringUtil.isNotBlank(newPsw)){
            //密码加密
            newPsw = StringUtil.MD5(newPsw);
            request.setPassword(newPsw);
        }
        dao.insert(request);
        return new Response(true);
    }

    @PutMapping
    @Override
    public Response update(@RequestBody UserSaveRequest request) throws Exception {
        //校验账号唯一性
        if(!checkUniq(request.getUserCode(),request.getId())){
            return new Response(false).error(ResponseCodeEnum.FAILED.getKey(),"账号重复");
        }
        //判断是否修改密码
        UserEntity userOld = dao.selectByPrimaryKey(request.getId());
        if(StringUtil.isNotBlank(request.getPassword())){
            //密码加密
            String newPsw = StringUtil.MD5(request.getPassword());
            if(!StringUtils.equals(newPsw,userOld.getPassword())){
                request.setPassword(newPsw);
            }
        }else{
            request.setPassword(userOld.getPassword());
        }
        dao.updateByPrimaryKey(request);
        return new Response(true);
    }

    @DeleteMapping("/{id}")
    @Override
    @Transient
    public boolean delete(@PathVariable("id")int id) {
        dao.deleteByPrimaryKey(id);
        return true;
    }

    @Override
    @GetMapping("/selectByName/{name}")
    public UserEntity selectByName(@PathVariable("name")String name) {
        UserEntity user = new UserEntity();
        user.setUserCode(name);
        return dao.selectOne(user);
    }

    @Override
    @GetMapping("/selectByRoleCode/{roleCode}")
    public List<UserEntity> selectByRoleCode(@PathVariable("roleCode")String roleCode) {
        UserEntity userEntity = new UserEntity();
        userEntity.setRoleCode(roleCode);
        return dao.select(userEntity);
    }

    /**
     * @Author mlf
     * @Description //判断账号是否唯一
     * @Date 下午 5:42 2020/1/16
     * @Param [userCode, id]
     * @return boolean
     **/
    public boolean checkUniq(String userCode,Integer id){
        UserEntity user = new UserEntity();
        user.setUserCode(userCode);
        List<UserEntity> userEntityList = dao.select(user);
        if(userEntityList!=null&&userEntityList.size()>0){
            if(userEntityList.size()>1){
                return false;
            }
            UserEntity userEntity = userEntityList.get(0);
            if(userEntity.getId()!=id){
                return false;
            }
        }
        return true;
    }

    public List<UserEntity> select(UserEntity param){
        return dao.select(param);
    }
}
