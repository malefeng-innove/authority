package com.innove.authority.bean.enums;

/**  
* @ClassName: ResponseCodeEnum.java  
* @Description: 返回编码和信息enum
* @author paradise  
* @date 2019年8月30日    
*/
public enum ResponseCodeEnum {
	
	SUCCESS(200, "请求成功"), 
	FAILED(-100, "请求失败"),
	ERROR(65336, "服务端错误");

	private int key;
	private String value;

	private ResponseCodeEnum(final int key, final String value) {
		this.value = value;
		this.key = key;
	}
	
	public static String getValueByKey(String name) {
		return ResponseCodeEnum.valueOf(name).getValue();
	}

	public String key() {
		return String.valueOf(key);
	}

	/**
	 * @return the key
	 */
	public int getKey() {
		return key;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

}
