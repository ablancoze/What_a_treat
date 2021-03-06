<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
        <a href="/What_a_treat/pages/Index.html">
            <img class="Logo" src="/What_a_treat/img/LogoWeb.png">
        </a>
    </header>
    
    <div class="registry-Box">
        
        <div class="Botones">
            
            <div class="Entrar">
                <button onclick="changeStileConectar()">Entrar</button>
            </div>
        
            <div class="Registrarse">
                <button onclick="changeStileRegistro()">Registrate</button>
            </div>
            
        </div>
        <c:choose>
        	
        	<c:when test="${registro == null}">
	        	<form method="post" action="Login" class="Conectar" id="Conectar" style="display: block">
		            
		            <fieldset>
						<p>${messages}</p>
		                <div>
		                    <input id="user" name="user" autofocus="" required="" type="text" placeholder="User name">
		                </div>
		                
		                <div>
		                    <input id="pass" name="pass" autofocus="" required="" type="password" placeholder="Password">
		                </div>
		                
		                <button id="button" type="submit" value="Entrar">Entrar</button>
		                
		            </fieldset>
		            
		        </form>
	
		        <form method="post" action="Registro" class="Registro" id="Registro" style="display: none">
		            
		            <fieldset>
		                
		                <div>
		                    <label>User Name</label>
		                    <input id="userName" name="userName" autofocus="" required="" type="text">
		                </div>
		                
		                <div>
		                    <label>Email</label>
		                    <input id="email" name="email" autofocus="" required="" type="email" placeholder="example@gmail.com">
		                </div>
		                
		                <div>
		                    <label>Password</label>
		                    <input id="pass" name="pass" autofocus="" required="" type="password">
		                </div>
		                
		                <button id="button" type="submit" value="Registro">Registrarse</button>
		                
		            </fieldset>
		            
		        </form>
        	</c:when>
        	
        	<c:otherwise>
        		<form method="post" action="Login" class="Conectar" id="Conectar" style="display: none">
		            <fieldset>
							<p>${messages}</p>
		                <div>
		                    <input id="user" name="user" autofocus="" required="" type="text" placeholder="User name">
		                </div>
		                
		                <div>
		                    <input id="pass" name="pass" autofocus="" required="" type="password" placeholder="Password">
		                </div>
		                
		                <button id="button" type="submit" value="Entrar">Entrar</button>
		                
		            </fieldset>
		            
		        </form>
	
		        <form method="post" action="Registro" class="Registro" id="Registro" style="display: block">
		            <fieldset>
		                
		                <div>
		                	<p>${usernameProblem}</p>
		                    <label>User Name</label>
		                    <input id="userName" name="userName" autofocus="" required="" type="text">
		                </div>
		                
		                <div>
		                	<p>${emailProblem}</p>
		                    <label>Email</label>
		                    <input id="email" name="email" autofocus="" required="" type="email" placeholder="example@gmail.com">
		                </div>
		                
		                <div>
		                    <label>Password</label>
		                    <input id="pass" name="pass" autofocus="" required="" type="password">
		                </div>
		                
		                <button id="button" type="submit" value="Registro">Registrarse</button>
		                
		            </fieldset>
		        </form>
        	</c:otherwise>
        </c:choose>
	        
        
    </div>
    
    
    <script>
        
        function changeStileConectar(){
            
            if (document.getElementById("Conectar").style.display == "none" ){
                document.getElementById("Registro").style.display = "none";
                document.getElementById("Conectar").style.display = "block";
            }
            
        }
        
        function changeStileRegistro(){
            if (document.getElementById("Registro").style.display == "none" ){
                document.getElementById("Conectar").style.display = "none";
                document.getElementById("Registro").style.display = "block";
            }
        }
    
    </script>
</body>
</html>
