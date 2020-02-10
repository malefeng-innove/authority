package com.innove.authority.bean.enums;

/**  
* @ClassName: RuntimeErrorEnum.java  
* @Description: 运行异常enum
* @author paradise  
* @date 2019年8月30日    
*/
public enum RuntimeErrorEnum {
	
	/*
	 * code规则如下：
	 * 第一位：2-数据交换服务错误 3-数据业务服务错误 9-通用
	 * 第二位：0-数据交换service 1-数据业务service
	 * 第三、四、五位：001-999错误编码
	 */

	QC_UP_ERROR("31010","质控数据上传错误"),
	QC_ITEM_UP_EMPTY_ERROR("31011","质控明细数据上传为空"),
	QC_EMPTY_ERROR("31012","质控数据查询为空"),
	RESULT_PUB_ERROR("31001","脱敏数据上传失败"),
	QC_ENVIRONMENT_ERROR("31002","质控环境不存在"),
	QC_RESULT_ERROR("31003","质控结果不存在"),
	
	TRANS_NO_APPLY_TO_DOWN("20001","没有需要下载的申请数据"),
	TRANS_NO_RESULT_TO_DOWN("20002","没有需要下载的结果数据"),
	TRANS_NO_ITEM("20003","提交的项目指标不存在"),

	Staff_REPETITION_ERROR("31020","该人员已存在"),
	AREA_REPETITION_ERROR("31021","该区域已存在"),
	PROVINCE_REPETITION_ERROR("31022","该省份已存在"),
	CITY_REPETITION_ERROR("31023","该城市已存在"),
	ORG_REPETITION_ERROR("31024","该机构已存在"),
	ORG_EQUIP_EMPTY_ERROR("31025","机构设备id不存在"),
	ORG_DEPART_REPETITION_ERROR("31026","部门已存在"),
	ORG_EQUIP_CODE_REPETITION_ERROR("31026","机构设备已存在"),
	ITEM_CODE_REPETITION_ERROR("31027","检验项目已存在"),
	DICT_CODE_UNIQ_ERROR("31028","字典编号已存在"),



	AREA_DELETE_ERROR("31029","存在下级省份，无法删除该区域信息"),
	PROVINCE_DELETE_ERROR("31030","存在下级城市，无法删除该省份信息"),
	CITY_DELETE_ERROR("31031","存在下级机构，无法删除该城市信息"),
	ORG_DELETE_ERROR("31032","存在下级部门，无法删除该机构信息"),
	PARENT_DEPART_DELETE_ERROR("31033","存在下级部门，无法删除该部门信息"),
	DEPART_DELETE_ERROR("31034","存在下级人员，无法删除该部门信息"),


	CHECK_DATA_SELECT_ERROR("31035","没有找到详情数据"),

	ROLE_NOT_NULL_ERROR("31036","未找到角色信息"),
	ROLE_RELATI_USER_NULL("31037","存在使用中的人员，无法删除角色信息"),

	DICT_GROUP_NOT_NULL_ERROR("31038","未找到字典组"),
	DICT_GROUP_RELATI_DICT_ERROR("31039","存在使用中的字典信息，无法删除字典组信息"),

	REQUEST_PARAM_ERROR("90001","请求参数错误"),
	OPERATE_FAIL("90002","操作失败"),
	INVALID_USER_NAME_ERROR("90003","账号无效"),
	INVALID_PSW_ERROR("90004","密码无效"),
	INVALID_VER_CODE_ERROR("90005","验证码无效"),
	INVALID_UNLOGIN_ERROR("90006","当前未登录"),
	UNAUTH_ERROR("90007","无访问权限"),
	SYSTEM_ERROR("99999","系统错误");
	
	private String code;
	
	private String msg;

	RuntimeErrorEnum(final String code, final String msg) {
		this.code = code;
		this.msg = msg;
	}

	public static RuntimeErrorEnum fromValue(String value) {
		for (RuntimeErrorEnum at : RuntimeErrorEnum.values()) {
			if (at.getMsg().equals(value)) {
				return at;
			}
		}
		return null;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
