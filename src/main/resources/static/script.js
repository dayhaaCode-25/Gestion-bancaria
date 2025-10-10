// Mostrar solo la sección correspondiente
function mostrarInicio() {
    document.getElementById('cuadroPrincipal').style.display = '';
    document.getElementById('transaccionesMenu').style.display = 'none';
    document.getElementById('configuracionMenu').style.display = 'none';
}
function mostrarTransaccionesMenu() {
    document.getElementById('cuadroPrincipal').style.display = 'none';
    document.getElementById('transaccionesMenu').style.display = 'block';
    document.getElementById('configuracionMenu').style.display = 'none';
    mostrarTransacciones();
}

function configuracionUsuario() {
    document.getElementById('cuadroPrincipal').style.display = 'none';
    document.getElementById('transaccionesMenu').style.display = 'none';
    document.getElementById('configuracionMenu').style.display = '';

    const usuario = JSON.parse(localStorage.getItem('usuario'));
    if (usuario) {
        document.getElementById('confNumeroCuenta').textContent = usuario.cuenta || '';
        document.getElementById('confTipoCuenta').textContent = usuario.tipoCuenta || 'Ahorros';
        document.getElementById('confSaldoCuenta').textContent = usuario.saldo || '';
        document.getElementById('confNombreCompleto').textContent = usuario.nombre + ' Gutierrez';
        document.getElementById('confEmailUsuario').textContent = usuario.nombre.toLowerCase() + '@correo.com';
        document.getElementById('confFechaCreacion').textContent = '10/10/2025';
        document.getElementById('confEstadoCuenta').textContent = 'Activa';
    }
    document.getElementById('mensajeCambioContrasena').style.display = 'none';
}

// Lógica para cambiar contraseña 
document.addEventListener('DOMContentLoaded', function() {
    const btn = document.getElementById('btnCambiarContrasena');
    if (btn) {
        btn.onclick = function() {
            const nueva = document.getElementById('nuevaContrasena').value;
            if (nueva && nueva.length >= 6) {
                document.getElementById('mensajeCambioContrasena').style.display = '';
                document.getElementById('nuevaContrasena').value = '';
            } else {
                document.getElementById('mensajeCambioContrasena').style.display = 'none';
                alert('La contraseña debe tener al menos 6 caracteres.');
            }
        }
    }
});
window.addEventListener('DOMContentLoaded', mostrarInicio);


function mostrarTransacciones() {
    
    let transacciones = JSON.parse(localStorage.getItem('transacciones')) || [];
    const lista = document.getElementById("lista-transacciones");
    lista.innerHTML = "";
    transacciones.forEach((t, idx) => {
        const li = document.createElement("li");
        li.textContent = `#${idx+1} - Tipo: ${t.tipo} - Monto: $${t.monto}`;
        lista.appendChild(li);
    });
}

// Actualizar saldo en la página principal
function actualizarSaldo(saldo) {
    document.getElementById('saldoCuenta').textContent = saldo;
    const confSaldo = document.getElementById('confSaldoCuenta');
    if (confSaldo) confSaldo.textContent = saldo;
}

// Manejar transacción (deposito/retiro)
document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('formTransaccion');
    if (form) {
        form.onsubmit = function(e) {
            e.preventDefault();
            const tipo = document.getElementById('tipoTransaccion').value;
            const monto = parseFloat(document.getElementById('montoTransaccion').value);
            if (!monto || monto <= 0) {
                alert('Ingresa un monto válido.');
                return;
            }
            let usuario = JSON.parse(localStorage.getItem('usuario'));
            if (!usuario) return;
            let saldoActual = parseFloat(usuario.saldo);
            if (tipo === 'deposito') {
                saldoActual += monto;
            } else if (tipo === 'retiro') {
                if (monto > saldoActual) {
                    alert('Saldo insuficiente.');
                    return;
                }
                saldoActual -= monto;
            }
            usuario.saldo = saldoActual;
            localStorage.setItem('usuario', JSON.stringify(usuario));
            actualizarSaldo(saldoActual);
            // Guardar transacción
            let transacciones = JSON.parse(localStorage.getItem('transacciones')) || [];
            transacciones.push({ tipo: tipo === 'deposito' ? 'Depósito' : 'Retiro', monto });
            localStorage.setItem('transacciones', JSON.stringify(transacciones));
            mostrarTransacciones();
            form.reset();
        }
    }
    // Mostrar el saldo actual
    let usuario = JSON.parse(localStorage.getItem('usuario'));
    if (usuario) actualizarSaldo(usuario.saldo);
});

