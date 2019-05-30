package com.livraria.Livraria.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Livro {
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private long id;
	
	@Column(nullable=true, length=100)
	@NotBlank(message = "Titulo é uma informação obrigatória.")
	private String titulo;
	
	@Column(nullable=true, length=10)
	@NotBlank(message = "Ano é uma informação obrigatória.")
	private String ano;
	
	@Column(nullable=true, length=300)
	@NotBlank(message = "Sinopse é uma informação obrigatória.")
	private String sinopse;
	
	@Column(nullable=true, length=50)
	@NotBlank(message = "Edição é uma informação obrigatória.")
	private String edicao;
	
	@Column(nullable=true, length=50)
	@NotBlank(message = "Peso é uma informação obrigatória.")
	private String peso;
	
	@Column(nullable=true, length=5)
	private double preco;
	
	@Column(nullable=true)
	private int quantidade;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_editora")
	private Editora editora;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="livro_autor")
	private List<Autor> autores = new ArrayList<>();
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_categoria")
	private Categoria categoria;
	
	@Column(nullable=true)
	private int qtd_no_pedido;
	
	public Livro(String titulo, String ano, String sinopse, String edicao, String peso, double preco, int quantidade) {
		this.titulo = titulo;
		this.ano = ano;
		this.sinopse = sinopse;
		this.edicao = edicao;
		this.peso = peso;
		this.preco = preco;
		this.quantidade = quantidade;
		this.qtd_no_pedido = 0;
	}
	
	public Livro() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public String getEdicao() {
		return edicao;
	}

	public void setEdicao(String edicao) {
		this.edicao = edicao;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}
		
	public void addAutor(Autor autor) {
		autores.add(autor);
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public int getQtd_no_pedido() {
		return qtd_no_pedido;
	}

	public void setQtd_no_pedido(int qtd_no_pedido) {
		this.qtd_no_pedido = qtd_no_pedido;
	}
	
	
	
}
