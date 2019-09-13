var socket = new WebSocket('ws://' + location.hostname + (location.port ? ':' + location.port : '') + '/iDoc/ws/server');

function enviar(mensaje) {
    socket.send(mensaje);
}

socket.onmessage = function (event) {
//    let datos = JSON.parse(event.data);
    console.log(event);
    PF('mensajeSockets').renderMessage({
        "summary": "Hubierón cambios",
        "detail": event.data,
        "severity": "info"
    });
    switch (event.data) {
        case "Persona":
            actualizarPersona();
            break;

        default:

            break;
    }
};


function actualizarPersona() {
    PrimeFaces.ab({s: "j_idt107:btnActualizarPersona", f: "j_idt107", u: "@all", a: true});
    return false;
}