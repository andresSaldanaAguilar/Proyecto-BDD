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
        dataType: 'text', 
        success: function(res) {
            console.log(res);
        }
    });

});

$("#agregarPredicado").click(function() {
    $("#PredicadosSimples").append($("#atributo option:selected" ).text()+$("#operador option:selected" ).text()+$("#valor option:selected" ).text()+"\n");
});

$("#validarPredicados").click(function() {

    text = $("#PredicadosSimples").val();
    text.replace("\n", "_");

    $.ajax({
        url: 'http://localhost:8080/validar/predicados',
        type: 'GET',
        data: { 
            predicados: text,
        },
        dataType: 'JSON', 
        success: function(res) {
            
        }
    });

});

$("#relacion").change(function() {   
    text = $( "#relacion option:selected" ).text();

    $.ajax({
        url: 'http://localhost:8080/atributos',
        type: 'GET',
        data: { 
            nombreAtributo: text,
        },
        dataType: 'JSON', 
        success: function(res) {
            
        }
    });
});



