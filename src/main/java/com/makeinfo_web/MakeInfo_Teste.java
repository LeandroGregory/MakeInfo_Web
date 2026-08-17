package com.makeinfo_web;

import com.makeinfo_web.dao.ItemVendaDAO;
import com.makeinfo_web.dao.VendaDAO;
import com.makeinfo_web.model.Cargo;
import com.makeinfo_web.model.Cliente;
import com.makeinfo_web.model.Fornecedor;
import com.makeinfo_web.model.Funcionario;
import com.makeinfo_web.model.ItemVenda;
import com.makeinfo_web.model.Produto;
import com.makeinfo_web.model.Usuario;
import com.makeinfo_web.model.Venda;
import com.makeinfo_web.service.CargoService;
import com.makeinfo_web.service.ClienteService;
import com.makeinfo_web.service.FornecedorService;
import com.makeinfo_web.service.FuncionarioService;
import com.makeinfo_web.service.ProdutoService;
import com.makeinfo_web.service.UsuarioService;
import com.makeinfo_web.service.VendaService;
import java.time.LocalDate;
import java.util.List;

/**
 * Classe de testes
 *
 * @author Leandro Gregory
 */
public class MakeInfo_Teste {

    public static void main(String[] args) {
        Cargo cargo = testarCargo();
        Fornecedor fornecedor = testarFornecedor();
        Funcionario funcionario = testarFuncionario(cargo);
        Cliente cliente = testarCliente();
        Produto produto = testarProduto(fornecedor);
        Usuario usuario = testarUsuario(funcionario);
        Venda venda = testarVenda(cliente, funcionario, produto);

        limpar(venda, usuario, produto, cliente, funcionario, fornecedor);

         System.out.println ("TESTES FINALIZADOS");
    }

    /* Metodos de teste de gravação de dados*/
    private static Cargo testarCargo() {
         System.out.println ("TESTE - CARGO");
        CargoService service = new CargoService();

        Cargo salvo = service.cadastrar("Vendedor Teste", 2000.0, 5);
        log("Cargo cadastrado", salvo.getId(), salvo.getDescricao());

        Cargo buscado = service.buscarPorId(salvo.getId());
        System.out.println("Total de cargos cadastrados: " + service.listarTodos().size());

        return buscado;
    }

    
    private static Fornecedor testarFornecedor() {
         System.out.println("TESTE - FORNECEDOR");
        FornecedorService service = new FornecedorService();

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNomeFornecedor("Fornecedor Teste");
        fornecedor.setTelefoneFornecedor("123456789");
        fornecedor.setLogradouroFornecedor("Av. teste");
        fornecedor.setNumeroFornecedor(200);
        fornecedor.setUfFornecedor("MG");

        Fornecedor salvo = service.cadastrar(fornecedor);
        log("Fornecedor cadastrado", salvo.getId(), salvo.getNomeFornecedor());

        return service.buscarPorId(salvo.getId());
    }

   
    private static Funcionario testarFuncionario(Cargo cargo) {
         System.out.println("TESTE - FUNCIONARIO");
        FuncionarioService service = new FuncionarioService();

        Funcionario funcionario = new Funcionario();
        funcionario.setNomeFuncionario("Funcionario Teste");
        funcionario.setCpfFuncionario("0123456");
        funcionario.setTelefoneFuncionario("31123456");
        funcionario.setCargo(cargo);

        Funcionario salvo = service.cadastrar(funcionario);
        log("Funcionario cadastrado", salvo.getId(), salvo.getNomeFuncionario());

        return service.buscarPorId(salvo.getId());
    }

