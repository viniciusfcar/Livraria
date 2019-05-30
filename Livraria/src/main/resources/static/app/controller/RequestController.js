class RequestController{

    constructor(parameters, url, method){
        this._request = new SubmitRequest(method, url, parameters);

    }

    submitRequestHemo() {
        /*let objetoRequest = {};
        let stringJson = JSON.stringify(this._request.getParameters());
        let json = JSON.parse(stringJson);
        objetoRequest = {
            array: [],
        }
        $.each(json._parameters, function (a, b) {
            objetoRequest.array.push(b.value);
        });

       $.ajax({
            url: this._request.getUrl(),
            type: this._request.getMethod(),
            data: {
               nome: array[0].value,
               estado: array[1].value,
               cidade: array[2].value,
               cep: array[3].value,
               rua: array[4].value,
               numero: array[5].value,
               bairro: array[6].value,
               //'hemo': objetoRequest.array,
            }
        });
        */
    }
}