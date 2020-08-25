package spring.consertaaqui.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

@Entity
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	private String nome;
	private String email;
	private String contato1;	
	private String contato2;
	private String dataNascimento;
	private String rua;
	private String senha;
	private String bairro;
	private String zona;
	private String descricao;
	private String genero;
	private String tipoUsuario;
	
	@Lob
	private byte[] imagem;
	
    @OneToMany(mappedBy = "usuario")
    private List<Servico> servico;
    
    @OneToMany(mappedBy = "usuario")
	private List<Solicitacao> solicitacoes;
	
	@OneToMany(mappedBy = "usuario")
	private List<Contrato> Contratos;
	
	@OneToMany(mappedBy = "usuario")
	private List<Avaliacao> avaliacoes;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContato1() {
		return contato1;
	}
	public void setContato1(String contato1) {
		this.contato1 = contato1;
	}
	public String getContato2() {
		return contato2;
	}
	public void setContato2(String contato2) {
		this.contato2 = contato2;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getZona() {
		return zona;
	}
	public void setZona(String zona) {
		this.zona = zona;
	}	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public boolean isPrestador() {
		return true;
	}
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario =  tipoUsuario;
	}
	public byte[] getImagem() {
		return imagem;
	}
	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}
	public List<Servico> getServico() {
		return servico;
	}
	public void setServico(List<Servico> servico) {
		this.servico = servico;
	}
	public List<Solicitacao> getSolicitacoes() {
		return solicitacoes;
	}
	public void setSolicitacoes(List<Solicitacao> solicitacoes) {
		this.solicitacoes = solicitacoes;
	}
	public List<Contrato> getContratos() {
		return Contratos;
	}
	public void setContratos(List<Contrato> contratos) {
		Contratos = contratos;
	}
	public List<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}
	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}
	
}
