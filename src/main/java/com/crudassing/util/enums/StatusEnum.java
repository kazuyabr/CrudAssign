package com.crudassing.util.enums;

public enum StatusEnum {
	ATIVA(1), CANCELADA(2);

	private final Integer value;

	StatusEnum(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return this.value;
	}

	public static StatusEnum getEnum(Integer status) {
		for (StatusEnum s : values()) {
			if (status.equals(s.getValue())) {
				return s;
			}
		}
		return null;
	}

}
