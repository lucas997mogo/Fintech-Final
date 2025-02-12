package br.com.fiap.fintech.controller;

import br.com.fiap.fintech.dao.EnderecoDao;
import br.com.fiap.fintech.dao.TelefoneDao;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.impl.OracleEnderecoDao;
import br.com.fiap.fintech.impl.OracleTelefoneDao;
import br.com.fiap.fintech.model.Endereco;
import br.com.fiap.fintech.model.Telefone;

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

@WebServlet("/cadastro")
public class CadastroTelefoneEnderecoServlet extends HttpServlet {

    private EnderecoDao enderecoDao = new OracleEnderecoDao();
    private TelefoneDao telefoneDao = new OracleTelefoneDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getParameter("acao");

        try {
            if ("cadastrarEndereco".equals(acao)) {
                cadastrarEndereco(request);
            } else if ("editarEndereco".equals(acao)) {
                editarEndereco(request);
            } else if ("excluirEndereco".equals(acao)) {
                excluirEndereco(request);
            } else if ("cadastrarTelefone".equals(acao)) {
                cadastrarTelefone(request);
            } else if ("editarTelefone".equals(acao)) {
                editarTelefone(request);
            } else if ("excluirTelefone".equals(acao)) {
                excluirTelefone(request);
            }
        } catch (DBException e) {
            request.setAttribute("erro", "Erro ao processar a ação: " + e.getMessage());
        }

        response.sendRedirect("listagem.jsp");
    }

    private void cadastrarEndereco(HttpServletRequest request) throws DBException {
        String pais = request.getParameter("pais");
        String estado = request.getParameter("estado");
        String cidade = request.getParameter("cidade");
        String cep = request.getParameter("cep");
        String logradouro = request.getParameter("logradouro");
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));

        Endereco endereco = new Endereco(0, pais, estado, cidade, cep, logradouro, idCliente);
        enderecoDao.cadastrarEndereco(endereco);
    }

    private void editarEndereco(HttpServletRequest request) throws DBException {
        int idEndereco = Integer.parseInt(request.getParameter("idEndereco"));
        String pais = request.getParameter("pais");
        String estado = request.getParameter("estado");
        String cidade = request.getParameter("cidade");
        String cep = request.getParameter("cep");
        String logradouro = request.getParameter("logradouro");

        Endereco endereco = new Endereco(idEndereco, pais, estado, cidade, cep, logradouro);
        enderecoDao.atualizarEndereco(endereco);
    }

    private void excluirEndereco(HttpServletRequest request) throws DBException {
        int idEndereco = Integer.parseInt(request.getParameter("idEndereco"));
        enderecoDao.removerEndereco(idEndereco);
    }

    private void cadastrarTelefone(HttpServletRequest request) throws DBException {
        String ddi = request.getParameter("ddi");
        String ddd = request.getParameter("ddd");
        String numero = request.getParameter("numero");
        String tipo = request.getParameter("tipo");
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));

        Telefone telefone = new Telefone(0, ddi, ddd, numero, tipo, idCliente);
        telefoneDao.cadastrarTelefone(telefone);
    }

    private void editarTelefone(HttpServletRequest request) throws DBException {
        int idTelefone = Integer.parseInt(request.getParameter("idTelefone"));
        String ddi = request.getParameter("ddi");
        String ddd = request.getParameter("ddd");
        String numero = request.getParameter("numero");
        String tipo = request.getParameter("tipo");

        Telefone telefone = new Telefone(idTelefone, ddi, ddd, numero, tipo);
        telefoneDao.atualizarTelefone(telefone);
    }

    private void excluirTelefone(HttpServletRequest request) throws DBException {
        int idTelefone = Integer.parseInt(request.getParameter("idTelefone"));
        telefoneDao.removerTelefone(idTelefone);
    }
}
