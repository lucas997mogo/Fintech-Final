package br.com.fiap.fintech.controller;

import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.DaoFactory;
import br.com.fiap.fintech.dao.ClienteDao;
import br.com.fiap.fintech.model.Cliente;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@WebServlet("/clientes")
public class ClienteServlet extends HttpServlet {

    private ClienteDao clienteDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        clienteDao = DaoFactory.getClienteDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String acao = req.getParameter("acao");
        System.out.println("Ação: " + acao);

        if (acao != null) {
            switch (acao) {
                case "cadastrar":
                    try {
                        cadastrar(req, resp);
                    } catch (DBException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "editar":
                    try {
                        editar(req, resp);
                    } catch (DBException e) {
                        req.setAttribute("erro", "Erro ao editar o cliente no POST");
                        e.printStackTrace();
                    }
                    break;
                case "excluir":
                    try {
                        excluir(req, resp);
                    } catch (DBException e) {
                        req.setAttribute("erro", "Erro ao excluir o cliente");
                        e.printStackTrace();
                    }
                    break;
                default:
                    req.setAttribute("erro", "Ação inválida");
            }
        } else {
            req.setAttribute("erro", "Ação não especificada");
        }
    }

    private void excluir(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, DBException {
        int codigo = Integer.parseInt(req.getParameter("codigoExcluir"));
        try {
            clienteDao.removerCliente(codigo);
            req.setAttribute("mensagem", "Cliente removido com sucesso!");
        } catch (DBException e) {
            req.setAttribute("erro", "Erro ao excluir o cliente");
        }
        listar(req, resp);
    }

    private void cadastrar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, DBException {
        try {
            // Captura a data e hora exatas no momento da requisição
            LocalDate now = LocalDate.now();
            String dtDeCadastro = now.toString();  // A data será formatada automaticamente como "yyyy-MM-dd"

            // Captura os outros parâmetros enviados pelo formulário
            String nome = req.getParameter("nome");
            String email = req.getParameter("email");
            String cpf = req.getParameter("cpf");
            String rg = req.getParameter("rg");
            String dtNascimentoStr = req.getParameter("dt_nascimento");

            // Verifica os dados capturados
            System.out.println("Dados capturados: ");
            System.out.println("Nome: " + nome);
            System.out.println("Email: " + email);
            System.out.println("CPF: " + cpf);
            System.out.println("RG: " + rg);
            System.out.println("Data de nascimento (str): " + dtNascimentoStr);


            if (nome == null || nome.isEmpty()) {
                req.setAttribute("erro", "Nome não pode ser vazio.");
                req.getRequestDispatcher("cadastro-cliente.jsp").forward(req, resp);
                return;
            }

            if (email == null || email.isEmpty()) {
                req.setAttribute("erro", "Email não pode ser vazio.");
                req.getRequestDispatcher("cadastro-cliente.jsp").forward(req, resp);
                return;
            }

            if (cpf == null || cpf.isEmpty()) {
                req.setAttribute("erro", "CPF não pode ser vazio.");
                req.getRequestDispatcher("cadastro-cliente.jsp").forward(req, resp);
                return;
            }

            if (dtNascimentoStr != null && !dtNascimentoStr.isEmpty()) {
                try {
                    LocalDate dtNascimento = LocalDate.parse(dtNascimentoStr);

                    // Criação do objeto Cliente
                    Cliente cliente = new Cliente(0, dtDeCadastro, nome, email, cpf, rg, dtNascimento);

                    // Log para verificar o objeto cliente
                    System.out.println("Cliente a ser cadastrado: " + cliente);

                    // Chama o DAO para salvar no banco
                    clienteDao.cadastrarCliente(cliente);

                    System.out.println("Nome: " + cliente.getNome());
                    System.out.println("Email: " + cliente.getEmail());


                    req.setAttribute("mensagem", "Cliente cadastrado com sucesso!");
                } catch (DateTimeParseException e) {
                    req.setAttribute("erro", "Erro ao processar a data de nascimento. Formato esperado: yyyy-MM-dd.");
                    req.getRequestDispatcher("cadastro-cliente.jsp").forward(req, resp);
                    return;
                }
            } else {
                req.setAttribute("erro", "Data de nascimento não pode ser vazia.");
                req.getRequestDispatcher("cadastro-cliente.jsp").forward(req, resp);
                return;
            }
        } catch (DBException db) {
            db.printStackTrace();
            req.setAttribute("erro", "Erro ao cadastrar cliente no banco de dados.");
            req.getRequestDispatcher("cadastro-cliente.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro nos dados do cliente. Por favor, valide os dados.");
            req.getRequestDispatcher("cadastro-cliente.jsp").forward(req, resp);
        }


        req.getRequestDispatcher("cadastro-cliente.jsp").forward(req, resp);
    }






    private void editar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, DBException {
        try {
            String idClienteStr = req.getParameter("id_cliente");
            if (idClienteStr == null || idClienteStr.isEmpty()) {
                throw new IllegalArgumentException("id_cliente não informado.");
            }
            
            int idCliente = Integer.parseInt(idClienteStr);
            String nome = req.getParameter("nome");
            String email = req.getParameter("email");
            String cpf = req.getParameter("cpf");
            String rg = req.getParameter("rg");
            String dtNascimentoStr = req.getParameter("dt_nascimento");
            System.out.println("ID Cliente recebido: " + idCliente);  // Verificação do valor

            System.out.println("[DEBUG] Parâmetros recebidos para edição:");
            System.out.println("ID Cliente: " + idCliente);
            System.out.println("Nome: " + nome);
            System.out.println("Email: " + email);
            System.out.println("CPF: " + cpf);
            System.out.println("RG: " + rg);
            System.out.println("Data de Nascimento: " + dtNascimentoStr);

            LocalDate dtNascimento = LocalDate.parse(dtNascimentoStr);
            Cliente cliente = new Cliente(idCliente, nome, email, cpf, rg, dtNascimento);

            System.out.println("[DEBUG] Cliente para atualizar: " + cliente);

            clienteDao.atualizarCliente(cliente);

            req.setAttribute("msg", "Cliente atualizado com sucesso!");
            listar(req, resp);

        } catch (Exception e) {
            req.setAttribute("erro", "Erro ao editar o cliente NO EDITAR.");
            e.printStackTrace();
            req.getRequestDispatcher("editar-cliente.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String acao = req.getParameter("acao");

        if (acao != null) {
            switch (acao) {
                case "listar":
                    try {
                        listar(req, resp);
                    } catch (DBException e) {
                        req.setAttribute("erro", "Erro ao listar clientes");
                        e.printStackTrace();
                    }
                    break;
                case "abrir-form":
                    try {
                        abrirForm(req, resp);
                    } catch (DBException e) {
                        req.setAttribute("erro", "Erro ao abrir o formulário");
                        e.printStackTrace();
                    }
                    break;
                case "abrir-form-cadastro":
                    try {
                        abrirFormCadastro(req, resp);
                    } catch (DBException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                default:
                    req.setAttribute("erro", "Ação inválida");
            }
        } else {
            req.setAttribute("erro", "Ação não especificada");
        }
    }

    private void abrirFormCadastro(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, DBException {

        List<Cliente> lista = clienteDao.listarCliente();
        req.setAttribute("clientes", lista);
        req.getRequestDispatcher("cadastro-cliente.jsp").forward(req, resp);

    }

    protected void abrirForm(HttpServletRequest req, HttpServletResponse resp) throws DBException, ServletException, IOException {
        String idParam = req.getParameter("id_cliente");
        if (idParam == null || idParam.isEmpty()) {
            req.setAttribute("erro", "ID do cliente não foi informado para edição.");
            req.getRequestDispatcher("lista-cliente.jsp").forward(req, resp);
            return;
        }

        int idCliente;
        try {
            idCliente = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            req.setAttribute("erro", "ID do cliente inválido.");
            req.getRequestDispatcher("lista-cliente.jsp").forward(req, resp);
            return;
        }

        Cliente cliente = clienteDao.buscarCliente(idCliente);
        req.setAttribute("cliente", cliente);
        req.getRequestDispatcher("editar-cliente.jsp").forward(req, resp);
    }


    private void listar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, DBException {
        List<Cliente> lista = clienteDao.listarCliente();
        req.setAttribute("clientes", lista);  // Use "clientes" for list
        req.getRequestDispatcher("lista-cliente.jsp").forward(req, resp);  // Ensure page name is consistent
    }
}
