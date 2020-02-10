package com.innove.authority.dao.mapper;

import com.innove.authority.dao.GenericMapper;
import com.innove.authority.bean.entity.MenuEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MenuDao extends GenericMapper<MenuEntity> {

    @Select("SELECT" +
            " m.menu_name " +
            "FROM" +
            " ( SELECT role_code FROM t_user WHERE user_code = #{userCode} ) u" +
            " LEFT JOIN t_role_menu rm ON u.role_code = rm.role_code" +
            " LEFT JOIN t_menu m ON m.menu_code = rm.menu_code" )
    List<String> selectNamesByUserCode(String userCode);
}
