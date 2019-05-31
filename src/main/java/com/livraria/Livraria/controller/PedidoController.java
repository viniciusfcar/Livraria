package com.livraria.Livraria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.livraria.Livraria.models.Pedido;
import com.livraria.Livraria.service.LivroService;
import com.livraria.Livraria.service.PedidoService;

@Controller
@RequestMapping("/pedido")
public class PedidoController {
	
	@Autowired
	private PedidoService service;
	
	@Autowired
	private LivroService serviceLivro;
	
	@GetMapping("/lista_pedidos")
	public ModelAndView listaPedidos() {
		ModelAndView mv = new ModelAndView("/pedido/lista_pedidos");
		mv.addObject("pedidos", service.findAll());
		mv.addObject("livros", serviceLivro.findAll());
		return mv;
	}
	
	@PostMapping("/salvar_pedido")
	public ModelAndView save(Pedido pedido) {
		service.save(pedido);
		return listaPedidos();
	}
	
	@RequestMapping("/cadastro_pedido")
	public ModelAndView cadastroPedido(Pedido pedido) {
		ModelAndView mv = new ModelAndView("/pedido/cadastro_pedido");
		mv.addObject("pedido", pedido);
		mv.addObject("livros", serviceLivro.findAll());
		return mv;
	}
	
	@GetMapping("/delete_pedido/{id}")
	public ModelAndView deletePedido(@PathVariable("id") long id) {
		service.delete(id);
		return listaPedidos();
	}
	
	@GetMapping("/alterar_pedido/{id}")
	public ModelAndView update(@PathVariable("id") long id) {
		Pedido pedido = service.findOne(id);
		return cadastroPedido(pedido);
	}
}
