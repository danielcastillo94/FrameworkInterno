Feature: Ejecutar cobro mensual de tarifa completa S300

  Como sistema CMS
  Quiero ejecutar correctamente el cobro mensual de la tarifa completa de S/3.00
  Para asegurar la facturación del servicio de tonos de espera

  Background:
    Given la línea ha pasado el proceso de elegibilidad
    And el CMS está disponible
    And la tarifa mensual de S/3.00 está configurada en el CMS
    And la línea tiene crédito o saldo suficiente disponible

  @funcional @cobro_mensual
  Scenario Outline: Validar cobro mensual de tarifa completa para línea <tipo_linea>
    Given Onmobile solicita cobro de suscripción mensual al CMS con tarifa Step 1 de S/3.00
    When el CMS recibe la solicitud de cobro correctamente
    And el CMS ejecuta el cobro de la tarifa mensual completa de S/3.00 a la línea
    And el sistema procesa el cobro exitosamente
    Then el monto de S/3.00 es cargado a la cuenta del cliente <tipo_linea>
    And el CMS envía respuesta exitosa del cobro a Onmobile
    And Onmobile recibe la confirmación de cobro exitoso
    And se valida el reflejo del cobro según el tipo de línea <tipo_linea>

    Examples:
      | tipo_linea |
      | postpago   |
      | prepago    |

  @funcional @cobro_mensual @postpago
  Scenario: Validar que en líneas postpago el cobro aparezca en la facturación
    Given Onmobile solicita cobro de suscripción mensual al CMS con tarifa Step 1 de S/3.00
    And la línea es de tipo postpago
    When el CMS ejecuta el cobro de la tarifa mensual completa de S/3.00
    And el cobro se procesa exitosamente
    Then el cobro de S/3.00 se refleja en el recibo del cliente postpago
    And la cuenta contable asociada es 4206050071

  @funcional @cobro_mensual @prepago
  Scenario: Validar que en líneas prepago el saldo se descuente correctamente
    Given Onmobile solicita cobro de suscripción mensual al CMS con tarifa Step 1 de S/3.00
    And la línea es de tipo prepago
    And el saldo inicial de la línea es mayor o igual a S/3.00
    When el CMS ejecuta el cobro de la tarifa mensual completa de S/3.00
    And el cobro se procesa exitosamente
    Then el saldo prepago de la línea se reduce en S/3.00
    And el nuevo saldo es igual al saldo inicial menos S/3.00