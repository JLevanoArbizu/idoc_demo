let socket = new WebSocket('ws://' + location.hostname + (location.port ? ':' + location.port : '') + '/iDoc/ws/server');

function enviar(mensaje) {
    socket.send(mensaje);
}

socket.onopen = function (event) {
    console.log('Conexión abierta ' + event);
};

socket.onmessage = function (event) {
    notificarActualizar(event);
};

socket.onclose = function (event) {
    console.log('Conexión socket cerrada ' + event);
};

socket.onerror = function (event) {
    console.log('Error! ' + event);
};

function cerrarSesion() {
    socket.onclose = function () {};
    socket.close();
    console.log('Conexión socket cerrada!!');
}


function notificarActualizar(event) {
    PF('mensajeSockets').renderMessage({
        "summary": "Hubierón cambios recientes",
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
        case "DocumentoTipo":
            actualizarDocumentoTipo();
            break;
        default:
            break;
    }
}


function actualizarPersona() {
    PrimeFaces.ab({s: "j_idt110:btnActualizarPersona", f: "j_idt110", u: "frmRegistrarPersona frmListarPersona", a: true, onco: function (xhr, status, args) {
            PF('wvDtTblPersona').filter();
            ;
        }});
    return false;
}

function actualizarArea() {
    PrimeFaces.ab({s: "j_idt107:btnActualizarArea", f: "j_idt107", u: "frmRegistrarArea frmListarArea", a: true});
    return false;
}

function actualizarActa() {
    PrimeFaces.ab({s: "j_idt110:btnActualizarActa", f: "j_idt110", u: "frmRegistrarActa frmActa", a: true, onco: function (xhr, status, args) {
            PF('wvTblActa').filter();
            ;
        }});
    return false;
}

function actualizarDocumento() {
    PrimeFaces.ab({s: "j_idt107:btnActualizarDocumento", f: "j_idt107", u: "FormTblDocumento FormDocumento", a: true});
    return false;
}

function actualizarDocumentoTipo() {
    PrimeFaces.ab({s: "j_idt113:btnActualizarDocumentoTipo", f: "j_idt113", a: true});
    return false;
}

function actualizarIncidenciaTipo() {
    PrimeFaces.ab({s: "j_idt110:btnActualizarIncidenciaTipo", f: "j_idt110", u: "frmRegistrarIncidenciaTipo frmListarIncidenciaTipo", a: true, onco: function (xhr, status, args) {
            PF('wvDtTblIncidenciaTipo').filter();
            ;
        }});
    return false;
}

function actualizarTrabajador() {
    PrimeFaces.ab({s: "j_idt110:btnActualizarTrabajador", f: "j_idt110", u: "frmRegistrarTrabajador frmListarTrabajador", a: true, onco: function (xhr, status, args) {
            PF('wdtTblTrabajador').filter();
            ;
        }});
    return false;
}

function actualizarTransferencia() {
    PrimeFaces.ab({s: "j_idt110:btnActualizarTransferencia", f: "j_idt110", u: "FormTblTransferencia FormTransferencia", a: true});
    return false;
}

function actualizarTupa() {
    PrimeFaces.ab({s: "j_idt110:btnActualizarTupa", f: "j_idt110", u: "FormTupa FormTblTupa", a: true, onco: function (xhr, status, args) {
            PF('wvTblTupa').filter();
            ;
        }});
    return false;
}

function actualizarEmpresa() {
    PrimeFaces.ab({s: "j_idt110:btnActualizarEmpresa", f: "j_idt110", u: "FormEmpresa FormTblEmpresa", a: true});
    return false;
}

function actualizarSugerencia() {
    PrimeFaces.ab({s: "j_idt110:btnActualizarSugerencia", f: "j_idt110", u: "frmSugerencia", a: true});
    return false;
}