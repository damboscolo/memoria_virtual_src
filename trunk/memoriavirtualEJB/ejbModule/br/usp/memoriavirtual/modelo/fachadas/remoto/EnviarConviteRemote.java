package br.usp.memoriavirtual.modelo.fachadas.remoto;

import javax.ejb.Remote;

@Remote
public interface EnviarConviteRemote {

	public String enviarConvite(String emails, String mensagem, String validade, String instituicao, String nivelAcesso);	
}