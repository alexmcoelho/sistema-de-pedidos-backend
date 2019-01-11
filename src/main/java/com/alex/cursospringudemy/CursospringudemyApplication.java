package com.alex.cursospringudemy;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alex.cursospringudemy.domain.Categoria;
import com.alex.cursospringudemy.domain.Cidade;
import com.alex.cursospringudemy.domain.Cliente;
import com.alex.cursospringudemy.domain.Endereco;
import com.alex.cursospringudemy.domain.Estado;
import com.alex.cursospringudemy.domain.ItemPedido;
import com.alex.cursospringudemy.domain.Pagamento;
import com.alex.cursospringudemy.domain.PagamentoComBoleto;
import com.alex.cursospringudemy.domain.PagamentoComCartao;
import com.alex.cursospringudemy.domain.Pedido;
import com.alex.cursospringudemy.domain.Produto;
import com.alex.cursospringudemy.domain.enums.EstadoPagamento;
import com.alex.cursospringudemy.domain.enums.TipoCliente;
import com.alex.cursospringudemy.repositories.CategoriaRepository;
import com.alex.cursospringudemy.repositories.CidadeRepository;
import com.alex.cursospringudemy.repositories.ClienteRepository;
import com.alex.cursospringudemy.repositories.EnderecoRepository;
import com.alex.cursospringudemy.repositories.EstadoRepository;
import com.alex.cursospringudemy.repositories.ItemPedidoRepository;
import com.alex.cursospringudemy.repositories.PagamentoRepository;
import com.alex.cursospringudemy.repositories.PedidoRepository;
import com.alex.cursospringudemy.repositories.ProdutoRepository;

@SpringBootApplication
public class CursospringudemyApplication implements CommandLineRunner{

	
	private CategoriaRepository categoriaRepository;
	private ProdutoRepository produtoRepository;
	private EstadoRepository estadoRepository;
	private CidadeRepository cidadeRepository;
	private ClienteRepository clienteRepository;
	private EnderecoRepository enderecoRepository;
	private PedidoRepository pedidoRepository;
	private PagamentoRepository pagamentoRepository;
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
    public CursospringudemyApplication(CategoriaRepository categoriaRepository,
    						ProdutoRepository produtoRepository,
    						EstadoRepository estadoRepository,
    						CidadeRepository cidadeRepository,
    						ClienteRepository clienteRepository,
    						EnderecoRepository enderecoRepository,
    						PedidoRepository pedidoRepository,
    						PagamentoRepository pagamentoRepository,
    						ItemPedidoRepository itemPedidoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.produtoRepository = produtoRepository;
        this.estadoRepository = estadoRepository;
        this.cidadeRepository = cidadeRepository;
        this.clienteRepository = clienteRepository;
        this.enderecoRepository = enderecoRepository;
        this.pedidoRepository = pedidoRepository;
        this.pagamentoRepository = pagamentoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
    }
	
	public static void main(String[] args) {
		SpringApplication.run(CursospringudemyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mosue", 80.00);
		
		//associando os produtos com as categorias
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		//associando as categorias com os produtos
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		//SALVANDO ESTADO E CIDADE
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade cid1 = new Cidade(null, "Uberlândia", est1);
		Cidade cid2 = new Cidade(null, "São Paulo", est2);
		Cidade cid3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(cid1));
		est2.getCidades().addAll(Arrays.asList(cid2, cid3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));
		
		//SALVANDO CLIENTE E ENDERECO
		Cliente c1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "12345678912", TipoCliente.PESSOAFISICA);
		c1.getTelefones().addAll(Arrays.asList("35333817", "88152506"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "88400000", c1, cid1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "88400000", c1, cid2);
		
		c1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(c1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		//SALVADO PEDIDOS...
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), c1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), c1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		c1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		//SALVANDO ITENS DE PEDIDO
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		//fazendo os pedidos conhecer os itens de pedido
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		//fazendo os produtos conhecer os itens de pedido
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}
	
	

}

