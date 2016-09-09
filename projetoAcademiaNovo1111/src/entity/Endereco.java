package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	//@GeneratedValue(strategy=GenerationType.SEQUENCE)//Oracle
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer IdEndereco;
	
	@Column(length = 40)
	private String rua;
	
	@Column(length = 40)
	private String bairro;
	
	@Column(length = 40)
	private String cidade;
	
	@Column(length = 10)
	private String cep;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="mat_Cliente")
	private Cliente cliente;

	
	public Endereco() {
	}

	public Endereco(Integer idEndereco, String rua, String bairro,
			String cidade, String cep) {
		super();
		IdEndereco = idEndereco;
		this.rua = rua;
		this.bairro = bairro;
		this.cidade = cidade;
		this.cep = cep;
	}

	public Endereco(Integer idEndereco, String rua, String bairro,
			String cidade, Cliente cliente) {
		super();
		IdEndereco = idEndereco;
		this.rua = rua;
		this.bairro = bairro;
		this.cidade = cidade;
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Endereco [IdEndereco=" + IdEndereco + ", rua=" + rua
				+ ", bairro=" + bairro + ", cidade=" + cidade + "]";
	}

	public Integer getIdEndereco() {
		return IdEndereco;
	}

	public void setIdEndereco(Integer idEndereco) {
		IdEndereco = idEndereco;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

}
