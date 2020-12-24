package com.crudassing.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
public class Assinatura implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3416363638672741171L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JoinColumn(name = "status_id", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Status status;
	@NotNull
	@Column(name = "criado_em")
	private Date criadoEm;
	@NotNull
	@Column(name = "atualizado_em")
	private Date atualizadoEm;

	public Assinatura(Status status, @NotNull Date criadoEm, @NotNull Date atualizadoEm) {
		super();
		this.status = status;
		this.criadoEm = criadoEm;
		this.atualizadoEm = atualizadoEm;
	}

	public Assinatura() {
		super();
	}

	@Override
	public String toString() {
		return "Assinatura [id=" + id + ", status=" + status + ", criadoEm=" + criadoEm + ", atualizadoEm="
				+ atualizadoEm + "]";
	}

}
