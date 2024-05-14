$(document).ready(function() {
    let selectedLanguage = $.cookie('lang') || "tr";
    $('#userTable').DataTable({
        "language":  getTranslation(selectedLanguage)
    });
});


function getTranslation(language){
    let languageTranslation = {
        "tr":
            {
                "sProcessing":   "İşleniyor...",
                "sLengthMenu":   "Sayfada _MENU_ Kayıt Göster",
                "sZeroRecords":  "Eşleşen Kayıt Bulunmadı",
                "sInfo":         "  _TOTAL_ Kayıttan _START_ - _END_ Arası Kayıtlar",
                "sInfoEmpty":    "Kayıt Yok",
                "sInfoFiltered": "( _MAX_ Kayıt İçerisinden Bulunan)",
                "sInfoPostFix":  "",
                "sSearch":       "Bul:",
                "sUrl":          "",
                "oPaginate": {
                    "sFirst":    "İlk",
                    "sPrevious": "Önceki",
                    "sNext":     "Sonraki",
                    "sLast":     "Son"
                }
            },
        "en":
            {
                "sEmptyTable":     "No data available in table",
                "sInfo":           "Showing _START_ to _END_ of _TOTAL_ entries",
                "sInfoEmpty":      "Showing 0 to 0 of 0 entries",
                "sInfoFiltered":   "(filtered from _MAX_ total entries)",
                "sInfoPostFix":    "",
                "sInfoThousands":  ",",
                "sLengthMenu":     "Show _MENU_ entries",
                "sLoadingRecords": "Loading...",
                "sProcessing":     "Processing...",
                "sSearch":         "Search:",
                "sZeroRecords":    "No matching records found",
                "oPaginate": {
                    "sFirst":    "First",
                    "sLast":     "Last",
                    "sNext":     "Next",
                    "sPrevious": "Previous"
                },
                "oAria": {
                    "sSortAscending":  ": activate to sort column ascending",
                    "sSortDescending": ": activate to sort column descending"
                }
            }
    }
    return languageTranslation[language]
}