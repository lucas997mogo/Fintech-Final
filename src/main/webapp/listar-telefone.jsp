<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="br.com.fiap.fintech.model.Telefone" %>
<%@ page import="br.com.fiap.fintech.dao.TelefoneDao" %>
<%@ page import="br.com.fiap.fintech.impl.OracleTelefoneDao" %>
<%@ page import="br.com.fiap.fintech.exception.DBException" %>
<!DOCTYPE html>
<html>
<head>
  <title>Lista de Telefones</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
  <h1 class="mt-4">Lista de Telefones</h1>
  <a href="cadastrar-telefone.jsp" class="btn btn-primary mb-4">Cadastrar Novo Telefone</a>

  <table class="table table-bordered">
    <thead>
    <tr>
      <th>ID</th>
      <th>DDI</th>
      <th>DDD</th>
      <th>Número</th>
      <th>Tipo</th>
      <th>Ações</th>
    </tr>
    </thead>
    <tbody>
    <%
      TelefoneDao telefoneDao = new OracleTelefoneDao();
      try {
        List<Telefone> telefones = telefoneDao.listarTelefone();
        for (Telefone telefone : telefones) {
    %>
    <tr>
      <td><%= telefone.getIdTelefone() %></td>
      <td><%= telefone.getDdi() %></td>
      <td><%= telefone.getDdd() %></td>
      <td><%= telefone.getNumero() %></td>
      <td><%= telefone.getTipo() %></td>
      <td>
        <a href="editar-telefone.jsp?id=<%= telefone.getIdTelefone() %>" class="btn btn-sm btn-warning">Editar</a>
        <a href="excluir-telefone?id=<%= telefone.getIdTelefone() %>" class="btn btn-sm btn-danger">Excluir</a>
      </td>
    </tr>
    <%
        }
      } catch (DBException e) {
        e.printStackTrace();
      }
    %>
    </tbody>
  </table>
</div>
</body>
</html>
