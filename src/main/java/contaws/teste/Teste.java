package contaws.teste;

import java.util.Date;
import java.util.List;

import contaws.dao.ContaDao;
import contaws.dao.MovimentoDao;
import contaws.modelo.Conta;
import contaws.modelo.Movimento;

public class Teste {

	public static void main(String[] args) {
		extrato();
	}

	public static void criarConta() {
		Conta c = new Conta("43.000-5", "376-5", "CC", 10.00, 100.00);
		ContaDao dao = new ContaDao();
		dao.criarConta(c);
	}
	
	public static void deposito() {
		ContaDao dao = new ContaDao();
		Conta c = dao.buscarConta("43.000-5", "376-5");
		Movimento m = new Movimento(new Date(), "D", 100);
		m.setConta(c);
		MovimentoDao mDao = new MovimentoDao();
		mDao.deposito(m);
	}
	
	public static void saque() {
		ContaDao dao = new ContaDao();
		Conta c = dao.buscarConta("43.000-5", "376-5");
		Movimento m = new Movimento(new Date(), "S", 10);
		m.setConta(c);
		MovimentoDao mDao = new MovimentoDao();
		mDao.saque(m);
	}
	
	public static void extrato() {
		ContaDao dao = new ContaDao();
		Conta c = dao.buscarConta("43.000-5", "376-5");
		MovimentoDao mDao = new MovimentoDao();
		List<Movimento> ext = mDao.extrato(c);
		
		for (Movimento m : ext) {
			System.out.println(
					(m.getTipo().equalsIgnoreCase("D"))?
							"Depósito - " + m.getValor():
							"Saque - " + m.getValor());
		}
		System.out.println("Saldo: " + c.getSaldo());
		System.out.println("Limite: " + c.getLimite());
	}

}