    private static Cliente testarCliente() {
         System.out.println("TESTE - CLIENTE");
        ClienteService service = new ClienteService();

        Cliente cliente = new Cliente();
        cliente.setNomeCliente("Cliente Teste");
        cliente.setCpfCliente("12345678900");
        cliente.setLogradouroCliente("Rua de Teste");
        cliente.setNumEnderecoCliente(100);
        cliente.setTelefoneCliente("31123456");

        Cliente salvo = service.cadastrar(cliente);
        log("Cliente cadastrado", salvo.getId(), salvo.getNomeCliente());

        Cliente buscado = service.buscarPorId(salvo.getId());
        buscado.setTelefoneCliente("31988888888");

        Cliente atualizado = service.atualizar(buscado);
        System.out.println("Cliente atualizado, novo telefone: " + atualizado.getTelefoneCliente());

        return atualizado;
    }

    private static Produto testarProduto(Fornecedor fornecedor) {
         System.out.println("TESTE - PRODUTO");
        ProdutoService service = new ProdutoService();

        Produto produto = new Produto();
        produto.setNomeProduto("Produto Teste");
        produto.setDescricaoProduto("Descricao do produto de teste");
        produto.setPreco("99.90");
        produto.setQuantidadeProduto(50);
        produto.setFornecedor(fornecedor);

        Produto salvo = service.cadastrar(produto);
        log("Produto cadastrado", salvo.getId(), salvo.getNomeProduto());

        return service.buscarPorId(salvo.getId());
    }

    private static Usuario testarUsuario(Funcionario funcionario) {
         System.out.println ("TESTE - USUARIO");
        UsuarioService service = new UsuarioService();

        Usuario usuario = new Usuario();
        usuario.setLogin("usuario.teste");
        usuario.setSenha("senha123");
        usuario.setCpf(funcionario.getCpfFuncionario());
        usuario.setNivel("VENDEDOR");
        usuario.setFuncionario(funcionario);

        Usuario salvo = service.cadastrar(usuario);
        log("Usuario cadastrado", salvo.getId(), salvo.getLogin());

        Usuario autenticado = service.autenticar("usuario.teste", "senha123");
        System.out.println("Autenticacao (esperado != null): " + autenticado);

        return salvo;
    }

    private static Venda testarVenda(Cliente cliente, Funcionario funcionario, Produto produto) {
         System.out.println ("TESTE - VENDA");
        VendaService service = new VendaService();

        Venda venda = new Venda();
        venda.setDataVenda(LocalDate.now().toString());
        venda.setStatus("ABERTA");
        venda.setTipoPagamento("PIX");
        venda.setNumParcelas(1);
        venda.setCliente(cliente);
        venda.setFuncionario(funcionario);

        ItemVenda item = new ItemVenda();
        item.setProduto(produto);
        item.setQuantidade(2);
        item.setValorUnitario(99.90);

        Venda vendaSalva = service.registrarVendaComItens(venda, List.of(item));
        System.out.println("Venda registrada: id=" + vendaSalva.getId()
                + ", total=" + vendaSalva.getTotalVenda());

        return service.buscarPorId(vendaSalva.getId());
    }

    /* Método de limpeza de dados */
    private static void limpar(Venda venda, Usuario usuario, Produto produto,
            Cliente cliente, Funcionario funcionario, Fornecedor fornecedor) {

         System.out.println ("LIMPANDO DADOS DE TESTE");

        ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
        for (ItemVenda item : itemVendaDAO.listarTodos()) {
            if (item.getVenda() != null && item.getVenda().getId() == venda.getId()) {
                itemVendaDAO.excluir(item.getId());
            }
        }

        new VendaDAO().excluir(venda.getId());
        new UsuarioService().excluir(usuario.getId());
        new ProdutoService().excluir(produto.getId());
        new ClienteService().excluir(cliente.getId());
        new FuncionarioService().excluir(funcionario.getId());
        new FornecedorService().excluir(fornecedor.getId());

        System.out.println("Dados de teste removidos com sucesso.");
    }

    /* Log para reduzir a repetição de código */
    private static void log(String acao, int id, String descricao) {
        System.out.println(acao + ": id=" + id + ", nome=" + descricao);
    }

}
