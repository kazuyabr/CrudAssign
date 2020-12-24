package com.crudassing.service;

import java.util.List;

import com.crudassing.entity.Status;

public interface StatusService {

	Status save(Status status);

	List<Status> getAll();

}
