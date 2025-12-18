Feature: Validar cobro parcial cuando no se puede cobrar la tarifa completa en renovación y se intenta cobrar Step 2

  Background:
    Given el usuario tiene acceso al sistema de gestión de suscripciones RBT
    And existe configuración de tarifas parciales en CMS con Step 1: S/3.00, Step 2: S/2.00, Step 3: S/1.00

  @TC-11 @funcional @cobro-parcial
  Scenario: Validar cobro parcial exitoso del Step 2 cuando falla el Step 1 por límite de crédito insuficiente
    Given una línea postpago o B2E está configurada con suscripción activa de RBT en plataforma OnMobile
    And el límite de crédito es insuficiente para cobro completo pero suficiente para Step 2
    When se simula renovación de servicio RBT con intento de cobro de tarifa completa Step 1 de S/3.00
    And el cobro del Step 1 falla por falta de límite de crédito disponible
    And OnMobile solicita al CMS realizar cobro del Step 2 de S/2.00
    Then CMS recibe la solicitud de cobro del Step 2
    And CMS ejecuta el cobro del Step 2 de S/2.00 exitosamente
    And CMS envía respuesta exitosa a OnMobile indicando cobro parcial del Step 2
    And el cobro del Step 2 se refleja correctamente en la factura del cliente con monto S/2.00
    And el cobro aparece en el recibo del cliente con el monto de S/2.00
    And CMS agenda reintentos de cobro del Step 3 de S/1.00 cada 24 horas durante el periodo restante hasta completar 8 semanas