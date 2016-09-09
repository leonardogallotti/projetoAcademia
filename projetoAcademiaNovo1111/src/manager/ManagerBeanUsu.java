package manager;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import entity.Usuario;
import persistence.UsuarioDao;

@ManagedBean(name="mbUsu")
@RequestScoped
public class ManagerBeanUsu implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	private Usuario usuarioLogado;
		
	
	public ManagerBeanUsu() {
		
	}
	
	@PostConstruct
	public void init(){
		usuario = new Usuario();
}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public void verificaLogin(){
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession  session = (HttpSession) fc.getExternalContext().getSession(true);
		try {
			if(session.getAttribute("usuarioLogado")==null){
				fc.getExternalContext().redirect("../login.jsf");	
			}
		} catch (Exception e) {
			fc.addMessage("loginForm", new FacesMessage("error:" + e.getMessage()));
		}
}

	public String logar(){
		FacesContext fc = FacesContext.getCurrentInstance();
		
		try {
			HttpSession  session = (HttpSession) fc.getExternalContext().getSession(true);
			usuarioLogado = new UsuarioDao().logar(usuario);
			usuario = new Usuario();
			
			if(usuarioLogado !=null){
				session.setAttribute("usuarioLogado", usuarioLogado);
				fc.addMessage("loginForm", new FacesMessage("Logado com Sucesso"));
				
				return usuarioLogado.getPerfil() + "/layout.jsf";
			}else{
				session.setAttribute("usuarioLogado", null);
				fc.addMessage("loginForm", new FacesMessage("Login Invalido"));
			}		
		} catch (Exception e) {
		  fc.addMessage("loginForm", new FacesMessage("error:" + e.getMessage()));	
		}
		return null;
	}

	public String logout(){
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
			
			session.setAttribute("usuarioLogado", null);
			session.invalidate();
			
			fc.addMessage("loginForm", new FacesMessage("Logout Realizado"));	
			
		} catch (Exception e) {
			fc.addMessage("loginForm", new FacesMessage("error:" + e.getMessage()));
		}
		 return "/login.jsf" ;
	}


}
