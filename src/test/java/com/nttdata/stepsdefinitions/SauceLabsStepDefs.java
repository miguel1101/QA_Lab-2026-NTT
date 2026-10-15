package com.nttdata.stepsdefinitions;

import com.nttdata.steps.SauceLabsLoginSteps;
import com.nttdata.support.ScreenshotAttacher;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertTrue;

/**
 * Step definitions especificos del login de Sauce Labs.
 * <p>
 * NO contiene el step "Given ingreso al aplicativo...": ese step es
 * generico y vive en {@link AppConfigStepsDefs}, reutilizable para
 * cualquier app.
 */
public class SauceLabsStepDefs {

    private final SauceLabsLoginSteps sauceLabsLoginSteps = new SauceLabsLoginSteps();

    private Scenario scenario;

    @Before
    public void configurarScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    @After
    public void tearDown(Scenario scenario) {
        System.out.println("[Hooks] Finalizando escenario: " + scenario.getName());
        try {
            // Llamamos al método que cierra o finaliza la sesión del driver para que el siguiente escenario empiece limpio
            com.nttdata.screens.AppConfigScreen.cerrarDriver();
        } catch (Exception e) {
            System.out.println("[Hooks] No se pudo cerrar el driver automáticamente: " + e.getMessage());
        }
    }

    @When("ingreso el usuario {string}")
    public void ingreso_el_usuario(String usuario) {
        sauceLabsLoginSteps.ingresarUsuario(usuario);
    }

    @And("ingreso la clave {string}")
    public void ingreso_la_clave(String clave) {
        sauceLabsLoginSteps.ingresarClave(clave);
    }

    @And("hago clic en LOGIN")
    public void hago_clic_en_login() {
        sauceLabsLoginSteps.clicEnLogin();
        ScreenshotAttacher.attach(scenario, "Despues de hacer login");
    }

    @Then("valido el login OK")
    public void valido_el_login_ok() {
        assertTrue(
                "No se encontro la pantalla de productos: el login no fue exitoso",
                sauceLabsLoginSteps.loginFueExitoso()
        );
    }
    @And("valido que carguen correctamente los productos en la galeria")
    public void valido_que_carguen_correctamente_los_productos_en_la_galeria() {
        assertTrue(
                "La galería de productos no cargó correctamente",
                sauceLabsLoginSteps.validarGaleriaProductos()
        );
        ScreenshotAttacher.attach(scenario, "Galeria de productos cargada");
    }

    @When("agrego {int} del siguiente producto {string}")
    public void agrego_del_siguiente_producto(Integer unidades, String producto) {
        // Aquí llamas a tu lógica para seleccionar y agregar las unidades del producto
        sauceLabsLoginSteps.agregarProductoAlCarrito(producto, unidades);
        ScreenshotAttacher.attach(scenario, "Producto agregado al carrito: " + producto);
    }

    @Then("valido el carrito de compra actualice correctamente")
    public void valido_el_carrito_de_compra_actualice_correctamente() {
        // Aquí validas que el contador o el detalle del carrito refleje los productos
        assertTrue(
                "El carrito de compras no se actualizó correctamente",
                sauceLabsLoginSteps.validarCarritoActualizado()
        );
        ScreenshotAttacher.attach(scenario, "Validacion de carrito exitosa");
    }

}
