CREATE TABLE MULTIMIDIA (ID BIGINT NOT NULL, CONTENT BYTEA, CONTENTTYPE VARCHAR(255), DESCRICAO VARCHAR(255), NOME VARCHAR(255), THUMB BYTEA, CONTAINERMULTIMIDIA_ID BIGINT, PRIMARY KEY (ID));
CREATE TABLE CONTAINERMULTIMIDIA (ID BIGINT NOT NULL, PRIMARY KEY (ID));
CREATE TABLE INSTITUICAO (ID BIGINT NOT NULL, ADMINISTRADORPROPRIEDADE VARCHAR(255), ALTITUDE VARCHAR(255), CAIXAPOSTAL VARCHAR(255), CEP VARCHAR(255), CIDADE VARCHAR(255), EMAIL VARCHAR(255), ENDERECO VARCHAR(255), ESTADO VARCHAR(255), IDENTIFICACAOPROPRIETARIO VARCHAR(255), LATITUDE VARCHAR(255), LEGISLACAOINCIDENTE VARCHAR(255), LOCALIDADE VARCHAR(255), LONGITUDE VARCHAR(255), NOME VARCHAR(255), PAIS VARCHAR(255), PROTECAOEXISTENTE VARCHAR(255), SINTESEHISTORICA VARCHAR(255), TELEFONE VARCHAR(255), TIPOPROPRIEDADE VARCHAR(255), URL VARCHAR(255), VALIDADE BOOLEAN, CONTAINERMULTIMIDIA_ID BIGINT, PRIMARY KEY (ID));
CREATE TABLE BEMPATRIMONIAL_TITULOS (ID BIGINT NOT NULL, TIPO VARCHAR(255), VALOR VARCHAR(255), BEMPATRIMONIAL_ID BIGINT, PRIMARY KEY (ID));
CREATE TABLE ITEMAUDITORIA (ID BIGINT NOT NULL, ATRIBUTOSIGNIFICATIVO VARCHAR(255), DATA DATE, NOTAS VARCHAR(255), TIPOACAO INTEGER, AUTORACAO_ID VARCHAR(255), PRIMARY KEY (ID));
CREATE TABLE AUTOR (ID BIGINT NOT NULL, ATIVIDADE VARCHAR(255), CODINOME VARCHAR(255), NASCIMENTO VARCHAR(255), NOME VARCHAR(255), OBITO VARCHAR(255), SOBRENOME VARCHAR(255), PRIMARY KEY (ID));
CREATE TABLE AUTORIA (TIPOAUTORIA INTEGER NOT NULL, BEMPATRIMONIAL_ID BIGINT NOT NULL, AUTOR_ID BIGINT NOT NULL, PRIMARY KEY (TIPOAUTORIA, BEMPATRIMONIAL_ID, AUTOR_ID));
CREATE TABLE APROVACAO (ID BIGINT NOT NULL, CHAVEESTRANGEIRA VARCHAR(255), DATA DATE, EXPIRACAO DATE, TABELAESTRANGEIRA VARCHAR(255), APROVADOR VARCHAR(255), PRIMARY KEY (ID));
CREATE TABLE USUARIO (ID VARCHAR(255) NOT NULL, ADMINISTRADOR BOOLEAN, ATIVO BOOLEAN, EMAIL VARCHAR(255) UNIQUE, NOMECOMPLETO VARCHAR(255), SENHA VARCHAR(255), TELEFONE VARCHAR(255), VALIDADE DATE, PRIMARY KEY (ID));
CREATE TABLE ASSUNTO (ASSUNTO VARCHAR(255) NOT NULL, PRIMARY KEY (ASSUNTO));
CREATE TABLE GRUPO (ID VARCHAR(255) NOT NULL, PRIMARY KEY (ID));
CREATE TABLE ACESSO (VALIDADE BOOLEAN, GRUPO VARCHAR(255) NOT NULL, INSTITUICAO BIGINT NOT NULL, USUARIO VARCHAR(255) NOT NULL, PRIMARY KEY (GRUPO, INSTITUICAO, USUARIO));
CREATE TABLE DESCRITOR (DESCRITOR VARCHAR(255) NOT NULL, PRIMARY KEY (DESCRITOR));
CREATE TABLE BEMPATRIMONIAL (ID BIGINT NOT NULL, DTYPE VARCHAR(31), COMPLEMENTO VARCHAR(255), CARACTERISTICASFISTECEXEC VARCHAR(255), COLECAO VARCHAR(255), CONTEUDO VARCHAR(255), EXTERNO BOOLEAN, IDMIDIA VARCHAR(255), LATITUDE VARCHAR(255), LOCALIZACAOFISICA VARCHAR(255), LONGITUDE VARCHAR(255), MEIODEACESSO VARCHAR(255), NUMERODEREGISTRO VARCHAR(255), TIPODOBEMPATRIMONIAL VARCHAR(255), ESTCONSERVACAO VARCHAR(255), ESTPRESERCACAO VARCHAR(255), NOTAESTCONSERVACAO VARCHAR(255), CONDICOESACESSO VARCHAR(255), CONDICOESREPRODUCAO VARCHAR(255), DATARETORNO VARCHAR(255), DISPONIBILIDADE VARCHAR(255), LEGISLACAO VARCHAR(255), NOTASUSOAPROVEITAMENTO VARCHAR(255), PROTECAO VARCHAR(255), PROTETORAINSTITUICAO VARCHAR(255), DADOSDOCTRANSACAO VARCHAR(255), HISTORICO VARCHAR(255), INSTRUMENTOPESQUISA VARCHAR(255), PRIMEIROPROPRIETARIO VARCHAR(255), TIPOAQUISICAO VARCHAR(255), VALORVENALTRANSACAO VARCHAR(255), ANO VARCHAR(255), EDICAO VARCHAR(255), LOCAL VARCHAR(255), OUTRASRESPONSABILIDADES VARCHAR(255), INSTITUICAO_ID BIGINT, CONTAINERMULTIMIDIA_ID BIGINT, PRIMARY KEY (ID));
CREATE TABLE BEMARQUEOLOGICO (ID BIGINT NOT NULL, AGUAPROXIMO VARCHAR(255), ALTURA VARCHAR(255), AREATOTAL VARCHAR(255), COMPRIMENTO VARCHAR(255), CONDICAOTOPOGRAFICA VARCHAR(255), EXPOSICAO VARCHAR(255), LARGURA VARCHAR(255), NOTAS VARCHAR(255), OUTROS VARCHAR(255), POSSUIVEGETACAO VARCHAR(255), PROFUNDIDADE VARCHAR(255), SITIODAPAISAGEM VARCHAR(255), USOATUAL VARCHAR(255), PRIMARY KEY (ID));
CREATE TABLE BEMNATURAL (ID BIGINT NOT NULL, CARACTERISTICASAMBIENTAIS VARCHAR(255), MEIOANTROPICO VARCHAR(255), RELEVO VARCHAR(255), PRIMARY KEY (ID));
CREATE TABLE BEMARQUITETONICO (ID BIGINT NOT NULL, ALCOVA BOOLEAN, ALTURAFACHFRONTAL VARCHAR(255), ALTURAFACHPOSTERIOR VARCHAR(255), ALTURATOTAL VARCHAR(255), AREATOTAL VARCHAR(255), CONDICAOTOPOGRAFIA VARCHAR(255), LARGURA VARCHAR(255), NUMERODEAMBIENTES INTEGER, NUMERODEPAVIMENTOS INTEGER, OUTROS VARCHAR(255), PEDIREITOTERREO VARCHAR(255), PORAO BOOLEAN, PROFUNDIDADE VARCHAR(255), SOTAO BOOLEAN, TIPOPEDIREITO VARCHAR(255), USO VARCHAR(255), PRIMARY KEY (ID));
CREATE TABLE BEMPATRIMONIAL_ASSUNTO (ASSUNTO_ID VARCHAR(255) NOT NULL, BEMPATRIMONIAL_ID BIGINT NOT NULL, PRIMARY KEY (ASSUNTO_ID, BEMPATRIMONIAL_ID));
CREATE TABLE BEMPATRIMONIAL_DESCRITOR (DESCRITOR_ID VARCHAR(255) NOT NULL, BEMPATRIMONIAL_ID BIGINT NOT NULL, PRIMARY KEY (DESCRITOR_ID, BEMPATRIMONIAL_ID));
CREATE TABLE BEMPATRIMONIAL_INTERVENCOES (DATA VARCHAR(255), DESCRICAO VARCHAR(255), RESPONSAVEL VARCHAR(255), BEMPATRIMONIAL_ID BIGINT);
CREATE TABLE BEMPATRIMONIAL_PESQUISADORES (DATAPESQUISA VARCHAR(255), NOME VARCHAR(255), NOTASPESQUISADOR VARCHAR(255), BEMPATRIMONIAL_ID BIGINT);
CREATE TABLE BEMPATRIMONIAL_FONTESINFORMACAO (BEMPATRIMONIAL_ID BIGINT, FONTESINFORMACAO VARCHAR(255));
CREATE TABLE BEMPATRIMONIAL_BENSRELACIONADOS (BEMPATRIMONIAL_ID BIGINT NOT NULL, BENSRELACIONADOS_ID BIGINT NOT NULL, PRIMARY KEY (BEMPATRIMONIAL_ID, BENSRELACIONADOS_ID));
ALTER TABLE MULTIMIDIA ADD CONSTRAINT FK_MULTIMIDIA_CONTAINERMULTIMIDIA_ID FOREIGN KEY (CONTAINERMULTIMIDIA_ID) REFERENCES CONTAINERMULTIMIDIA (ID);
ALTER TABLE INSTITUICAO ADD CONSTRAINT FK_INSTITUICAO_CONTAINERMULTIMIDIA_ID FOREIGN KEY (CONTAINERMULTIMIDIA_ID) REFERENCES CONTAINERMULTIMIDIA (ID);
ALTER TABLE BEMPATRIMONIAL_TITULOS ADD CONSTRAINT FK_BEMPATRIMONIAL_TITULOS_BEMPATRIMONIAL_ID FOREIGN KEY (BEMPATRIMONIAL_ID) REFERENCES BEMPATRIMONIAL (ID);
ALTER TABLE ITEMAUDITORIA ADD CONSTRAINT FK_ITEMAUDITORIA_AUTORACAO_ID FOREIGN KEY (AUTORACAO_ID) REFERENCES USUARIO (ID);
ALTER TABLE AUTORIA ADD CONSTRAINT FK_AUTORIA_AUTOR_ID FOREIGN KEY (AUTOR_ID) REFERENCES AUTOR (ID);
ALTER TABLE AUTORIA ADD CONSTRAINT FK_AUTORIA_BEMPATRIMONIAL_ID FOREIGN KEY (BEMPATRIMONIAL_ID) REFERENCES BEMPATRIMONIAL (ID);
ALTER TABLE APROVACAO ADD CONSTRAINT FK_APROVACAO_APROVADOR FOREIGN KEY (APROVADOR) REFERENCES USUARIO (ID);
ALTER TABLE ACESSO ADD CONSTRAINT FK_ACESSO_USUARIO FOREIGN KEY (USUARIO) REFERENCES USUARIO (ID);
ALTER TABLE ACESSO ADD CONSTRAINT FK_ACESSO_GRUPO FOREIGN KEY (GRUPO) REFERENCES GRUPO (ID);
ALTER TABLE ACESSO ADD CONSTRAINT FK_ACESSO_INSTITUICAO FOREIGN KEY (INSTITUICAO) REFERENCES INSTITUICAO (ID);
ALTER TABLE BEMPATRIMONIAL ADD CONSTRAINT FK_BEMPATRIMONIAL_CONTAINERMULTIMIDIA_ID FOREIGN KEY (CONTAINERMULTIMIDIA_ID) REFERENCES CONTAINERMULTIMIDIA (ID);
ALTER TABLE BEMPATRIMONIAL ADD CONSTRAINT FK_BEMPATRIMONIAL_INSTITUICAO_ID FOREIGN KEY (INSTITUICAO_ID) REFERENCES INSTITUICAO (ID);
ALTER TABLE BEMARQUEOLOGICO ADD CONSTRAINT FK_BEMARQUEOLOGICO_ID FOREIGN KEY (ID) REFERENCES BEMPATRIMONIAL (ID);
ALTER TABLE BEMNATURAL ADD CONSTRAINT FK_BEMNATURAL_ID FOREIGN KEY (ID) REFERENCES BEMPATRIMONIAL (ID);
ALTER TABLE BEMARQUITETONICO ADD CONSTRAINT FK_BEMARQUITETONICO_ID FOREIGN KEY (ID) REFERENCES BEMPATRIMONIAL (ID);
ALTER TABLE BEMPATRIMONIAL_ASSUNTO ADD CONSTRAINT FK_BEMPATRIMONIAL_ASSUNTO_ASSUNTO_ID FOREIGN KEY (ASSUNTO_ID) REFERENCES ASSUNTO (ASSUNTO);
ALTER TABLE BEMPATRIMONIAL_ASSUNTO ADD CONSTRAINT FK_BEMPATRIMONIAL_ASSUNTO_BEMPATRIMONIAL_ID FOREIGN KEY (BEMPATRIMONIAL_ID) REFERENCES BEMPATRIMONIAL (ID);
ALTER TABLE BEMPATRIMONIAL_DESCRITOR ADD CONSTRAINT FK_BEMPATRIMONIAL_DESCRITOR_DESCRITOR_ID FOREIGN KEY (DESCRITOR_ID) REFERENCES DESCRITOR (DESCRITOR);
ALTER TABLE BEMPATRIMONIAL_DESCRITOR ADD CONSTRAINT FK_BEMPATRIMONIAL_DESCRITOR_BEMPATRIMONIAL_ID FOREIGN KEY (BEMPATRIMONIAL_ID) REFERENCES BEMPATRIMONIAL (ID);
ALTER TABLE BEMPATRIMONIAL_INTERVENCOES ADD CONSTRAINT FK_BEMPATRIMONIAL_INTERVENCOES_BEMPATRIMONIAL_ID FOREIGN KEY (BEMPATRIMONIAL_ID) REFERENCES BEMPATRIMONIAL (ID);
ALTER TABLE BEMPATRIMONIAL_PESQUISADORES ADD CONSTRAINT FK_BEMPATRIMONIAL_PESQUISADORES_BEMPATRIMONIAL_ID FOREIGN KEY (BEMPATRIMONIAL_ID) REFERENCES BEMPATRIMONIAL (ID);
ALTER TABLE BEMPATRIMONIAL_FONTESINFORMACAO ADD CONSTRAINT FK_BEMPATRIMONIAL_FONTESINFORMACAO_BEMPATRIMONIAL_ID FOREIGN KEY (BEMPATRIMONIAL_ID) REFERENCES BEMPATRIMONIAL (ID);
ALTER TABLE BEMPATRIMONIAL_BENSRELACIONADOS ADD CONSTRAINT FK_BEMPATRIMONIAL_BENSRELACIONADOS_BEMPATRIMONIAL_ID FOREIGN KEY (BEMPATRIMONIAL_ID) REFERENCES BEMPATRIMONIAL (ID);
ALTER TABLE BEMPATRIMONIAL_BENSRELACIONADOS ADD CONSTRAINT FK_BEMPATRIMONIAL_BENSRELACIONADOS_BENSRELACIONADOS_ID FOREIGN KEY (BENSRELACIONADOS_ID) REFERENCES BEMPATRIMONIAL (ID);
CREATE SEQUENCE APROVACAO_SEQ START WITH 1;
CREATE SEQUENCE CONTAINERMULTIMIDIA_SEQ START WITH 1;
CREATE SEQUENCE ITEMAUDITORIA_SEQ START WITH 1;
CREATE SEQUENCE MULTIMIDIA_SEQ START WITH 1;
CREATE SEQUENCE TITULO_SEQ START WITH 1;
CREATE SEQUENCE INSTITUICAO_SEQ START WITH 1;
CREATE SEQUENCE AUTOR_SEQ START WITH 1;
CREATE SEQUENCE BEMPATRIMONIAL_SEQ START WITH 1;
/* Usuario admin padr�o para o desenvolvimento do sistema:
ID:mvirtual
Email:mvirtual@usp.br
senha:mvirtual
*/
INSERT INTO USUARIO values( 'mvirtual', true, true, 'mvirtual@usp.br','Mem�ria Virtual',
'8D1E3B49C3A725414FBED43AD7E0A480DEA6220A83DF3B10C4496270FC5A1E6328732550F4AC8C4F6ADE0EAE7F82DC9CF3219D724E6369AA044FD630B9C5E178',
'(16)7777-7777',null);

INSERT INTO GRUPO values('CATALOGADOR');
INSERT INTO GRUPO values('REVISOR');
INSERT INTO GRUPO values('GERENTE');



