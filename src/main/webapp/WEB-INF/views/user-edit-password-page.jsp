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
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header class="header--form-page">
    <nav class="container container--70">
        <ul class="nav--actions">
            <li class="logged-user">
                Witaj ${fullName}
                <ul class="dropdown">
                    <li><a href="/user/edit">Ustawienia</a></li>
                    <li><a href="/user/donations">Moje zbiórki</a></li>
                    <li><a href="/logout">Wyloguj</a></li>
                </ul>
            </li>
        </ul>

        <%@include file="navimenu.jsp" %>

    </nav>

    <div class="slogan container container--90">
        <div class="slogan--item">
            <h1>
                Oddaj rzeczy, których już nie chcesz<br/>
                <span class="uppercase">potrzebującym</span>
            </h1>

            <div class="slogan--steps">
                <div class="slogan--steps-title">Wystarczą 4 proste kroki:</div>
                <ul class="slogan--steps-boxes">
                    <li>
                        <div><em>1</em><span>Wybierz rzeczy</span></div>
                    </li>
                    <li>
                        <div><em>2</em><span>Spakuj je w worki</span></div>
                    </li>
                    <li>
                        <div><em>3</em><span>Wybierz fundację</span></div>
                    </li>
                    <li>
                        <div><em>4</em><span>Zamów kuriera</span></div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</header>

<section class="login-page">
    <c:if test="${param.success!=null}">
        <div class="alert alert-success" role="alert">
            <h1>******Pomyślnie zmieniono hasło******</h1>
        </div>
    </c:if>
    <c:if test="${param.error!=null}">
        <div class="alert alert-success" role="alert">
            <h1>******Niepoprawne dane******</h1>
        </div>
    </c:if>
    <h2>Edytuj hasło:</h2>
    <form:form method="post" modelAttribute="passwordDTO" action="/user/edit/password">
        <div class="form-group">
            <form:input path="oldPassword" type="password" name="oldPassword" placeholder="Stare hasło"/>
            <form:errors cssClass="error" path="oldPassword" element="div"/>
        </div>
        <div class="form-group">
            <form:input path="password" type="password" name="password" placeholder="Hasło"/>
            <form:errors cssClass="error" path="password" element="div"/>
        </div>
        <div class="form-group">
            <form:input path="rePassword" type="password" name="rePassword" placeholder="Nowe hasło"/>
            <form:errors cssClass="error" path="rePassword" element="div"/>
        </div>

        <div class="form-group form-group--buttons">
            <button class="btn" type="submit">Zmień hasło</button>
        </div>

    </form:form>

</section>

<%@include file="footer.jsp" %>


<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
