package persistence;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import entity.Cliente;
import entity.Endereco;

public class ClienteDao {
	Session session;
	Transaction transaction;
	Criteria criteria;
	Query query;

	
	public void create(Cliente c, Endereco e) throws Exception {
		session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		session.save(c);
		e.setCliente(c);
		session.save(e);
		transaction.commit();
		session.close();

	}

	public void update(Cliente c) throws Exception {
		session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		session.update(c);
		transaction.commit();
		session.close();
	}

	public void delete(Cliente c) throws Exception {

		session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		session.delete(c);
		transaction.commit();
		session.close();

	}

	public List<Cliente> findAll() {
		session = HibernateUtil.getSessionFactory().openSession();
		List<Cliente> listCliente = session.createQuery("from Cliente").list();
		session.close();
		return listCliente;
	}

	public List<Cliente> findAllCriteria() {
		session = HibernateUtil.getSessionFactory().openSession();
		criteria = session.createCriteria(Cliente.class);
		List<Cliente> listCliente = criteria.list();
		return listCliente;
	}
	
	public Cliente findByCode(Integer cod) {
		session = HibernateUtil.getSessionFactory().openSession();
		Cliente cliente = (Cliente) session.get(Cliente.class, cod);
		session.close();
		return cliente;
	}

	public Cliente findByCodeCriteria(Integer cod) {
		session = HibernateUtil.getSessionFactory().openSession();
		criteria = session.createCriteria(Cliente.class);
		Cliente cliente = (Cliente) criteria.add(Restrictions.idEq(cod)).uniqueResult();
		return cliente;
	}

	
	public List<Cliente> findByNome(String nome) {
		session = HibernateUtil.getSessionFactory().openSession();
		criteria = session.createCriteria(Cliente.class);
		criteria.add(Restrictions.like("nome", nome + "%"));
		List<Cliente> lista = criteria.list();
		session.close();
		return lista;

	}

	public List<Cliente> findByMat(Integer matCliente) {
		session = HibernateUtil.getSessionFactory().openSession();
		criteria = (Criteria) session.createCriteria(Cliente.class);
		criteria.add(Restrictions.sqlRestriction("matCliente  like'"+ matCliente + "%'"));
		List<Cliente> lista = criteria.list();
		session.close();
		return lista;

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	public static void main(String[] args) {
//
//		try {
//			
//			Cliente c = new Cliente(null, "testData2", "a", new Date("26/01/2016"), null, new Date("26/03/2016"),"44444444");
//			Endereco e = new Endereco(null, "Castro Menezes", "cordovil", "RJ","111111");
//			new ClienteDao().create(c, e);
//
//			System.out.println("Gravado");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}

}