package entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Cliente implements Comparable<Cliente>, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	//@GeneratedValue(strategy=GenerationType.SEQUENCE)//Oracle
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer matCliente;
	
	@Column(length = 50, unique=true)
	private String nome;
	
	//@Column//oracle
	@Column(columnDefinition="enum('a','i')")//so no mysql
	private String ativo;
	
	@Temporal(TemporalType.DATE)
	private Date dataEntrada;
	
	@Column
	private String imagem;
	
	@Temporal(TemporalType.DATE)
	private Date dataPg;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAvaliacao;
	
	@Column
	private String telefone;
	
	
	@OneToOne(mappedBy="cliente",fetch=FetchType.EAGER,cascade={CascadeType.ALL})
	private Endereco endereco;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_pacote")
	private Pacote pacote;
	
	
	public Cliente() {

	}

	public Cliente(Integer matCliente, String nome, String ativo, Date dataEntrada, String imagem, Date dataPg,
			String telefone, Endereco endereco) {
		super();
		this.matCliente = matCliente;
		this.nome = nome;
		this.ativo = ativo;
		this.dataEntrada = dataEntrada;
		this.imagem = imagem;
		this.dataPg = dataPg;
		this.telefone = telefone;
		this.endereco = endereco;
	
	}


	public Cliente(Integer matCliente, String nome, String ativo, Date dataEntrada, String imagem, Date dataPg,
			String telefone) {
		super();
		this.matCliente = matCliente;
		this.nome = nome;
		this.ativo = ativo;
		this.dataEntrada = dataEntrada;
		this.imagem = imagem;
		this.dataPg = dataPg;
		this.telefone = telefone;
	}

		
	public Cliente(Integer matCliente, String nome, String ativo,
			Date dataEntrada, String imagem, Date dataPg, Date dataAvaliacao,
			String telefone, Endereco endereco, Pacote pacote) {
		super();
		this.matCliente = matCliente;
		this.nome = nome;
		this.ativo = ativo;
		this.dataEntrada = dataEntrada;
		this.imagem = imagem;
		this.dataPg = dataPg;
		this.dataAvaliacao = dataAvaliacao;
		this.telefone = telefone;
		this.endereco = endereco;
		this.pacote = pacote;
	}

	@Override
	public String toString() {
		return "Cliente [matCliente=" + matCliente + ", nome=" + nome + ", ativo=" + ativo + ", dataEntrada="
				+ dataEntrada + ", imagem=" + imagem + ", dataPg=" + dataPg + ", telefone="
				+ telefone + ", endereco=" + endereco + "]";
	}

	public Integer getMatCliente() {
		return matCliente;
	}

	public void setMatCliente(Integer matCliente) {
		this.matCliente = matCliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
		
	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	
	public Date getDataPg() {
		return dataPg;
	}

	public void setDataPg(Date dataPg) {
		this.dataPg = dataPg;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public Pacote getPacote() {
		return pacote;
	}

	public void setPacote(Pacote pacote) {
		this.pacote = pacote;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Date getDataAvaliacao() {
		return dataAvaliacao;
	}

	public void setDataAvaliacao(Date dataAvaliacao) {
		this.dataAvaliacao = dataAvaliacao;
	}

	@Override
	public int compareTo(Cliente c) {
		return this.nome.compareTo(c.getNome());
	}
	
	@Override
	public boolean equals(Object aCliente) {
		Cliente c = (Cliente) aCliente;
		return getNome().equals(c.getNome()) ;
	}
	@Override
	public int hashCode() {
		return nome.hashCode();
	}

	
	
}