# language: es
Característica: Validar elegibilidad de línea prepago activa

  Antecedentes:
    Dado que la línea debe estar activa en la plataforma
    Y el API de elegibilidad debe estar disponible
    Y la línea debe contar con saldo prepago disponible

  Escenario: Validar que una línea prepago activa cumpla con los criterios de elegibilidad para el servicio de tonos de espera
    Dado que consulto elegibilidad de una línea prepago activa mediante API APIGEE AMX
    Cuando el sistema recibe la solicitud de consulta de elegibilidad correctamente
    Y valido que la línea sea activa y de tipo móvil prepago
    Entonces el sistema confirma que la línea es activa y de tipo prepago
    Cuando verifico que la línea no se encuentre en la BlackList SVA
    Entonces el sistema confirma que la línea no está en BlackList SVA
    Cuando valido que el cliente tenga comportamiento de pago CP3, CP4 o CP5
    Entonces el sistema confirma que el comportamiento de pago del cliente está dentro de CP3, CP4 o CP5
    Cuando verifico que la línea tenga saldo prepago suficiente para la suscripción
    Entonces el sistema confirma que el saldo prepago es suficiente para el servicio
    Cuando envío respuesta de elegibilidad exitosa al APIGEE AMX
    Entonces el APIGEE AMX recibe la respuesta indicando que la línea es elegible para el servicio