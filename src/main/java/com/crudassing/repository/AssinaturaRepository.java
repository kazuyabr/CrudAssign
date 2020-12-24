package com.crudassing.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.crudassing.entity.Assinatura;

public interface AssinaturaRepository extends JpaRepository<Assinatura, Integer> {

	Optional<Assinatura> findById(Integer id);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "update assinatura set status_id = 2, atualizado_em = CURRENT_TIMESTAMP() where id = :id", nativeQuery = true)
	void cancelar(@Param("id") Integer id);

}
