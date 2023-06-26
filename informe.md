  <h3 align="center">Laboratorio N°2 : Programacion Orientada a Objetos  </h3>

  <p align="center">
    Paradigmas de Programacion 2023 - FAMAF


Este informe trata sobre la implementacion de un lector automatico de feeds,
como aplicación de consola, bajo el paradigma orientado a objetos,
utilizando como lenguaje de programación Java.


Integrantes:
- Fernando Alvarado, fernando.alvarado@mi.unc.edu.ar
- Ivan Ponzio, ivan.ponzio@mi.unc.edu.ar
- Armando Carral, armando.carral@mi.unc.edu.ar

## Tabla de contenidos

  - [Implemetacion](#Implementacion)
  - [Conclusiones](#Conclusiones)


## Implementacion

Lo primero que tuvimos que hacer para realizar este laboratorio fue investigar
como usar el paradigmas orientados a objetos en Java.
Luego de investigar aquello, comenzamos a realizar los pipelines de trabajo que nos sugirio la catedra.

Para el primer caso, en el modulo de SubscriptionParser parseamos las url's del archivo JSON, y este al parsea va armando una SingleSubscritpion, y a estos se los va agregando a una instancion de Subscription, que contiene una lista de subscripciones.

Para el segundo caso, tuvimos que trabajar en la clase httpRequester, el cual tiene dos metodos
el getFeedRss y getFeedReddit, los cuales reciben el url del feed, estos metodos se encargan de 
solicitar al servidor de feed con el url dado para que el servidor le devuelva el archivo del feed que
solicitaron. Obtenidos estos feed, los metodos devuelven dicho archivo solicitado.

En el caso tres es donde parseamos los atributos de los articulos del Feed que nos solicitaron, es decir
title, description,pubDate,link, la implementacion de esto fue en la clase RssParser, el cual tuvimos que
ir recorriendo todo el feed para extraer esos datos y con estos crear un articulo y agregarlo a una lista
de articulos.

El ultimo caso que teniamos que implementar es de las entidades nombradas,
primero empezamos creando las subclases correspondientes a cada tipo de entidad, con
su respectivos metodos y atributos, asignandole una frecuencia a cada subclase y una
frecuencia general en la clase base. Lo hicimos a traves de la herencia con NameEntity 
como base. Despues continuamos modificando el metodo de computo y el diccionario 
utilizado en la heuristica para poder probar la funcionalidad en nuestra 
implementacion. Luego continuamos creando las clase base de los temas y sus subclases.
Se agrego un atributo tema a cada entidad nombrada.

Por ultimo en el FeedReaderMain, una vez ya parseado todo, para el caso del "-ne" llamamos
computerNameEntity para detectar entidades nombradas, y crear una subclases si es que esta
la entidad en el diccionario con sus respectiva categoria, etc. Por ultimo llamamos a prettyPrint para que imprima.


## Conclusiones
Como conclusion llegamos a que fue un buen proyecto, distintos a los que veniamos haciendo 
en la carrera, lo que llevo que aprendamos cuestiones propias de la orientacion a objetos y 
sobre todo de java, que nos dificultaban la tarea, ya que nos costaba entender la logica 
del lenguaje.
Tambien tuvimos que entender como funcionan las entidades nombradas, y la heuristica para
computarlas.
Por ultimo nos dimos cuenta que nos falto generalizar mas los parseos pero nos costo entender al principio el paradigma a orientado a objetos. 
