package br.com.algaworks.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import br.com.algaworks.models.Empresa;
import br.com.algaworks.models.RamoAtividade;
import br.com.algaworks.models.TipoEmpresa;
import br.com.algaworks.repository.Empresas;
import br.com.algaworks.repository.RamoAtividades;
import br.com.algaworks.service.EmpresaService;
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
    
    @Inject
    private EmpresaService empresaService;
    
    private List<Empresa> listaEmpresas;
    
    private String termoPesquisa;
    
    private Converter ramoAtividadeConverter;
    
    private Empresa empresa;
    
    public void prepararNovaEmpresa() {
        empresa = new Empresa();
    }
    
    public void prepararEdicao() {
    	ramoAtividadeConverter = new RamoAtividadeConverter(Arrays.asList(empresa.getRamoAtividade()));
    }
    
    public void salvar() {
        empresaService.salvar(empresa);
        
        atualizar();
        
        messages.info("Empresa cadastrada com sucesso!");
        
        RequestContext.getCurrentInstance().update(Arrays.asList("empresaForm:empresasDataTable", "empresaForm:messages"));
    }
    
	public void pesquisar() {
        listaEmpresas = empresas.pesquisar(termoPesquisa);
        
        if (listaEmpresas.isEmpty()) {
            messages.info("Sua consulta não retornou registros.");
        }
    }
    
    public void excluir() {
    	empresaService.excluir(empresa);
    	empresa = null;
    	atualizar();
        messages.info("Empresa excluída com sucesso!");
    }
    
    private void atualizar() {
//        if (jaHouvePesquisa()) {
//            pesquisar();
//        }
    	todasEmpresas();
	}
    
    public void todasEmpresas() {
        listaEmpresas = empresas.todas();
    }
    
    public List<RamoAtividade> completarRamoAtividade(String termo) {
        List<RamoAtividade> listaRamoAtividades = ramoAtividades.pesquisar(termo);
        
        ramoAtividadeConverter = new RamoAtividadeConverter(listaRamoAtividades);
        
        return listaRamoAtividades;
    }
    
    private boolean jaHouvePesquisa() {
        return termoPesquisa != null && !"".equals(termoPesquisa);
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
    
    public Converter getRamoAtividadeConverter() {
        return ramoAtividadeConverter;
    }
    
    public Empresa getEmpresa() {
        return empresa;
    }
    
    public void setEmpresa(Empresa empresa) {
    	this.empresa = empresa;
    }
    
    public boolean empresaSelecionada() {
    	return empresa != null && empresa.getId() != null;
    }

}