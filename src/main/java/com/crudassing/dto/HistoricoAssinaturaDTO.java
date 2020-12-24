package com.crudassing.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class HistoricoAssinaturaDTO {

	private Integer id;
	@NotNull(message = "Deve informar o tipo deste histórico")
	private String tipo;
	@NotNull(message = "Deve informar o ID da assinatura")
	private Integer assinatura;
	@JsonProperty("criado_em")
	private Date criadoEm;

	@Override
	public String toString() {
		return "HistoricoAssinaturaDTO [id=" + id + ", tipo=" + tipo + ", assinatura=" + assinatura + ", criadoEm="
				+ criadoEm + "]";
	}

	public HistoricoAssinaturaDTO() {
		super();
	}

	public HistoricoAssinaturaDTO(Integer id, @NotNull(message = "Deve informar o tipo deste histórico") String tipo,
			@NotNull(message = "Deve informar o ID da assinatura") Integer assinatura, Date criadoEm) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.assinatura = assinatura;
		this.criadoEm = criadoEm;
	}

}
