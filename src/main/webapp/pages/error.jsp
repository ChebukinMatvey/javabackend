<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Error page</title>
    <link rel="stylesheet" href="/css/response.css">
</head>
<body>

<div class="info">
    <h1 style="color: red">${errorText}</h1>
    <a href="/home">StartPage</a>
</div>
</body>
</html>