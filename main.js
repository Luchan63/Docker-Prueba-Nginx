// services 
const baseUrl = 'http://localhost:8001/alumnos';


export const getAlumnos = async () => {
    const response = await fetch(`${baseUrl}/`);
    const data = await response.json();
    console.log(data);
    return data;
}

export const getAlumno = async (id) => {
    const response = await fetch(`${baseUrl}/${id}`);
    const data = await response.json();
    console.log(data);
    return data;
}

export const createAlumno = async (alumno) => {
    const response = await fetch(`${baseUrl}/`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(alumno)
    });
    const data = await response.json();
    console.log(data);
    return data;
}

export const updateAlumno = async (id, alumno) => {
    const response = await fetch(`${baseUrl}/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(alumno)
    });
    const data = await response.json();
    console.log(data);
    return data;
}

export const deleteAlumno = async (id) => {
    const response = await fetch(`${baseUrl}/${id}`, {
        method: 'DELETE'
    });
    const data = await response.json();
    console.log(data);
    return data;
}

//  eventos javaScript
//================================================================================================
// table alumnos
//================================================================================================
document.addEventListener("DOMContentLoaded", async () => {

    const data = await getAlumnos();
    const tbody = document.getElementById("alumnos");
    tbody.innerHTML = "";
    data.forEach(alumno => {
        const row = `<tr>
            <td>${alumno.id}</td>
            <td>${alumno.nombre}</td>
            <td>${alumno.apellido}</td>
            <td>${alumno.email}</td>
            <td>${alumno.telefono}</td>
            <td>${alumno.direccion}</td>
            <td>
                <a href="form.html?id=${alumno.id}" class="btn">Editar</a>
                <button class="btn btn-delete " style="background: red;" data-id="${alumno.id}">Eliminar</button>
            </td>
        </tr>`;
        tbody.innerHTML += row;
    });
    document.querySelectorAll(".btn-delete").forEach(btn => {
        btn.addEventListener("click", async (e) => {
            const id = e.target.getAttribute("data-id");
            
            if (confirm("¿Seguro que quieres eliminar este alumno?")) {
                try {
                    await deleteAlumno(id);
                } catch (error) {
                    console.error("Error al eliminar el alumno:", error);
                }
            }
        });
    });
});
//================================================================================================
//================================================================================================
// form create
//================================================================================================

const form = document.getElementById("form");
const id = new URLSearchParams(window.location.search).get("id");
const nombre = document.getElementById("nombre");
const apellido = document.getElementById("apellido");
const email = document.getElementById("email");
const telefono = document.getElementById("telefono");
const direccion = document.getElementById("direccion");

if (id) {
    const data = await getAlumno(id);
    nombre.value = data.nombre;
    apellido.value = data.apellido;
    email.value = data.email;
    telefono.value = data.telefono;
    direccion.value = data.direccion;

    form.addEventListener("submit", async (e) => {
        e.preventDefault();
        const alumno = {
            nombre: nombre.value,
            apellido: apellido.value,
            email: email.value,
            telefono: telefono.value,
            direccion: direccion.value
        };
        await updateAlumno(id, alumno);
        window.location.href = "index.html";
    });
}
form.addEventListener("submit", async (e) => {
    e.preventDefault();
    const alumno = {
        nombre: nombre.value,
        apellido: apellido.value,
        email: email.value,
        telefono: telefono.value,
        direccion: direccion.value
    };
    await createAlumno(alumno);
    window.location.href = "index.html";
});
