<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<style>
    input[type="checkbox"] {
        height: 1.3em;
    }
</style>

<div class="modal fade" id="updateUserModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel"><spring:theme code="label.update.user"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <input type="text" hidden id="originalUsername">
                <form>
                    <div class="form-group">
                        <label for="updateId">Id</label>
                        <input type="text" class="form-control" id="updateId" readonly>
                    </div>
                    <div class="form-group">
                        <label for="updateUsername"><spring:theme code="label.username"/></label>
                        <input type="text" class="form-control" id="updateUsername" onfocusout="checkIfUserNameAlreadyTakenAfterSecond('#updateUsernameError','#updateUserBtn',true)">
                        <small class="text-danger d-none" id="updateUsernameError"></small>
                    </div>
                    <div class="form-group">
                        <label for="updateEmail"><spring:theme code="label.email"/></label>
                        <input type="text" class="form-control" id="updateEmail">
                    </div>
                    <div class="form-group">
                        <label for="updatePhoneNumber"><spring:theme code="label.phoneNumber"/></label>
                        <input type="text" class="form-control" id="updatePhoneNumber">
                    </div>
                    <div class="form-group">
                        <label for="updateBirthDate"><spring:theme code="label.birthDate"/></label>
                        <input type="date" class="form-control" id="updateBirthDate">
                    </div>
                    <sec:authorize access="hasAnyRole('ADMIN')">

                        <div class="form-group">
                            <label for="updateIsAdmin" class="form-check-label"><spring:theme code="label.isAdmin"/></label>
                            <input type="checkbox" class="form-check-input col-6" role="switch" id="updateIsAdmin">
                        </div>
                    </sec:authorize>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal"><spring:theme code="label.close"/></button>
                <button id="updateUserBtn" type="button" class="btn btn-primary" onclick="updateUser()"><spring:theme code="label.update.user"/></button>
            </div>
        </div>
    </div>
</div>