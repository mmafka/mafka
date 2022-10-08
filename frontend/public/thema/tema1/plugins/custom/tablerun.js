$("#table").DataTable({
    "responsive": true, "lengthChange": false, "autoWidth": false,"paging":false,"info":false,"ordering":false,"oLanguage": {"sZeroRecords": "", "sEmptyTable": ""},
    "buttons": ["copy", "pdf", "excel", "csv", "print", "colvis"]
}).buttons().container().appendTo('#table_wrapper .col-md-6:eq(0)');
