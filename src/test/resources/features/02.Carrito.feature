# language: en
Feature: Validar la funcionalidad del carrito de compras en Sauce Labs

  @Smoke @Carrito
  Scenario Outline: Validar agregar productos al carrito de compras
    Given ingreso al aplicativo "saucelabs"
    And ingreso el usuario "standard_user"
    And ingreso la clave "secret_sauce"
    And hago clic en LOGIN
    And valido que carguen correctamente los productos en la galeria
    When agrego <UNIDADES> del siguiente producto "<PRODUCTO>"
    Then valido el carrito de compra actualice correctamente

    Examples:
      | PRODUCTO                  | UNIDADES |
      | Sauce Labs Backpack       | 1        |
      | Sauce Labs Bolt - T-Shirt | 1        |
      | Sauce Labs Bike Light     | 2        |