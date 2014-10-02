package br.com.fotos.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.fotos.dao.DAO;
import br.com.fotos.dao.EventoDAO;
import br.com.fotos.dao.FotoDAO;
import br.com.fotos.modelo.Evento;
import br.com.fotos.modelo.Foto;

@ManagedBean
@SessionScoped
public class GaleriaBean {

	private List<Evento> eventos = new ArrayList<Evento>();
	private Evento evento = new Evento();
	private Evento eventoSelecionado = new Evento();
	private DAO<Evento> eventoDAO = new DAO<Evento>(Evento.class);
	private DAO<Foto> fotoDAO = new DAO<Foto>(Foto.class);
	private Foto foto = new Foto();
	private StreamedContent imagem = new DefaultStreamedContent();
	private List<Foto> fotos = new ArrayList<Foto>();

	public void salvaEvento() {
		try {
			eventoDAO.salva(evento);
			evento = new Evento();
			eventos = eventoDAO.listaTodos();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Evento adicionado", "Evento adicionado"));
		} catch (Exception ex) {
			Logger.getLogger(GaleriaBean.class.getName()).log(Level.SEVERE,
					null, ex);
		}
	}

	public void salvaFotos() {
		try {
			fotoDAO.salva(foto);
			foto = new Foto();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Foto adicionada", "Foto adicionada"));
		} catch (Exception ex) {
			Logger.getLogger(GaleriaBean.class.getName()).log(Level.SEVERE,
					null, ex);
		}

	}

	public void remove() {
		DAO<Evento> dao = new DAO<Evento>(Evento.class);
		dao.remove(evento);
		this.eventos = eventoDAO.listaTodos();
		this.evento = new Evento();
	}

	public void enviaImagem(FileUploadEvent event) {
		try {
			imagem = new DefaultStreamedContent(event.getFile()
					.getInputstream());
			foto.setEvento(eventoSelecionado);
			foto.setImagem(event.getFile().getContents());
		} catch (IOException ex) {
			Logger.getLogger(GaleriaBean.class.getName()).log(Level.SEVERE,
					null, ex);
		}
	}

	public void listaFotosEvento() {
		try {
			FotoDAO dao = new FotoDAO();
			fotos = dao.buscaPorEvento(eventoSelecionado);
			for (Foto f : fotos) {
				FacesContext facesContext = FacesContext.getCurrentInstance();
				ServletContext scontext = (ServletContext) facesContext
						.getExternalContext().getContext();
				String nomeArquivo = f.getId().toString() + ".jpg";
				String arquivo = scontext.getRealPath("/temp/" + nomeArquivo);
				// String arquivo = "C:\\workspace\\fotos\\WebContent\\temp\\" +
				// nomeArquivo;
				criaArquivo(f.getImagem(), arquivo);
			}
		} catch (Exception ex) {
			Logger.getLogger(GaleriaBean.class.getName()).log(Level.SEVERE,
					null, ex);
		}
	}

	/*
	 * public void lista() { FotoDAO dao = new FotoDAO(); fotos =
	 * dao.buscaPorEvento(eventoSelecionado); for (Foto f : fotos) {
	 * FacesContext facesContext = FacesContext.getCurrentInstance();
	 * ServletContext scontext = (ServletContext) facesContext
	 * .getExternalContext().getContext(); String nomeArquivo =
	 * f.getId().toString() + ".jpg"; String arquivo =
	 * scontext.getRealPath("c:/lixo/" + nomeArquivo);
	 * criaArquivo(f.getImagem(), arquivo); }
	 * System.out.println("Listando as fotos"); }
	 */

	public void criaArquivo(byte[] bytes, String arquivo) {
		FileOutputStream fos;
		System.out.println("Foto " + arquivo);
		try {
			fos = new FileOutputStream(arquivo);
			fos.write(bytes);
			fos.close();
		} catch (FileNotFoundException ex) {
			Logger.getLogger(GaleriaBean.class.getName()).log(Level.SEVERE,
					null, ex);
		} catch (IOException ex) {
			Logger.getLogger(GaleriaBean.class.getName()).log(Level.SEVERE,
					null, ex);
		}
	}

	public GaleriaBean() {
		try {
			EventoDAO dao = new EventoDAO();
			eventos = dao.listaOrdenada();
			//eventos = eventoDAO.listaTodos();
		} catch (Exception ex) {
			Logger.getLogger(GaleriaBean.class.getName()).log(Level.SEVERE,
					null, ex);
		}
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public Evento getEvento() {
		if (evento.getData() == null) {
			evento.setData(Calendar.getInstance());
		}
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Evento getEventoSelecionado() {
		return eventoSelecionado;
	}

	public void setEventoSelecionado(Evento eventoSelecionado) {
		this.eventoSelecionado = eventoSelecionado;
	}

	public DAO<Evento> getEventoDAO() {
		return eventoDAO;
	}

	public void setEventoDAO(DAO<Evento> eventoDAO) {
		this.eventoDAO = eventoDAO;
	}

	public DAO<Foto> getFotoDAO() {
		return fotoDAO;
	}

	public void setFotoDAO(DAO<Foto> fotoDAO) {
		this.fotoDAO = fotoDAO;
	}

	public StreamedContent getImagem() {
		return imagem;
	}

	public void setImagem(StreamedContent imagem) {
		this.imagem = imagem;
	}

	public List<Foto> getFotos() {
		return fotos;
	}

	public void setFotos(List<Foto> fotos) {
		this.fotos = fotos;
	}

	public Foto getFoto() {
		return foto;
	}

}
