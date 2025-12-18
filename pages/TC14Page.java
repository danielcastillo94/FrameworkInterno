# language: es
Característica: Aprovisionar servicio RBT en HLR de Claro Perú
  Como sistema de aprovisionamiento
  Quiero validar que el servicio RBT sea aprovisionado correctamente en el HLR
  Para asegurar que los clientes puedan utilizar tonos de espera personalizados

  Antecedentes:
    Dado que el proceso de aprovisionamiento ha sido iniciado exitosamente
    Y Instanlink está operativo y conectado al IMS/HLR
    Y la línea está activa en la red de Claro
    Y el cobro y elegibilidad han sido validados previamente

  @funcional @rbt @hlr @aprovisionamiento
  Escenario: Validar aprovisionamiento exitoso de servicio RBT en HLR
    Dado que Instanlink recibe la solicitud de aprovisionamiento del componente de integración
    Cuando Instanlink envía comandos de provisión al IMS/HLR de Claro
    Y el IMS/HLR ejecuta los comandos de aprovisionamiento para activar servicio RBT
    Y el IMS/HLR responde con código de éxito OK a Instanlink
    Entonces Instanlink debe tener la solicitud de aprovisionamiento en cola
    Y el IMS/HLR debe recibir los comandos de aprovisionamiento
    Y el IMS/HLR debe procesar los comandos exitosamente
    Y Instanlink debe recibir respuesta OK del HLR
    Cuando se realiza una llamada de prueba a la línea aprovisionada
    Entonces la llamada debe establecerse correctamente
    Y se debe escuchar el tono de espera RBT configurado o por defecto
    Y el HLR debe mostrar el servicio RBT activo para la línea