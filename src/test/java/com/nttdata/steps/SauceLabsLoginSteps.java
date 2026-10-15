package com.nttdata.steps;

import com.nttdata.screens.SauceLabsLoginScreen;

/**
 * Caso de uso de login en la app movil de Sauce Labs.
 * Delega toda la interaccion de UI en {@link SauceLabsLoginScreen}.
 */
public class SauceLabsLoginSteps {

    private final SauceLabsLoginScreen screen = new SauceLabsLoginScreen();

    public void ingresarUsuario(String usuario) {
        System.out.println("[SauceLabsLoginSteps] ingresarUsuario -> " + usuario);
        screen.escribirUsuario(usuario);
    }

    public void ingresarClave(String clave) {
        System.out.println("[SauceLabsLoginSteps] ingresarClave");
        screen.escribirClave(clave);
    }

    public void clicEnLogin() {
        System.out.println("[SauceLabsLoginSteps] clicEnLogin");
        screen.clicEnLogin();
    }

    public boolean loginFueExitoso() {
        System.out.println("[SauceLabsLoginSteps] loginFueExitoso: validando resultado...");
        return screen.estaEnPantallaDeProductos();
    }

    public boolean validarGaleriaProductos() {
        System.out.println("[SauceLabsLoginSteps] validarGaleriaProductos: verificando carga de la galeria...");
        return screen.validarGaleriaCargada();
    }

    public void agregarProductoAlCarrito(String producto, int unidades) {
        System.out.println("[SauceLabsLoginSteps] agregarProductoAlCarrito -> Producto: " + producto + ", Unidades: " + unidades);
        for (int i = 0; i < unidades; i++) {
            screen.seleccionarYAgregarProducto(producto);
        }
    }

    public boolean validarCarritoActualizado() {
        System.out.println("[SauceLabsLoginSteps] validarCarritoActualizado: verificando contador/carrito...");
        return screen.verificarCarritoConProductos();
    }
}