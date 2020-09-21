package com.avc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avc.model.Solicitud;


public interface SolicitudesRepository extends JpaRepository<Solicitud, Integer> {

}
