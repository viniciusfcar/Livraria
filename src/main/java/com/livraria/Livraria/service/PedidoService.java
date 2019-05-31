package com.livraria.Livraria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.livraria.Livraria.enums.StatusPedido;
import com.livraria.Livraria.models.Pedido;
import com.livraria.Livraria.models.Usuario;
import com.livraria.Livraria.repository.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repository;
	
	@Autowired
	private UsuarioService serviceUsuario;
	
	public List<Pedido> findAll() {
		return repository.findAll();
	}
	
	public Pedido findOne(long id) {
		return repository.getOne(id);
	}
	
	public void save(Pedido pedido) {
		repository.saveAndFlush(pedido);
	}
	
	public void update(long id) {
		
	}
	
	public void delete(long id) {
		repository.deleteById(id);
	}
	
	public Pedido pedidoOpen(@PathVariable("id") long id) {
		Usuario u = serviceUsuario.findOne(id);
		Pedido aux = null;
		List<Pedido> pedidos = u.getPedidos();
		for(Pedido p : pedidos) {
			if(p.getStatus() == StatusPedido.ABERTO) {
				aux = p;
			}
		}
		
		return aux;
	}
}
