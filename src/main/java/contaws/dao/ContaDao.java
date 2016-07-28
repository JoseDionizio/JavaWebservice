package contaws.dao;

import contaws.dao.utils.HibernateUtil;
import contaws.modelo.Conta;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

public class ContaDao {

	public void criarConta(Conta conta){
	
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(conta);		
		session.getTransaction().commit();
		session.close();
	}
	
	public Conta buscarConta(String numero, String agencia){
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Criteria c = session.createCriteria(Conta.class);
		c.add(Restrictions.eq("numero", numero));
		c.add(Restrictions.eq("agencia", agencia));
		
		Conta conta = (Conta) c.uniqueResult();
		session.close();
		
		return conta;
	}
	
	
}
