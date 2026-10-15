# language: en
Feature: Inicio de sesion en Sauce Labs Sample App

  Como usuario de la app movil de Sauce Labs
  Quiero iniciar sesion con credenciales validas
  Para acceder al catalogo de productos

  @SauceLabsLogin @Smoke
  Scenario: Login exitoso con credenciales validas
  Given ingreso al aplicativo "saucelabs"
  When ingreso el usuario "standard_user"
  And ingreso la clave "secret_sauce"
  And hago clic en LOGIN
  Then valido el login OK
  And cierro el aplicativo "com.swaglabsmobileapp"