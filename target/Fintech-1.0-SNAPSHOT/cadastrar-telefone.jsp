<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>Cadastrar Telefone</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
  <h1 class="mt-4">Cadastrar Telefone</h1>

  <form action="cadastrarTelefone" method="post">
    <div class="form-group">
      <label for="ddi">DDI:</label>
      <input type="text" class="form-control" id="ddi" name="ddi" required>
    </div>
    <div class="form-group">
      <label for="ddd">DDD:</label>
      <input type="text" class="form-control" id="ddd" name="ddd" required>
    </div>
    <div class="form-group">
      <label for="numero">Número:</label>
      <input type="text" class="form-control" id="numero" name="numero" required>
    </div>
    <div class="form-group">
      <label for="tipo">Tipo:</label>
      <select class="form-control" id="tipo" name="tipo" required>
        <option value="Residencial">Residencial</option>
        <option value="Comercial">Comercial</option>
        <option value="Celular">Celular</option>
      </select>
    </div>
    <button type="submit" class="btn btn-primary">Cadastrar</button>
    <a href="listar-telefone.jsp" class="btn btn-secondary">Cancelar</a>
  </form>
</div>
</body>
</html>
