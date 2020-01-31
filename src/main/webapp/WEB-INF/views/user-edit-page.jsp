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
            <h1>******Pomyślnie zmieniono dane******</h1>
        </div>
    </c:if>
    <h2>Edytuj dane:</h2>
    <form:form method="post" modelAttribute="user" action="/user/edit">
        <div class="form-group">
            <form:input path="firstName" type="text" name="firstName" placeholder="Imię"/>
            <form:errors cssClass="error" path="firstName" element="div"/>
        </div>
        <div class="form-group">
            <form:input path="lastName" type="text" name="lastName" placeholder="Nazwisko"/>
            <form:errors cssClass="error" path="lastName" element="div"/>
        </div>
        <div class="form-group">
            <form:input path="email" type="email" name="email" placeholder="Email"/>
            <form:errors cssClass="error" path="email" element="div"/>
        </div>

        <form:hidden path="id"></form:hidden>
        <div class="form-group form-group--buttons">
            <button class="btn" type="submit">Zapisz zmiany</button>
            <a href="/user/edit/password">
                <button class="btn" type="button">Zmień hasło</button>
            </a>
        </div>

    </form:form>

</section>

<%@include file="footer.jsp" %>


<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
