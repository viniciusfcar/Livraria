package com.livraria.Livraria.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import com.livraria.Livraria.enums.StatusPedido;

@Entity
public class Pedido {
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private long id;
	
	@Column(nullable=true)
	private Date data;
	
	@Column(nullable=true, length=10)
	private double valor_total;
	
	@Column
	private int qtd_livros;
	
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	
	@OneToMany
	@JoinColumn(name="id_livro")
	private List<Livro> livros = new ArrayList<>();
	
	@Column(nullable=true)
	private StatusPedido status;

	public Pedido(Date data, double valor_total) {
		super();
		this.data = data;
		this.valor_total = valor_total;
		this.qtd_livros = 0;
	}
	
	public Pedido() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public double getValor_total() {
		return valor_total;
	}

	public void setValor_total(double valor_total) {
		this.valor_total = valor_total;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}
	
	public void addLivro(Livro livro) {
		livros.add(livro);
	}

	public StatusPedido getStatus() {
		return status;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}

	public int getQtd_livros() {
		return qtd_livros;
	}

	public void setQtd_livros(int qtd_livros) {
		this.qtd_livros = qtd_livros;
	}
	
	
	
}
