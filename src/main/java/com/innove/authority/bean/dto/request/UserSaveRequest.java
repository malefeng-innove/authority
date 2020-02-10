package com.innove.authority.bean.dto.request;

import com.innove.authority.bean.entity.UserEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(description = "新增账户信息请求包")
@Data
public class UserSaveRequest extends UserEntity {
}
