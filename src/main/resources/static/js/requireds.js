var incremento = 0;

$(document).ready(function () {
    $("#cidade").attr("disabled", true);
    $("#estado").attr("disabled", true);
})

$("#cadastro-hemo").click(function (event) {
	var clearfild = new ClearfieldsController();
    let requerid = new RequiredController(this);
    let form = document.getElementById("form");
    if(requerid.requiredAll(event, form, 7) == false){
        $("#cidade").attr("disabled", false);
        $("#bairro").attr("disabled", false);
        $("#estado").attr("disabled", false);
        $("#cadastro-hemo").submit();
    }else{
        event.preventDefault();
    }

    if(event.originalEvent !== undefined){
        incremento++;
        if(incremento == 2){
            clearfild.clear("nome-div");
            clearfild.clear("cidade-div");
            clearfild.clear("estado-div");
            clearfild.clear("cep-div");
            clearfild.clear("rua-div");
            clearfild.clear("numero-div");
            clearfild.clear("bairro-div");
            incremento = 0;
        }
    }
   
});