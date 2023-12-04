/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/

	
$(function() {
    $('#form').on('keyup keypress', function(e) {
        var keyCode = e.keyCode || e.which;
        if (keyCode === 13) {
            //  e.preventDefault();
            //  return false;
            //this.submit();
            $('#cerca').trigger('click');
            return true;
        }
    });

    var zTreeObj = $.fn.zTree.init($("#treeStruttAmm"), {
        check : {
            radioType : "all",
            enable : true,
            chkStyle : "radio"
        },
        data : {
           	key : {
           	    name : "descrizioneCompleta",
           	    children : "sottoElementi"
        	}
        }
    }, elencoStruttureAmministrativoContabiliJson);
    
    $('#confermaStrutturaAmministrativoContabile').click(function(e) {    // FIXME fare funzione in ztree.js
        var checkedNodes = zTreeObj.getCheckedNodes(true);
        
        if (checkedNodes.length > 0) {
            $('#idStrutturaAmministrativoContabile').val(checkedNodes[0].uid);
            $('#SPAN_StrutturaAmministrativoContabile').text(checkedNodes[0].descrizioneCompleta);
        }
    });
    $('#deselezionaStrutturaAmministrativoContabile').click(function(e) {
        var checkedNodes = zTreeObj.getCheckedNodes(true);
        if (checkedNodes.length > 0) {
            zTreeObj.checkNode(checkedNodes[0], false, true, false);
        }
        e.stopPropagation();
        return false;
    });
});