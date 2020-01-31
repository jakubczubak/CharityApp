<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header class="header--form-page">
    <nav class="container container--70">
        <ul class="nav--actions">
            <sec:authorize access="hasAnyRole('ADMIN','USER')">

                <li class="logged-user">

                    Witaj ${fullName}
                    <ul class="dropdown">
                        <li><a href="/user/edit">Ustawienia</a></li>
                        <li><a href="/user/donations">Moje zbiórki</a></li>
                        <li><a href="/logout">Wyloguj</a></li>
                    </ul>
                </li>
            </sec:authorize>
        </ul>

        <%@include file="navimenu.jsp" %>
        >

    </nav>

    <div style="padding-top: 100px">
        <h2>
            Niepoprawnie wypełniony formularz kontaktowy!
        </h2>
        <div class="login-page">
            <c:forEach items="${contactErrorList}" var="error" varStatus="index">
                <label class="error">${index.count}.${error.name}</label>
            </c:forEach>
        </div>


    </div>
</header>

<%@include file="footer.jsp" %>


<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
