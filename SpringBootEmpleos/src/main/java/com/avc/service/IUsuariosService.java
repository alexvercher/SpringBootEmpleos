package com.avc.service;

import java.util.List;

import com.avc.model.Usuario;

public interface IUsuariosService {

	public void guardar(Usuario usuario);
	public void eliminar(Integer idUsuario);
	public List<Usuario> buscarTodos();
	public List<Usuario> buscarRegistrados();
	public Usuario buscarPorId(Integer idUsuario);	
	public Usuario buscarPorUsername(String username);
	public int bloquear(int idUsuario);
	public int activar(int idUsuario);
}
