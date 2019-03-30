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
        <a href="<c:url value = "/whatAtreat"/>">
            <img class="Logo" src="Sin%20t%C3%ADtulo.png">
        </a>
    </header>
    <h4>Registro realizado</h4>
    <form method="post" action="ConfirmarRegistro" class="Registro" id="Registro" style="display: block">
        <p>${username}</p>
        <p>${email}</p>
        
        <h5>Pulse para confirmar</h5>
        <button id="button" type="submit" >confirmar</button>
    </form>
    
    <form method="post" action="Registro" class="Registro" id="Registro" style="display: block">
        <h5>Si alguno de los datos es incorrecto pulse aqui y vuelva a registrarse</h5>
        <button id="button" type="submit" >Volver a registrarme</button>
    </form>

    
</body>
</html>