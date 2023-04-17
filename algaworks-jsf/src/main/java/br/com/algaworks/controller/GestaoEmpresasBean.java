package br.com.algaworks.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.algaworks.models.Empresa;
import br.com.algaworks.repository.Empresas;

@Named
@ViewScoped
public class GestaoEmpresasBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Empresas empresas;

	private List<Empresa> listaEmpresas;
	
	public void todasEmpresas() {
		listaEmpresas = empresas.listarTodas();
	}
	
	public List<Empresa> getListaEmpresas() {
		return listaEmpresas;
	}

	public void setListaEmpresas(List<Empresa> listaEmpresas) {
		this.listaEmpresas = listaEmpresas;
	}
	
	

}
