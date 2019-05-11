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
    JSON_data = textToJSON(text);
    console.log(JSON_data);
    $.ajax({                        
        url: 'http://localhost:8080/validar/predicados',                     
        type: 'POST',                 
        data: JSON_data, 
        headers: {
            'Content-Type': 'application/json'
        },
        success: function(data)             
        {
                       
        }
    });
});

$("#relacion").change(function() {
    $.ajax({
        url: 'http://localhost:8080/atributos',
        type: 'GET',
        dataType: 'text', 
        success: function(res) {
            console.log(res);
        }
    });
});

function textToJSON(text){
    JSON_obj = {};
    JSON_obj['predicados'] = text;
    return JSON.stringify(JSON_obj);
}


