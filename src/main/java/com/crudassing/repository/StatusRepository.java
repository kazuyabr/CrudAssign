package com.crudassing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crudassing.entity.Status;

public interface StatusRepository extends JpaRepository<Status, Integer> {

}
