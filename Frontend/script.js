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

$("#agregarPredicado").click(function() {
    $("#PredicadosSimples").append($("#atributo option:selected" ).text()+$("#operador option:selected" ).text()+$("#valor").val()+"\n");
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

$("#validarPredicados").click(function() {

    text = $("#PredicadosSimples").val();
    text.replace("\n", "_");

    $.ajax({
        url: 'http://localhost:8080/validar/predicados',
        type: 'GET',
        data: { 
            relacion: $("#relacion").val(),
            predicados: text,
        },
        dataType: 'JSON', 
        success: function(res) {
            
        }
    });

});

$("#generarFragmentos").click(function() {

    text = $("#PredicadosSimples").val();
    text.replace("\n", "_");

    $.ajax({
        url: 'http://localhost:8080/generar/miniterminos',
        type: 'GET',
        data: {
            relacion: $("#relacion").val(),
            predicados: text,
        },
        dataType: 'JSON', 
        success: function(res) {
            
        }
    });

});

$("#validarFragmentos").click(function() {

    text = $("#Miniterminos").val();
    text.replace("\n", "_");

    $.ajax({
        url: 'http://localhost:8080/validar/miniterminos',
        type: 'GET',
        data: { 
            relacion: $("#relacion").val(),
            miniterminos: text,
        },
        dataType: 'JSON', 
        success: function(res) {
            
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
