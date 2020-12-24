package com.crudassing.service;

import java.util.List;
import java.util.Optional;

import com.crudassing.entity.HistoricoAssinatura;

public interface HistoricoService {

	HistoricoAssinatura save(HistoricoAssinatura entity);

	List<HistoricoAssinatura> getAll();

	Optional<HistoricoAssinatura> getById(Integer id);

	void updateHistorico(Integer id, String tipo);

	void deleteHistorico(Integer id);

}
