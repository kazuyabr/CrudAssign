package com.crudassing.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
@Table(name = "historico_assinatura")
public class HistoricoAssinatura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull
	private String tipo;
	@JoinColumn(name = "id_assinatura", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Assinatura assinatura;
	@Column(name = "criado_em")
	private Date criadoEm;

}
