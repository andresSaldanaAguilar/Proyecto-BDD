	var cont = 0;
	var contV = 0;

$(document).ready(function() {
	
	var text;

    $("#horizontal").click(function() {
        $("#cardV").toggle();
        $("#cardH").toggle();
        $("#horizontal").addClass( "active" );
        $("#vertical").removeClass( "active" );
    });

    $("#vertical").click(function() {
        $("#cardV").toggle();
        $("#cardH").toggle();
        $("#vertical").addClass( "active" );
        $("#horizontal").removeClass( "active" );
    });
	
	
	//Vertical
	$.ajax({
        url: 'http://localhost:8080/relacionesV',
        type: 'GET',
        dataType: 'JSON', 
        success: function(res) {
            $('#relacionV').find('option').remove();
            res.forEach(element => {
                $("#relacionV").append(new Option(element));  
            });
			$("#relacionV>option:eq(0)").attr('selected', true);
			
			text = $( "#relacionV option:selected" ).text();
			var id = 0;
			
			$.ajax({
				url: 'http://localhost:8080/atributosV',
				type: 'GET',
				data: { 
					nombreRelacion: text,
				},
				dataType: 'JSON', 
				success: function(res) {
					$('<div>', { id: "atributoV" , class: "form-check" }).appendTo($('#check'));
					
					acomodar( res );
				}
			});
        }
    });

	//Horizontal
    $.ajax({
        url: 'http://localhost:8080/relaciones',
        type: 'GET',
        dataType: 'JSON', 
        success: function(res) {
            $('#relacion').find('option').remove();
            res.forEach(element => {
                $("#relacion").append(new Option(element));  
            });
			$("#relacion>option:eq(0)").attr('selected', true);
			
			text = $( "#relacion option:selected" ).text();
			
			$.ajax({
				url: 'http://localhost:8080/atributos',
				type: 'GET',
				data: { 
					nombreRelacion: text,
				},
				dataType: 'JSON', 
				success: function(res) {
					$('#atributo').find('option').remove();
					res.forEach(element => {
						$("#atributo").append(new Option(element));  
					});
				}
			});
        }
    });
});

//Horizontal
$("#agregarPredicado").click(function() {
	
	var texto;
	
	$("#generarFragmentos").attr("disabled","disabled");
	$("#validarFragmentos").attr("disabled","disabled");
	$("#enviar").attr("disabled","disabled");
	$("#Miniterminos").empty();
	
	texto = $("#atributo option:selected" ).text()+$("#operador option:selected" ).text()+"'"+$("#valor").val()+"'";
	
	if( $("#PredicadosSimples").val().indexOf(texto) == -1  )
	{
		if( cont == 0 )
		{
			$("#PredicadosSimples").append(texto);
			cont++;
		}
		else
		{
			$("#PredicadosSimples").append("\n"+texto);
		}
	}
});

$("#relacion").change(function() {   
    text = $( "#relacion option:selected" ).text();

    $.ajax({
        url: 'http://localhost:8080/atributos',
        type: 'GET',
        data: { 
            nombreRelacion: text,
        },
        dataType: 'JSON', 
        success: function(res) {
            $('#atributo').find('option').remove()
            res.forEach(element => {
                $("#atributo").append(new Option(element));  
            });
        }
    });
	
	$("#PredicadosSimples").empty();
	$("#Miniterminos").empty();
	
	$("#generarFragmentos").attr("disabled","disabled");
	$("#validarFragmentos").attr("disabled","disabled");
	$("#enviar").attr("disabled","disabled");
	cont = 0;
});

$("#limpiarPredicados").click(function() {
	$("#PredicadosSimples").empty();
	$("#Miniterminos").empty();
	$('#mini').find('option').remove();
	
	$("#generarFragmentos").attr("disabled","disabled");
	$("#validarFragmentos").attr("disabled","disabled");
	$("#enviar").attr("disabled","disabled");
	cont = 0;
});

$("#validarPredicados").click(function() {

    text = $("#PredicadosSimples").val();

	if( $("#PredicadosSimples").val().indexOf('\n') != -1 ){

		$.ajax({
			url: 'http://localhost:8080/validar/predicados',
			type: 'GET',
			data: { 
				relacion: $("#relacion").val(),
				predicados: text,
			},
			dataType: 'JSON', 
			success: function(res) {
				
				if( res == 1 )
				{
					alert("Predicados validados");
					$("#generarFragmentos").removeAttr("disabled"); 
				}
				else
				{
					alert("Los predicados no cumplen la regla COM/Min");
					$("#generarFragmentos").attr("disabled","disabled");
				}
			}
		});
		
	}
	else{
		alert("Son necesarios 2 predicados");
	}
});

$("#generarFragmentos").click(function() {
	var x , y , cont=1;
    text = $("#PredicadosSimples").val();
	$("#Miniterminos").empty();
	$("#validarFragmentos").removeAttr("disabled"); 

    var textos = text.split("\n");
	
	for( x=0 ; x<textos.length ; x++ )
	{
		for( y=x+1 ; y<textos.length ; y++ )
		{
			$("#Miniterminos").append("m"+ cont   +": (" + textos[x] + ") and (" + textos[y] + ") \n");
			$("#Miniterminos").append("m"+(cont+1)+": (" + textos[x] + ") and !(" + textos[y] + ") \n");
			$("#Miniterminos").append("m"+(cont+2)+": !(" + textos[x] + ") and (" + textos[y] + ") \n");
			$("#Miniterminos").append("m"+(cont+3)+": !(" + textos[x] + ") and !(" + textos[y] + ") \n");
			cont+=4;
		}
	}
});

