package com.crudassing.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.crudassing.entity.HistoricoAssinatura;

public interface HistoricoAssinaturaRepository extends JpaRepository<HistoricoAssinatura, Integer> {

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "update historico_assinatura ha set ha.tipo = :tipo where ha.id = :id", nativeQuery = true)
	void update(@Param("id") Integer id, @Param("tipo") String tipo);

}
