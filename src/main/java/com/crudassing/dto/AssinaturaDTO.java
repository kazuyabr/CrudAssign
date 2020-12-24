package com.crudassing.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class AssinaturaDTO {

	private Integer id;
	private Integer status;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", locale = "pt-BR", timezone = "Brazil/East")
	private Date criadoEm;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", locale = "pt-BR", timezone = "Brazil/East")
	private Date atualizadoEm;

	public AssinaturaDTO(Integer id, Integer status, Date criadoEm, Date atualizadoEm) {
		super();
		this.id = id;
		this.status = status;
		this.criadoEm = criadoEm;
		this.atualizadoEm = atualizadoEm;
	}

	public AssinaturaDTO() {
		super();
	}

	@Override
	public String toString() {
		return "AssinaturaDTO [id=" + id + ", status=" + status + ", criadoEm=" + criadoEm + ", atualizadoEm="
				+ atualizadoEm + "]";
	}

}
