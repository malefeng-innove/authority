package com.innove.authority.bean.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "账户列表信息查询响应包")
public class UserListResponse {

    @ApiModelProperty(value = "账户ID")
    private Integer id;

    @ApiModelProperty(value = "用户名")
    private String userCode;

    @ApiModelProperty(value = "角色")
    private String roleName;

    @ApiModelProperty(value = "姓名")
    private String staffName;

    @ApiModelProperty(value = "用户电话")
    private String userTel;

    @ApiModelProperty(value = "用户状态(1-正常 -1-删除)")
    private Integer userStatus;

}
