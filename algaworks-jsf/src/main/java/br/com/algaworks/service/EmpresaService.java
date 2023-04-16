package br.com.algaworks.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.algaworks.models.Empresa;
import br.com.algaworks.repository.Empresas;
import br.com.algaworks.util.Transacional;

public class EmpresaService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Empresas empresas;
	
	@Transacional
	public void salvar(Empresa empresa) {
		empresas.guardar(empresa);
	}
	
	@Transacional
	public void excluir(Empresa empresa) {
		empresas.remover(empresa);
	}
	
	@Transacional
	public List<Empresa>  listarTodas() {
		return empresas.pesquisar("");
	}
	

}
