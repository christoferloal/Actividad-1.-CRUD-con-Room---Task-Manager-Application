ACTIVIDADES - PERSISTENCIA CON ROOM

Actividad 1. CRUD con Room

-Task Manager Application

Aplicación Android desarrollada como parte de la Actividad 1 de Persistencia de Datos.  
Permite gestionar tareas mediante operaciones CRUD almacenadas localmente en SQLite usando Room.

Descripcion

La aplicación implementa:
- Persistencia de datos con Room.
- Arquitectura MVC.
- Operaciones CRUD completas: agregar, editar, eliminar y listar tareas.
- Validaciones básicas en el formulario.
- Interfaz gráfica con RecyclerView.
- Estándares de nomenclatura solicitados:
  - Clases en PascalCase
  - Métodos y variables en camelCase
  - Columnas de base de datos en snake_case

Instrucciones de instalación y ejecución.

1. Clonar el repositorio:
   git clone https://github.com/christoferloal/Actividad-1.-CRUD-con-Room---Task-Manager-Application.git
2. Abrir el proyecto en Android Studio.
3. Esperar a que se sincronicen las dependencias de Gradle.
4. Ejecutar la app en un emulador o dispositivo físico.

Estructura de la base de datos.

 columna           -tipo      -descripción                       

 id                -Int       -llave primaria autogenerada       
 task_title        -String    -título de la tarea                
 task_description  -String    -descripción de la tarea           
 created_at        -String    -fecha de creación (timestamp)     
 is_completed      -Boolean   -estado de la tarea                


Capturas de pantalla de las interfaces de usuario.

Pantalla principal

<img width="1080" height="2340" alt="pantallap_20251129_211132" src="https://github.com/user-attachments/assets/16c532e2-0763-412a-8032-6c223decd24a" />

<img width="1080" height="2340" alt="pantallap2_20251129_211238" src="https://github.com/user-attachments/assets/ad858d89-6c15-4c31-b358-68c6549cebea" />

Formulario de agregar/editar tarea

<img width="1080" height="2340" alt="Formulario_20251129_210626" src="https://github.com/user-attachments/assets/d48112df-b9ba-4d37-bc72-0d0e812a604e" />

<img width="1080" height="2340" alt="Formulario2_20251129_211328" src="https://github.com/user-attachments/assets/cea67cd0-c392-4b28-a30f-d36199392e98" />


