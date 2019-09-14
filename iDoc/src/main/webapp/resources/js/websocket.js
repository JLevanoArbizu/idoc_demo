var socket = new WebSocket('ws://' + location.hostname + (location.port ? ':' + location.port : '') + '/iDoc/ws/server');

function enviar(mensaje) {
    socket.send(mensaje);
}

socket.onmessage = function (event) {
    PF('mensajeSockets').renderMessage({
        "summary": "Hubierón cambios",
        "detail": event.data,
        "severity": "info"
    });
    switch (event.data) {
        case "Persona":
            actualizarPersona();
            break;
        case "Area":
            actualizarArea();
            break;
        case "Documento":
            actualizarDocumento();
            break;
        case "Empresa":
            actualizarEmpresa();
            break;
        case "IncidenciaTipo":
            actualizarIncidenciaTipo();
            break;
        case "Trabajador":
            actualizarTrabajador();
            break;
        case "Transferencia":
            actualizarTransferencia();
            break;
        case "Tupa":
            actualizarTupa();
            break;
        case "Sugerencia":
            actualizarSugerencia();
            break;
        default:
            break;
    }
};


function actualizarPersona() {
    PrimeFaces.ab({s: "j_idt107:btnActualizarPersona", f: "j_idt107", u: "frmRegistrarPersona frmListarPersona", a: true});
    return false;
}

function actualizarArea() {
    PrimeFaces.ab({s: "j_idt107:btnActualizarArea", f: "j_idt107", u: "frmRegistrarArea frmListarArea", a: true});
    return false;
}

function actualizarActa() {
    PrimeFaces.ab({s: "j_idt107:btnActualizarActa", f: "j_idt107", u: "frmRegistrarActa frmActa", a: true});
    return false;
}

function actualizarDocumento() {
    PrimeFaces.ab({s: "j_idt107:btnActualizarDocumento", f: "j_idt107", u: "FormTblDocumento FormDocumento", a: true});
    return false;
}

function actualizarDocumentoTipo() {

}

function actualizarIncidenciaTipo() {
    PrimeFaces.ab({s: "j_idt107:btnActualizarIncidenciaTipo", f: "j_idt107", u: "frmRegistrarIncidenciaTipo frmListarIncidenciaTipo", a: true});
    return false;
}

function actualizarTrabajador() {
    PrimeFaces.ab({s: "j_idt107:btnActualizarTrabajador", f: "j_idt107", u: "frmRegistrarTrabajador frmListarTrabajador", a: true});
    return false;
}

function actualizarTransferencia() {
    PrimeFaces.ab({s: "j_idt107:btnActualizarTransferencia", f: "j_idt107", u: "FormTblTransferencia FormTransferencia", a: true});
    return false;
}

function actualizarTupa() {
    PrimeFaces.ab({s: "j_idt107:btnActualizarTupa", f: "j_idt107", u: "FormTupa FormTblTupa", a: true});
    return false;
}

function actualizarEmpresa() {
    PrimeFaces.ab({s: "j_idt107:btnActualizarEmpresa", f: "j_idt107", u: "FormEmpresa FormTblEmpresa", a: true});
    return false;
}

function actualizarSugerencia() {
    PrimeFaces.ab({s: "j_idt110:btnActualizarSugerencia", f: "j_idt110", a: true});
    return false;
}