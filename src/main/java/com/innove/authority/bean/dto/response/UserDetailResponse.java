package com.innove.authority.bean.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@ApiModel(description = "账户信息查询响应包")
@Data
public class UserDetailResponse {

    @ApiModelProperty(value = "账户ID")
    private Integer id;

    @ApiModelProperty(value = "用户账号")
    private String userCode;

    @ApiModelProperty(value = "员工编号")
    private String staffCode;

    @ApiModelProperty(value = "用户电话")
    private String userTel;

    @ApiModelProperty(value = "角色编号")
    private String roleCode;

    @ApiModelProperty(value = "用户状态(1-正常 -1-删除)")
    private Integer userStatus;

    @ApiModelProperty(value = "创建时间(yyyy-MM-dd HH:mm:ss)")
    private Date createTime;

    @ApiModelProperty(value = "修改时间(yyyy-MM-dd HH:mm:ss)")
    private Date updateTime;

    @ApiModelProperty(value = "操作用户编号")
    private String openUser;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "菜单名称列表")
    private List<String> menuNames;
}
