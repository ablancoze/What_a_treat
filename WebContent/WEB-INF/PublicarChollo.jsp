<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/cssPublicarChollo.css">
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

        <div class = "PublicarChollo">
            
            <form method="post" action="PublicarChollo" id="formularioPublicacion" class = "formularioPublicacion">
               <legend>
                   <h1>Deja tu chollo</h1>
                </legend>
                
                <h3>Titulo</h3>
                <input id="titulo" name="titulo" autofocus="" required="" type="text"><br>
                
                <h3>Descripcion del chollo</h3>
                <input id="descripcion" name="descripcion" autofocus="" required="" type="text"><br>
                
                <h3>Enlace a al chollo</h3>
                <input id="link" name="link" autofocus="" required="" type="text"><br>
                
                <h3>Precio del chollo</h3>
                <input id="precio" name="precio" autofocus="" required="" type="number"><br>
                
                <h3>Precio habitualo</h3>
                <input id="precioSinChollo" name="precioSinChollo" type="number"><br>
                
                <h3>Cupon de descuento</h3>
                <input id="cupon" name="cupon" type="text"><br>
                
                <h3>Fecha de expiracion del chollo</h3>
                <input id="fecha" name="fecha" type="date"><br>
                
                <h3>Link de la imagen del producto</h3>
                <input id="imagen" name="imagen" type="text"><br>
                
                <h3>Tienda</h3>
                <select name="shop" autofocus="" required="">
                	<option value="">Escoga una tienda</option>
					<c:forEach var="shopList" items="${shop}">
						<option value="${shopList.id}">${shopList.name}</option>
					</c:forEach>
				</select>
                
                <h3>Categoria</h3>
                <select name="category" autofocus="" required="">
                	<option value="">Escoga una categoria</option>
					<c:forEach var="categoryList" items="${category}">
						<option value="${categoryList.id}">${categoryList.name}</option>
					</c:forEach>                	
				</select>
                
                <h3>Palabra destacada</h3>
                <input id="palabraDestacada" name="palabraDestacada" type="text">
                <button id="botonPublicarChollo" type="submit" value="Publica" class="botonPublicarChollo">Publicar chollo</button>
            </form>
            
        
        </div>

    </body>
</html>
