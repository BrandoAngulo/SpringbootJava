window.addEventListener('DOMContentLoaded', event => {

});

async function registrarUsuario() {
    let datos ={};
    datos.nombre = document.getElementById('nombre').value;
    datos.apellido = document.getElementById('apellido').value;
    datos.correo = document.getElementById('correo').value;
    datos.pass = document.getElementById('pass').value;

    let confirmarPass = datos.confirmarPass = document.getElementById('confirmarPass').value;
    let pass = datos.pass = document.getElementById('pass').value;

    if (confirmarPass != pass){
        alert('Confirmar contraseña es diferente');
        return;
    }

    const request = await fetch('api/usuarios', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
    });
}

