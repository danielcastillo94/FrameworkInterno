Feature: Validar cobro parcial cuando no se puede cobrar la tarifa completa en renovación y se intenta cobrar Step 2

  Background:
    Given una línea postpago o B2E activa con suscripción RBT vigente
    And configuración de tarifas parciales creada en CMS con Step 1: S/3.00, Step 2: S/2.00, Step 3: S/1.00
    And límite de crédito insuficiente para cobro completo pero suficiente para Step 2

  @ID_11 @Funcional @CobroParcial
  Scenario: Validar cobro parcial con Step 2 de S/2.00 en renovación RBT
    Given que la línea está configurada correctamente con servicio RBT activo en plataforma OnMobile
    When se simula renovación de servicio RBT con intento de cobro de tarifa completa Step 1 de S/3.00
    Then el CMS indica que el cobro del Step 1 falló por falta de límite de crédito
    When OnMobile solicita al CMS realizar cobro del Step 2 de S/2.00
    Then el CMS recibe solicitud de cobro del Step 2
    When el CMS ejecuta el cobro del Step 2 de S/2.00
    Then el cobro del Step 2 se ejecuta exitosamente
    And el CMS envía respuesta exitosa a OnMobile indicando cobro parcial del Step 2
    And OnMobile recibe confirmación de cobro exitoso del Step 2
    And el cobro del Step 2 se refleja correctamente en la factura del cliente con monto S/2.00
    And el cobro aparece en el recibo del cliente con el monto de S/2.00
    And el CMS intenta cobrar la diferencia Step 3 de S/1.00 cada 24 horas durante el periodo restante hasta completar 8 semanas