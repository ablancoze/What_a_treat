<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/cssRegistro.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Quicksand" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Bangers" rel="stylesheet">
    <title>What a Treat</title>
</head>

<body>
    
    <header>
        <a href="<c:url value = "/whatAtreat"/>">
            <img class="Logo" src="Sin%20t%C3%ADtulo.png">
        </a>
    </header>
    
    <div class="registry-Box">
        
        <div class="Botones">
            
            <div class="Entrar">
                <button onclick="changeStile()">Entrar</button>
            </div>
        
            <div class="Registrarse">
                <button>Registrate</button>
            </div>
            
        </div>
        
        <form class="Conectar">
            
            <fieldset>
            	<c:set var=username value="26"/>
                <c:if test="${username == null}">
                
                </c:if>
                <div>
                    <input id="user" name="user" autofocus="" required="" type="text" placeholder="User name">
                </div>
                
                <div>
                    <input id="pass" name="pass" autofocus="" required="" type="password" placeholder="Password">
                </div>
                
            </fieldset>
            
        </form>

        <form method="post" action="OrderServlet" class="Registro">
            
            <fieldset>
                
                <div>
                    <label>User Name</label>
                    <input id="user-name" name="user-name" autofocus="" required="" type="text">
                </div>
                
                <div>
                    <label>Email</label>
                    <input id="email" name="email" autofocus="" required="" type="email" placeholder="example@gmail.com">
                </div>
                
                <div>
                    <label>Password</label>
                    <input id="pass" name="pass" autofocus="" required="" type="password">
                </div>
                
                <button id="button" type="submit" value="Submit order">Registrarse</button>
                
            </fieldset>
            
        </form>
        
    </div>
    
    
    <script>
        
        function changeStile(){
            
        }
    
    </script>
</body>
</html>
