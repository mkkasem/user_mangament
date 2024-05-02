<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>error page</title>
    <link rel="stylesheet" href="<c:url value="/resources/assets/custom/css/errorPage.css" />">

</head>
<body>
    <div class="content">
        <svg viewBox="0 0 960 300">
            <symbol id="s-text">
                <text text-anchor="middle" x="50%" y="50%">404</text>
            </symbol>

            <g class = "g-ants">
                <use xlink:href="#s-text" class="text"></use>
                <use xlink:href="#s-text" class="text"></use>
                <use xlink:href="#s-text" class="text"></use>
                <use xlink:href="#s-text" class="text"></use>
                <use xlink:href="#s-text" class="text"></use>
            </g>
        </svg>

        <h1>Page Not Found</h1>
        <a href="/">Back to Home</a>
    </div>
</body>
</html>