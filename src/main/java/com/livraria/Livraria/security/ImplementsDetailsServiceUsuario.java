package com.livraria.Livraria.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.livraria.Livraria.models.Usuario;
import com.livraria.Livraria.repository.UsuarioRepository;

@Repository
public class ImplementsDetailsServiceUsuario implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository repositury;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = repositury.findByUsername(email);;
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuário não encontrado!");
		}
		return usuario;
	}

}