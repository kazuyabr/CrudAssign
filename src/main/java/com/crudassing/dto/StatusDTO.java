package com.crudassing.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.crudassing.util.enums.StatusEnum;

import lombok.Data;

@Data
public class StatusDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull
	@Pattern(regexp = "^(1|2)$", message = "Para o tipo somente são aceitos os valores 1 -> ATIVA ou 2 -> CANCELADA")
	private StatusEnum tipo;

	public StatusDTO(Integer id,
			@NotNull @Pattern(regexp = "^(1|2)$", message = "Para o tipo somente são aceitos os valores 1 -> ATIVA ou 2 -> CANCELADA") StatusEnum tipo) {
		super();
		this.id = id;
		this.tipo = tipo;
	}

	public StatusDTO() {
		super();
	}

	@Override
	public String toString() {
		return "StatusDTO [id=" + id + ", tipo=" + tipo + "]";
	}

}
