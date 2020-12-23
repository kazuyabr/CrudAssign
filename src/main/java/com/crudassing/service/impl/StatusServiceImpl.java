package com.crudassing.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crudassing.entity.Status;
import com.crudassing.repository.StatusRepository;
import com.crudassing.service.StatusService;

@Service
public class StatusServiceImpl implements StatusService {

	@Autowired
	StatusRepository repository;

	@Override
	public Status save(Status status) {
		return repository.save(status);
	}

}
