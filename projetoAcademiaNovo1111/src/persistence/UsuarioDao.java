package persistence;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import entity.Usuario;

public class UsuarioDao {

	Session session;
	Transaction transaction;
	Criteria criteria;
	Query query;

	public void create(Usuario u) throws Exception {

		session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		session.save(u);
		transaction.commit();
		session.close();
	}

	public Usuario logar(Usuario u) throws Exception {

		session = HibernateUtil.getSessionFactory().openSession();
		criteria = session.createCriteria(Usuario.class);
		criteria.add(Restrictions.and(Restrictions.eq("login", u.getLogin()),
				     Restrictions.eq("senha", u.getSenha())));
		Usuario user = (Usuario) criteria.uniqueResult();
		session.close();
		return user;
	}

	public static void main(String[] args) {

		try {

			Usuario u1 = new Usuario(null, "admin", "admin", "adm");
			Usuario u2 = new Usuario(null, "recepcao", "recepcao", "usu");

			UsuarioDao ud = new UsuarioDao();

			ud.create(u1);
			ud.create(u2);

			System.out.println("ok");

		} catch (Exception e) {
			System.out.println("error:" + e.getMessage());
		}
	}

}
