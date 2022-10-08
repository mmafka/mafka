$(function () {
    // Summernote
    $('#soru').summernote()
    $('#cozum').summernote()

    // CodeMirror
    CodeMirror.fromTextArea(document.getElementById("codeMirrorDemo"), {
        mode: "htmlmixed",
        theme: "monokai"
    });
})
function getSoruMetni()
{
    return $('#soru').val();
}
function getCozum()
{
    return $('#cozum').val();
}