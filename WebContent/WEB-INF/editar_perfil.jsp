<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
                <div class="searchBox">
                    <input type="search" id="search" placeholder="What are you looking for?" >
                </div>
                <div>
                    <button type="submit"><i  class="material-icons">search</i></button>
                </div>
            </div>

            <div class="botonesUsuario">
                <a class="botonPublicar" onclick="openclosePublicar()">
                    <div class="icono">
                        <i class="material-icons">add_circle</i>
                    </div>
                </a>
		        <a class="botonUsuario" onclick="opencloseUsuario()">
		        	<div class="icono">
		            	<i class="material-icons md-41">account_circle</i>
		            </div>
		        </a>
            </div>
        </header>
        
        <nav id="navegadorSecundario" class="navegadorLinks">
            <section class="publicarChollo" id="publicarChollo" style="display: none">
                <div>
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
            <ul id="navegador" class="navegacion">
                <li>Tiendas
                    <div>
                        <a href="#">Aliexpress</a>
                        <a href="#">Amazon</a>
                        <a href="#">MediaMarkt</a>
                        <a href="#">PCcomponentes</a>
                        <a href="#">Banggood</a>
                        <a href="#">Ebay</a>
                        <a href="#">Gearbest</a>
                        <a href="#">Steam</a>
                    </div>
                </li>

                <li>Categorias
                    <a href="#home"></a>
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
                <div class="col-6 col-s-9">
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
                            <h4>${user.username}</h4>
                            <input id="username" name="username" type="text" placeholder="Introduce un nuevo nombre de usuario" value="${user.username}">
                            <h4>${user.email}</h4>
                            <input id="email" name="email" type="text" placeholder="Introduce el nuevo precio del chollo" value="${user.email}">
                            <p>Para cambiar la password primero debes introducir tu contraseña actual</p>
                            <input id="Oldpass" name="Oldpass" type="password">
                            <p>Nueva password</p>
                            <input id="Newpass" name="Newpass" type="password" placeholder="Introduce tu nueva password">
                        </div>
                        <button id="botonPublicarChollo" type="submit" value="Publica" class="botonPublicarChollo">Aplicar cambios</button>
                    </form>
                </div>
            </div>
        </div>
        
<footer class="footer">
	<div class="Ayuda usurio">
	</div>
	<div class="IconosSocialMedia">
		<a href="https://www.facebook.com/alvaro.blancomangut.37">
			<i class="fa fa-facebook-square" style="font-size:36px;color:#002531"></i>
		</a>			
		<a href="https://github.com/ablancoze">	
			<i class="fa fa-github" style="font-size:36px;color:#002531"></i>
		</a>		
		<a href="https://www.linkedin.com/in/%C3%A1lvaro-blanco-mangut-ab2313163/">
			<i class="fa fa-linkedin-square" style="font-size:36px;color:#002531"></i>	
		</a>		
		<a href="https://www.starwars.com/">
			<i class="fa fa-rebel" style="font-size:36px;color:#002531"></i>
		</a>
		<a href="https://www.youtube.com/watch?v=sNbGxOccYWc">
			<i class="fa fa-youtube-play" style="font-size:36px;color:#002531"></i>
		</a>
	</div>
</footer> 
        
<script>

    function openclosenav(){

        if(document.getElementById("navegador").style.width == "0px"){
            document.getElementById("navegador").style.width = "200px"; 

        }else{
            document.getElementById("navegador").style.width = "0";
            document.getElementById("navegadorSecundario").style.width = "100%";
        }
    }

    function openclosePublicar(){
        if (document.getElementById("publicarChollo").style.display == "none"){
            document.getElementById("publicarChollo").style.display = "block";
        }else{
            document.getElementById("publicarChollo").style.display = "none";
        }

    }
    
    function opencloseUsuario(){
        if (document.getElementById("verPerfil").style.display == "none"){
             document.getElementById("verPerfil").style.display = "block"
        }else{
            document.getElementById("verPerfil").style.display = "none";
        }
    }
    
    </script>
    
</body>
</html>