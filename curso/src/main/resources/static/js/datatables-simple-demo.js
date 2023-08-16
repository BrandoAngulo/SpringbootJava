window.addEventListener('DOMContentLoaded', event => {
    // Simple-DataTables
    // https://github.com/fiduswriter/Simple-DataTables/wiki
    const usuarios = document.getElementById('usuarios');
    cargarUsuarios();
    if (usuarios) {
        new simpleDatatables.DataTable(usuarios);
    }
});

async function cargarUsuarios() {

    const request = await fetch('usuarios/', {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    });
    const personas = await request.json();

    console.log(personas.nombre);
    //let listaPersonas = '';
    //for (let persona of personas) {
    let usuarioTabla = '<td>'+personas.id+'<td><td>'+personas.nombre+'<td><td>'+personas.celular+'<td>'

      //  listaPersonas +=usuarioTabla;
    //}
    document.querySelector('#usuarios tbody').outerHTML = usuarioTabla;

}
