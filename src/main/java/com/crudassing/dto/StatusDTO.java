package com.crudassing.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.crudassing.util.enums.StatusEnum;

public class StatusDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull
	@Pattern(regexp = "^(0|1)$", message = "Para o tipo somente são aceitos os valores 0 -> ATIVA ou 1 -> CANCELADA")
	private StatusEnum tipo;

}
