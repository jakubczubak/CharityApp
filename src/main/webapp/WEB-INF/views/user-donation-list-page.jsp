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
    <span class="navbar-brand mb-0 h1">Lista moich darowizn:</span>
    <a href="/user/donations/archived/list">
        <button type="button" class="btn btn-success">Zarchiwizowane darowizny</button>
    </a>
    <a href="/donation">
        <button type="button" class="btn btn-primary">Powrót</button>
    </a>
</nav>
<c:if test="${param.success!=null}">
    <div class="alert alert-success text-center" role="alert">
        Pomyślnie usunięto darowiznę!
    </div>
</c:if>
<c:if test="${param.error!=null}">
    <div class="alert alert-danger text-center" role="alert">
        Nie można usunąć darowizny z statusem ODEBRANE/POTWIERDZONE/PRZEKAZANE.
        Możesz ją zarchiwizować!.
    </div>
</c:if>
<c:if test="${param.fail!=null}">
    <div class="alert alert-danger text-center" role="alert">
        Nie można edytować darowizny z statusem ODEBRANE/POTWIERDZONE/PRZEKAZANE.
        Możesz ją zarchiwizować!.
    </div>
</c:if>
<c:if test="${param.successarchive!=null}">
    <div class="alert alert-success text-center" role="alert">
        Pomyślnie zarchiwizowano darowizne!
    </div>
</c:if>
<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Kategoria darowizny:</th>
        <th scope="col">Ilość 60l worków:</th>
        <th scope="col">Wspierana fundacja:</th>
        <th scope="col">Status darowizny:</th>
        <th scope="col">Szczegóły:</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${userDonationList}" var="donation" varStatus="index">
        <tr>
            <th scope="row">${index.count}</th>
            <td>
                <c:forEach items="${donation.categories}" var="category">
                    -${category.name}<br>
                </c:forEach>
            </td>
            <td>${donation.quantity}x</td>
            <td>"${donation.institution.name}"</td>
            <td>
                <c:if test="${donation.status.name == 'Zlozone'}">
                    <button type="button" title="Złożone" class="btn btn-warning">Złożone</button>
                </c:if>
                <c:if test="${donation.status.name == 'Potwierdzone'}">
                    <button type="button" title="Złożone" class="btn btn-info">Potwierdzone</button>
                </c:if>
                <c:if test="${donation.status.name == 'Odebrane'}">
                    <button type="button" title="Odebrane" class="btn btn-primary">Odebrane</button>
                </c:if>
                <c:if test="${donation.status.name == 'Przekazane'}">
                    <button type="button" title="Przekazane" class="btn btn-success">Przekazane
                    </button>
                </c:if>
            </td>
            <td>
                <a href="/user/donation/details/${donation.id}">
                    <button type="button" title="szczegóły" class="btn btn-primary"><i class="fas fa-info-circle"></i>
                    </button>
                </a>
                <a href="/user/donation/archive/${donation.id}">
                    <button type="button" title="archiwizuj" class="btn btn-success"><i class="fas fa-archive"></i>
                    </button>
                </a>
                <a href="/user/donation/edit/${donation.id}">
                    <button type="button" title="edytuj" class="btn btn-warning"><i class="fas fa-edit"></i>
                    </button>
                </a>
                <a href="/user/donation/remove/${donation.id}">
                    <button type="button" title="usuń" class="btn btn-danger"><i class="fas fa-trash-alt"></i>
                    </button>
                </a>


            </td>
        </tr>
    </c:forEach>
    </tbody>
    </tbody>
</table>
</body>
</html>
