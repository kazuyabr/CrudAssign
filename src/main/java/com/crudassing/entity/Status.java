package com.crudassing.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.crudassing.util.enums.StatusEnum;

import lombok.Data;

@Entity
@Data
public class Status implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2882126454517227369L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull
	@Enumerated(EnumType.STRING)
	private StatusEnum tipo;

	public Status(Integer id, @NotNull StatusEnum tipo) {
		super();
		this.id = id;
		this.tipo = tipo;
	}

	public Status() {
		super();
	}

	@Override
	public String toString() {
		return "Status [id=" + id + ", tipo=" + tipo + "]";
	}

}
