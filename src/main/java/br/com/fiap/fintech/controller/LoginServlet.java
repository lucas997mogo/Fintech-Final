package br.com.fiap.fintech.controller;

import br.com.fiap.fintech.dao.UsuarioDao;
import br.com.fiap.fintech.factory.DaoFactory;
import br.com.fiap.fintech.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UsuarioDao dao;

    public LoginServlet() {
        dao = DaoFactory.getUsuarioDAO();
    }

    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        Usuario usuario = new Usuario(email, senha);

        if (dao.validarUsuario(usuario)) {

            HttpSession session = request.getSession();
            session.setAttribute("user", email);
            response.sendRedirect("header.jsp");

        } else {
            request.setAttribute("erro", "Usuário e/ou senha inválidos");
            request.getRequestDispatcher("home.jsp").forward(request, response);
        }
    }

    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.invalidate();
        request.getRequestDispatcher("home.jsp").forward(request, response);

    }
}