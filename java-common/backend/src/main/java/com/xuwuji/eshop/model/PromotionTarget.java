package com.xuwuji.eshop.model;

public enum PromotionTarget {
	BRAND("Ʒ�ƻ", "0"), CATEGORY("��Ʒ����", "1"), SHOP("ȫ���", "2");

	private String desc;
	private String code;

	private PromotionTarget(String desc, String code) {
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

	public PromotionTarget getByCode(String code) {
		for (PromotionTarget status : PromotionTarget.values()) {
			if (status.code.equals(code)) {
				return status;
			}

		}
		return null;
	}
}
