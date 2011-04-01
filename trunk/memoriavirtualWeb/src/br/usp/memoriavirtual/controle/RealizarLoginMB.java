package br.usp.memoriavirtual.controle;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;

import br.usp.memoriavirtual.modelo.entidades.Usuario;
import br.usp.memoriavirtual.modelo.fachadas.remoto.RealizarLoginRemote;

public class RealizarLoginMB {

    @EJB
    private RealizarLoginRemote realizarLoginEJB;
    private String usuario;
    private String senha;

    /**
     * Verifica as informacões de usuário e senha na base de dados.
     * 
     * @return <code>true</code> se o usuário foi autenticado com sucesso ou
     *         <code>false</code> caso contrário.
     */
    public String autenticarUsuario() {
	boolean autenticado = false;

	Usuario usuarioAutenticado = realizarLoginEJB.realizarLogin(this.getUsuario(), this.getSenha());
	if (usuarioAutenticado != null) {
	    autenticado = true;
	    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuarioAutenticado);
	} else {
	    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}

	return autenticado ? "sucesso" : "falha";
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
	return usuario;
    }

    /**
     * @param usuario
     *            the usuario to set
     */
    public void setUsuario(String usuario) {
	this.usuario = usuario;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
	return senha;
    }

    /**
     * @param senha
     *            the senha to set
     */
    public void setSenha(String senha) {
	this.senha = senha;
    }

}
