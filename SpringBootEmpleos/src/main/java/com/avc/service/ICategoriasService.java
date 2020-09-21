package com.avc.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.avc.model.Categoria;

public interface ICategoriasService {
	
	public void guardar(Categoria categoria);
	public List<Categoria> buscarTodas();
	public Categoria buscarPorId(Integer idCategoria);	
	public void eliminar(Integer id);
	public Page<Categoria> buscarTodas(Pageable page);
}
