// datos de actividades
let datos = [
  ["Sala 1", "Spinning", "Mario Lozano", "05/05/2026", "07:00h", 20, 7],
  ["Sala 2", "Yoga", "Laura Perez", "05/05/2026", "09:30h", 15, 3],
  ["Sala 3", "Crossfit", "Carlos Ruiz y Ana Gomez", "05/05/2026", "10:00h", 25, 12],
  ["Sala 4", "Pilates", "Marta Sanchez", "06/05/2026", "11:00h", 12, 0],
  ["Sala 1", "HIIT", "Mario Lozano", "06/05/2026", "18:00h", 20, 9],
  ["Sala 2", "Zumba", "Laura Perez", "06/05/2026", "19:30h", 30, 15],
  ["Sala 3", "Functional Training", "Carlos Ruiz", "07/05/2026", "08:00h", 20, 6],
  ["Sala 4", "Body Pump", "Ana Gomez", "07/05/2026", "10:30h", 25, 11],
  ["Sala 1", "Spinning", "Mario Lozano", "07/05/2026", "19:00h", 20, 4],
  ["Sala 2", "Meditacion", "Marta Sanchez", "08/05/2026", "09:00h", 15, 8],
  ["Sala 3", "Boxeo", "Carlos Ruiz", "08/05/2026", "17:00h", 16, 2],
  ["Sala 4", "Stretching", "Laura Perez y Ana Gomez", "08/05/2026", "20:00h", 20, 14]
];

// cojo cosas del html
let lista = document.getElementById("listaActividades");
let sala = document.getElementById("sala");
let fecha = document.getElementById("fecha");
let filtrar = document.getElementById("filtrar");
let limpiar = document.getElementById("limpiar");
let anterior = document.getElementById("anterior");
let siguiente = document.getElementById("siguiente");
let textoPagina = document.getElementById("textoPagina");

// cosas de las paginas
let pagina = 1;
let porPagina = 6;
let datosQueSeVen = datos;

// pinto al cargar
pintar();

// funcion para sacar las tarjetas
function pintar() {
  lista.innerHTML = "";

  let desde = (pagina - 1) * porPagina;
  let hasta = desde + porPagina;

  // recorro solo las que tocan en esta pagina
  for (let i = desde; i < hasta && i < datosQueSeVen.length; i++) {
    let a = datosQueSeVen[i];

    lista.innerHTML +=
      "<article class='tarjeta'>" +
        "<h3>" + a[1] + "</h3>" +
        "<p><b>Sala:</b> " + a[0] + "</p>" +
        "<p><b>Entrenador/es:</b> " + a[2] + "</p>" +
        "<p><b>Fecha:</b> " + a[3] + "</p>" +
        "<p><b>Hora:</b> " + a[4] + "</p>" +
        "<p><b>Capacidad:</b> " + a[5] + "</p>" +
        "<p><b>Plazas libres:</b> " + a[6] + "</p>" +
      "</article>";
  }

  // por si no hay nada
  if (datosQueSeVen.length == 0) {
    lista.innerHTML = "<p>No hay actividades para mostrar.</p>";
  }

  textoPagina.innerHTML = "Pagina " + pagina;
}

// boton de filtrar
filtrar.onclick = function() {
  datosQueSeVen = [];
  pagina = 1;

  let salaElegida = sala.value;
  let fechaElegida = fecha.value;

  // cambio la fecha del input al formato del listado
  if (fechaElegida != "") {
    let partes = fechaElegida.split("-");
    fechaElegida = partes[2] + "/" + partes[1] + "/" + partes[0];
  }

  // miro una por una
  for (let i = 0; i < datos.length; i++) {
    let a = datos[i];

    if ((salaElegida == "" || a[0] == salaElegida) &&
        (fechaElegida == "" || a[3] == fechaElegida)) {
      datosQueSeVen.push(a);
    }
  }

  pintar();
};

// boton pa quitar filtros
limpiar.onclick = function() {
  sala.value = "";
  fecha.value = "";
  pagina = 1;
  datosQueSeVen = datos;
  pintar();
};

// pagina siguiente
siguiente.onclick = function() {
  let totalPaginas = Math.ceil(datosQueSeVen.length / porPagina);

  if (pagina < totalPaginas) {
    pagina++;
    pintar();
  }
};

// pagina anterior
anterior.onclick = function() {
  if (pagina > 1) {
    pagina--;
    pintar();
  }
};
