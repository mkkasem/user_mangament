<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html;encoding=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<c:url value="/resources/assets/css/bootstrap.min.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/assets/custom/css/style.css" />">
    <link href="<c:url value="/resources/assets/css/bootstrap-select.min.css" />" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value="/resources/assets/css/flags/flag.css" />" >

</head>
<body>

    <section> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>

        <div class="signin">

            <div class="content">

                <h2><spring:theme code="login"/></h2>
                <div class="inputBox">

                    <select class="selectpicker" data-width="fit" id="language-select">
                        <option value="tr" data-content='<div class="flag-icon  flag-icon-tr"></div> Türkiye'>Türkiye</option>
                        <option  value="en" data-content='<div class="flag-icon flag-icon-us"></div> English'>English</option>
                    </select>
                </div>
                <form:form cssClass="form" action="/spring_security_check" method="POST" modelAttribute="loginForm">
                    <div class="inputBox">

                        <input type="text" required name="userName"> <i><spring:theme code="login.username"/></i>

                    </div>

                    <div class="inputBox">
                        <input type="password" required name="password"> <i><spring:theme code="login.password"/></i>
                    </div>

                    <div class="inputBox">

                        <input type="submit" value="Login">

                    </div>

                </form:form>

            </div>

        </div>

    </section>


<script src="<c:url value="/resources/assets/js/jquery.min.js" />"></script>
<script src="<c:url value="/resources/assets/js/popper.min.js" />"></script>
<script src="<c:url value="/resources/assets/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/assets/js/bootstrap-select.min.js" />"></script>
<script src="<c:url value="/resources/assets/custom/js/utils.js"/>"></script>
<script src="<c:url value="/resources/assets/js/jquery.cookie.min.js"/>"></script>

<script>
    $(document).ready(function(){
        if (${param.error == 'true'}) {
            showMessageModal("<spring:theme code="incorrect.credentials"/>","<spring:theme code="label.error"/>")
        }
    });
</script>

<tags:messsageModal/>

</body>

</html>
