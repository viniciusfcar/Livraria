package com.livraria.Livraria.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.livraria.Livraria.enums.StatusPedido;
import com.livraria.Livraria.models.Livro;
import com.livraria.Livraria.models.Pedido;
import com.livraria.Livraria.models.Usuario;
import com.livraria.Livraria.service.LivroService;
import com.livraria.Livraria.service.PedidoService;
import com.livraria.Livraria.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@Autowired
	private LivroService serviceLivro;
	
	@Autowired
	private PedidoService servicePedido;
	
	private static final String GET_MUNICIPIO_URL = "http://apps.widenet.com.br/busca-cep/api/cep/";
		
	@GetMapping("lista_usuarios")
	public ModelAndView listaUsuarios() {
		ModelAndView mv = new ModelAndView("usuario/lista_usuarios");
		mv.addObject("usuarios", service.findAll());
		return mv;
	}
	
	@PostMapping("salvar_usuario")
	public ModelAndView save(@Valid Usuario usuario, BindingResult result) {
		
		System.out.println(usuario.toString());
		
		if(result.hasErrors()) {
			return cadastroUsuario(usuario);
	    }
		
		service.save(usuario);
		return validacao();
	}
	
	@RequestMapping("cadastro_usuario")
	public ModelAndView cadastroUsuario(Usuario usuario) {
		ModelAndView mv = new ModelAndView("usuario/cadastro_usuario");
		mv.addObject("usuario", usuario);
		return mv;
	}
	
	@GetMapping("delete_usuario/{id}")
	public ModelAndView deleteUsuario(@PathVariable("id") long id) {
		service.delete(id);
		return listaUsuarios();
	}
	
	@GetMapping("alterar_usuario/{id}")
	public ModelAndView update(@PathVariable("id") long id) {
		Usuario usuario = service.findOne(id);
		return cadastroUsuario(usuario);
	}
	
	@GetMapping("lista_livros")
	public ModelAndView lista_livros() {
		ModelAndView mv = new ModelAndView("usuario/lista_livros");
		mv.addObject("livros", serviceLivro.findAll());
		return mv;
	}
	
	@GetMapping("home")
	public ModelAndView home() {
		return new ModelAndView("usuario/home");
	}
	
	@GetMapping("lista_pedidos")
	public ModelAndView lista_pedidos() {
		ModelAndView mv = new ModelAndView("usuario/lista_pedidos");
		mv.addObject("pedidos", servicePedido.findAll());
		mv.addObject("livros", serviceLivro.findAll());
		return mv;
	}
	
	@RequestMapping("adicionar_pedido/{idLivro, idUsuario}")
	public ModelAndView adicionar_pedido(long idLivro, long idUsuario) {
			
		Date d = new Date();
		Pedido pedido = servicePedido.pedidoOpen(idUsuario);
		Livro livro = serviceLivro.findOne(idLivro);
		Usuario usuario = service.findOne(idUsuario);
		
		if(pedido != null) {
			double i;
			pedido.addLivro(livro);
			pedido.setData(d);
			i = pedido.getValor_total();
			pedido.setValor_total(i + livro.getPreco());
			livro.setQuantidade(livro.getQuantidade() - 1);
			livro.setQtd_no_pedido(livro.getQtd_no_pedido() + 1);
			pedido.setQtd_livros(pedido.getQtd_livros() + 1);
			servicePedido.save(pedido);
			
		} else {
			double i;
			pedido = new Pedido();
			pedido.addLivro(livro);
			pedido.setData(d);
			i = pedido.getValor_total();
			pedido.setValor_total(i + livro.getPreco());
			pedido.setStatus(StatusPedido.ABERTO);			
			usuario.addPedido(pedido);
			livro.setQuantidade(livro.getQuantidade() - 1);
			livro.setQtd_no_pedido(livro.getQtd_no_pedido() + 1);
			pedido.setQtd_livros(pedido.getQtd_livros() + 1);
			servicePedido.save(pedido);
		}
		return lista_pedidos();		
		
	}
	
	@RequestMapping("detalhes_pedido/{id}")
	public ModelAndView detalhesPedido(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("usuario/detalhes_pedido");
		Pedido pedido = servicePedido.findOne(id);
		List<Livro> livros = pedido.getLivros();
		
		for(Livro l : livros) {
			mv.addObject("autores", l.getAutores());
		}
		mv.addObject("livros", livros);
		mv.addObject("pedido", pedido);
		return mv;
	}
	
	@RequestMapping("finalizar_pedido/{id}")
	public ModelAndView finalizarPedido(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("usuario/finalizar_pedido");
		Pedido pedido = servicePedido.findOne(id);
		pedido.setStatus(StatusPedido.ENCERRADO);
		servicePedido.save(pedido);

		mv.addObject("livros", pedido.getLivros());
		mv.addObject("pedido", pedido);		
		
		return mv;
	}
	
	@GetMapping("detalhes_livro/{id}")
	public ModelAndView detalhes_livro(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("usuario/detalhes_livro");
		Livro livro = serviceLivro.findOne(id);
		mv.addObject("livro", livro);
		mv.addObject("autores", livro.getAutores());
		
		return mv;
	}
	
	@GetMapping("validacao")
	public ModelAndView validacao() {
		ModelAndView mv = new ModelAndView("usuario/validacao");
		return mv;
	}
	
	@RequestMapping("cep/{cep}")
	public Usuario cep(String cep) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		ParameterizedTypeReference<Usuario> responseType = new ParameterizedTypeReference<Usuario>() {
		};
		
		ResponseEntity<Usuario> responseEntity = restTemplate.exchange(GET_MUNICIPIO_URL + cep +"/.json", HttpMethod.GET, null,
						responseType);
		Usuario usuario = responseEntity.getBody();
		return usuario;
	}
	
}
