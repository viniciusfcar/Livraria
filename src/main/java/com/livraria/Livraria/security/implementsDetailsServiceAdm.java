package com.livraria.Livraria.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.livraria.Livraria.models.Administrador;
import com.livraria.Livraria.repository.AdministradorRepository;

@Repository
public class implementsDetailsServiceAdm  implements UserDetailsService{
	
	@Autowired
	private AdministradorRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Administrador adm = repository.findByUsername(login);
		
		if(adm == null) {
			throw new UsernameNotFoundException("Usuário não encontrado!");
		}
		return adm;
	}

}
