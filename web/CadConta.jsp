<html lang="pt-br">
    <head>
        <title>Banco da Heresia</title>
        <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    </head>
    <body>
        <div>Banco da Heresia</div>
        <a>Cad conta</a>
        <a> Fazer Operação</a>
        
        <form   action="/banco/cadastro" method="post" class="form-vertical">
<fieldset>


<legend>CadConta</legend>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="conta">conta</label>  
  <div class="col-md-4">
  <input id="conta" name="conta" type="text" placeholder="número da conta" class="form-control input-md" required="">
    
  </div>
</div>



<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="cpf">Cpf</label>  
  <div class="col-md-4">
  <input id="cpf" name="cpf" type="text" placeholder="cpf" class="form-control input-md" required="">
  <span class="help-block"></span>  
  </div>
</div>
<!-- Button -->
<div class="form-group">
  <label class="col-md-4 control-label" for="OK"></label>
  <div class="col-md-4">
      <button id="enviar" type="submit" name="OK" class="btn btn-success">cadastrar</button>
  </div>
</div>
</fieldset>
</form>
        
  <a href='/banco'>Back</b>      
    </body>
</html>
