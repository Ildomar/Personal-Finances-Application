package br.com.javaparaweb.financeiro.web;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.javaparaweb.financeiro.usuario.Usuario;
import br.com.javaparaweb.financeiro.usuario.UsuarioRN;

@ManagedBean(name = "usuarioBean")
@RequestScoped
public class UsuarioBean {
	
	private List<SelectItem> idiomas;
	
	public List<SelectItem> getIdiomas(){
		if(this.idiomas==null) {
			this.idiomas =  new ArrayList<SelectItem>();
			this.idiomas.add(new SelectItem("pt_BR", "Português"));
			this.idiomas.add(new SelectItem("en_US", "English"));
			this.idiomas.add(new SelectItem("es_ES","Espanol"));
		}
		return idiomas;
	}
	

	private List<Usuario> lista;
	private String destinoSalvar;
	
	private String confirmarSenha;
	
	private Usuario usuario = new Usuario();

	
	public String novo() {
		this.destinoSalvar = "usuariosucesso"; //Para indicar que é novo e não edição
		this.usuario = new Usuario();
		this.usuario.setAtivo(true);
		return "publico/usuario";
	}
	
	public  String salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		String senha =  this.usuario.getSenha();
		if(!senha.equals(this.confirmarSenha)) {
			
			System.out.println("Idioma: "+this.usuario.getIdioma());
			
			FacesMessage facesMessage = new FacesMessage("A senha não foi confirmada corretamente!");
			context.addMessage(null, facesMessage);
			return null;
		}
		System.out.println("Nasc.: "+this.usuario.getNascimento());
		UsuarioRN usuarioRN =  new UsuarioRN();
		usuarioRN.salvar(this.usuario);
		
		return this.destinoSalvar;
	}
	
	
	public String ativar() {
		if(this.usuario.isAtivo()) {
			this.usuario.setAtivo(false);
		}else {
			this.usuario.setAtivo(true);
		}
		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.salvar(this.usuario);
		return null;
	}
	
	public String editar() {
		this.confirmarSenha = this.usuario.getSenha();
		return "/publico/usuario";
	}
	
	
	public String excluir() {
		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.excluir(this.usuario);
		this.lista=null;
		return null;
	}
	
	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		
		this.confirmarSenha = confirmarSenha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getLista() {
		if(this.lista==null) {
			UsuarioRN usuarioRN = new UsuarioRN();
			this.lista = usuarioRN.listar();
		}
		return this.lista;
	}

	public String getDestinoSalvar() {
		return destinoSalvar;
	}

	public void setDestinoSalvar(String destinoSalvar) {
		this.destinoSalvar = destinoSalvar;
	}


	

}//fim classe
