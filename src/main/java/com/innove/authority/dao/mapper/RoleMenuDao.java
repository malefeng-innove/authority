package com.innove.authority.dao.mapper;

import com.innove.authority.dao.GenericMapper;
import com.innove.authority.bean.dto.request.RoleMenuDeleteRequest;
import com.innove.authority.bean.entity.RoleMenuEntity;
import com.innove.authority.util.StringUtil;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface RoleMenuDao extends GenericMapper<RoleMenuEntity> {

    @SelectProvider(type=RoleMenuProvider.class,method="listSql")
    String[] selectByRoleCode(String roleCode);

    @DeleteProvider(type=RoleMenuProvider.class,method="deleteByRolceCodeSql")
    int deleteByRoleCode(String roleCode);

    @DeleteProvider(type=RoleMenuProvider.class,method="deletesSql")
    int deletes(RoleMenuDeleteRequest request);

    class RoleMenuProvider{

        public String listSql(String roleCode){
            return "select menu_code from t_role_menu where role_code = '"+roleCode+"'";
        }

        public String deleteByRolceCodeSql(String roleCode){
            return "delete from t_role_menu where role_code = '"+roleCode+"'";
        }

        public String deletesSql(RoleMenuDeleteRequest request){
            String roleCode = request.getRoleCode();
            List<String> menuCodes = request.getMenuCodes();
            if(StringUtil.isBlank(roleCode)){
                return "select 0";
            }
            StringBuilder sql = new StringBuilder();
            sql.append("delete from t_role_menu where ")
                    .append(" role_code = '").append(roleCode).append("'");
            if(menuCodes!=null&&menuCodes.size()>0){
                sql.append(" and menu_code in ('")
                        .append(String.join("','",menuCodes))
                        .append("')");
            }
            return sql.toString();
        }
    }
}
