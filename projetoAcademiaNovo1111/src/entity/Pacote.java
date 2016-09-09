package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Pacote implements Comparable<Pacote>, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	//@GeneratedValue(strategy=GenerationType.SEQUENCE)//Oracle
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idPacote;
	
	@Column(length=350)
	private String descPacote;
	
	@Column
	private Double valorPacote;

	@OneToMany(mappedBy="pacote", fetch=FetchType.EAGER, cascade={CascadeType.ALL})
	private List<Cliente> clientes;

	public Pacote() {

	}

	public Pacote(Integer idPacote, String descPacote, Double valorPacote,
			List<Cliente> clientes) {
		super();
		this.idPacote = idPacote;
		this.descPacote = descPacote;
		this.valorPacote = valorPacote;
		this.clientes = clientes;
	}

	public Pacote(Integer idPacote, String descPacote, Double valorPacote) {
		super();
		this.idPacote = idPacote;
		this.descPacote = descPacote;
		this.valorPacote = valorPacote;
	}

	@Override
	public String toString() {
		return "Pacote [idPacote=" + idPacote + ", descPacote=" + descPacote
				+ ", valorPacote=" + valorPacote + "]";
	}

	public Integer getIdPacote() {
		return idPacote;
	}

	public void setIdPacote(Integer idPacote) {
		this.idPacote = idPacote;
	}

	public String getDescPacote() {
		return descPacote;
	}

	public void setDescPacote(String descPacote) {
		this.descPacote = descPacote;
	}

	public Double getValorPacote() {
		return valorPacote;
	}

	public void setValorPacote(Double valorPacote) {
		this.valorPacote = valorPacote;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	@Override
	public boolean equals(Object aPacote) {
		Pacote p = (Pacote) aPacote;
		return this.descPacote.equals(p.getDescPacote());
	}

	@Override
	public int hashCode(){
		return descPacote.hashCode();
	}

	@Override
	public int compareTo(Pacote p) {
		return this.idPacote.compareTo(p.getIdPacote());
	}

	public void adicionarCliente(Cliente c) {
		if (clientes == null)
			clientes = new ArrayList<Cliente>();
		clientes.add(c);
	}

}
