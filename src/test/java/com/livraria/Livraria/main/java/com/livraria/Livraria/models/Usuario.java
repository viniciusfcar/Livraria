package com.livraria.Livraria.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Usuario implements  UserDetails{
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany
	private Set<Role> role = new HashSet<Role>();
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private long id;
	
	@Column(nullable=true, length=100)
	@NotBlank(message = "Nome é uma informação obrigatória.")
	private String nome;
	
	@Column(nullable=true, length=60)
	@NotBlank(message = "Username é uma informação obrigatória.")
	private String username;
	
	@Column(nullable=true)
	@NotBlank(message = "Senha é uma informação obrigatória.")
	private String senha;
	
	@Column(nullable=true)
	@NotBlank(message = "Cep é uma informação obrigatória.")
	private String cep;
	
	@Column(nullable=true, length=100)
	@NotBlank(message = "Rua é uma informação obrigatória.")
	private String rua;
	
	@Column(nullable=true, length=100)
	@NotBlank(message = "Bairro é uma informação obrigatória.")
	private String bairro;
	
	@Column(nullable=true, length=100)
	@NotBlank(message = "Cidade é uma informação obrigatória.")
	private String cidade;
	
	@Column(nullable=true, length=100)
	@NotBlank(message = "Esatdo é uma informação obrigatória.")
	private String estado;
	
	@OneToMany
	@JoinColumn(name="id_pedido")
	private List<Pedido> pedidos;
	
	public Usuario(String nome, String username, String senha, String cep, String rua, String bairro, String cidade, String estado) {
		super();
		this.nome = nome;
		this.username = username;
		this.senha = senha;
		this.cep = cep;
		this.rua = rua;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
	}
	
	public Usuario() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	public void addPedido(Pedido pedido) {
		pedidos.add(pedido);
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<>();
		authorities.addAll(getRole());
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.senha;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public Set<Role> getRole() {
		return role;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}
	
	public void addRole(Role r) {
		role.add(r);
	}

	@Override
	public String toString() {
		return "Usuario [role=" + role + ", id=" + id + ", nome=" + nome + ", username=" + username + ", senha=" + senha
				+ ", cep=" + cep + ", rua=" + rua + ", bairro=" + bairro + ", cidade=" + cidade + ", estado=" + estado
				+ "]";
	}	
	
	
}
