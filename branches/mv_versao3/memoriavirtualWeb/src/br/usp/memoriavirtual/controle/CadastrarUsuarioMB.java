package br.usp.memoriavirtual.controle;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpServletRequest;

import br.usp.memoriavirtual.modelo.entidades.Acesso;
import br.usp.memoriavirtual.modelo.entidades.Usuario;
import br.usp.memoriavirtual.modelo.fachadas.ModeloException;
import br.usp.memoriavirtual.modelo.fachadas.remoto.CadastrarUsuarioRemote;
import br.usp.memoriavirtual.modelo.fachadas.remoto.MemoriaVirtualRemote;
import br.usp.memoriavirtual.utils.MensagensDeErro;
import br.usp.memoriavirtual.utils.ValidacoesDeCampos;

public class CadastrarUsuarioMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2202578392589624271L;
	@EJB
	private MemoriaVirtualRemote memoriaVirtualEJB;
	@EJB
	private CadastrarUsuarioRemote cadastrarUsuarioEJB;
	private String id = "";
	private String email = "";
	private String nomeCompleto = "";
	private String telefone = "";
	private String senha = "";
	private String confirmacaoSenha = "";
	private Usuario convite = null;

	public CadastrarUsuarioMB() {

	}

	@PostConstruct
	public void inicializar() {
		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();

		Usuario convite = (Usuario) request.getSession()
				.getAttribute("usuario");
		this.convite = convite;
		this.email = convite.getEmail();
	}

	public String completarCadastro() {

		this.validateId();
		this.validateEmail();
		this.validateNomeCompleto();
		this.validateSenha();
		this.validateConfirmacaoSenha();

		Usuario usuario = new Usuario(this.id, this.email, this.nomeCompleto,
				this.telefone, this.senha);

		if (!FacesContext.getCurrentInstance().getMessages().hasNext()) {

			String validacaoConvite = this.convite.getId();

			try {
				cadastrarUsuarioEJB.cadastrarUsuario(usuario, validacaoConvite);
			} catch (ModeloException e) {
				usuario = null;
				MensagensDeErro
						.getErrorMessage("convite_invalido", "resultado");
			} catch (RuntimeException e) {
				usuario = null;
				MensagensDeErro.getErrorMessage("erro_cadastramento",
						"resultado");
			}

			if (usuario != null) {
				usuario = usuario.clone();
				HttpServletRequest request = (HttpServletRequest) FacesContext
						.getCurrentInstance().getExternalContext().getRequest();
				request.getSession().setAttribute("usuario", usuario);

				/*
				 * Coloca a lista de acessos do usuario no sessao para ser
				 * utilizados na verificação antes de renderizar o menu.
				 */
				List<Acesso> listaAcessos = memoriaVirtualEJB
						.listarAcessos(usuario);

				request.getSession().setAttribute("acessos", listaAcessos);
			}

			this.id = "";
			this.email = "";
			this.nomeCompleto = "";
			this.telefone = "";
			this.senha = "";
			this.confirmacaoSenha = "";
			this.convite = null;

			MensagensDeErro.getSucessMessage("cadastro_concluido", "resultado");

		}
		return "falhou";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void validateId(AjaxBehaviorEvent event) {
		this.validateId();
	}

	public void validateId() {

		FacesContext context = FacesContext.getCurrentInstance();
		String bundleName = "mensagens";
		ResourceBundle bundle = context.getApplication().getResourceBundle(
				context, bundleName);

		if (this.id.equals("")) {
			String[] argumentos = { bundle.getString("id") };
			MensagensDeErro.getErrorMessage("campo_vazio", argumentos,
					"validacaoId");
		} else if (this.id.length() < 4) {
			String[] argumentos = { bundle.getString("id"),
					bundle.getString("id_minimo") };
			MensagensDeErro.getErrorMessage("tamanho_minimo", argumentos,
					"validacaoId");
		} else if (!memoriaVirtualEJB
				.verificarDisponibilidadeIdUsuario(this.id)) {
			String[] argumentos = { "id" };
			MensagensDeErro.getErrorMessage("ja_cadastrado", argumentos,
					"validacaoId");
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void validateEmail(AjaxBehaviorEvent event) {
		this.validateEmail();
	}

	public void validateEmail() {

		FacesContext context = FacesContext.getCurrentInstance();
		String bundleName = "mensagens";
		ResourceBundle bundle = context.getApplication().getResourceBundle(
				context, bundleName);

		if (this.email.equals("")) {
			String[] argumentos = { bundle.getString("email") };
			MensagensDeErro.getErrorMessage("campo_vazio", argumentos,
					"validacaoEmail");
		} else if (!ValidacoesDeCampos.validarFormatoEmail(this.email)) {
			String[] argumentos = { bundle.getString("email") };
			MensagensDeErro.getErrorMessage("formato_invalido", argumentos,
					"validacaoEmail");
		} else if (!memoriaVirtualEJB.verificarDisponibilidadeEmail(this.email)
				&& !this.email.equals(this.convite.getEmail())) {
			String[] argumentos = { "email" };
			MensagensDeErro.getErrorMessage("ja_cadastrado", argumentos,
					"validacaoEmail");
		}
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void validateSenha(AjaxBehaviorEvent event) {
		this.validateSenha();
	}

	public void validateSenha() {

		FacesContext context = FacesContext.getCurrentInstance();
		String bundleName = "mensagens";
		ResourceBundle bundle = context.getApplication().getResourceBundle(
				context, bundleName);

		if (this.senha.equals("")) {
			String[] argumentos = { bundle.getString("senha") };
			MensagensDeErro.getErrorMessage("campo_vazio", argumentos,
					"validacaoSenha");
		} else if (this.senha.length() < 6) {
			String[] argumentos = { bundle.getString("senha"),
					bundle.getString("senha_minima") };
			MensagensDeErro.getErrorMessage("tamanho_minimo", argumentos,
					"validacaoSenha");
		}
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public void validateNomeCompleto(AjaxBehaviorEvent event) {
		this.validateNomeCompleto();
	}

	public void validateNomeCompleto() {

		FacesContext context = FacesContext.getCurrentInstance();
		String bundleName = "mensagens";
		ResourceBundle bundle = context.getApplication().getResourceBundle(
				context, bundleName);

		if (this.nomeCompleto.equals("")) {
			String[] argumentos = { bundle.getString("nome_completo") };
			MensagensDeErro.getErrorMessage("campo_vazio", argumentos,
					"validacaoNomeCompleto");
		}
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}

	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	}

	public void validateConfirmacaoSenha(AjaxBehaviorEvent event) {
		this.validateConfirmacaoSenha();
	}

	public void validateConfirmacaoSenha() {

		FacesContext context = FacesContext.getCurrentInstance();
		String bundleName = "mensagens";
		ResourceBundle bundle = context.getApplication().getResourceBundle(
				context, bundleName);

		if (confirmacaoSenha.equals("")) {
			String[] argumentos = { bundle.getString("confirmacao_senha") };
			MensagensDeErro.getErrorMessage("campo_vazio", argumentos,
					"validacaoConfirmacaoSenha");
		} else if (!this.confirmacaoSenha.equals(this.senha)) {
			String[] argumentos = { bundle.getString("confirmacao_senha"),
					bundle.getString("senha") };
			MensagensDeErro.getErrorMessage("confirmacao_errado", argumentos,
					"validacaoConfirmacaoSenha");
		}
	}

}
