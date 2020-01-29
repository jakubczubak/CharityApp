<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/sb-admin-2.min.css"/>"/>
    <script src="https://kit.fontawesome.com/c395dd8104.js" crossorigin="anonymous"></script>

</head>
<body>
<nav class="navbar navbar-light bg-light">
    <span class="navbar-brand mb-0 h1">Szczegóły przekazanej darowizny:</span>
    <span class="navbar-brand mb-0 h1"></span>

    <a href="/user/donations">
        <button type="button" class="btn btn-primary">Powrót</button>
    </a>
</nav>

<div class="row">
    <div class="card-body">
        <h5 style="color: black">Kategoria darowizny:</h5>
        <c:forEach items="${currentDonation.categories}" var="category">
            -${category.name}<br>
        </c:forEach>
        <h5 style="color: black">Ilość oddanych worków:</h5>
        ${currentDonation.quantity}x<br>
        <h5 style="color: black">Informacje nt. wspieranej instytucji:</h5>
        Fundacja: "${currentDonation.institution.name}"<br>
        Cel i misja: ${currentDonation.institution.description}<br>
    </div>
    <div class="card-body">
        <h5 style="color: black">Dane darczyńcy:</h5>
        ${currentDonation.user.firstName}<br>
        ${currentDonation.user.lastName}<br>
        ${currentDonation.user.email}<br>
        ${currentDonation.phoneNumber}<br>
    </div>
    <div class="card-body">
        <h5 style="color: black">Adres odbioru darowizny:</h5>
        ${currentDonation.city}<br>
        ${currentDonation.zipCode}<br>
        ${currentDonation.street}<br>
        <h5 style="color: black">Termin odbioru darowizny:</h5>
        ${currentDonation.pickUpTime}<br>
        ${currentDonation.pickUpDate}
    </div>

</div>
<div>

    <c:if test="${currentDonation.status.name == 'Zlozone'}">
        <button type="button" title="Złożone" class="btn btn-warning btn-lg btn-block">Złożone</button>
    </c:if>
    <c:if test="${currentDonation.status.name == 'Odebrane'}">
        <button type="button" title="Odebrane" class="btn btn-primary btn-lg btn-block">Odebrane</button>
    </c:if>
    <c:if test="${currentDonation.status.name == 'Przekazane'}">
        <button type="button" title="Przekazane" class="btn btn-success btn-lg btn-block">Przekazane</button>
    </c:if>
</div>
</body>
</html>
