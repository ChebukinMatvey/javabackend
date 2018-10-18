<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Store</title>
  <link rel="stylesheet" href="css/main.css">
  <link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script src="js/jquery.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script src="js/script.js"></script>
</head>
<body>


<div class="popup login_popup" method="post">
  <div class="bg_popup" onclick="closeLoginPopup()"></div>
  <div class="content_popup">
    <form action="/login.do" method="post">
      <input type="text" placeholder="login" name="login">
      <input type="password" placeholder="password" name="pass">
      <input type="submit" value="Login">
    </form>
  </div>
</div>


<div class="popup reg_popup" >
  <div class="bg_popup" onclick="closeRegPopup()"></div>
  <div class="content_popup">
    <form action="/register.do" method="post">
      <input type="text" placeholder="login" name="login">
      <input type="password" placeholder="password" name="pass">
      <input type="submit" value="Register">
    </form>
  </div>
</div>


<div class="container">

  <header>
    <p class="inline">Make your life easier with apple</p>

    <c:if test="${sessionScope.User eq null}">
      <a href="#" class="inline" onclick="showLoginPopup()">Login</a>
      <a href="#" class="inline" onclick="showRegPopup()">Register</a>
    </c:if>
    <c:if test="${sessionScope.User ne null}">
      <a href="/pages/cart.jsp">Cart</a>
      <a href="/logout.do">Exit</a>
    </c:if>

  </header>

  <div class="filter">

    <form action="" id="filter_form">
      <div class="filter_block">
        <p class="label">Name</p>
        <select class="name" id="nameSelect">
          <option value="default" >Any</option>
          <option value="IPhone SE">IPhone SE</option>
          <option value="IPhone 7"> IPhone 7</option>
          <option value="IPhone 10"> IPhone 10</option>
          <option value="IPhone 6s"> IPhone 6s</option>
          <option value="IPhone 8"> IPhone 8</option>
        </select>
      </div>
      <div class="filter_block">
        <p class="label">Price</p>
        <input type="text" class="price-block" id="min" readonly value="0">
        <input type="text" class="price-block" id="max" readonly value="40000">
        <div id="slider-range"></div>
      </div>

      <div class="filter_block">
        <p class="label">Capacity</p>
        <select class="name" id="capacitySelect">
          <option value="default">Any</option>
          <option value="32">32</option>
          <option value="64"> 64</option>
          <option value="128"> 128</option>
        </select>
      </div>
      <input type="button" class="filter_button" onclick="goFilter()" value="Filter">
    </form>

  </div>

  <div class="contentcontainer">
    <div class="content">
      <div class="cart" id="item1">

        <div class="imgDiv" id="img1">
          <img src="img/${Content.firstItem.imgStr}" id="Item1Img" alt="">
        </div>

        <div class="info">
          <p class="infoText" id="Item1Name">Name: ${Content.firstItem.name}</p>
          <p class="infoText" id="Item1Capacity">Capacity: ${Content.firstItem.capacity} <span class="smaller">gb</span></p>
          <p class="infoText" id="Item1Price">Price: ${Content.firstItem.price} <span class="smaller">uah</span></p>
        </div>

        <button class="buyButton" id="buy1" onclick="buy(this)">Buy</button>

      </div>

      <div class="cart" id="item2">

        <div class="imgDiv" id="img2">
          <img src="img/${Content.secondItem.imgStr}"  id="Item2Img" alt="">
        </div>

        <div class="info">
          <p class="infoText" id="Item2Name">Name: ${Content.secondItem.name} </p>
          <p class="infoText" id="Item2Capacity">Capacity: ${Content.secondItem.capacity} <span class="smaller">gb</span></p>
          <p class="infoText" id="Item2Price">Price: ${Content.secondItem.price} <span class="smaller">uah</span></p>
        </div>

        <button class="buyButton" id="buy2" onclick="buy(this)">Buy</button>

      </div>

    </div>

    <div class="pages">
      <button onclick="prevpage()">&#8592;</button>
      <button onclick="nextpage()">&#8594;</button>
    </div>

  </div>



  <footer>

  </footer>

  <script>
  </script>

</div>
</body>
</html>