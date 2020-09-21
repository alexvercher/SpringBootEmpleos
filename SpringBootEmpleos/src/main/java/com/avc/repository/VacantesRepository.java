package com.avc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avc.model.Vacante;

public interface VacantesRepository extends JpaRepository<Vacante, Integer>{

	List<Vacante> findByEstatus(String estatus);
	List<Vacante> findByDestacadoAndEstatusOrderByIdDesc(Integer destacado, String estatus);
	List<Vacante> findBySalarioBetweenOrderBySalarioDesc(double s_inicial, double s_final);
	List<Vacante> findByEstatusIn(String[] estatus);
}
