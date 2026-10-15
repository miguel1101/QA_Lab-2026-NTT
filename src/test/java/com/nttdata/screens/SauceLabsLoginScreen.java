package com.nttdata.screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.nttdata.screens.AppConfigScreen.getDriver;

public class SauceLabsLoginScreen {

    private static final Duration TIMEOUT = Duration.ofSeconds(20);

    private final By txtUsuario = AppiumBy.accessibilityId("test-Username");
    private final By txtClave = AppiumBy.xpath("//android.widget.EditText[@content-desc=\"test-Password\"]");
    private final By btnLogin = AppiumBy.accessibilityId("test-LOGIN");
    private final By tituloProductos = AppiumBy.accessibilityId("test-PRODUCTS");

    public void escribirUsuario(String usuario) {
        System.out.println("[SauceLabsLoginScreen] Escribiendo usuario: " + usuario);
        AndroidDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);

        // Espera explícita mejorada para asegurar visibilidad antes de interactuar
        WebElement campoUsuario = wait.until(ExpectedConditions.visibilityOfElementLocated(txtUsuario));
        campoUsuario.clear(); // Limpia el campo por si acaso
        campoUsuario.sendKeys(usuario);
    }

    public void escribirClave(String clave) {
        System.out.println("[SauceLabsLoginScreen] Escribiendo clave...");
        AndroidDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);

        // Espera explícita para asegurar que el campo password sea visible
        WebElement campoClave = wait.until(ExpectedConditions.visibilityOfElementLocated(txtClave));
        campoClave.clear();
        campoClave.sendKeys(clave);
    }

    public void clicEnLogin() {
        System.out.println("[SauceLabsLoginScreen] Haciendo clic en el boton LOGIN");
        AndroidDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);

        // Espera explícita para asegurar que el botón sea clickeable
        WebElement botonLogin = wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
        botonLogin.click();
    }
    public boolean estaEnPantallaDeProductos() {
        System.out.println("[SauceLabsLoginScreen] Validando que se llego a la pantalla de productos...");
        AndroidDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        WebElement titulo = wait.until(ExpectedConditions.presenceOfElementLocated(tituloProductos));
        boolean visible = titulo.isDisplayed();
        System.out.println("[SauceLabsLoginScreen] Pantalla de productos visible: " + visible);
        return visible;
    }

    public boolean validarGaleriaCargada() {
        System.out.println("[SauceLabsLoginScreen] Validando que cargue la galería de productos...");
        AndroidDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        WebElement titulo = wait.until(ExpectedConditions.visibilityOfElementLocated(tituloProductos));
        return titulo.isDisplayed();
    }

    public void seleccionarYAgregarProducto(String nombreProducto) {
        System.out.println("[SauceLabsLoginScreen] Buscando y agregando: " + nombreProducto);
        AndroidDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);

        try {
            String scrollCommand = "new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\").instance(0))" +
                    ".scrollIntoView(new UiSelector().text(\"" + nombreProducto + "\"));";
            driver.findElement(AppiumBy.androidUIAutomator(scrollCommand));
        } catch (Exception e) {
            System.out.println("[SauceLabsLoginScreen] El scroll ya no era necesario.");
        }

        String xpathBtnAgregar = "//*[@text='" + nombreProducto + "']/../following-sibling::*//android.view.ViewGroup[@content-desc='test-ADD TO CART']";
        WebElement btnAgregar = null;
        try {
            btnAgregar = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathBtnAgregar)));
        } catch (Exception e) {
            System.out.println("[SauceLabsLoginScreen] Usando selector alternativo UiAutomator para el botón...");
            String uiAutomatorClick = "new UiSelector().text(\"ADD TO CART\")";
            btnAgregar = driver.findElement(AppiumBy.androidUIAutomator(uiAutomatorClick));
        }

        btnAgregar.click();
    }

    public boolean verificarCarritoConProductos() {
        System.out.println("[SauceLabsLoginScreen] Verificando que el carrito se actualizó...");
        AndroidDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        By badgeCarrito = AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"test-Cart\"]//android.widget.TextView");
        WebElement carritoBadge = wait.until(ExpectedConditions.visibilityOfElementLocated(badgeCarrito));
        return carritoBadge.isDisplayed();
    }
}