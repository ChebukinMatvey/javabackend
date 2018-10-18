<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html >
<head>
    <meta charset="UTF-8">
    <title>Cart</title>
    <link rel="stylesheet" href="/css/cart.css">
    <script src="/js/jquery.js"></script>
    <script src="/js/script.js"></script>
</head>
<body>

<div class="orderForm">
    <div class="orderFormBg" onclick="closeOrderFrom()"></div>
    <div  CLASS="content_popup">
        <form action="/order.do" method="post">
            <input type="text" placeholder="Adress" name="adress">
            <input type="email" placeholder="Email" name="email">
            <input type="submit" value="Go">
        </form>
    </div>
</div>

<header>
    <p>Welcome to cart ${sessionScope.User.getLogin()}</p>
</header>

<c:if test="${!sessionScope.Cart.isEmpty()}">
    <div class="ItemsInfo  container">
            <div>
                <h1>Your goods</h1>
            </div>
            <div class="Items">
                <table border="1px" cellpadding="10px">
                    <tr class="headers">
                        <td>Name</td>
                        <td>Capacity</td>
                        <td>Price</td>
                        <td></td>
                    </tr>
                    <c:forEach items="${sessionScope.Cart.iterator()}" var="item">
                        <tr>
                            <td>${item.getName()}</td>
                            <td>${item.getCapacity()}</td>
                            <td>${item.getPrice()}</td>
                            <td><button class="deletebutton" onclick="deleteProduct(this)">x</button></td>
                        </tr>
                    </c:forEach>
                </table>
                <h1>Total price: ${sessionScope.Cart.TotalPrice()}</h1>
                <button class="MakeOrder" onclick="showOrderForm()"> Make order </button>
            </div>
    </div>
</c:if>

<c:if test="${sessionScope.Cart.isEmpty()}">
    <h1>No goods</h1>
</c:if>



<script>
    function closeOrderFrom(){
        document.querySelector(".orderForm").style.display="none";
    }
    function showOrderForm(){
        document.querySelector(".orderForm").style.display="block";
    }
</script>

</body>
</html>