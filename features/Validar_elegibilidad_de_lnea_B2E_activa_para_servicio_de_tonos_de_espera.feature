Feature: Validar elegibilidad de línea B2E activa para servicio de tonos de espera

  Como sistema de validación de servicios
  Quiero verificar la elegibilidad de una línea B2E activa
  Para confirmar si puede contratar el servicio de tonos de espera

  Background:
    Given la línea debe estar activa en la plataforma
    And el API de elegibilidad debe estar disponible
    And la línea debe tener límite de crédito disponible

  @funcional @elegibilidad @B2E
  Scenario: Validar que una línea B2E activa cumpla con los criterios de elegibilidad para el servicio de tonos de espera
    Given que se realiza una consulta de elegibilidad de una línea B2E activa mediante API APIGEE AMX
    When el sistema recibe la solicitud de consulta de elegibilidad correctamente
    Then el sistema valida que la línea sea activa y de tipo móvil B2E
    And el sistema verifica que la línea no se encuentre en la BlackList SVA
    And el sistema valida que el cliente tenga comportamiento de pago CP3, CP4 o CP5
    And el sistema verifica que la línea tenga límite de crédito disponible suficiente para la tarifa mensual de S/3.00
    And el APIGEE AMX recibe la respuesta indicando que la línea es elegible para el servicio