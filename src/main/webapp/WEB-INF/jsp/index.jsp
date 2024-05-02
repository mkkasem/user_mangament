<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html;encoding=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Management</title>
    <link rel="stylesheet" href="<c:url value="/resources/assets/css/bootstrap.min.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/assets/custom/css/spinner.css" />">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/assets/css/dataTable.css" />">
    <link href="<c:url value="/resources/assets/css/bootstrap-select.min.css" />" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value="/resources/assets/css/flags/flag.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/assets/custom/css/dataTable.css" />">

</head>
<body>
<div class="container mt-5">
    <spring:theme code="label.username"/> : <%=request.getSession().getAttribute("username")%>

    <div class="text-center m-4">
        <select class="selectpicker" data-width="fit" id="language-select">
            <option value="tr" data-content='<div class="flag-icon  flag-icon-tr"></div> Türkiye'>Türkiye</option>
            <option  value="en" data-content='<div class="flag-icon flag-icon-us"></div> English'>English</option>
        </select>
    </div>

    <div class="text-right m-4">

        <form action="/logout" method="post">
            <button type="submit" class="btn btn-danger"><i class="fas fa-sign-out-alt"></i> <spring:theme code="label.logout"/></button>
        </form>
    </div>

    <div class="row mb-4">
        <div class="col-md-6">
            <button class="btn btn-primary" data-toggle="modal" onclick="openCreateUserModal('#createUserModal')"><i class="fas fa-plus-circle"></i> <spring:theme code="label.add.user"/></button>
        </div>
    </div>

    <h1 class="text-center mb-4"><spring:theme code="label.user.list"/> </h1>
    <div class="table-responsive">
        <tags:usersTable/>
    </div>

</div>



<script src="<c:url value="/resources/assets/js/jquery.min.js" />"></script>
<script src="<c:url value="/resources/assets/js/popper.min.js" />"></script>
<script src="<c:url value="/resources/assets/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/assets/js/bootstrap-select.min.js" />"></script>
<script src="<c:url value="/resources/assets/js/jquery.cookie.min.js"/>"></script>
<script src="<c:url value="/resources/assets/js/jquery.dataTables.min.js"/>"></script>
<script src="<c:url value="/resources/assets/js/dataTables.bootstrap4.min.js"/>"></script>

<script src="<c:url value="/resources/assets/custom/js/utils.js"/>"></script>
<script src="<c:url value="/resources/assets/custom/js/dataTable.js"/>"></script>
<script src="<c:url value="/resources/assets/custom/js/user.js"/>"></script>

<sec:authorize access="hasAnyRole('ADMIN')">
    <script src="<c:url value="/resources/assets/custom/js/adminUser.js"/>"></script>
</sec:authorize>

<div class="loading d-none" ></div>
<script>
    let successLocalized = "<spring:theme code="label.success"/>";
    let errorLocalized = "<spring:theme code="label.error"/>";
    let internalErrorLocalized = "<spring:theme code="label.internal.server.error"/>";
</script>
</body>
<tags:createUserModal/>
<sec:authorize access="hasAnyRole('ADMIN')">
    <tags:deleteUserModal/>
    <tags:updateUserModal/>
</sec:authorize>
<tags:messsageModal/>

</html>
