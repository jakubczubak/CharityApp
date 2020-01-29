<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>CharityAPP USER - Dashboard</title>

    <!-- Custom fonts for this template-->
    <link href="<c:url value="/resources/vendor/fontawesome-free/css/all.min.css"/>" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
          rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="<c:url value="/resources/css/sb-admin-2.min.css"/>" rel="stylesheet">

</head>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

        <!-- Sidebar - Brand -->
        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="/admin">
            <div class="sidebar-brand-icon rotate-n-15">
                <i class="fas fa-laugh-wink"></i>
            </div>
            <div class="sidebar-brand-text mx-3">ADMIN PANEL</div>
        </a>

        <!-- Divider -->
        <hr class="sidebar-divider my-0">

        <!-- Nav Item - Dashboard -->
        <li class="nav-item active">
            <a class="nav-link" href="/admin">
                <i class="fas fa-fw fa-tachometer-alt"></i>
                <span>Dashboard</span></a>
        </li>

        <!-- Divider -->
        <hr class="sidebar-divider">

        <!-- Heading -->
        <div class="sidebar-heading">
            Zarządzaj:
        </div>

        <!-- Nav Item - Tables -->
        <%@include file="navIteam.jsp" %>

        <!-- Sidebar Toggler (Sidebar) -->
        <div class="text-center d-none d-md-inline">
            <button class="rounded-circle border-0" id="sidebarToggle"></button>
        </div>

    </ul>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <!-- Topbar -->
            <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                <!-- Sidebar Toggle (Topbar) -->
                <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                    <i class="fa fa-bars"></i>
                </button>


                <!-- Topbar Navbar -->
                <ul class="navbar-nav ml-auto">


                    <div class="topbar-divider d-none d-sm-block"></div>

                    <!-- Nav Item - User Information -->
                    <li class="nav-item dropdown no-arrow">
                        <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span class="mr-2 d-none d-lg-inline text-gray-600 small">${fullName}</span>
                            <i class="fas fa-user fa-2x"></i>
                        </a>
                        <!-- Dropdown - User Information -->
                        <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                             aria-labelledby="userDropdown">

                            <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                Wyloguj
                            </a>
                        </div>
                    </li>

                </ul>

            </nav>
            <!-- End of Topbar -->

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <!-- Page Heading -->
                <div class="d-sm-flex align-items-center justify-content-between mb-4">
                    <h1 class="h3 mb-0 text-gray-800">Lista użytkowników:</h1>
                    <a href="/admin/user/block/list" class="d-none d-sm-inline-block btn  btn-danger shadow-sm"><i
                            class="fas fa-user-slash"></i> Zablokowani użytkownicy:</a>
                </div>

                <c:if test="${param.successremove!=null}">
                    <div class="alert alert-success text-center" role="alert">
                        Pomyślnie usunąłeś użytkownika!
                    </div>
                </c:if>
                <c:if test="${param.successblock!=null}">
                    <div class="alert alert-success text-center" role="alert">
                        Pomyślnie zablokowałeś użytkownika!
                    </div>
                </c:if>
                <c:if test="${param.successedit!=null}">
                    <div class="alert alert-success text-center" role="alert">
                        Pomyślnie edytowałeś dane dotyczące użytkownika!
                    </div>
                </c:if>
                <div class="row">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Imię i Nazwisko:</th>
                            <th scope="col">Adres email:</th>
                            <th scope="col">Akcje:</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${users}" var="user" varStatus="index">
                            <tr>
                                <th scope="row">${index.count}</th>
                                <td>${user.fullName}</td>
                                <td>${user.email}</td>
                                <td>
                                    <a href="/admin/user/edit/${user.id}">
                                        <button type="button" class="btn btn-primary"><i class="fas fa-edit"></i>
                                        </button>
                                    </a>
                                    <a href="/admin/user/remove/${user.id}">
                                        <button type="button" class="btn btn-danger"><i class="fas fa-trash-alt"></i>
                                        </button>
                                    </a>
                                    <a href="/admin/user/block/${user.id}">
                                        <button type="button" class="btn btn-danger"><i class="fas fa-ban"></i></button>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>


            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- End of Main Content -->

        <!-- Footer -->
        <footer class="sticky-footer bg-white">
            <div class="container my-auto">
                <div class="copyright text-center my-auto">
                    <span>CharityAPP &copy; Jakub Czubak 2020</span>
                </div>
            </div>
        </footer>
        <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<!-- Logout Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Na pewno chcesz się wylogować?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">Naciśnij "Wyloguj" jeśli chcesz zakończyć obecną sesję.</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Anuluj</button>
                <a class="btn btn-primary" href="/logout">Wyloguj</a>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript-->
<script src="<c:url value="/resources/vendor/jquery/jquery.min.js"/>"></script>
<script src="<c:url value="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"/>"></script>

<!-- Core plugin JavaScript-->
<script src="<c:url value="/resources/vendor/jquery-easing/jquery.easing.min.js"/>"></script>

<!-- Custom scripts for all pages-->
<script src="<c:url value="/resources/js/sb-admin-2.min.js"/>"></script>

<!-- Page level plugins -->
<script src="<c:url value="/resources/vendor/chart.js/Chart.min.js"/>"></script>

<!-- Page level custom scripts -->
<script src="<c:url value="/resources/js/demo/chart-area-demo.js"/>"></script>
<script src="<c:url value="/resources/js/demo/chart-pie-demo.js"/>"></script>

</body>

</html>
