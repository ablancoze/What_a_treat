<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
<link rel="stylesheet" href="css/cssResponsive.css" type="text/css"><link>
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Quicksand" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Bangers" rel="stylesheet">
<title>What a Treat</title>
</head>
<body>

        <header>
        <div class="seccionLogo">
            <a class="boton">
                <span style="font-size:30px;cursor:pointer;display: flex;" onclick="openclosenav()"><i  class="material-icons">menu</i></span>
            </a>
            <a href="<c:url value = "/whatAtreat"/>">
                <img class="Logo" src="Sin%20t%C3%ADtulo.png">
            </a>
            
        </div>
        
        <div class="buscador">
        	<form method="post" action="whatAtreat">
	        	<div class="searchBox">
	                <input name="search" type="search" id="search" placeholder="What are you looking for?" >
	            </div>
	            <div>
	                <button type="submit"><i  class="material-icons">search</i></button>
	            </div>
        	</form>
        </div>
        
        <div class="botonesUsuario">
            <a class="botonPublicar" onclick="openclosePublicar()">
                <div class="icono">
                    <i class="material-icons">add_circle</i>
                </div>
                <div id="trianguloPublicar" class="triangulo-equilatero-bottom" style="display: none">
        		</div>
            </a>
            <c:choose>
            	<c:when test="${user == null}">
		            <a class="botonUsuario" href="<c:url value = "/Login"/>">
		                <div class="icono">
		                    <i class="material-icons md-41">account_circle</i>
		                </div>
		            </a>
		        </c:when>
		      	<c:otherwise>
		        	<a class="botonUsuario" onclick="opencloseUsuario()">
		                <div class="icono">
		                    <i class="material-icons md-41">account_circle</i>
		                </div>
		                <div id="trianguloUsuario" class="triangulo-equilatero-bottom" style="display: none">
        				</div>
		            </a>
		        </c:otherwise>
		     </c:choose>     
        </div>
    </header>
    <nav id="navegadorSecundario" class="navegadorLinks">
        <section class="publicarChollo" id="publicarChollo" style="display: none">
            <div>
            <c:choose>
        		<c:when test="${user == null}">
        			<a alt="Publicar un chollo" href="<c:url value = "/Login"/>">
                    	<i class="material-icons">
                        	local_offer
                    	</i>
                    Publicar Chollo
                	</a> 
                	<a alt="Publicar un cupon" href="<c:url value = "/Login"/>">
                        <i class="material-icons">
                            local_activity
                        </i>
                        Publicar Cupon
                	</a>
        		</c:when>
        		<c:otherwise>
					<a alt="Publicar un chollo" href="<c:url value = "/PublicarChollo"/>">
                    	<i class="material-icons">
                        	local_offer
                    	</i>
                    Publicar Chollo
                	</a> 
                	<a alt="Publicar un cupon" href="">
                        <i class="material-icons">
                            local_activity
                        </i>
                        Publicar Cupon
                	</a>
        		</c:otherwise>
        	</c:choose>


            </div>
        </section>
        
        <section class="verPerfil" id="verPerfil" style="display: none">
            <div>
                <a alt="Ir a tu perfil" href="<c:url value = "/UserProfile"/>">
					Mi cuenta
                </a> 
                <a alt="Salir de tu perfil" href="<c:url value = "/Logout"/>">
					Cerrar sesion
                </a> 
            </div>
        </section>
        <p>La mejor paginda de chollos</p>
    </nav>
    
    <div class="row">
    	<div class="eventBox" id="eventBoxid">
        	<c:forEach var="cholloHot" items="${chollosHot}">
        		<div class="cholloEventBox">
	                <a href="">
	                    <img class="imagenEvent" alt="carrito bebe" src = "${cholloHot.imagen}">
	                </a>
	                <p>${cholloHot.description}</p>
            	</div>
        	</c:forEach>
        </div>
        <ul id="navegador" class="navegacion" style="width: 0px">
            <li>Tiendas
                <div>
                	<c:forEach var="Tiendas" items="${ListaTiendas}">
                    	<a href="#">${Tiendas.name}</a>
                    </c:forEach>
                </div>
            </li>
            
            <li>Categorias
            	<div>
            		<c:forEach var="Categorias" items="${ListaCategorias}">
            			<a href="#home">${Categorias.name}</a>
            		</c:forEach>
            	</div>
            </li>
            
            <li>Chollos Aliexpress
                <a href="#home"></a>
            </li>
            
            <li>Chollos Amazon
                <a href="#home"></a>
            </li>
            
            <li>Chollos MediaMarkt
                <a href="#home"></a>
            </li>
            
            <li>Contacto
                <a href="#home"></a>
            </li>
            
        </ul>
    
        <div id="principal" class="mainContainer"> 
            <div class="col-6 col-s-9" style="background: white;box-shadow: 0 0.23077rem 0.38462rem 0 rgba(0, 0, 0, .25);">
                    <form method="post" action="EditProfile" id="formularioEditarPerfil" class="editContainer">
                        <div class="infoFoto">
                            <div class="imagenChollo">
                                <img src = "FotoPerfilAlvaroAlumno.jpg">
                            </div>

                            <div>
                                <input id="imagen" name="imagen" type="text" placeholder="Cambiar foto de perfil">
                                <i class="material-icons">link</i>
                            </div>
                        </div>

                        <div class="infoChollo">
                            <h4>${user.username}</h4> <p style="color: red;font-weight: bolder;margin-left: auto;">${usernameProblem}</p>
                            <input id="username" name="username" type="text" placeholder="Introduce un nuevo nombre de usuario" value="">
                            <h4>${user.email}</h4> <p style="color: red;font-weight: bolder;margin-left: auto;">${emailProblem}</p>
                            <input id="email" name="email" type="text" placeholder="Introduce un nuevo email" value="">
                            <p>Para cambiar la password primero debes introducir tu contraseña actual</p> <p style="color: red;font-weight: bolder;margin-left: auto;">${messages}</p>
                            <input id="Oldpass" name="Oldpass" type="password">
                            <p>Nueva password</p>
                            <input id="Newpass" name="Newpass" type="password" placeholder="Introduce tu nueva password">
                        </div>
                        <button id="botonPublicarChollo" type="submit" value="Publica" class="botonPublicarChollo">Aplicar cambios</button>
                    </form>
                    <form method="post" action="EliminarCuenta">
                    	<button style="margin-bottom: 15px;margin-left: 86%;;background-color: #fafbfc;color: #cb2431;border: 1px solid rgba(27,31,35,.2);cursor: pointer;font-weight: 600;border-radius: .25em;padding: 7px;font-size: 14px;" type="submit" >Eliminiar cuenta</button>                   
                    </form>
                </div>
            </div>
        </div>
        
