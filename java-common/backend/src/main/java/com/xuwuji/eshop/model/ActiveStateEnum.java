package com.xuwuji.eshop.model;

/**
 * basic state ����״̬
 * 
 * @author xuwuji
 *
 */
public enum ActiveStateEnum {
	ACTIVE("��Ч", "1"), INVALID("��Ч���¼�", "0");

	private String desc;
	private String code;

	private ActiveStateEnum(String desc, String code) {
		this.desc = desc;
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ActiveStateEnum getByCode(String code) {
		for (ActiveStateEnum status : ActiveStateEnum.values()) {
			if (status.code.equals(code)) {
				return status;
			}

		}
		return null;
	}

}
