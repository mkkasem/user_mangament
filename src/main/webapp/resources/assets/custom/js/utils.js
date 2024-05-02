$(document).ready(function() {
    $('.selectpicker').selectpicker('val', $.cookie('lang'));

});
$(function(){
    $('.selectpicker').selectpicker();
});
$('#language-select').on('change', function() {
    let lang = this.value;
    let currentPageUrl = window.location.href;
    fetch('/change-language?lang=' + lang + '&referer=' + encodeURIComponent(currentPageUrl), {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (response.ok) {
                // Redirect to the new page after language change
                window.location.href = response.url;
            } else {
                console.error('Error changing language:', response.statusText);
                window.location.href =window.location.origin +"/errorPage";

            }
        })
        .catch(error => {
            console.error('Error changing language:', error);
        });
});

function showLoader(){
    $('.loading').removeClass("d-none");

}

function hideLoader(){
    $('.loading').addClass("d-none");

}
function showMessageModal(message,title){
    $("#MessageModalTitle").html(title);
    $("#MessageText").html(message);
    $("#messageModal").modal("show");
}