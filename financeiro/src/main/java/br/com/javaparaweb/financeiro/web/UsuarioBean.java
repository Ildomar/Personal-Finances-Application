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
	

	
	private String confirmarSenha;
	
	private Usuario usuario = new Usuario();

	
	public String novo() {
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
		
		return "usuariosucesso";
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


	

}//fim classe
