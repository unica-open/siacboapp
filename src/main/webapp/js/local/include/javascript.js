/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/

			$(document).ready(function() {
				$('#risultatiricerca').dataTable(
				{	
				"sScrollY": 300,
        		"sDom": "<'row datatableth'r>t<'row datatableth'<'span6'><'span6'>>",
				"sPaginationType": "bootstrap",
				"oLanguage": {
				<!--"sLengthMenu": "Display _MENU_ records per page",-->

				"sProcessing": "Attendere prego...",
				"sZeroRecords": "Non sono presenti risultati di ricerca secondo i parametri inseriti",
            	"sInfo": "_START_ - _END_ di _TOTAL_ risultati",
 				"sInfoEmpty": "0 - 0 di 0 risultati",
				"sInfoFiltered": "(filtrato su _MAX_ risultati)",
				"sInfoPostFix": "",
				"sSearch": "Cerca",
				"sUrl": "",
				"oPaginate": {
					"sFirst":    "Prima",
					"sPrevious": "Prec.",
					"sNext":     "Succ",
					"sLast":     "Ultima"
				}
				}
				}
				);
			} );





			
	$(document).ready(function() {
				$('table.oggettiTrovati').dataTable(
			{
			"sDom": "<'row datatableth'<'span12'l>r>t<'row datatableth'<'span6'i><'span6'p>>",
			"sPaginationType": "bootstrap",
			"oLanguage": {
			<!--"sLengthMenu": "Display _MENU_ records per page",-->

			"sProcessing": "Attendere prego...",
			"sZeroRecords": "Non sono presenti risultati di ricerca secondo i parametri inseriti",
			"sInfo": "_START_ - _END_ di _TOTAL_ risultati",
			"sInfoEmpty": "0 - 0 di 0 risultati",
			"sInfoFiltered": "(filtrato su _MAX_ risultati)",
			"sInfoPostFix": "",
			"sSearch": "Cerca",
			"sUrl": "",
			"oPaginate": {
				"sFirst":    "Prima",
				"sPrevious": "Prec.",
				"sNext":     "Succ",
				"sLast":     "Ultima"
			}
			}
			}
			);
		} );




	
		$(document).ready(function() {
        $('.btnVisible').hide();
		var Statusbtn = true;	
        var btnCheck=$('input[name=optionsRadios_riacc]');
		var btnIn=$('a[name=btnInvisible]');
        $(btnCheck).click(function(){ 
			var optRad=$(this).filter(':checked').val();
			//alert(radioVal);
			if(optRad=="si"){            
            $('.btnVisible').show();
            }else{
              $('.btnVisible').hide();
            } 
        });
		
		 $(btnIn).click(function(){ 
			$('.btnVisible').hide();
        });
		
		});



		
		$(document).ready(function() {
        $('.riaccVisible').hide();
        var riacc=$('input[name=optionsRadios_riacc]');
        $(riacc).click(function(){          
           var optRad=$(this).filter(':checked').val();
           //alert(radioVal);
           if(optRad=="si"){            
            $('.riaccVisible').show();
            }else{
              $('.riaccVisible').hide();
            } 
        });
 
      });


	      
      $(document).ready(function() {
        $('.campiSubimpegno').hide();
        $('.datiVisibili2').hide();
         $('.datiVisibili1').show();
        var abbina=$('input[name=optAbbina]');
        $(abbina).click(function(){          
           var optAbbina=$(this).filter(':checked').val();
          // alert(optAbbina);
           if(optAbbina=="impegno"){            
            $('.campiSubimpegno').hide();
            $('.datiVisibili2').hide();
              $('.datiVisibili1').show();
              $('#subimpegnoNum').val('');
            }else if(optAbbina=="subimpegno"){
              $('.campiSubimpegno').show();
              $('.datiVisibili1').hide();
              $('#subimpegnoNum').change(function(){
                  var selectOpt=$('#subimpegnoNum').val();
                  if(selectOpt!=''){              
                    //$('.datiVisibili2').slideToggle('slow', function() {});
                    $('.datiVisibili2').show();
                  }else{
                    $('.datiVisibili2').hide();
                  }
                }); 
                }     
            });
        
        

    $(document).ready(function() {

    		$('#tabHidden').click(function(){          
           $('.tabHidden').slideToggle('slow', function() {});
        });
        $('.vis').hide();
        //var radio1=$('input[id=pluriennale1]');
        var pluri=$('input[name=pluriennale_radio]');
        $(pluri).click(function(){          
           var radioVal=$(this).filter(':checked').val();
           //alert(radioVal);
           if(radioVal=="si"){            
            $('.vis').show();
            }else{
              $('.vis').hide();
            } 
        });
        
        
        $(document).on('click.cr_title', '[data-toggle=collapse]', function (e) {
          var $this = $(this), href
            , target = $this.attr('data-target')
              || e.preventDefault()
              || (href = $this.attr('href')) && href.replace(/.*(?=#[^\s]+$)/, '') //strip for ie7
            , option = $(target).data('collapse') ? 'toggle' : $this.data()
            if($this.hasClass('cr_title')){
              $this[$(target).hasClass('in') ? 'addClass' : 'removeClass']('arrow')  
              $this[$(target).hasClass('in') ? '' : 'removeClass']('first_cr') 
              }
         // $(target).collapse(option)
        });
                         
        
        $(document).on("click", ".conferma", function(e) {  
            //alert($(this).attr('rel'));
            var link=$(this).attr('rel');
            bootbox.dialog({ 
              className:'dialogAlert',
              title: "Attenzione!",
              message: "I dati non salvati andranno persi: sei sicuro di voler proseguire?",  
              buttons: {
                success: {
                  label: "Conferma",
                  className: "btn-primary",
                  callback: function() {
                     window.location.href=link;                    
                  }
                },
                main: {
                label: "Chiudi",
                className: "btn-primary",
                  callback: function() {
                 
                  }
                }
              }
            });
          });

                         
           /*  $("ul#azioni_btn li [data-toggle=collapse]").on('click', function (e) {
                 var idBtn=$(this).attr('href');                                  
                 $('.cont_collapse .in').each(function(){
                    if($(this).attr('id')!=idBtn){
                      $(this).collapse('toggle');
                    }else{
                      $(this).collapse();                    
                    }
                 });        
              }); */
              
              
		$('.buttonPluriennale').hide();
		$('.anniPluriennale').hide();
		$('#motivo').change(function(){
			var selectOpt=$('#motivo').children(":selected").attr("id");
			if(selectOpt=='pluriennale'){              
			  $('.buttonPluriennale').show();
			  $('.anniPluriennale').show();
			  $('.singleButton').hide();
			}else{
			  $('.buttonPluriennale').hide();
			  $('.anniPluriennale').hide();
			  $('.singleButton').show();
			}
		});

 
 
        $(document).on("click", ".confermaPluri", function(e) {  
            //alert($(this).attr('rel'));
            var link=$(this).attr('rel');
            var campoAnnoP=$('#annipluri').val();
           // alert(campoAnnoP);
            if(campoAnnoP==''){
              bootbox.dialog({ 
                className:'dialogAlert',
                title: "Attenzione!",
                message: "Non hai specificato gli anni di pluriennale: sei sicuro di voler proseguire?",  
                buttons: {
                  success: {
                    label: "Conferma",
                    className: "btn-primary",
                    callback: function() {
                       window.location.href=link;                    
                    }
                  },
                  main: {
                  label: "Chiudi",
                  className: "btn-primary",
                    callback: function() {
                   
                    }
                  }
                }
              });
            }else{
             window.location.href=link;
            }
          });   
   
      });
	  
	
	
	var config = {
	  '.chosen-select'           : {},
	  '.chosen-select-deselect'  : {allow_single_deselect:true},
	  '.chosen-select-no-single' : {disable_search_threshold:10},
	  '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
	  '.chosen-select-width'     : {width:"85%"}
	}
	for (var selector in config) {
	  $(selector).chosen(config[selector]);
	}
	
	$(".chosen-select").chosen()



	$("#wizard").bwizard();
	$('.datepicker').datepicker({language:'it'});
	$(".selectpicker").selectpicker();
	

	
    });


