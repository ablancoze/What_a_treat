<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css\cssResponsive.css" type="text/css"><link>
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
            <a href="what_a_treat.html">
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
        <div class="eventBox" id="eventBoxid">
            <div class="cholloEventBox">
                <a href="#home">
                    <img class="imagenEvent" alt="carrito bebe" src="10021021841-6-118x118.jpg">
                </a>
                <p>Riñonera putimadre para el pc o lo que sea XD</p>
            </div>
            <div class="cholloEventBox">
                <a href="#home">
                    <img class="imagenEvent" alt="carrito bebe" src="10021021841-6-118x118.jpg">
                </a>
                <p>Riñonera putimadre para el pc o lo que sea XD</p>
            </div>
            <div class="cholloEventBox">
                <a href="#home">
                    <img class="imagenEvent" alt="carrito bebe" src="10021021841-6-118x118.jpg">
                </a>
                <p>Riñonera putimadre para el pc o lo que sea XD</p>
            </div>
        </div>
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
				<c:forEach var="chollo" items="${chollosList}">
					<div class="cholloBox">
						<div class="cholloImagen">
							<a href="">
							<img src = "${chollo.first.imagen}">
							</a>
						</div>
						<div class="textoChollo">
							<div class="progressBar">
								<i class="material-icons">mood</i> <i class="material-icons">mood_bad</i>
								<div class="progress"></div>
							</div>
							<h3>${chollo.first.title}</h3>
							<div class="precioTienda">
								<h4>${chollo.first.price}</h4>
								<a href="">#tienda</a>
							</div>
							<p>${chollo.first.description}</p>
							<div class="autorVerChollo">
								<a href="">${chollo.second.username}</a>
								<button href="">Ver Chollo</button>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
            <div class="col-3 col-s-12">
                <div class="aside">
                    <h2>What?</h2>
                    <p>Chania is a city on the island of Crete.</p>
                    <h2>Where?</h2>
                    <p>Crete is a Greek island in the Mediterranean Sea.</p>
                    <h2>How?</h2>
                    <p>You can reach Chania airport from all over Europe.</p>
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
            document.getElementById("principal").style.width = "80%";
            document.getElementById("navegadorSecundario").style.width = "87%";
            
        }else{
            document.getElementById("navegador").style.width = "0";
            document.getElementById("principal").style.width = "90%";
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
