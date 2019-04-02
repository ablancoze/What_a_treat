<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
<link rel="stylesheet" href="css/cssPaginaChollo.css" type="text/css"><link>
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
            
            <div class="col-6 col-s-9">
                
                <div class="cholloBox">
                    <div class="imgTitleUser">
                        <div>
                            <img src="${chollo.imagen}">
                        </div>
                        <div class="TitleUser">
                            <h1>${chollo.title}</h1>
                            <div>
                            	<div>
                          			<img src="FotoPerfilAlvaroAlumno.jpg"> <% //${userPublicacion.imagen} %>
                                	<a>${userPublicacion.username}</a>
                            	</div>
                                <% //${chollo.fecha} %>
                                <a>${shop.name}</a>
                            </div>
                        </div>
                    </div>
                    
                    <div class="likesPrecio">
                        <div class="like">
                        	
                        		 <h3>Puntuacion    ${chollo.likes}</h3>
                        	
                        	
                        		<i class='fas fa-thumbs-up' style='font-size:24px;color: #00BF00 ;cursor: pointer' onmouseover="this.style.color='#00DF00'" onMouseOut="this.style.color='#00BF00'"></i>
                            	<i class='fas fa-thumbs-down' style='font-size:24px;color: #BF0000;cursor: pointer' onmouseover="this.style.color='#DF0000'" onMouseOut="this.style.color='#BF0000'"></i>
                        	
                        </div>
                        
                        <div class="precio">
                            <h2 style="ma">${chollo.price} &#8364</h2>
                            <button class="botonPublicarComentario" style="margin-top: 0" >Ver Chollo</button>
                        </div>

                    </div>
                    
                    <div>
                    	<p>${chollo.description}</p>
                    </div>
                    
                    <div class="comentarios">
                    	<h3 style="font: 700 19px/21px 'Roboto', trebuchet ms;margin-bottom: 26px;">Comentarios</h3>
                    	<c:forEach var="comentario" items="${comentarios}">
	                    	<div class="boxComentarios">
	                    		<div class="infoUserComent">
	                    			<img src = "FotoPerfilAlvaroAlumno.jpg">
	                    			<p style="font: 700 15px/15px 'Roboto', trebuchet ms;text-transform: uppercase;display: block;margin-left: 10px;margin-bottom: 0;height: 16px;">${comentario.username}</p>
	                    		</div>
	                    		<div class="comentarioUser">
	                    			<p style="font: 15px Arial, Helvetica, trebuchet ms;">${comentario.comentario}</p>
	                    		</div>
	                    	</div>
                    	</c:forEach>

                	</div>
                	
                	<div class="formComentario">
                		<form method="post" action="PublicarComentario" >
                			<fieldset style="padding: 20px 24px;border: 1px solid #ededed;margin-bottom: 30px;box-shadow: 0 1px 1px #EDEDED;background: #fff;">
                				<legend style="font: 700 19px/21px 'Roboto', trebuchet ms; text-align: center">DEJANOS TU COMENTARIO</legend>
                				<textarea id="comentario" type="text"  name="comentario" rows="10" cols="30" aria-required="true" required="required" style="width: 100%;background: #f2f2f2;font: 400 15px 'helvetica', trebuchet ms;height: 150px;resize: none;margin-bottom: 20px;border: 1px solid #cbd0d2;padding: 10px;clear: both"></textarea>
                				
                				<c:if test="${user==null}">
	                				<div>
	                					<div style="background: none #002531;width: 38px;height: 38px;text-align: center;line-height: 38px;">
	                						<i style="font-size:15px;color:#2a80b9" class='fas'>&#xf007;</i>
	                					</div>
	                					<input id="username" type="text" name="username" placeholder="Nombre" required="required" style="padding: 0 0 0 5px">
	                				</div>
	                				<div>
	                					<div style="background: none #002531;width: 38px;height: 38px;text-align: center;line-height: 38px;">
	                						<i style="font-size:15px;color:#2a80b9" class='fas'>&#xf0e0;</i>
	                					</div>
	                					<input id="email" type="text" name="email" placeholder="Correo electronico" required="required" style="padding: 0 0 0 5px">
	                				</div>
                				</c:if>
                				
                				<button type="submit" class="botonPublicarComentario">
                					<input type="hidden" name="cid" value="${chollo.id}" id="cid">
                					<input type="hidden" name="uid" value="${userPublicacion.id}" id="uid">
                					<input type="hidden" name="sid" value="${shop.id}" id="sid">
                					Publicar
                				</button>	
                			</fieldset>
                		</form>
                	</div>
                    
                </div>
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
            document.getElementById("principal").style.width = "75%";
            document.getElementById("navegadorSecundario").style.width = "100%";
            
        }else{
            document.getElementById("navegador").style.width = "0";
            document.getElementById("principal").style.width = "75%";
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