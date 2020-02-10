package com.innove.authority.bean.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * t_user
 * @author mlf
 * @Date 2020-01-15
 */
@ApiModel(description = "用户表")
@Table(name = "t_user")
@Data
public class UserEntity implements Serializable {
    /**
     * t_user.id
     */
    @ApiModelProperty(value = "")
    @Id
    private Integer id;

    /**
     * t_user.user_code
     */
    @ApiModelProperty(value = "用户账号")
    private String userCode;

    /**
     * t_user.password
     */
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * t_user.staff_code
     */
    @ApiModelProperty(value = "员工编号")
    private String staffCode;

    /**
     * t_user.user_tel
     */
    @ApiModelProperty(value = "用户电话")
    private String userTel;

    /**
     * t_user.role_code
     */
    @ApiModelProperty(value = "角色编号")
    private String roleCode;

    /**
     * t_user.user_status
     */
    @ApiModelProperty(value = "用户状态(1-正常 -1-删除)")
    private Integer userStatus;

    /**
     * t_user.create_time
     */
    @ApiModelProperty(value = "创建时间(yyyy-MM-dd HH:mm:ss)")
    private Date createTime;

    /**
     * t_user.update_time
     */
    @ApiModelProperty(value = "修改时间(yyyy-MM-dd HH:mm:ss)")
    private Date updateTime;

    /**
     * t_user.open_user
     */
    @ApiModelProperty(value = "操作用户编号")
    private String openUser;

    /**
     * t_user.remark
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    private static final long serialVersionUID = 1L;
}