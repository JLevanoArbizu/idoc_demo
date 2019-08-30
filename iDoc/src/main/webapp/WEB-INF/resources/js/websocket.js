var socket = new WebSocket("ws://localhost:8080/iDoc/ws/actas");

function onMessage(event){
    var btnNotificar = document.getElementById("btnNotificar");
    btnNotificar.disabled = true;

    var datos = JSON.parse(event.data);
    alert(datos);

}

socket.onmessage = onMessage;

function enviar(){
    socket.send("{\"start\":\"true\"}");
}