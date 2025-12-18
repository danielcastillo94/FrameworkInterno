#language: es
Característica: Validar rechazo de elegibilidad por línea en BlackList SVA
  Como sistema de elegibilidad
  Quiero validar que las líneas registradas en BlackList SVA sean rechazadas
  Para evitar que usuarios no autorizados accedan al servicio

  Antecedentes:
    Dado que la línea existe en la plataforma
    Y el API de elegibilidad está disponible
    Y la línea está registrada en BlackList SVA

  Escenario: Rechazar elegibilidad de línea en BlackList SVA
    Dado que consulto elegibilidad de una línea activa mediante API APIGEE AMX
    Cuando el sistema recibe la solicitud de consulta de elegibilidad
    Y valida que la línea sea activa y de tipo móvil
    Y verifica que la línea se encuentra registrada en la BlackList SVA
    Entonces el sistema rechaza la elegibilidad de la línea por estar en BlackList SVA
    Y marca la línea como no elegible
    Y envía respuesta de no elegibilidad al APIGEE AMX indicando el motivo
    Y Onmobile informa al cliente que no es un usuario elegible