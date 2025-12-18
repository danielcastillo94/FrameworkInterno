Feature: Validar elegibilidad de línea postpago masivo activa

  Background:
    Given la línea debe estar activa en la plataforma
    And el API de elegibilidad debe estar disponible
    And la línea debe tener límite de crédito disponible

  @ElegibilidadPostpagoMasivo @Funcional
  Scenario: Validar que una línea postpago masivo activa cumpla con los criterios de elegibilidad para el servicio de tonos de espera
    Given consulto elegibilidad de una línea postpago masivo activa mediante API APIGEE AMX
    When el sistema recibe la solicitud de consulta de elegibilidad
    Then el sistema confirma que la línea es activa y de tipo postpago masivo
    And el sistema confirma que la línea no está en BlackList SVA
    And el sistema confirma que el comportamiento de pago del cliente está dentro de CP3 CP4 o CP5
    And el sistema confirma que el límite de crédito disponible es suficiente para la tarifa mensual de S/3.00
    And el APIGEE AMX recibe la respuesta indicando que la línea es elegible para el servicio