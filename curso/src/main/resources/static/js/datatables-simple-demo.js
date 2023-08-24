window.addEventListener('DOMContentLoaded', event => {
    const usuarios = document.getElementById('usuarios');
    cargarUsuarios();
    if (usuarios) {
        new simpleDatatables.DataTable(usuarios);
    }
});

async function cargarUsuarios() {
    const request = await fetch('usuarios', {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    });
    const usuarios = await request.json();

    let listadoHtml = '';
    for (let usuario of usuarios) {
        let usuarioHtml = '<tr><td>' + usuario.id + '</td><td>' + usuario.nombre + '</td><td>' + usuario.apellido + '</td><td>' + usuario.celular + '</td><td>' + usuario.correo + '</td><td><button>Eliminar</button></td></tr>';
        listadoHtml += usuarioHtml;
    }
    console.log(listadoHtml);
    document.querySelector('#usuarios tbody').outerHTML = listadoHtml;


}
