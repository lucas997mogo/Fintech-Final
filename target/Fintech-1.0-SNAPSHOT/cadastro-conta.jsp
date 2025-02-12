<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cadastrar Conta</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
    <h1>Cadastrar Nova Conta</h1>



    <!-- Verifica se o ID do cliente foi passado e exibe uma mensagem de erro se não estiver presente -->

    <c:if test="${not empty param.id_cliente}">
        <p>Cliente ID: ${param.id_cliente}</p>
    </c:if>

    <!-- Formulário de Cadastro de Conta -->
    <form action="contas" method="post">
        <input type="hidden" name="acao" value="cadastrar">
        <!-- Campo oculto para o ID do Cliente -->
        <input type="hidden" name="id_cliente" value="${param.id_cliente}">
        <div class="form-group">
            <label for="tipoConta">Tipo de Conta</label>
            <input type="text" class="form-control" id="tipoConta" name="tipoConta" required>
        </div>

        <div class="form-group">
            <label for="saldo">Saldo Inicial</label>
            <input type="number" class="form-control" id="saldo" name="saldo" step="0.01" required>
        </div>

        <div class="form-group">
            <label for="dtDeAbertura">Data de Abertura</label>
            <input type="date" class="form-control" id="dtDeAbertura" name="dtDeAbertura" required>
        </div>

        <button type="submit" class="btn btn-primary">Cadastrar Conta</button>
        <a href="clientes?acao=listar" class="btn btn-secondary">Voltar</a>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
