function deleteUser(id){
    if (id === "")
        return;
    let url = location.protocol + "//" + location.host + "/user/"+id;
    showLoader();
    $.ajax({
        async: true,
        cache: false,
        method: "DELETE",
        dataType: "json",
        contentType: "application/json",
        headers: {
            "Content-Type": "application/json"
        },
        url: url,
        success: function (response) {
            hideLoader();
            if (response?.code ===200) {
                showMessageModal(response.message,successLocalized);
                setTimeout(function (){
                    window.location.reload();
                },3000);
            }else{
                showMessageModal(internalErrorLocalized,errorLocalized);
            }
        },
        error: function () {
            hideLoader();
            showMessageModal(internalErrorLocalized,errorLocalized);
            return false;
        }
    });
}


function openDeleteModal(id, deleteMessage){
    $('#deleteUserBtn').unbind("click")
    $('#deleteUserBtn').bind('click', ()=> deleteUser(id));
    $("#deleteModalBody").text(deleteMessage.replace("{X}",id));
    $("#deleteUserModal").modal("show");
}

function openUpdateModal(id){

    if (id === "")
        return;
    var url = location.protocol + "//" + location.host + "/user/"+id;
    showLoader();
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
            if (response?.code ===200) {
                fillUpdateUserModal(response.data);
                $("#updateUserModal").modal("show");
            }
        },
        error: function (data, textStatus) {
            showMessageModal(internalErrorLocalized,errorLocalized);
            return false;
        }
    });
}

function fillUpdateUserModal(user){
    $("#updateId").val(user.id)
    $("#updateUsername").val(user.username)
    $("#originalUsername").val(user.username)
    $("#updateEmail").val(user.email)
    $("#updatePhoneNumber").val(user.phoneNumber)
    let birthDate = new Date(user.birthDate).toLocaleDateString().split('T')[0]
        .replaceAll("/","-").split("-"
        ).map(ele =>  ele.length === 1 ? "0"+ele : ele)
        .reverse();
    birthDate = birthDate[0]+"-"+birthDate[2]+"-"+birthDate[1]

    $("#updateBirthDate").val(birthDate)
    $("#updateIsAdmin").prop("checked",user.admin)
}



function updateUser(){
    let id= $("#updateId").val();
    let username= $("#updateUsername").val();
    let email= $("#updateEmail").val();
    let phoneNumber= $("#updatePhoneNumber").val();
    let birthDate= $("#updateBirthDate").val();
    let admin= $("#updateIsAdmin").prop("checked");


    const req = {
        id,
        username,
        email,
        phoneNumber,
        birthDate,
        admin
    }
    $.ajax({
        async: true,
        cache: false,
        method: "PUT",
        dataType: "json",
        data: JSON.stringify(req),
        contentType: "application/json",
        headers: {
            "Content-Type": "application/json"
        },
        url: location.protocol + "//" + location.host + "/user/",
        success: function (response) {
            hideLoader();
            if (response?.code ===200) {
                showMessageModal(response.message,successLocalized);
                $("#updateUserModal").modal("hide");
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


