<footer class="footer">
	<div class="Ayudausurio">
		<img alt="" src="logo_60.png">
	</div>
	<div class="IconosSocialMedia">
		<a href="https://www.facebook.com/alvaro.blancomangut.37">
			<i  style="font-size:36px;color:#2a80b9" class='fab'>&#xf09a;</i>
		</a>			
		<a href="https://github.com/ablancoze">	
			<i style="font-size:36px;color:#2a80b9" class='fab'>&#xf09b;</i>
		</a>		
		<a href="https://www.linkedin.com/in/%C3%A1lvaro-blanco-mangut-ab2313163/">
			<i style="font-size:36px;color:#2a80b9" class='fab'>&#xf08c;</i>	
		</a>		
		<a href="https://www.starwars.com/">
			<i style="font-size:36px;color:#2a80b9" class='fab'>&#xf1d0;</i>
		</a>
		<a href="https://www.youtube.com/watch?v=sNbGxOccYWc">
			<i style="font-size:36px;color:#2a80b9" class='fab'>&#xf167;</i>
		</a>
	</div>
</footer> 
<script>

    function openclosenav(){

        if(document.getElementById("navegador").style.width == "0px"){
            document.getElementById("navegador").style.width = "250px"; 
            document.getElementById("principal").style.width = "80%";
            document.getElementById("navegadorSecundario").style.width = "100%";
            
        }else{
            document.getElementById("navegador").style.width = "0";
            document.getElementById("principal").style.width = "90%";
            document.getElementById("navegadorSecundario").style.width = "100%";
        }
    }
    
    function openclosePublicar(){
        if (document.getElementById("publicarChollo").style.display == "none"){
            document.getElementById("publicarChollo").style.display = "block";
            document.getElementById("trianguloPublicar").style.display = "block";
            
        }else{
            document.getElementById("publicarChollo").style.display = "none";
            document.getElementById("trianguloPublicar").style.display = "none";
        }
    }
    
    function opencloseUsuario(){
        if (document.getElementById("verPerfil").style.display == "none"){
             document.getElementById("verPerfil").style.display = "block";
             document.getElementById("trianguloUsuario").style.display = "block";
        }else{
            document.getElementById("verPerfil").style.display = "none";
            document.getElementById("trianguloUsuario").style.display = "none";
        }
    }
    
</script>

</body>
</html>
