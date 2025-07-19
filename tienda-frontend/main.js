document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('form-agregar-producto');
    form.addEventListener('submit', agregarProducto);

    const tablaBody = document.getElementById('cuerpo-tabla');
    tablaBody.addEventListener('click', manejarClickTabla);

    cargarProductos();
});

async function cargarProductos() {
    const url = 'http://localhost:8080/api/productos';
    const tablaBody = document.getElementById('cuerpo-tabla');

    try {
        const respuesta = await fetch(url);
        if (!respuesta.ok) {
            throw new Error(`Error HTTP: ${respuesta.status}`);
        }
        const productos = await respuesta.json();

        tablaBody.innerHTML = '';

        if (productos.length === 0) {
            tablaBody.innerHTML = '<tr><td colspan="8" style="text-align:center;">No hay productos. Agrega uno nuevo para verlo aquí.</td></tr>';
            return;
        }

        productos.forEach(producto => {
            const fila = document.createElement('tr');
            fila.innerHTML = `
                <td>${producto.id}</td>
                <td>${producto.nombre}</td>
                <td>${producto.descripcion}</td>
                <td>${producto.categoria}</td>
                <td>$${producto.precio.toFixed(2)}</td>
                <td>${producto.stock}</td>
                <td><img src="${producto.imagenUrl}" alt="${producto.nombre}" style="width: 50px; height: 50px; object-fit: cover;"></td>
                <td>
                    <button class="btn btn-eliminar" data-id="${producto.id}">Eliminar</button>
                </td>
            `;
            tablaBody.appendChild(fila);
        });
    } catch (error) {
        console.error('No se pudieron cargar los productos:', error);
        tablaBody.innerHTML = '<tr><td colspan="8" style="text-align:center;">Error al cargar los productos. Asegúrate de que el Back-End esté corriendo.</td></tr>';
    }
}

async function agregarProducto(event) {
    event.preventDefault();

    const producto = {
        nombre: document.getElementById('nombre').value,
        descripcion: document.getElementById('descripcion').value,
        categoria: document.getElementById('categoria').value,
        imagenUrl: document.getElementById('imagenUrl').value,
        precio: parseFloat(document.getElementById('precio').value),
        stock: parseInt(document.getElementById('stock').value)
    };

    const url = 'http://localhost:8080/api/productos';

    try {
        const respuesta = await fetch(url, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(producto)
        });

        if (respuesta.ok) {
            alert('¡Producto agregado con éxito!');
            document.getElementById('form-agregar-producto').reset();
            cargarProductos();
        } else {
            throw new Error('Falló la creación del producto.');
        }
    } catch (error) {
        console.error('Error al agregar el producto:', error);
        alert('Error al agregar el producto. Revisa la consola para más detalles.');
    }
}


function manejarClickTabla(event) {
    if (event.target.classList.contains('btn-eliminar')) {
        const id = event.target.dataset.id;
        const confirmar = confirm(`¿Estás seguro de que deseas eliminar el producto con ID ${id}?`);
        if (confirmar) {
            eliminarProducto(id);
        }
    }
}


async function eliminarProducto(id) {
    const url = `http://localhost:8080/api/productos/${id}`;

    try {
        const respuesta = await fetch(url, {
            method: 'DELETE'
        });

        if (respuesta.ok) {
            alert(`Producto con ID ${id} eliminado con éxito.`);
            cargarProductos();
        } else {
            throw new Error('Falló la eliminación del producto.');
        }
    } catch (error) {
        console.error('Error al eliminar el producto:', error);
        alert('Error al eliminar el producto. Revisa la consola para más detalles.');
    }
}