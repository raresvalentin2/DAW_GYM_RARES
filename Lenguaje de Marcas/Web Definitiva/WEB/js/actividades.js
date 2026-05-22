/*
Codigo JavaScript para cargar las actividades desde el archivo JSON
Tambien permite filtrar por sala y fecha y navegar con paginacion
*/

// datos del json
let datos = [];
let datosQueSeVen = [];
let pagina = 1;
let porPagina = 6;

let lista = document.getElementById("listaActividades");
let sala = document.getElementById("sala");
let fecha = document.getElementById("fecha");
let filtrar = document.getElementById("filtrar");
let limpiar = document.getElementById("limpiar");
let anterior = document.getElementById("anterior");
let siguiente = document.getElementById("siguiente");
let textoPagina = document.getElementById("textoPagina");

fetch("data/actividades.json")
  .then(respuesta => respuesta.json())
  .then(json => {
    datos = json;
    datosQueSeVen = datos;
    pintar();
  });

function pintar() {
  lista.innerHTML = "";

  let desde = (pagina - 1) * porPagina;
  let hasta = desde + porPagina;

  for (let i = desde; i < hasta && i < datosQueSeVen.length; i++) {
    let a = datosQueSeVen[i];

    lista.innerHTML +=
      "<article class='tarjeta'>" +
        "<h3>" + a.actividad + "</h3>" +
        "<p><b>Sala:</b> " + a.sala + "</p>" +
        "<p><b>Entrenador/es:</b> " + a.entrenadores.join(", ") + "</p>" +
        "<p><b>Fecha:</b> " + a.fechaTexto + "</p>" +
        "<p><b>Hora:</b> " + a.hora + "</p>" +
        "<p><b>Capacidad:</b> " + a.capacidad + "</p>" +
        "<p><b>Plazas libres:</b> " + a.plazasLibres + "</p>" +
      "</article>";
  }

  if (datosQueSeVen.length == 0) {
    lista.innerHTML = "<p>No hay actividades para mostrar.</p>";
  }

  textoPagina.innerHTML = "Pagina " + pagina;
}

filtrar.onclick = function() {
  datosQueSeVen = [];
  pagina = 1;

  for (let i = 0; i < datos.length; i++) {
    let a = datos[i];

    if ((sala.value == "" || a.sala == sala.value) &&
        (fecha.value == "" || a.fecha == fecha.value)) {
      datosQueSeVen.push(a);
    }
  }

  pintar();
};

limpiar.onclick = function() {
  sala.value = "";
  fecha.value = "";
  pagina = 1;
  datosQueSeVen = datos;
  pintar();
};

siguiente.onclick = function() {
  let totalPaginas = Math.ceil(datosQueSeVen.length / porPagina);

  if (pagina < totalPaginas) {
    pagina++;
    pintar();
  }
};

anterior.onclick = function() {
  if (pagina > 1) {
    pagina--;
    pintar();
  }
};
