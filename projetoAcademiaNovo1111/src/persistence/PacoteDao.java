package persistence;

import java.util.List;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import entity.Pacote;

public class PacoteDao {

	Session     session;
	Transaction transaction;
	Criteria    criteria;
	Query       query;
	


	public void create(Pacote p) throws Exception{
		session =  HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		session.save(p);
		transaction.commit();
		session.close();
		
}

	public void update(Pacote p) throws Exception{
		session =  HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		session.update(p);
		transaction.commit();
		session.close();
		
}

	public void delete(Pacote p) throws Exception{
		session =  HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		session.delete(p);
		transaction.commit();
		session.close();
		
}

   public List<Pacote> findAll(){
	session = HibernateUtil.getSessionFactory().openSession();
	List<Pacote> listPacote = session.createQuery("from Pacote").list();
	session.close();   
	return listPacote;	   
   }

   public List<Pacote> findAllCriteria(){
	session = HibernateUtil.getSessionFactory().openSession();
	criteria = session.createCriteria(Pacote.class);
	List<Pacote> listPacote = criteria.list();
	return listPacote;	   
   }   
   
   public List<Pacote> findByNome(String descPacote){
	   session = HibernateUtil.getSessionFactory().openSession();
	   criteria = session.createCriteria(Pacote.class);
	   criteria.add(Restrictions.like("descPacote", descPacote + "%"));
	   List<Pacote> lista = criteria.list(); 
	   session.close();
	   return lista;	   
   }
   
   public Pacote findByCode(Integer code){
	   session = HibernateUtil.getSessionFactory().openSession();
	   Pacote p = (Pacote)session.get(Pacote.class, code);
	   session.close();
	   return p;
   }

   public Pacote findByCodeCriteria(Integer code){
	   session = HibernateUtil.getSessionFactory().openSession();
	   criteria = session.createCriteria(Pacote.class);
	   Pacote p = (Pacote) criteria.add(Restrictions.idEq(code)).uniqueResult();
	   return p;
   }
   
   

   public static void main(String[] args) {
	
	   try {
		
		   System.out.println(new PacoteDao().findByNome("j"));
		   
		   
		   
	} catch (Exception e) {
		e.printStackTrace();
	}
	   
   }



}