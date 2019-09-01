var socket = new WebSocket('ws://'+location.hostname+(location.port ? ':'+location.port: '')+'/iDoc/ws/actas');

function enviar(usuario) {
    socket.send(usuario + " hizo un nuevo registro!");
}

socket.onmessage = function (event) {
//    let datos = JSON.parse(event.data);
    PF('growlWV').renderMessage({
        "summary": "Mensaje",
        "detail": event.data,
        "severity": "info"
    });
};
