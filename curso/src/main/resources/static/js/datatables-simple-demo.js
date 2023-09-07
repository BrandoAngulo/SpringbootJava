window.addEventListener('DOMContentLoaded', event => {

    const datatablesSimple = document.getElementById('datatablesSimple');
    if (datatablesSimple) {
        new simpleDatatables.DataTable(datatablesSimple);
    }
});

// async function cargarUsuarios() {
//     console.log('Entro al al cargar');
//     const request = await fetch('api/usuarios', {
//         method: 'GET',
//         headers: getHeaders()
//     }
//
//     );
//     console.log('Entro al for');
//     const usuarios = await request.json();
//
//     let listadoHtml = '';
//     for (let usuario of usuarios) {
//         let btnEliminar = '<a href="#" onclick="eliminarUsuario(' + usuario.id + ')" style="text-decoration: none" class="btn btn-danger">Eliminar</a>';
//         let usuarioHtml = '<tr><td>' + usuario.id + '</td><td>' + usuario.nombre + '</td><td>' + usuario.apellido + '</td><td>' + usuario.celular + '</td><td>' + usuario.correo + '</td><td>' + btnEliminar + '</td></tr>';
//         listadoHtml += usuarioHtml;
//     }
//
//     document.querySelector('#usuarios tbody').outerHTML = listadoHtml;
// }

// function getHeaders() {
//     return {
//         'Accept': 'application/json',
//         'Content-Type': 'application/json',
//         'Authorization': localStorage.token
//     };
// }
//
// async function eliminarUsuario(id) {
//
//     if (!confirm('Esta seguro')) {
//         return;
//     }
//     const request = await fetch('api/eliminar/' + id, {
//         method: 'DELETE',
//         headers: getHeaders()
//     });
//
//     console.log(`User eliminado: ${id}`);
//     location.reload();
// }
