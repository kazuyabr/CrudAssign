package com.crudassing.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crudassing.entity.Assinatura;
import com.crudassing.repository.AssinaturaRepository;
import com.crudassing.service.AssinaturaService;

@Service
public class AssinaturaServiceImpl implements AssinaturaService {

	@Autowired
	AssinaturaRepository repository;

	@Override
	public Assinatura save(Assinatura a) {
		return repository.save(a);
	}

	@Override
	public Optional<Assinatura> findById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public void cancelById(Integer id) {
		repository.cancelar(id);
	}

	@Override
	public List<Assinatura> getAll() {
		return repository.findAll();
	}

}
