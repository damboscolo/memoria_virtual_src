package br.usp.memoriavirtual.modelo.entidades;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Acesso implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USUARIO")
    private Usuario usuario;
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "INSTITUICAO")
    private Instituicao instituicao;
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "GRUPO")
    private Grupo grupo;

    /**
     * Construtor padrão
     */
    public Acesso() {
	super();
    }

    public Acesso(Usuario usuario, Instituicao instituicao, Grupo grupo) {
	super();
	this.usuario = usuario;
	this.instituicao = instituicao;
	this.grupo = grupo;
    }

    public Usuario getUsuario() {
	return usuario;
    }

    public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
    }

    public Instituicao getInstituicao() {
	return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
	this.instituicao = instituicao;
    }

    public Grupo getGrupo() {
	return grupo;
    }

    public void setGrupo(Grupo grupo) {
	this.grupo = grupo;
    }

}
