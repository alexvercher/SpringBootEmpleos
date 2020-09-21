package com.avc.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.avc.model.Solicitud;


public interface ISolicitudesService {
	public void guardar(Solicitud solicitud);
	public void eliminar(Integer idSolicitud);
	public Solicitud buscarPorId(Integer idSolicitud);
	public List<Solicitud> buscarTodas();
	public Page<Solicitud> buscarTodas(Pageable page);
}
