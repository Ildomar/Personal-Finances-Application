package br.com.javaparaweb.financeiro.usuario;

import java.util.List;

public interface UsuarioDAO {

	public void salvar(Usuario usuario);    //     C
	public Usuario carregar(Integer codigo);//     R
	public void atualizar(Usuario usuario);//      U
	public void excluir(Usuario usuario);//        D
	public Usuario buscarPorLogin(String login);
	public List<Usuario> listar();
	
}//fim interface
