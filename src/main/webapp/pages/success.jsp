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
    <h1 style="color: green">${successText}</h1>
    <a href="/items.do">StartPage</a>
    <a href="/pages/cart.jsp">Cart</a>
</div>

</body>
</html>