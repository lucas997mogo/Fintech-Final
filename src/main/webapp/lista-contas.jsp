<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Lista de Contas</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-4">
    <h1>Lista de Contas</h1>

    <c:if test="${not empty param.id_cliente}">
        <p>Cliente ID: ${param.id_cliente}</p>
    </c:if>


    <!-- Tabela de Contas -->
    <table class="table table-bordered mt-3">
        <thead>
        <tr>
            <th>ID</th>
            <th>Tipo de Conta</th>
            <th>Saldo</th>
            <th>Data de Abertura</th>
            <th>ID do Cliente</th>
            <th>Ações</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="conta" items="${contas}">
            <tr>
                <td>${conta.idConta}</td>
                <td>${conta.tipoConta}</td>
                <td>${conta.saldo}</td>
                <td>${conta.dtDeAbertura}</td>
                <td>
                    <a href="contas?acao=abrir-form&id_conta=${conta.idConta}" class="btn btn-primary btn-sm">Editar</a>
                    <form action="contas" method="post" style="display:inline;">
                        <input type="hidden" name="acao" value="excluir">
                        <input type="hidden" name="codigoExcluir" value="${conta.idConta}">
                        <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Tem certeza que deseja excluir esta conta?');">Excluir</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- Botões para cadastrar nova conta e voltar -->
    <div class="mt-3">
        <a href="contas?acao=abrir-form-cadastro&id_cliente=${cliente.idCliente}" class="btn btn-primary btn-sm">Cadastrar Nova Conta</a>
        <a href="home.jsp" class="btn btn-warning">Voltar</a>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
