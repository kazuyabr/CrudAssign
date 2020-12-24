package com.crudassing.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crudassing.entity.HistoricoAssinatura;
import com.crudassing.repository.HistoricoAssinaturaRepository;
import com.crudassing.service.HistoricoService;

@Service
public class HistoricoAssinaturaServiceImpl implements HistoricoService {

	@Autowired
	HistoricoAssinaturaRepository repository;

	@Override
	public HistoricoAssinatura save(HistoricoAssinatura entity) {
		return repository.save(entity);
	}

	@Override
	public List<HistoricoAssinatura> getAll() {
		return repository.findAll();
	}

	@Override
	public Optional<HistoricoAssinatura> getById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public void updateHistorico(Integer id, String tipo) {
		repository.update(id, tipo);
	}

	@Override
	public void deleteHistorico(Integer id) {
		repository.deleteById(id);
	}

}
