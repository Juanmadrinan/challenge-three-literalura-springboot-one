<h1>Challenge Literalura ONE - Oracle Next Education</h1>

<p>Un saludo especial al equipo de revisión de ONE, les presento mi proyecto Literalura desarrollado conforme a las indicaciones requeridas.</p>

<h2>Presentacion de la Aplicación Literalura</h2>
<p>En esta ocación se trabajo sobre el framework de Spring Boot del lenguaje de programación Java, acontinuacion el listado de temas a tratar en este README</p>

<ul>
  <li>Explicación de la API (Gutendex)</li>
  <li>Explicación Basica sobre la Logica y Estructura del Proyecto</li>
  <li>Evidencia Funcionamiento de la Aplicación 😀</li>
</ul>

<h3>Explicación de la API (Gutendex)</h3>
<p>Gutendex es una API desarrollada por los creadores del proyecto Gutenberg, esta contiene una amplia cantidad de datos sobre libros de acceso libre, esta a diferencia de la mayoria de API's no necesita de KEY para hacer peticiones a dicha API. 
En la pagina oficial de la documentación se encuentra las intrucciones de uso de las cuales me guia para desarrollar la API Literalura que la consume.</p>

![image](https://github.com/Juanmadrinan/challenge-three-literalura-springboot-one/assets/125837348/d6c22ed5-e0bd-40f7-a61a-30c03b7e1fec)
<p align="center">[Tomada de ](https://gutendex.com/)</p>


<h3>Explicación Basica sobre la Logica y Estructura del Proyecto</h3>
<p>La estructura de proyecto tiene como objetivo las buenas practicas y legibilidad del codigo</p>
<ul>
  <li>Directorio Menu</li>
    
  ![image](https://github.com/Juanmadrinan/challenge-three-literalura-springboot-one/assets/125837348/c5868862-08cc-471b-af60-583ab5d0568a)
  <p>Contiene la clase Menu en la cual se encuentran las funciones llamadas por la Aplicacion en un switch que permite elegir al Usuario la funcionalidad que necesite entre 5 Opciones requeridas en el plantemiento del Challenge mas 1 que toma como desafio la cual permite ver el Top 10 libros mas descargados.</p>
  
  <li>Directorio Models</li>
  
  ![image](https://github.com/Juanmadrinan/challenge-three-literalura-springboot-one/assets/125837348/d52a2bb5-7562-4877-bd2b-3381de086109)
  <p>Aqui se encuentran los modelos Libro y Autor, en los cuales se distribuyen los datos obtenidos por el JSON envidado por la API Gutenberg, estos datos antes de distribuirse fueron tomados por el Record "Libros" que toma todo el resultado de la busqueda en una lista, que distribuye los datos en el Record "DatosLibro", esto con el objetivo de hacer el mejor tratamiento de datos permitiendome guardar los arrays de datos que vienen en el JSON en listas como "autores" e "idiomas", finalmente estos datos llegan a la clase correspondiente de manera limpia y organizada</p>

  <li>Directorio Repository</li>

  ![image](https://github.com/Juanmadrinan/challenge-three-literalura-springboot-one/assets/125837348/49d5fe2c-1b2f-4deb-a460-58edada21e01)
  <p>En el Directorio Repository se guardan las interfaces que con Spring JPA me permite manejar los datos con querys desde la aplicación como guardar o buscar datos.</p>

  <li>Directorio Service</li>

  ![image](https://github.com/Juanmadrinan/challenge-three-literalura-springboot-one/assets/125837348/82b73650-7927-44c9-897f-5e7ca6144a94)
  <p>En esta ubicación estan las clases ConsumoAPI (Llama a la API con la URL), ConvierteDatos que implementa la interfaz IConvierteDatos en la cual se usa Jackson para tomar el JSON pasado por la API a datos de Java.</p>

  <li>Directorio Resources</li>

  ![image](https://github.com/Juanmadrinan/challenge-three-literalura-springboot-one/assets/125837348/03a3d32c-9eeb-4f31-a803-9a154a595fc8)
  <p>Contiene aplication.properties con la conexión a la base de datos Postgress</p>
</ul>

<h3>Evidencia Funcionamiento de la Aplicación 😀 (Evidencias recortadas por el tamaño de Imagen)</h3>

<h4>Menu Principal</h3>

![image](https://github.com/Juanmadrinan/challenge-three-literalura-springboot-one/assets/125837348/5e7c6862-deb7-49a2-b643-d2b5955b259e)

<h4>Busqueda de Libros</h4>

![image](https://github.com/Juanmadrinan/challenge-three-literalura-springboot-one/assets/125837348/b78c6d26-37f6-44fb-a82e-31f44b8004d5)

<h4>Listado de Libros</h4>

![image](https://github.com/Juanmadrinan/challenge-three-literalura-springboot-one/assets/125837348/58c2f043-dd9e-4100-b368-520ac0793269)

<h4>Listado de Autores</h4>

![image](https://github.com/Juanmadrinan/challenge-three-literalura-springboot-one/assets/125837348/d4c4d7e1-869d-4e8f-bf99-0825f8e9508b)

<h4>Listado de Autores vivos en determinado año</h4>

![image](https://github.com/Juanmadrinan/challenge-three-literalura-springboot-one/assets/125837348/fb525d92-8a11-46ce-92ff-4ac7a9d4a938)

<h4>Listado de libros por idioma</h4>

![image](https://github.com/Juanmadrinan/challenge-three-literalura-springboot-one/assets/125837348/986fcbdd-3fc0-4953-b12a-ca7a9e4f6f39)

![image](https://github.com/Juanmadrinan/challenge-three-literalura-springboot-one/assets/125837348/30040e6b-572c-4497-9dfe-a6eb73a70fc1)

<h4>Top 10 libros mas descargados 📈🌎</h4>

![image](https://github.com/Juanmadrinan/challenge-three-literalura-springboot-one/assets/125837348/71e36b3c-e8e7-42d6-992e-24b2b525551c)

<h4>Evidencia de la Base de datos</h4>
<h5>Tabla Autor</h5>

![image](https://github.com/Juanmadrinan/challenge-three-literalura-springboot-one/assets/125837348/6eaac358-893a-4b8a-a278-0cdcc41a743a)

<h5>Tabla Libros</h5>

![image](https://github.com/Juanmadrinan/challenge-three-literalura-springboot-one/assets/125837348/6367ea8e-7b53-44df-bf84-4e5376157f0a)

<h3>Gracias Alura Y ONE 😊</h3>
