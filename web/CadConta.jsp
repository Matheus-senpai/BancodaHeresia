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
        <a> Fazer Opera��o</a>
        
        <form   action="/banco/cadastro" method="post" class="form-vertical">
<fieldset>


<legend>CadConta</legend>


<div class="form-group">
  <label class="col-md-4 control-label" for="conta">Conta</label>  
  <div class="col-md-4">
  <input id="conta" name="conta" type="text" placeholder="" class="form-control input-md" required="">
    
  </div>
</div>




<div class="form-group">
  <label class="col-md-4 control-label" for="cpf">CPF</label>  
  <div class="col-md-4">
  <input id="cpf" name="cpf" type="text" placeholder="" class="form-control input-md" required="">
 
  </div>
</div>

<div class="form-group">
  <label class="col-md-4 control-label" for="OK"></label>
  <div class="col-md-4">
      <button id="enviar" type="submit" name="OK" class="btn btn-success">OK</button>
  </div>
</div>
</fieldset>
</form>
        
  <a href='/BancodaHeresia'>Back</b>      
    </body>
</html>
