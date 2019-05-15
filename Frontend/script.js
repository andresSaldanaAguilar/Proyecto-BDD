	var cont = 0;

$(document).ready(function() {

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

    $.ajax({
        url: 'http://localhost:8080/relaciones',
        type: 'GET',
        dataType: 'JSON', 
        success: function(res) {
            $('#relacion').find('option').remove()
            res.forEach(element => {
                $("#relacion").append(new Option(element));  
            });
        }
    });

    text = $( "#relacion option:selected" ).text();
    $.ajax({
        url: 'http://localhost:8080/atributos',
        type: 'GET',
        data: { 
            nombreRelacion: 'colaborausertab',
        },
        dataType: 'JSON', 
        success: function(res) {
            $('#atributo').find('option').remove()
            res.forEach(element => {
                $("#atributo").append(new Option(element));  
            });
        }
    });
});

$("#agregarPredicado").click(function() {
	$("#generarFragmentos").attr("disabled","disabled");
	$("#validarFragmentos").attr("disabled","disabled");
	$("#Miniterminos").empty();
	
	if( cont == 0 )
	{
		$("#PredicadosSimples").append($("#atributo option:selected" ).text()+$("#operador option:selected" ).text()+"'"+$("#valor").val()+"'");
		cont++;
	}
	else
	{
		$("#PredicadosSimples").append("\n"+$("#atributo option:selected" ).text()+$("#operador option:selected" ).text()+"'"+$("#valor").val()+"'");
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
});

$("#limpiarPredicados").click(function() {
	$("#PredicadosSimples").empty();
	$("#Miniterminos").empty();
	
	$("#generarFragmentos").attr("disabled","disabled");
	$("#validarFragmentos").attr("disabled","disabled");
	cont = 0;
});

$("#validarPredicados").click(function() {

    text = $("#PredicadosSimples").val();

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
			
        }
    });
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
            miniterminos: text,
        },
        dataType: 'JSON', 
        success: function(res) {
            
        }
    });

});
