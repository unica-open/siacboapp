/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
$(function() {
    $.extend(jQuery.fn.simplePagination.defaults, {
        items_per_page : 10,
        number_of_visible_page_numbers : 5
    });
    
    $('#simple-pagination').simplePagination();
    $('.modalConsultaButton').click(function(event) {
        var button = $(this);
        $('#modalConsulta div.modal-body').html('Attendere, prego...');
        $.ajax({
            url : 'consultaFlusso.do?uid=' + button.data('uid-flusso') + "&codiceTipoOrdinativi=" + $('#codiceTipoFlussi').val(),
            cache : false
        }).done(function(html) {
            $('#modalConsulta div.modal-body').html(html);
            $('.codiceFlussoConsulta').html(button.data('numero-flusso'));
        });
    });
});
