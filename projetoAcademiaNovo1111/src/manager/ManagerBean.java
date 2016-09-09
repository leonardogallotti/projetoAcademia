package manager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.view.JasperViewer;

import org.joda.time.DateTime;
import org.lavieri.modelutil.cep.WebServiceCep;
import org.primefaces.event.CaptureEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.chart.PieChartModel;

import control.controlEnt;
import persistence.ClienteDao;
import persistence.HibernateUtil;
import persistence.PacoteDao;
import entity.Cliente;
import entity.Endereco;
import entity.Pacote;

@ManagedBean(name ="mb")
@ViewScoped
public class ManagerBean extends controlEnt implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Pacote pacote;
	private Endereco endereco;
	private Cliente cliente;
	private List<Cliente> listCliente;
	
	private String MBcep;

	private Integer matCliente;
	private String nome = "";
	
	

	public ManagerBean() {
		buscarPorNome();
		buscarPorMatricula();
	}

	
	@PostConstruct
	public void init() {
		cliente = new Cliente();
		endereco = new Endereco();
		pacote = new Pacote();
		listCliente = new ClienteDao().findAll();
	}

	public List<Cliente> getListCliente() {
		Collections.sort(listCliente);
		return listCliente;
	}

	public void setListCliente(List<Cliente> listCliente) {
		this.listCliente = listCliente;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getMBcep() {
		return MBcep;
	}

	public void setMBcep(String mBcep) {
		MBcep = mBcep;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public Integer getMatCliente() {
		return matCliente;
	}

	public void setMatCliente(Integer matCliente) {
		this.matCliente = matCliente;
	}


	public void cadastrar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		Date dataAtual = new Date();
		
		try {			
			cliente.setAtivo("a");
			cliente.setDataEntrada(dataAtual);
			cliente.setPacote(new PacoteDao().findByCode(pacote.getIdPacote()));
			new ClienteDao().create(cliente, endereco);					
			fc.addMessage("form1", new FacesMessage("Dados Gravados com sucesso"));
			init();
		} catch (Exception e) {
			fc.addMessage("form1", new FacesMessage("error: " + e.getMessage()));
		} finally {
			init();
			MBcep="";
		}

	}

	
	  public void oncapture(CaptureEvent captureEvent) {
		  FacesContext fc = FacesContext.getCurrentInstance();    
	        
		  try {
	            byte[] arq = captureEvent.getData();
		        cliente.setImagem(cliente.getNome() + ".jpg");
		        ServletContext context = (ServletContext)fc.getExternalContext().getContext();
		        String caminho = context.getRealPath("/img");
		         
	        	FileImageOutputStream fios;
	        	fios = new FileImageOutputStream(new File(caminho + "/" + cliente.getImagem()));
	            fios.write(arq);
	            fios.close();
	            
	        }
	        
		  catch(Exception e) {
	            fc.addMessage("form1", new FacesMessage("error:" + e.getMessage()));
	        }
	  }
		  
  
	  public void excluir() {
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			new ClienteDao().delete(cliente);			
			fc.addMessage("form2", new FacesMessage("Dados excluidos"));

		} catch (Exception e) {
			fc.addMessage("form2", new FacesMessage("ERROR:" + e.getMessage()));
		} finally{
			init();
		}
	}

	public void editar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			
			cliente.setPacote(new PacoteDao().findByCode(pacote.getIdPacote()));
			new ClienteDao().update(cliente);
			fc.addMessage("form2", new FacesMessage("Dados Atualizados"));
		
		} catch (Exception e) {
			fc.addMessage("form2", new FacesMessage("ERROR :" + e.getMessage()));
			e.printStackTrace();
		} finally {
			buscarPorNome();
			init();
		}

	}

	
	public void buscarEndereco() {
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			WebServiceCep search = WebServiceCep.searchCep(MBcep);
			if (search.wasSuccessful()) {
				endereco.setCep(MBcep);
				endereco.setRua(search.getLogradouro());
				endereco.setBairro(search.getBairro());
				endereco.setCidade(search.getCidade());
				fc.addMessage("form1", new FacesMessage("Cep encontrado"));
			} else {
				endereco = new Endereco();
				MBcep = "";
				fc.addMessage("form1", new FacesMessage("Cep não localizado"));
			}
		} catch (Exception e) {
			fc.addMessage("form1", new FacesMessage("ERROR:" + e.getMessage()));
		}

	}

	
	public void pagamento() {
		FacesContext fc = FacesContext.getCurrentInstance();
		Date dataAtualPg = new Date();
		
		try {
				cliente.setDataPg(dataAtualPg);
				cliente.setAtivo("a");
				new ClienteDao().update(cliente);				
				fc.addMessage("form3", new FacesMessage("Pagamaneto Efetuado"));
			
		} catch (Exception e) {			
			fc.addMessage("form3", new FacesMessage("ERROR:" + e.getMessage()));					
		}finally {
			init();
		}

	}
	
	public void entrada() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			 String msg =	controlEnt.controle(cliente);			
		    fc.addMessage("form4", new FacesMessage(msg));
		
		} catch (Exception e) {								
			fc.addMessage("form4", new FacesMessage("Error:" + e.getMessage() ));
		}
	}

	
	public String relatorio() {
		try {
	FacesContext fc = FacesContext.getCurrentInstance();
			
	JasperReport report = JasperCompileManager.compileReport("C:/Temp/relatorio1.jrxml");
	JasperPrint print = JasperFillManager.fillReport(report, null, HibernateUtil.getSessionFactory().openSession().connection());
	JasperExportManager.exportReportToPdfFile(print, "C:/relatorio/relatorio.pdf");
			
	fc.addMessage("form5", new FacesMessage("Relatorio Gerado para C:/RELATORIO"));
		
//	InputStream arquivo = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/relatorio.jasper");
//	byte[] pdf = JasperRunManager.runReportToPdf(arquivo, null, HibernateUtil.getSessionFactory().openSession().connection());
//
//	HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//
//	ServletOutputStream out = response.getOutputStream();
//	out.write(pdf);
//	out.flush();
//	out.close();
//
//	FacesContext.getCurrentInstance().responseComplete();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void agendAvaliacao() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
				new ClienteDao().update(cliente);
				fc.addMessage("form9", new FacesMessage("Agendamento Efetuado"));
			} catch (Exception e) {
			
			fc.addMessage("form9", new FacesMessage("ERROR:" + e.getMessage()));
					
		}finally {
			init();
		}

	}
	
	public void buscarPorNome() {
		listCliente = new ClienteDao().findByNome(nome);
	}

	public void buscarPorMatricula() {
		listCliente = new ClienteDao().findByMat(matCliente);
	}
	
	
	
	
	
	
}