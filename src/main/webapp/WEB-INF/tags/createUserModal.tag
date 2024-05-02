<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<style>
    input[type="checkbox"] {
        height: 1.3em;
    }
</style>

<div class="modal fade" id="createUserModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel"><spring:theme code="label.add.user"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <label for="username"><spring:theme code="label.username"/></label>
                        <input type="text" class="form-control" id="username" onkeyup="checkIfUserNameAlreadyTakenAfterSecond('#usernameError','#createUserBtn',false)">
                        <small class="text-danger d-none" id="usernameError"></small>
                    </div>
                    <div class="form-group">
                        <label for="email"><spring:theme code="label.email"/></label>
                        <input type="text" class="form-control" id="email">
                    </div>
                    <div class="form-group">
                        <label for="password"><spring:theme code="label.password"/></label>
                        <input type="password" class="form-control" id="password" pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*\W)(?!.* ).{8,16}$">
                    </div>
                    <div class="form-group">
                        <label for="phoneNumber"><spring:theme code="label.phoneNumber"/></label>
                        <input type="text" class="form-control" id="phoneNumber">
                    </div>
                    <div class="form-group">
                        <label for="birthDate"><spring:theme code="label.birthDate"/></label>
                        <input type="date" class="form-control" id="birthDate">
                    </div>
                    <sec:authorize access="hasAnyRole('ADMIN')">

                    <div class="form-group">
                        <label for="isAdmin" class="form-check-label"><spring:theme code="label.isAdmin"/></label>
                        <input type="checkbox" class="form-check-input col-6" role="switch" id="isAdmin">
                    </div>
                    </sec:authorize>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button id="createUserBtn" type="button" class="btn btn-primary" onclick="createNewUser()">create user</button>
            </div>
        </div>
    </div>
</div>