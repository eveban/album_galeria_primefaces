package br.com.fotos.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import br.com.fotos.dao.UsuarioDAO;
import br.com.fotos.modelo.Usuario;

@ManagedBean
@ViewScoped
public class UsuarioMB implements Serializable {

	private Usuario usuario = new Usuario();
	private boolean usuarioLogado;

	public String efetuaLogin() {
		UsuarioDAO dao = new UsuarioDAO();
		boolean loginValido = dao.existe(this.usuario);
		if (loginValido) {
			return "fotos";
		} else {
			return "login";
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(boolean usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

}
