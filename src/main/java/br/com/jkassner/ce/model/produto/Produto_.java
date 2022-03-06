package br.com.jkassner.ce.model.produto;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import br.com.jkassner.ce.model.Empresa;

@StaticMetamodel(Produto.class)
public class Produto_ {
		public static volatile SingularAttribute<Produto, String> nome;
	    public static volatile SingularAttribute<Produto, Boolean> ativo;
	    public static volatile SingularAttribute<Produto, Empresa> empresa;
}
