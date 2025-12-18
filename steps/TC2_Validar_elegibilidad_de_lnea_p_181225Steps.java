Feature: Validar elegibilidad de línea postpago masivo activa

  Background:
    Given la línea debe estar activa en la plataforma
    And el API de elegibilidad debe estar disponible
    And la línea debe tener límite de crédito disponible

  @Funcional @Elegibilidad @PostpagoMasivo
  Scenario: Validar que una línea postpago masivo activa cumpla con los criterios de elegibilidad para el servicio de tonos de espera
    Given que consulto la elegibilidad de una línea postpago masivo activa mediante API APIGEE AMX
    When el sistema recibe la solicitud de consulta de elegibilidad correctamente
    Then valido que la línea sea activa y de tipo móvil postpago masivo
    And verifico que la línea no se encuentre en la BlackList SVA
    And valido que el cliente tenga comportamiento de pago CP3, CP4 o CP5
    And verifico que la línea tenga límite de crédito disponible suficiente para la tarifa mensual de S/3.00
    And envío respuesta de elegibilidad exitosa al APIGEE AMX
    Then el APIGEE AMX recibe la respuesta indicando que la línea es elegible para el servicio