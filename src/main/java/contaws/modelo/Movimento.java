package contaws.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

@XmlRootElement
@Entity
public class Movimento {

	@Id
	@GenericGenerator(name="novo_id", strategy = "increment")
	@GeneratedValue(generator="novo_id")
	private int id;
	
	private Date data;
	private String tipo; // deposito ou saque.
	private double valor;
	
	@ManyToOne
	private Conta conta;
	
	public Movimento() {
		super();
	}

	public Movimento(Date data, String tipo, double valor) {
		super();
		this.data = data;
		this.tipo = tipo;
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	} 
}
