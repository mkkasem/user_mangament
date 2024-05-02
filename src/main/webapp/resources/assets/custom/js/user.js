let typingTimer;
const doneTypingInterval = 1000; // half a second


function openCreateUserModal(id){
    $("#usernameError").addClass("d-none");
    $("#createUserBtn").prop("disabled",false);
    $(id).find("input").each((index,ele)=>{
        if ($(ele).prop("type")==="checkbox")
            $(ele).prop("checked",false);
        else
            $(ele).val("")
    })
    $(id).modal("show");
}


function createNewUser() {

    let username = $("#username").val();
    let email = $("#email").val();
    let password = $("#password").val();
    let phoneNumber = $("#phoneNumber").val();
    let birthDate = $("#birthDate").val();
    let admin = $("#isAdmin").prop("checked");

    const req= {
        username,
        email,
        password,
        phoneNumber,
        birthDate,
        admin
    }
    showLoader();

    $.ajax({
        async: true,
        cache: false,
        method: "POST",
        dataType: "json",
        data: JSON.stringify(req),
        contentType: "application/json",
        headers: {
            "Content-Type": "application/json"
        },
        url: location.protocol + "//" + location.host + "/user/",
        success: function (response) {
            hideLoader();
            if (response?.code ===201) {
                showMessageModal(response.message,successLocalized);
                $("#createUserModal").modal("hide");
                setTimeout(function (){
                    window.location.reload();
                },3000);
            }else{
                showMessageModal(internalErrorLocalized,errorLocalized);
            }
        },
        error: function (response) {
            let errorMessage="";
            hideLoader();
            if (response?.responseJSON?.data){
                for (const [key, value] of Object.entries(response.responseJSON.data)) {
                    errorMessage+="<p>"+ value + "</p>"
                }
            }
            showMessageModal(errorMessage,errorLocalized);
            return false;
        }
    });
}


function isUserNameAlreadyTaken(spanId,buttonId,isUpdate){
    let username;
    if (isUpdate){
         username = $("#updateUsername").val();
         if (username===$("#originalUsername").val())
             return;
    }
    else
        username = $("#username").val();

    if (username === "")
        return;
    var url = location.protocol + "//" + location.host + "/user/exists/"+username;
    $.ajax({
        async: true,
        cache: false,
        method: "GET",
        dataType: "json",
        contentType: "application/json",
        headers: {
            "Content-Type": "application/json"
        },
        url: url,
        success: function (response) {
            hideLoader();
            if (response.data === true) {
                $(spanId).html(response.message);
                $(spanId).removeClass("d-none");
                $(buttonId).prop("disabled",true);
            }
        },
        error: function (data, textStatus) {
            showMessageModal(internalErrorLocalized,errorLocalized);
            $(buttonId).prop("disabled",true);
        }
    });
}

function checkIfUserNameAlreadyTakenAfterSecond(spanId,buttonId,isUpdate) {
    $(spanId).addClass("d-none");
    $(buttonId).prop("disabled",false);
    clearTimeout(typingTimer);
    typingTimer = setTimeout(()=>isUserNameAlreadyTaken(spanId,buttonId,isUpdate), doneTypingInterval);
};