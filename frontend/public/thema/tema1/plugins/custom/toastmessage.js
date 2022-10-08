function basarili(text) {
    toastr.success(text)

}
function basarisiz(text) {
    toastr.error(text)
    }
    function uyari(text) {
        toastr.warning(text)
        }
    function disableModal(text) {
        $(".close").click();
        }