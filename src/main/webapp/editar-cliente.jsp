<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cadastro de produtos</title>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0"
    >
    <link
            rel="stylesheet"
            href="./resources/css/bootstrap.css"
    >
</head>
<body>
<%@include file="header.jsp" %>
<div class="container">
    <div class="mt-5 ms-5 me-5">
        <div class="card mb-3">
            <div class="card-header">
                EDIÇÃO DE CLIENTE
            </div>

            <c:if test="${not empty mensagem}">
                <div
                        class="alert alert-success ms-2 me-2 m-auto mt-2">${mensagem}
                </div>
            </c:if>

            <c:if test="${not empty erro}">
                <div
                        class="alert alert-danger ms-2 me-2 m-auto mt-2">${erro}
                </div>
            </c:if>

            <div class="card-body">
                <form
                        action="clientes"
                        method="post">

                    <input
                            type="hidden"
                            value="editar"
                            name="acao"
                    >
                    <input type="hidden" name="id_cliente" value="${cliente.idCliente}">


                    <div class="form-group">
                        <label
                                for="id-nome">Nome
                        </label>
                        <input
                                type="text"
                                name="nome"
                                id="id-nome"
                                class="form-control"
                                value="${cliente.nome}"
                        >

                    </div>
                    <div class="form-group">
                        <label
                                for="id-email">Email
                        </label>
                        <input
                                type="text"
                                name="email"
                                id="id-email"
                                class="form-control"
                                value="${cliente.email}"
                        >
                    </div>
                    <div class="form-group">
                        <label
                                for="id-cpf">CPF
                        </label>
                        <input
                                type="text"
                                name="cpf"
                                id="id-cpf"
                                class="form-control"
                                value="${cliente.cpf}"
                        >
                    </div>
                    <div class="form-group">
                        <label
                                for="id-rg">RG
                        </label>
                        <input
                                type="text"
                                name="rg"
                                id="id-rg"
                                class="form-control"
                                value="${cliente.rg}"
                        >
                    </div>
                    <div class="form-group">
                        <label for="dt_nascimento">Data de Nascimento:</label>
                        <input type="date" id="dt_nascimento" name="dt_nascimento" class="form-control"
                               value="${cliente.dtNascimento}" required>

                    </div>

                    <input
                            type="submit"
                            value="Salvar"
                            class="btn btn-primary mt-3">
                    <a
                            href="clientes?acao=listar"
                            class="btn btn-warning mt-3">Cancelar
                    </a>
                </form>
            </div>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>
<script src="resources/js/bootstrap.bundle.js"></script>
</body>
</html>