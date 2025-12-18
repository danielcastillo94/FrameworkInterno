Feature: Validar rechazo de elegibilidad por comportamiento de pago fuera de CP3, CP4 y CP5

  Como sistema de elegibilidad
  Quiero validar el comportamiento de pago de las líneas
  Para rechazar aquellas que no cumplan con los criterios CP3, CP4 o CP5

  Background:
    Given el API de elegibilidad está disponible
    And la línea existe en la plataforma

  @Funcional @Elegibilidad @ComportamientoPago
  Scenario: Rechazar línea con comportamiento de pago diferente a CP3, CP4 o CP5
    Given tengo una línea activa con comportamiento de pago "CP1"
    When consulto la elegibilidad de la línea mediante API APIGEE AMX
    Then el sistema recibe la solicitud correctamente
    And valida que la línea sea activa y de tipo móvil
    And verifica que la línea no esté en BlackList SVA
    And valida el comportamiento de pago del cliente asociado
    And verifica que el comportamiento de pago sea diferente a CP3, CP4 y CP5
    And rechaza la elegibilidad de la línea por comportamiento de pago no válido
    And envía respuesta de no elegibilidad al APIGEE AMX con el motivo "comportamiento_pago_invalido"
    And Onmobile informa al cliente que no es elegible

  @Funcional @Elegibilidad @ComportamientoPago
  Scenario Outline: Rechazar líneas con diferentes comportamientos de pago no válidos
    Given tengo una línea activa con comportamiento de pago "<comportamiento_pago>"
    When consulto la elegibilidad de la línea mediante API APIGEE AMX
    Then el sistema rechaza la elegibilidad
    And el motivo del rechazo es "comportamiento_pago_invalido"
    And el cliente recibe notificación de no elegibilidad

    Examples:
      | comportamiento_pago |
      | CP1                 |
      | CP2                 |
      | CP6                 |
      | CP7                 |
      | CP8                 |