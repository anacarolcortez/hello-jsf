package br.com.algaworks.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.algaworks.models.Empresa;
import br.com.algaworks.models.RamoAtividade;
import br.com.algaworks.models.TipoEmpresa;

public class CamadaPersistencia {
	
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("HelloJSF");
		
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		RamoAtividades ramoAtividades = new RamoAtividades(em);
		
		Empresas empresas = new Empresas(em);
		
		List<RamoAtividade> listaAtividades = ramoAtividades.pesquisar("");
		System.out.println(listaAtividades);
		
		List<Empresa> listaEmpresas = empresas.pesquisar("");
		System.out.println(listaEmpresas);
		
		Empresa empresa = new Empresa();
		empresa.setNomeFantasia("Empresa da Ana");
		empresa.setRazaoSocial("Ana Enterprise");
		empresa.setRamoAtividade(listaAtividades.get(2));
		empresa.setDataFundacao(new Date());
		empresa.setCnpj("18.002.543/0001-03");
		empresa.setTipoEmpresa(TipoEmpresa.EIRELI);
		
		empresas.guardar(empresa);
		
		em.getTransaction().commit();
		
		List<Empresa> listaEmpresasFinal = empresas.pesquisar("");
		System.out.println(listaEmpresasFinal);
				
	}

}
