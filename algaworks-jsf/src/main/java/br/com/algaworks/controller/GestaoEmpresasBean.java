package br.com.algaworks.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.algaworks.models.Empresa;
import br.com.algaworks.models.RamoAtividade;
import br.com.algaworks.models.TipoEmpresa;
import br.com.algaworks.repository.Empresas;
import br.com.algaworks.repository.RamoAtividades;
import br.com.algaworks.util.FacesMessages;

@Named
@ViewScoped
public class GestaoEmpresasBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Empresas empresas;
	
	@Inject
	private FacesMessages messages;
	
	@Inject
	private RamoAtividades ramoAtividades;

	private List<Empresa> listaEmpresas;
	private String termoPesquisa;
	private RamoAtividadeConverter ramoAtividadeConverter;
	
	public void todasEmpresas() {
		listaEmpresas = empresas.listarTodas();
	}
	
	public void pesquisar() {
		listaEmpresas = empresas.pesquisar(termoPesquisa);
		
		if(listaEmpresas.isEmpty()) {
			messages.info("Esta consulta não retornou registros");
		}
	}
	
	public List<RamoAtividade> completarRamoAtividade(String termo){
		List<RamoAtividade> listaRamoAtividades = ramoAtividades.pesquisar(termo);
		
		ramoAtividadeConverter = new RamoAtividadeConverter(listaRamoAtividades);
		
		return listaRamoAtividades;
	}
	
	public List<Empresa> getListaEmpresas() {
		return listaEmpresas;
	}

	public String getTermoPesquisa() {
		return termoPesquisa;
	}

	public void setTermoPesquisa(String termoPesquisa) {
		this.termoPesquisa = termoPesquisa;
	}
	
	public TipoEmpresa[] getTiposEmpresa() {
		return TipoEmpresa.values();
	}

	public RamoAtividadeConverter getRamoAtividadeConverter() {
		return ramoAtividadeConverter;
	}
	

}
