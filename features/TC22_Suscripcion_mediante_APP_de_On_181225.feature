Feature: Suscripcion mediante APP de Onmobile con datos moviles activos y deteccion automatica de numero

  Como usuario de telefonia movil
  Quiero suscribirme al servicio de tonos de espera mediante la APP de Onmobile usando datos moviles
  Para que mi numero sea detectado automaticamente mediante Header Enrichment

  Background:
    Given el usuario tiene una linea movil prepago o postpago masivo o B2E activa
    And el usuario esta conectado a datos moviles y no a WiFi
    And la configuracion de Header Enrichment esta activa en PGW
    And las IPs y URLs de Onmobile estan configuradas en HLIMSPGW07C

  @TC22 @Funcional @HeaderEnrichment @Suscripcion
  Scenario: Validar suscripcion mediante APP con Header Enrichment exitoso
    Given el usuario accede a la APP de Onmobile utilizando datos moviles activos
    Then la APP carga correctamente y muestra el catalogo de tonos de espera
    When el usuario selecciona la opcion de suscripcion desde la APP
    Then el sistema detecta automaticamente el MSISDN mediante Header Enrichment
    And el numero telefonico se muestra en pantalla
    When el usuario visualiza su numero pre-cargado y confirma la suscripcion mediante doble optin
    Then el sistema registra la confirmacion y envia solicitud de elegibilidad al APIGEE AMX
    When el sistema valida elegibilidad de linea activa movil, no en BlackList SVA, CP 3-4-5, y saldo disponible
    Then el sistema retorna respuesta exitosa de elegibilidad
    When el sistema ejecuta cobro de tarifa mensual completa de S/. 3.00 mediante CMS
    Then CMS procesa el cobro exitosamente y envia confirmacion
    When el sistema envia solicitud de aprovisionamiento al APIGEE AMX
    Then Instanlink provisiona el servicio en HLR y confirma aprovisionamiento exitoso
    When el sistema envia notificacion de aprovisionamiento exitoso al APIGEE AMX
    Then APIGEE AMX notifica confirmacion a Onmobile
    When Onmobile aprovisiona el servicio en sus plataformas
    Then el servicio queda activo en plataforma Onmobile
    And el sistema envia SMS de confirmacion al usuario
    And el usuario recibe SMS confirmando suscripcion exitosa
    When el usuario realiza o recibe una llamada de prueba
    Then se escucha el tono de espera por default o el seleccionado