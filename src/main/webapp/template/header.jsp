<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <c:set var="url">${pageContext.request.requestURL}</c:set>
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />
    <title>Rental Kars</title>
    <link rel="stylesheet" href="https://bootswatch.com/5/cerulean/bootstrap.css">
    <link rel="stylesheet" href="https://bootswatch.com/_vendor/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://bootswatch.com/_vendor/prismjs/themes/prism-okaidia.css">
    <script>var base = document.getElementsByTagName("base")[0].href;</script>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <div class="container-fluid">
        <a class="navbar-brand" href="index.jsp">RentalKars</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarColor01">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link" href="CarServlet">Parco Auto</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="user/dashboard.jsp">Account</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="admin/dashboard.jsp">Admin</a>
                </li>

            </ul>
        </div>
    </div>
</nav>
<br>