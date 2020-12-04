# Proyecto final seguridad
## Chat cifrado AES 128 Diffie-Hellman
### Cómo se hizo
Para la implementación del chat se desarrollaron dos proyectos distintos, uno utilizado para el funcionamiento del servidor y otro para el funcionamiento del cliente del chat.
Debe estar ejecutándose el servidor para que este esté pendiente de las peticiones de los clientes. Además, es necesario que hayan dos clientes en linea
para poder ejecutar la ventana de chat, esto con el objetivo de esperar a un segundo participante para ejecutar el algoritmo Diffie-Hellman.
Cuando dos clientes se conectan, el servidor gestiona sus conexiones y queda a la escucha, esperando a que empiece la conversación para intercambiar
las correspondientes llaves. Una vez se inicia la conversación, el servidor provee a cada cliente una clave (igual para ambos) equivalente a un número primo 
bastante alto. Una vez cada uno de los clientes recibe esta clave del servidor, la eleva a una potencia entre 6 y 8 (su clave privada) que es escogida de manera aleatoria por 
cada uno de los clientes. Cuando se ha calculado este valor, se comparte con el otro usuario y viceversa, compartiendo una llave pública entre cada usuario.
Una vez intercambiadas las llaves, cada usuario nuevamente eleva este número recibido a su clave privada, obteniendo para ambos la misma clave, con la cual se 
realiza la encriptación y desencriptación de los mensajes, a través de AES con una clave de 128 bits.

### Dificultades
Nuestra mayor dificultad se basó en el formato de conversión al momento de enciptar y desencriptar los mensajes, pues debían pasarse los mensajes de una variable String a un arreglo de bytes,
por lo cual habían errores de decodificación en el momento de la conversión. Además, también tuvimos dificultades al momento de trabajar con números tan grandes, por lo que se debió usar 
un dato de tipo BigInteger para almacenar las claves.

### Conclusiones
Con la elaboración de este proyecto pudimos ver más de cerca y entender mejor el algoritmo Diffie-Hellman, además de sus ventajas para el intercambio de claves de manera segura.
Por otra parte, pudimos evidenciar lo segura que puede llegar a ser una clave de 128 bits por su longitud y aún más utilizando el algoritmo mencionado anteriormente.
Otro aspecto a destacar es que hicimos uso de el API de seguridad de Java y aprendimos acerca de esta, evidenciando que tiene herramientas sumamente útiles para elaborar 
aplicaciones más seguras.
