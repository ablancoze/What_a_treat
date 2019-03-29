<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
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
                <a class="botonUsuario" href="Registro.jsp">
                    <div class="icono">
                        <i class="material-icons md-41">account_circle</i>
                    </div>
                </a>
            </div>
        </header>
        <nav id="navegadorSecundario" class="navegadorLinks">
            <section class="publicarChollo" id="publicarChollo" style="display: none">
                <div>
                    <a alt="Publicar un chollo" href="PublicarChollo.html">
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
                    <div class="userContainer">

                        <div class="infoUser">
                            <div class="foto">
                                <i class="material-icons md-41">account_circle</i>
                            </div>

                            <div class="datosUser">
                                <p>${user.username}</p>
                                <p>${user.email}</p>
                            </div>

                        </div>

                        <div class="chollosUser">
                            <c:forEach var="chollo" items="${cholloList}">
	                            <div class="cholloBoxPequeña">
	                                
	                                <div class="cholloImagen">
	                                    <img src = "${chollo.imagen}">
	                                </div>
	                                
	                                <div>
	                                    <h4>${chollo.title}</h4>
	                                    <h5>${chollo.price}</h5>
	                                </div>
	                                
	                                <div class="iconoEditar">
	                                    <a>
	                                        <i class="material-icons">edit</i>
	                                    </a>
	                                </div>
	                                
	                            </div>
	                         </c:forEach>   
                        </div>

                    </div>
                </div>
            </div>
            
            
        </div>
        <footer class="footer">
          <p>Resize the browser window to see how the content respond to the resizing.</p>
        </footer> 
    <script>

        function openclosenav(){

            if(document.getElementById("navegador").style.width == "0px"){
                document.getElementById("navegador").style.width = "250px"; 
                document.getElementById("navegadorSecundario").style.width = "87%";

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

    </script>

        </body>
</html>