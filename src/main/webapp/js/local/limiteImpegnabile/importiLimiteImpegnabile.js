/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/

$(function() {
    var checkRegex = /^([1-9]\d*|0)(\,|\,\d{0,2})?$/;
    
    $('#form').on('keyup keypress', function(e) {
        var keyCode = e.keyCode || e.which;
        if (keyCode === 13) {
            e.preventDefault();
            return false;
        }
    });
    var selected = false;
    
    $('input.importoAnno').keypress(function(e) {
        if (e.which < 32) {
            return true;
        }
        var t = [$(this).val().slice(0,this.selectionStart), e.key, $(this).val().slice(this.selectionEnd) ].join('');
        if (t.match(/^([1-9]\d*|0)(\,|\,\d{0,2})?$/) != null) {
            // $(this).val(t);
            selected = false;
            return true;
        }
        return false;
    }).focus(function(e) {
        $(this).val($(this).val().replace(/\./g, ''));
    }).blur(function(e) {
        $(this).val($(this).val().replace(/\./g, ''));
        
        if (!checkRegex.test($(this).val())) {
            $(this).val('')
        } else {
            $(this).val(
                $(this).val().replace(/[^\d\,]/g,'')
                    .replace(/^(\d+)\,?$/, '$1,00')
                    .replace(/(\,\d)$/, '$10')
                    .replace(/\B(?=(\d{3})+(?!\d))/g, '.'));
        }
    }).select(function(e) {
        selected = true;
    });
});
   