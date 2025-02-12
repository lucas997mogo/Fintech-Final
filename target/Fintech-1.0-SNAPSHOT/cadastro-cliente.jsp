<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Cadastrar Cliente</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-4">
  <h1>Cadastrar Cliente</h1>

  <!-- Display Messages -->
  <c:if test="${not empty mensagem}">
    <div class="alert alert-success">${mensagem}</div>
  </c:if>
  <c:if test="${not empty erro}">
    <div class="alert alert-danger">${erro}</div>
  </c:if>

  <!-- Client Registration Form -->
  <form action="clientes" method="post" class="mt-4">
    <input type="hidden" name="acao" value="cadastrar"/>


    <div class="form-group">
      <label for="nome">Nome:</label>
      <input type="text" id="nome" name="nome" class="form-control" required>
    </div>
    <div class="form-group">
      <label for="email">Email:</label>
      <input type="email" id="email" name="email" class="form-control" required>
    </div>
    <div class="form-group">
      <label for="cpf">CPF:</label>
      <input type="text" id="cpf" name="cpf" class="form-control" required>
    </div>
    <div class="form-group">
      <label for="rg">RG:</label>
      <input type="text" id="rg" name="rg" class="form-control" >
    </div>
    <div class="form-group">
      <label for="dt_nascimento">Data de Nascimento:</label>
      <input type="date" id="dt_nascimento" name="dt_nascimento" class="form-control" required>
    </div>

    <button type="submit" class="btn btn-primary">Cadastrar</button>
    <a href="home.jsp" class="btn btn-secondary">Cancelar</a>
  </form>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
