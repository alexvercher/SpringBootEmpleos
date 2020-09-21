package com.avc.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.avc.model.Categoria;
import com.avc.repository.CategoriasRepository;
import com.avc.service.ICategoriasService;

@Service
@Primary
public class CategoriasServiceJpa implements ICategoriasService {

	@Autowired
	private CategoriasRepository repoCategorias;
	
	@Override
	public void guardar(Categoria categoria) {
		repoCategorias.save(categoria);
	}

	@Override
	public List<Categoria> buscarTodas() {
		return repoCategorias.findAll();
	}

	@Override
	public Categoria buscarPorId(Integer idCategoria) {
		Optional<Categoria> categoria = repoCategorias.findById(idCategoria);
		if (categoria.isPresent())
			return categoria.get();
		else
			return null;
	}

	public void eliminar(Integer idCategoria) {
		repoCategorias.deleteById(idCategoria);
	}

	@Override
	public Page<Categoria> buscarTodas(Pageable page) {
		return repoCategorias.findAll(page);
	}
}
