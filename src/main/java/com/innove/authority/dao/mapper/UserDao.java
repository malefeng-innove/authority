package com.innove.authority.dao.mapper;

import com.innove.authority.dao.GenericMapper;
import com.innove.authority.bean.dto.request.UserListRequest;
import com.innove.authority.bean.dto.response.UserDetailResponse;
import com.innove.authority.bean.dto.response.UserListResponse;
import com.innove.authority.bean.entity.UserEntity;
import com.innove.authority.util.StringUtil;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserDao extends GenericMapper<UserEntity> {

    @SelectProvider(type=UserProvider.class,method="listSql")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userCode", column = "user_code"),
            @Result(property = "roleName", column = "role_name"),
            @Result(property = "staffName", column = "staff_name"),
            @Result(property = "userTel", column = "user_tel"),
            @Result(property = "userStatus", column = "user_status"),
            @Result(property = "orgName", column = "org_name")
    })
    List<UserListResponse> list(UserListRequest request);

    @Select("select * from t_user where user_code = #{userCode}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userCode", column = "user_code"),
            @Result(property = "staffCode", column = "staff_code"),
            @Result(property = "userTel", column = "user_tel"),
            @Result(property = "roleCode", column = "role_code"),
            @Result(property = "userStatus", column = "user_status"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "openUser", column = "open_user"),
            @Result(property = "remark", column = "remark"),
            @Result(property = "menuNames", column = "user_code",many = @Many(select = "com.innove.authority.dao.mapper.MenuDao.selectNamesByUserCode"))
    })
    UserDetailResponse detail(String userCode);

    class UserProvider{
        public String listSql(UserListRequest request){
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT ")
                    .append(" U.id, " )
                    .append(" U.user_code, ")
                    .append(" R.role_name, " )
                    .append(" S.staff_name, " )
                    .append(" U.user_tel, " )
                    .append(" U.user_status,  " )
                    .append(" o.org_name  " )
                    .append("FROM ")
                    .append(" t_user U " )
                    .append(" LEFT JOIN t_role R ON U.role_code = R.role_code ")
                    .append(" LEFT JOIN t_staff S ON S.staff_code = U.staff_code  " )
                    .append(" LEFT JOIN t_user_data d ON d.user_code = U.user_code  " )
                    .append(" LEFT JOIN t_org o ON o.org_code = d.org_code  " )
                    .append("WHERE " )
                    .append(" 1 = 1  " );
            if(StringUtil.isNotBlank( request.getRoleCode())){
                sql.append( " AND U.role_code = '" ).append(request.getRoleCode()).append("'");
            }
            if(StringUtil.isNotBlank(request.getUserCode())){
                sql.append(String.format(" AND LOCATE( %s ,U.user_code) > 0 ",request.getUserCode()) );
            }
            if(request.getUserStatus()!=null){
                sql.append(" AND U.user_status =").append(request.getUserStatus());
            }
            if(StringUtil.isNotBlank(request.getStaffName())){
                sql.append(" AND  LOCATE('").append(request.getStaffName()).append("' ,S.staff_name) > 0 ");
            }
            return sql.toString();
        }
    }
}
