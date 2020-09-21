package com.avc.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.avc.model.Vacante;

public interface IVacantesService {

	public List<Vacante> buscarTodas();
	public Vacante buscarPorId(Integer id);
	public void guardar (Vacante vacante);
	public List<Vacante> buscarDestacadas();
	public void eliminar(Integer id);
	public List<Vacante> buscarByExample(Example<Vacante> example);
	public Page<Vacante> buscarTodas(Pageable page);
}
