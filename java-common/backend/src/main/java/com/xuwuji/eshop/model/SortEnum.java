package com.xuwuji.eshop.model;

public enum SortEnum {
	ALL("�ۺ�", "0"), TIME("����", "1"), PRICE("�ۺ�", "2"), SALE("����", "3");

	private String desc;
	private String code;

	private SortEnum(String desc, String code) {
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

	public static SortEnum getByCode(String code) {
		for (SortEnum status : SortEnum.values()) {
			if (status.code.equals(code)) {
				return status;
			}

		}
		return null;
	}
}
