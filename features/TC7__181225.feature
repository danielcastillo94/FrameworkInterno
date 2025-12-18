# language: es
Característica: Validar saldo prepago suficiente para suscripción

  Como sistema de elegibilidad
  Quiero validar el saldo prepago disponible de una línea
  Para determinar si es elegible para suscribirse a un servicio

  Antecedentes:
    Dado que la línea debe estar activa en la plataforma
    Y el API de elegibilidad debe estar disponible
    Y la línea debe ser de tipo prepago
    Y el sistema debe poder consultar el saldo prepago disponible

  Escenario: Validar elegibilidad de línea prepago con saldo suficiente
    Dado que consulto elegibilidad de una línea prepago activa mediante API APIGEE AMX
    Cuando el sistema recibe la solicitud de consulta de elegibilidad correctamente
    Y valido que la línea sea activa y de tipo móvil prepago
    Y el sistema confirma que la línea es activa y de tipo prepago
    Y verifico que la línea no se encuentre en la BlackList SVA
    Y el sistema confirma que la línea no está en BlackList SVA
    Y valido que el cliente tenga comportamiento de pago CP3, CP4 o CP5
    Y el sistema confirma que el comportamiento de pago del cliente está dentro de CP3, CP4 o CP5
    Y consulto el saldo prepago disponible de la línea
    Y el sistema obtiene el saldo prepago disponible de la línea
    Y comparo el saldo prepago disponible con el costo del servicio
    Y el sistema valida si el saldo prepago es suficiente para el servicio
    Entonces apruebo o rechazo la elegibilidad según el resultado de la validación del saldo
    Y el sistema marca la línea como elegible si tiene saldo suficiente, o no elegible si no lo tiene
    Y envío respuesta de elegibilidad al APIGEE AMX con el resultado
    Y el APIGEE AMX recibe la respuesta con el estado de elegibilidad de la línea

  Escenario: Validar elegibilidad de línea prepago con saldo insuficiente
    Dado que consulto elegibilidad de una línea prepago activa mediante API APIGEE AMX con saldo insuficiente
    Cuando el sistema recibe la solicitud de consulta de elegibilidad correctamente
    Y valido que la línea sea activa y de tipo móvil prepago
    Y el sistema confirma que la línea es activa y de tipo prepago
    Y verifico que la línea no se encuentre en la BlackList SVA
    Y el sistema confirma que la línea no está en BlackList SVA
    Y valido que el cliente tenga comportamiento de pago CP3, CP4 o CP5
    Y el sistema confirma que el comportamiento de pago del cliente está dentro de CP3, CP4 o CP5
    Y consulto el saldo prepago disponible de la línea
    Y el sistema obtiene el saldo prepago disponible de la línea menor al costo
    Y comparo el saldo prepago disponible con el costo del servicio
    Entonces el sistema valida que el saldo prepago es insuficiente para el servicio
    Y el sistema marca la línea como no elegible por saldo insuficiente
    Y envío respuesta de elegibilidad al APIGEE AMX con el resultado de rechazo
    Y el APIGEE AMX recibe la respuesta con el estado no elegible de la línea