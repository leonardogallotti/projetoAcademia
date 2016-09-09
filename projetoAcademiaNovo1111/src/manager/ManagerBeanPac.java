package manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.message.Message;
import org.primefaces.model.chart.PieChartModel;

import entity.Cliente;
import entity.Pacote;
import persistence.ClienteDao;
import persistence.PacoteDao;

@ManagedBean(name="mbPac")
@ViewScoped
public class ManagerBeanPac implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Pacote>  listPacote;

	private Cliente cliente;
	
	private Pacote pacote;
	
	private String descPacote = "";
	
	
	public ManagerBeanPac() {
		buscarPacote();
	}
	
		
	@PostConstruct
	public void init(){
		pacote = new Pacote(); 
		listPacote = new PacoteDao().findAll();		
	}
	
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Pacote> getListPacote() {
		Collections.sort(listPacote);	
		return listPacote;
	}

	public void setListPacote(List<Pacote> listPacote) {
		this.listPacote = listPacote;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
		
	public Pacote getPacote() {
		return pacote;
	}

	public void setPacote(Pacote pacote) {
		this.pacote = pacote;
	}
	
	public String getDescPacote() {
		return descPacote;
	}

	public void setDescPacote(String descPacote) {
		this.descPacote = descPacote;
	}

	
	public void criar(){
	FacesContext fc = FacesContext.getCurrentInstance();
	
	try {
		new PacoteDao().create(pacote);
		pacote = new Pacote();		
		fc.addMessage("form7", new FacesMessage("Pacote Criado com Sucesso"));
		
	} catch (Exception e) {
		fc.addMessage("form7", new FacesMessage("Error:" + e.getMessage()));
	}
	
	}
	
	

	public void editar(){
		FacesContext fc = FacesContext.getCurrentInstance();
		
		try {
			new PacoteDao().update(pacote);						
			fc.addMessage("form6", new FacesMessage("Pacote atualizado com Sucesso"));
		
		} catch (Exception e) {
			fc.addMessage("form6", new FacesMessage("Error:" + e.getMessage()));
		}
		finally{
			init();
		}
		}
		
		
	public void excluir(){
		FacesContext fc = FacesContext.getCurrentInstance();
		
		try {
			new PacoteDao().delete(pacote);						
			fc.addMessage("form6", new FacesMessage("Pacote excluido com Sucesso"));
		
		} catch (Exception e) {
			fc.addMessage("form6", new FacesMessage("Error:" + e.getMessage()));
		}
		finally{
			init();
		}
		}
	
	public void buscarPacote(){
		listPacote = new PacoteDao().findByNome(descPacote);
	}
	
	
	
		
	}
	
	

