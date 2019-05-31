var bool = true;
class RequiredController {

    constructor(id){
        let input = document.getElementById(id.id);
        this._seletor = new TagsView();
    }

    requiredAll(event, form, length){
		for(let i = 1; i <= length;i++){
            if(document.getElementById(form[i].id).value == "") {
                this._seletor.criarElement("div", "alert alert-danger", document.getElementById(form[i].id+"-div"), form[i].id+"-div");
                this._seletor.updateElementWithoutFather("div", "campo obrigatório!");
                bool = true;
            }
        }
		if(bool == true){
		    return true;
        }
		return false;
    }
}