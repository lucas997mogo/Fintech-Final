<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Lista de Clientes</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-4">
    <h1>Lista de Clientes</h1>

    <!-- Display Messages -->
    <c:if test="${not empty msg}">
        <div class="alert alert-success">${msg}</div>
    </c:if>
    <c:if test="${not empty erro}">
        <div class="alert alert-danger">${erro}</div>
    </c:if>

    <!-- Client List Table -->
    <table class="table table-bordered mt-3">
        <thead>
        <tr>
            <th>ID</th>
            <th>Data de Cadastro</th>
            <th>Nome</th>
            <th>Email</th>
            <th>CPF</th>
            <th>RG</th>
            <th>Data de Nascimento</th>
            <th>Ações</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="cliente" items="${clientes}">
            <tr>
                <td>${cliente.idCliente}</td>
                <td>${cliente.dtDeCadastro}</td>
                <td>${cliente.nome}</td>
                <td>${cliente.email}</td>
                <td>${cliente.cpf}</td>
                <td>${cliente.rg}</td>
                <td>${cliente.dtNascimento}</td>
                <td>
                    <a href="clientes?acao=abrir-form&id_cliente=${cliente.idCliente}" class="btn btn-primary btn-sm">Editar</a>
                    <a href="contas?acao=listar&id_cliente=${cliente.idCliente}" class="btn btn-primary btn-sm" >Contas do cliente</a>


                    <a href="contas?acao=abrir-form-cadastro&id_cliente=${cliente.idCliente}" class="btn btn-primary btn-sm">Cadastrar Nova Conta para cliente</a>
                    <a href="cadastro?acao=listar&id_cliente=${cliente.idCliente}" class="btn btn-primary btn-sm" >Telefones</a>


                    <a href="cadastro?acao=cadastrarTelefone&id_cliente=${cliente.idCliente}" class="btn btn-primary btn-sm">Cadastrar novo telefone</a>
                    <form action="clientes" method="post" style="display:inline;">
                        <input type="hidden" name="acao" value="excluir">
                        <input type="hidden" name="codigoExcluir" value="${cliente.idCliente}">
                        <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Tem certeza que deseja excluir este cliente?');">Excluir</button>
                    </form>

                </td>
            </tr>
        </c:forEach>
        </tbody>
        <a
                href="home.jsp"
                class="btn btn-warning mt-3">Voltar
        </a>
    </table>

</div>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
