$(document).ready(function() {
    $.fn.initBootTable = function() {
        $(this).bootstrapTable('destroy');
        $(this).bootstrapTable().
        unbind('check.bs.table').on('check.bs.table', function (e, row) {
            consUsuaRol([{name : 'codiPara', value : row.id.trim()}]);
        });
        return false;
    };
    INIT_OBJE_CORTO();
});

function INIT_OBJE_CORTO()
{
    $("#TablCorto").initBootTable();
}