$("#validarFragmentos").click(function() {

    text = $("#Miniterminos").val();

    $.ajax({
        url: 'http://localhost:8080/validar/miniterminos',
        type: 'GET',
        data: { 
            relacion: $("#relacion").val(),
            miniterminos: text,
        },
        dataType: 'text', 
        success: function( res ) {
			
			$("#Miniterminos").empty();
			$("#Miniterminos").append(res);
			
			var mini = res.split("\n");
			var x;
			
			
			for( x=0 ; x<mini.length ; x++ )
			{
				if( mini[x] != "" )
				{
					$("#mini").append(new Option(mini[x]));  
				}
			}
			
        }
    });
	
	$("#enviar").removeAttr("disabled"); 
	
});


$("#enviar").click(function() {

    text = $("#Miniterminos").val();
    text.replace("\n", "_");

    $.ajax({
        url: 'http://localhost:8080/enviar',
        type: 'GET',
        data: { 
            relacion: $("#relacion").val(),
            sitio: $("#sitio").val(),
            nombre: $("#nombre").val(),
            miniterminos: $("#mini").val(),
        },
        dataType: 'JSON', 
        success: function(res) {
			alert("Fragmentos creados");
        }
    });
});


//Vertical
$("#relacionV").change(function() {   
    text = $( "#relacionV option:selected" ).text();
	var id = 0;
	
	$.ajax({
		url: 'http://localhost:8080/atributosV',
		type: 'GET',
		data: { 
			nombreRelacion: text,
		},
		dataType: 'JSON', 
		success: function(res) {
			$("#atributoV").empty();
			
			acomodar( res );
		}
	});
	
	$("#FragmentosSimples").empty();
	
	$("#validarFragmentosVer").attr("disabled","disabled");
	$("#enviarV").attr("disabled","disabled");
	contV = 0;
});

$("#agregarFragmentoVer").click(function() {
	
	var x , y;
	var texto;
	var clases;
	
	clases = $('#atributoV').find('input');
	
	texto = "'";
	
	y = 0;
	
	for( x=0 ; x<clases.length ; x++ ){
			if( $(clases[x]).attr('checked') == 'checked' ){
				if( y == 0 ){
					texto += $(clases[x]).attr('name');
					y++;
				}
				else{
					texto += "," + $(clases[x]).attr('name');
				}
			}
	}
	texto += "'";
	
	if( $("#FragmentosSimples").val().indexOf(texto) == -1 && y != 0 )
	{
		$("#enviarV").attr("disabled","disabled");
		$("#validarFragmentosVer").removeAttr("disabled"); 
	
		contV++;
		texto = "fr" + contV + ": " + texto ;
		if( contV == 1 )
		{
			$("#FragmentosSimples").append(texto);
		}
		else
		{
			$("#FragmentosSimples").append("\n"+texto);
		}
	}
});

$("#limpiarFragmentosVer").click(function() {
	$("#FragmentosSimples").empty();
	$('#frag').find('option').remove();
	
	$("#validarFragmentosVer").attr("disabled","disabled");
	$("#enviarV").attr("disabled","disabled");
	contV = 0;
});

$("#validarFragmentosVer").click(function() {
	
	text = $( "#relacionV option:selected" ).text();
	$.ajax({
			url: 'http://localhost:8080/atributosV',
			type: 'GET',
			data: { 
				nombreRelacion: text,
			},
			dataType: 'JSON', 
			success: function(res) {
				
				var comodin = 1;
				
				res.forEach(element => {
					if( $("#FragmentosSimples").val().indexOf(element) == -1 ){
						comodin = 0;
					}
				});
				
				if( comodin == 1 ){
					alert("Fragmentos validados");
					
					$("#enviarV").removeAttr("disabled"); 
					
					var frag = $("#FragmentosSimples").val().split("\n");
					var x;
					
					for( x=0 ; x<frag.length ; x++ )
					{
						if( frag[x] != "" )
						{
							$("#frag").append(new Option(frag[x]));  
						}
					}
				}
				else{
					alert("Existe un problema con los fragmentos");
				}
			}
		});
});

$("#enviarV").click(function() {

    text = $("#FragmentosSimples").val();
    text.replace("\n", "_");

    $.ajax({
        url: 'http://localhost:8080/enviarV',
        type: 'GET',
        data: { 
            relacion: $("#relacionV").val(),
            sitio: $("#sitioV").val(),
            nombre: $("#nombreV").val(),
            miniterminos: $("#frag").val(),
        },
        dataType: 'JSON', 
        success: function(res) {
			alert("Fragmentos creados");
        }
    });
});

function acomodar( res ){
	
	var id = 0;
	
	res.forEach(element => {
		$('<input />', { class: 'form-check-input' , type: 'checkbox' , value: '' , id: 'check'+id , name: element }).appendTo($('#atributoV'));
		$('#atributoV').append(" " + element);
		$('<br/>').appendTo($('#atributoV'));
		id++;
	});
	
	$('.form-check-input').on('click', function(element) {
		if( $(this).attr('checked') == null )
		{
			$(this).attr('checked','checked');
		}
		else
		{
			$(this).removeAttr("checked"); 
		}
		
	});
	
	$.ajax({
        url: 'http://localhost:8080/llaves',
        type: 'GET',
        data: { 
            nombreRelacion: $("#relacionV").val(),
        },
        dataType: 'JSON', 
        success: function(res) {
            res.forEach(element => {
				clases = $('#atributoV').find('input');
				
				for( x=0 ; x<clases.length ; x++ ){
					if( $(clases[x]).attr('name') == element ){
						$(clases[x]).attr('checked','checked');
						$(clases[x]).attr('disabled','disabled');
					}
				}
			});
        }
    });
	
}