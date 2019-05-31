package com.livraria.Livraria.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.livraria.Livraria.models.Administrador;
import com.livraria.Livraria.models.Role;
import com.livraria.Livraria.models.Usuario;
import com.livraria.Livraria.repository.AdministradorRepository;
import com.livraria.Livraria.repository.RoleRepository;

@Service
public class AdministradorService implements UserDetailsService{
	
	@Autowired
	private AdministradorRepository repository;
	
	@Autowired
	private RoleRepository role;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
		
	public List<Administrador> findAll() {
		return repository.findAll();
	}
	
	public Administrador findOne(long id) {
		return repository.getOne(id);
	}
	
	public void delete(long id) {
		repository.deleteById(id);
	}
	
	public void save(Administrador administrador) {
		administrador.setSenha( passwordEncoder.encode(administrador.getPassword() ));
		Role r = role.getOne((long) 1);
		administrador.addRole(r);
		repository.save(administrador);
	}
	
	public void update(long id) {
		
	}

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		UserDetails adm = repository.findByUsername(login);
		
		org.springframework.security.core.userdetails.User userFinal = new org.springframework.security.core.userdetails.User(adm.getUsername(), adm.getPassword(), getPermissoes(adm));
		System.out.println(userFinal.getAuthorities());
		return userFinal;
	}
	
	private Collection<? extends GrantedAuthority> getPermissoes(UserDetails user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
			
		Set<Role> permissoes = ((Administrador) user).getRole();
		for( Role r : permissoes ) {
			 authorities.add( new SimpleGrantedAuthority(r.getNome().toUpperCase() ) );
		}
		
		
		return authorities;
	}
}
