package br.usp.memoriavirtual.controle;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.usp.memoriavirtual.modelo.entidades.Usuario;
import br.usp.memoriavirtual.modelo.fachadas.remoto.EnviarConviteRemote;

public class EnviarConviteMB {

    @EJB
    private EnviarConviteRemote enviarConviteEJB;
    private String emails = "";
    private String mensagem = "";
    private String validade = "";
    private String instituicao = "";
    private String nivelAcesso = "";
    private String erro = "";
    private Usuario usuario = null;
    private List<SelectItem> itens = null;
    
    public String enviarConvite(){
    	boolean sucesso = true;
    	this.erro = this.enviarConviteEJB.enviarConvite(emails, mensagem, validade, instituicao, nivelAcesso);
    	if(erro != "sucesso")
    		sucesso = false;
    	return sucesso ? "sucesso" : "falha";
    }
    
    public List<SelectItem> getNiveisPermitidos(){
    	//TODO verificar quais niveis sao permitidos para o usurio logado.
    	new ArrayList<SelectItem>();
    	itens.add(new SelectItem("Teste")); //itens.add(new SelectItem(instituicao.getId, instituicao.getnome)); // o primeiro parametro � o valor que vc passa para o mb e o segundo � o label que ficar� na p�gina jsp 
    	return itens;
    }
    
    public List<SelectItem> getInstituicoesPermitidos(){
    	//TODO verificar quais instituições sao permitidas
    	//Se o usurio logado for gerente, verificar quais instituicoes ele é gerente
    	usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
    	List<SelectItem> instituicoes = new ArrayList<SelectItem>();
    	instituicoes.add(new SelectItem("Teste")); //instituicoes.add(new SelectItem(quadra.getId, quadra.getDescricao)); // o primeiro parametro � o valor que vc passa para o mb e o segundo � o label que ficar� na p�gina jsp 
    	return instituicoes;
    }
    
    public void carregarNiveisAcesso(){
    	itens.add(new SelectItem(validade));
    }

	/**
	 * @return String contendo a lista de emails
	 */
	public String getEmails() {
		return emails;
	}

	/**
	 * @param Define String contendo a lista de emails a enviar convite
	 */
	public void setEmails(String emails) {
		this.emails = emails;
	}

	/**
	 * @return A mensagem personalizada a ser enviada junto com os convites
	 */
	public String getMensagem() {
		return mensagem;
	}

	/**
	 * @param mensagem Define a mensagem personalizada a ser enviada junto com os convites
	 */
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	/**
	 * @return A validade do convite
	 */
	public String getValidade() {
		return validade;
	}

	/**
	 * @param validade Define a validade do convite
	 */
	public void setValidade(String validade) {
		this.validade = validade;
	}

	/**
	 * @return O nivel de acesso do usuario
	 */
	public String getNivelAcesso() {
		return nivelAcesso;
	}

	/**
	 * @param nivelAcesso Define o nivel de acesso do usuario
	 */
	public void setNivelAcesso(String nivelAcesso) {
		this.nivelAcesso = nivelAcesso;
	}

	/**
	 * @return A instituição
	 */
	public String getInstituicao() {
		return instituicao;
	}

	/**
	 * @param instituicao Define a instituição
	 */
	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}

	/**
	 * @return O erro corrido
	 */
	public String getErro() {
		return erro;
	}    

}