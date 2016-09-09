package entity;

import javax.persistence.*;

@Entity
public class Usuario {

	@Id
	//@GeneratedValue(strategy=GenerationType.SEQUENCE)//Oracle
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idUsuario;

	@Column(length=35, unique=true)
	private String login;

	@Column(length = 50)
	private String senha;

	@Column
	//@Column(columnDefinition="enum('adm','usu')")
	private String perfil;

	public Usuario() {

	}

	public Usuario(Integer idUsuario, String login, String senha, String perfil) {
		super();
		this.idUsuario = idUsuario;
		this.login = login;
		this.senha = senha;
		this.perfil = perfil;
	}

	
	
	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

}