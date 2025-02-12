package br.com.fiap.fintech.controller;

import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.DaoFactory;
import br.com.fiap.fintech.dao.ContaDao;
import br.com.fiap.fintech.model.Cliente;
import br.com.fiap.fintech.model.Conta;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/contas")
public class ContaServlet extends HttpServlet {

    private ContaDao contaDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        contaDao = DaoFactory.getContaDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String acao = req.getParameter("acao");
        if (acao != null) {
            switch (acao) {
                case "cadastrar":
                    try {
                        cadastrar(req, resp);
                    } catch (DBException e) {
                        req.setAttribute("erro", "Erro ao cadastrar a conta");
                        e.printStackTrace();
                        req.getRequestDispatcher("cadastro-conta.jsp").forward(req, resp);
                    }
                    break;
                case "editar":
                    try {
                        editar(req, resp);
                    } catch (DBException e) {
                        req.setAttribute("erro", "Erro ao editar a conta");
                        e.printStackTrace();
                        req.getRequestDispatcher("editar-conta.jsp").forward(req, resp);
                    }
                    break;
                case "excluir":
                    try {
                        excluir(req, resp);
                    } catch (DBException e) {
                        req.setAttribute("erro", "Erro ao excluir a conta");
                        e.printStackTrace();
                        req.getRequestDispatcher("lista-contas.jsp").forward(req, resp);
                    }
                    break;
                default:
                    req.setAttribute("erro", "Ação inválida");
                    req.getRequestDispatcher("lista-contas.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("erro", "Ação não especificada");
            req.getRequestDispatcher("lista-contas.jsp").forward(req, resp);
        }
    }

    private void excluir(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, DBException {
        try {
            int idConta = Integer.parseInt(req.getParameter("codigoExcluir"));
            contaDao.removerConta(idConta);
            req.setAttribute("mensagem", "Conta removida com sucesso!");
        } catch (DBException e) {
            req.setAttribute("erro", "Erro ao excluir a conta");
            e.printStackTrace();
        }
        listar(req, resp);
    }

    private void cadastrar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, DBException {
        try {
            LocalDate dtDeAbertura = LocalDate.now();
            String tipoConta = req.getParameter("tipo_conta");
            double saldo = Double.parseDouble(req.getParameter("saldo"));

            String clienteIdParam = req.getParameter("id_cliente");
            if (clienteIdParam == null || clienteIdParam.isEmpty()) {
                req.setAttribute("erro", "ID do cliente não foi especificado.");
                req.getRequestDispatcher("cadastro-conta.jsp").forward(req, resp);
                return;
            }

            int clienteId = Integer.parseInt(clienteIdParam);

            if (tipoConta == null || tipoConta.isEmpty()) {
                req.setAttribute("erro", "Tipo de conta não pode ser vazio.");
                req.getRequestDispatcher("cadastro-conta.jsp").forward(req, resp);
                return;
            }

            Conta conta = new Conta(0, tipoConta, saldo, dtDeAbertura, clienteId);
            contaDao.cadastrarConta(conta);
            req.setAttribute("mensagem", "Conta cadastrada com sucesso!");
            req.getRequestDispatcher("cadastro-conta.jsp").forward(req, resp);
        } catch (DBException db) {
            req.setAttribute("erro", "Erro ao cadastrar conta no banco de dados.");
            db.printStackTrace();
            req.getRequestDispatcher("cadastro-conta.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("erro", "Erro nos dados da conta. Por favor, valide os dados.");
            e.printStackTrace();
            req.getRequestDispatcher("cadastro-conta.jsp").forward(req, resp);
        }
    }

    private void editar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, DBException {
        try {
            int idConta = Integer.parseInt(req.getParameter("id_conta"));
            String tipoConta = req.getParameter("tipo_conta");
            double saldo = Double.parseDouble(req.getParameter("saldo"));
            LocalDate dtDeAbertura = LocalDate.parse(req.getParameter("dt_de_abertura"));
            int clienteId = Integer.parseInt(req.getParameter("cliente_id_cliente"));

            Conta conta = new Conta(idConta, tipoConta, saldo, dtDeAbertura, clienteId);
            contaDao.atualizarConta(conta);
            req.setAttribute("msg", "Conta atualizada com sucesso!");
            listar(req, resp);
        } catch (Exception e) {
            req.setAttribute("erro", "Erro ao editar a conta.");
            e.printStackTrace();
            req.getRequestDispatcher("editar-conta.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String acao = req.getParameter("acao");

        if (acao != null) {
            switch (acao) {
                case "listar":
                    listar(req, resp);
                    break;
                case "abrir-form":
                    try {
                        abrirForm(req, resp);
                    } catch (DBException e) {
                        req.setAttribute("erro", "Erro ao abrir o formulário");
                        e.printStackTrace();
                        req.getRequestDispatcher("lista-contas.jsp").forward(req, resp);
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
                    req.getRequestDispatcher("lista-contas.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("erro", "Ação não especificada");
            req.getRequestDispatcher("lista-contas.jsp").forward(req, resp);
        }
    }

    protected void abrirFormCadastro(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, DBException {
        List<Conta> lista = contaDao.listarContas(0);
        req.setAttribute("conta", lista);
        req.getRequestDispatcher("cadastro-conta.jsp").forward(req, resp);

    }

    protected void abrirForm(HttpServletRequest req, HttpServletResponse resp) throws DBException, ServletException, IOException {
        String idParam = req.getParameter("id_conta");
        if (idParam == null || idParam.isEmpty()) {
            req.setAttribute("erro", "ID da conta não foi informado para edição.");
            req.getRequestDispatcher("lista-contas.jsp").forward(req, resp);
            return;
        }

        int idConta;
        try {
            idConta = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            req.setAttribute("erro", "ID da conta inválido.");
            req.getRequestDispatcher("lista-contas.jsp").forward(req, resp);
            return;
        }

        Conta conta = contaDao.buscarConta(idConta);
        req.setAttribute("conta", conta);
        req.getRequestDispatcher("editar-conta.jsp").forward(req, resp);
    }

    protected void listar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String idClienteParam = req.getParameter("id_cliente");
            if (idClienteParam == null || idClienteParam.isEmpty()) {
                req.setAttribute("erro", "ID do cliente não foi especificado.");
                req.getRequestDispatcher("lista-contas.jsp").forward(req, resp);
                return;
            }

            int idCliente = Integer.parseInt(idClienteParam);
            List<Conta> contas = contaDao.listarContas(idCliente);
            req.setAttribute("contas", contas);
            req.getRequestDispatcher("lista-contas.jsp").forward(req, resp);
        } catch (DBException e) {
            req.setAttribute("erro", "Erro ao listar contas");
            e.printStackTrace();
            req.getRequestDispatcher("lista-contas.jsp").forward(req, resp);
        }
    }
}
