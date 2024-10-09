<p align="center">
  <img src="/images/conversion.jpg" alt="Portada del proyecto" width="600">
</p>

<h1 align = "center"> Conversor de Monedas</h1>

Este proyecto desarrollado en Java permite realizar conversiones entre diferentes tipos de monedas de forma sencilla. Además, ofrece la opción de generar distintos tipos de historiales según las necesidades del usuario

## Características

- Conversión entre diferentes monedas.
- Actualización automática de tipos de cambio.
- Interfaz en consola fácil de usar.
- Persistencia de datos en archivos `.json` para mantener registros de conversiones.

## Requisitos

Antes de instalar y ejecutar el proyecto, asegúrate de tener lo siguiente instalado:

- [Java JDK 11 o superior]
- Conexión a internet para acceder a la API de conversión de monedas

## Instalación

1. Clona este repositorio:

2. Navega al directorio del proyecto: (src -> ConversorApp)

3. Compila y ejecuta el proyecto:

## Uso

1. Al ejecutar la aplicación, verás un menú que te permite seleccionar la moneda de origen y destino
   al igual que los dos tipos de historiales disponibles.
   
   <img src="/images/menu.jpg" alt="Menu de la app" width="500">

2. Para las opciones (1, 2, 3, 4, 5, 6) Ingresa el valor a convertir.

   <img src="/images/usoConvertirMoneda.jpg" alt="Ejemplo usando la opcion 5" width="500">
   
3. La opcion 7 nos devuelve las ultimas 5 conversiones realizadas en el ultimo DIA de uso de la app.

   <img src="/images/usoHistorialParcial.jpg" alt="Ejemplo usando la opcion 7" width="500">

4. La opcion 8 nos solicita una entrada de acuerdo a la lista de fechas de las cuales se tiene un historial de
   conversiones realizadas.

   <img src="/images/opcionesFechas.jpg" alt="Ejemplo usando la opcion 8 entrada" width="500">
   
   <img src="/images/usoHisotialTotal.jpg" alt="Ejemplo usando la opcion 8 historial" width="500">
   
5. Por ultimo con la opcion "9" se termina el programa

   

## Contribuciones

Si deseas contribuir a este proyecto, por favor sigue los siguientes pasos:

1. Haz un fork del repositorio.
2. Crea una nueva rama (`git checkout -b nueva-funcionalidad`).
3. Realiza tus cambios y realiza un commit (`git commit -m 'Añadir nueva funcionalidad'`).
4. Envía tus cambios al repositorio remoto (`git push origin nueva-funcionalidad`).
5. Abre un Pull Request.

## Licencia

Este proyecto está bajo la Licencia MIT. Mira el archivo [LICENSE](LICENSE) para más detalles.
