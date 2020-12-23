package com.crudassing.service;

import java.util.List;
import java.util.Optional;

import com.crudassing.entity.Assinatura;

public interface AssinaturaService {

	Assinatura save(Assinatura a);

	List<Assinatura> getAll();

	Optional<Assinatura> findById(Integer id);

	void cancelById(Integer id);

}
