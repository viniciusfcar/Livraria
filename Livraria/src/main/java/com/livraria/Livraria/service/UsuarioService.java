package com.livraria.Livraria.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.livraria.Livraria.models.Role;
import com.livraria.Livraria.models.Usuario;
import com.livraria.Livraria.repository.RoleRepository;
import com.livraria.Livraria.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private RoleRepository role;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public List<Usuario> findAll() {
		return repository.findAll();
	}
	
	public Usuario findOne(long id) {
		return repository.getOne(id);
	}
	
	public void delete(long id) {
		repository.deleteById(id);
	}
	
	public void save(Usuario usuario) {
		usuario.setSenha( passwordEncoder.encode(usuario.getPassword() ));
		Role r = role.getOne((long) 2);
		usuario.addRole(r);
		System.out.println(r.toString());
		repository.save(usuario);
	}
	
	public void update(long id) {
		
	}
	
	public Usuario getUsuarioLogado(@AuthenticationPrincipal Usuario usuario){
		usuario.getId();
		return usuario; 
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails user = repository.findByUsername(username);
		
		org.springframework.security.core.userdetails.User userFinal = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getPermissoes(user));
		System.out.println(userFinal.getAuthorities());
		return userFinal;
	}
	
	private Collection<? extends GrantedAuthority> getPermissoes(UserDetails user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
			
		Set<Role> permissoes = ((Usuario) user).getRole();
		for( Role r : permissoes ) {
			 authorities.add( new SimpleGrantedAuthority(r.getNome().toUpperCase() ) );
		}
		
		
		return authorities;
	}
	
}
