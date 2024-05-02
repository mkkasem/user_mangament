<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>



<table id="userTable" class="table table-striped table-bordered">
    <thead>
    <tr>
        <th> Id</th>
        <th> <spring:theme code="label.username"/></th>
        <th> <spring:theme code="label.email"/></th>
        <th> <spring:theme code="label.password"/></th>
        <th> <spring:theme code="label.phoneNumber"/></th>
        <th> <spring:theme code="label.birthDate"/></th>
        <sec:authorize access="hasAnyRole('ADMIN')">
        <th></th>
        </sec:authorize>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${userList}" var="user" varStatus="loop">
        <tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.email}</td>
            <td>*********</td>
            <td>${user.phoneNumber}</td>
            <td>${user.birthDate.toString().split(" ")[0]}</td>
            <sec:authorize access="hasAnyRole('ADMIN')">
                <td>
                    <button class="btn btn-primary" data-toggle="modal" data-target="#myModal"  onclick="openUpdateModal(${user.id})"><i class="fas fa-edit"></i> <spring:theme code="label.update"/></button>
                    <button type="submit" class="btn btn-danger" onclick="openDeleteModal(${user.id},'<spring:theme code="label.delete.message"/>')"><i class="fas fa-trash"></i> <spring:theme code="label.delete"/></button>
                </td>
            </sec:authorize>
        </tr>
    </c:forEach>
    </tbody>
</table>