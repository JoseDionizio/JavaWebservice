package contaws.dao;

import java.util.List;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import contaws.dao.utils.HibernateUtil;
import contaws.modelo.Conta;
import contaws.modelo.Movimento;

public class MovimentoDao {

	public void deposito(Movimento movimento){
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(movimento);
		
		Conta conta = movimento.getConta();
		conta.setSaldo(conta.getSaldo() + movimento.getValor());
		
		session.update(conta);
		
		session.getTransaction().commit();
		session.close();
	}
	
	
	public void saque(Movimento movimento){
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(movimento);
		
		Conta conta = movimento.getConta();
		conta.setSaldo(conta.getSaldo() - movimento.getValor());
		
		session.update(conta);
		
		session.getTransaction().commit();
		session.close();
	}
	
	public List<Movimento> extrato(Conta c) {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Movimento.class);
		criteria.add(Restrictions.eq("conta", c));
		
		List<Movimento> movimentos = (List<Movimento>) criteria.list();
		session.close();
		return movimentos;
	}
	
}
