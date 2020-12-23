package com.crudassing.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class HistoricoAssinaturaDTO {

	private Integer id;
	@NotNull(message = "Deve informar o tipo deste histórico")
	private String tipo;
	@NotNull(message = "Deve informar o ID da assinatura")
	private Integer assinatura;
	@Column(name = "criado_em")
	private Date criadoEm;

	@Override
	public String toString() {
		return "HistoricoAssinaturaDTO [id=" + id + ", tipo=" + tipo + ", assinatura=" + assinatura + ", criadoEm="
				+ criadoEm + "]";
	}

	public HistoricoAssinaturaDTO(@NotNull(message = "Deve informar o tipo deste histórico") String tipo,
			@NotNull(message = "Deve informar o ID da assinatura") Integer assinatura, Date criadoEm) {
		super();
		this.tipo = tipo;
		this.assinatura = assinatura;
		this.criadoEm = criadoEm;
	}

	public HistoricoAssinaturaDTO() {
		super();
	}

}
