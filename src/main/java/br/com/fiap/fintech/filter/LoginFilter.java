package br.com.fiap.fintech.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;


//A anotação @WebFilter determina a URL em que o filtro vai atuar. Como queremos filtrar todas as
// requisições, vamos configurar a URL como “/*”, o asterisco (*) é o caractere coringa que pode assumir qualquer valor

@WebFilter("/*")
public class LoginFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse resp,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        String url = req.getRequestURI();

        // Permitir o acesso à página de login, home e editar cliente (ou outras páginas específicas)
        if (session.getAttribute("user") == null &&
                !url.endsWith("login") &&
                !url.contains("resources") &&
                !url.contains("home") &&
                !url.contains("clientes?acao=abrir-form")) { // Permitir acessar a página de edição

            request.setAttribute("erro", "Entre com o usuário e senha!");
            request.getRequestDispatcher("home.jsp").forward(request, resp);
        } else {
            chain.doFilter(request, resp);
        }
    }
}