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

});

$("#agregarPredicado").click(function() {
    $("#PredicadosSimples").append($("#atributo option:selected" ).text()+$("#operador option:selected" ).text()+$("#valor option:selected" ).text()+"\n");
});