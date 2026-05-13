// validacion sencilla del formulario

let formulario = document.querySelector(".formulario");
let nombre = document.getElementById("nombre");
let email = document.getElementById("email");
let telefono = document.getElementById("telefono");
let mensaje = document.getElementById("mensaje");
let aviso = document.getElementById("avisoFormulario");

formulario.onsubmit = function(evento) {
  evento.preventDefault();

  aviso.innerHTML = "";
  aviso.style.color = "red";

  if (nombre.value.trim() == "") {
    aviso.innerHTML = "Falta poner el nombre.";
    return;
  }

  if (email.value.trim() == "") {
    aviso.innerHTML = "Falta poner el email.";
    return;
  }

  if (email.value.indexOf("@") == -1 || email.value.indexOf(".") == -1) {
    aviso.innerHTML = "El email no está bien escrito.";
    return;
  }

  if (telefono.value.trim() != "" && telefono.value.length < 9) {
    aviso.innerHTML = "El telefono tiene que tener al menos 9 numeros.";
    return;
  }

  if (mensaje.value.trim() == "") {
    aviso.innerHTML = "Falta escribir el mensaje.";
    return;
  }

  aviso.style.color = "lightgreen";
  aviso.innerHTML = "Formulario enviado correctamente.";

  formulario.reset();
};