# Práctica DAO · Biblioteca (UD2, parte 1)

Proyecto Maven (Java 21) para practicar JDBC con el patrón DAO y una arquitectura por capas.

## Puesta en marcha

1. En MySQL Workbench, ejecuta `sql/01_esquema.sql` y después `sql/02_datos.sql`.
2. Revisa `db.properties` (usuario y contraseña de tu MySQL). Debe estar en la raíz del proyecto.
3. Abre el proyecto en NetBeans (File → Open Project) y ejecuta `App`.
   Al principio solo funcionan los dos ejemplos de géneros; el resto sale como `(pendiente)`.

## Estructura

```
biblioteca/
├── pom.xml
├── db.properties            ← credenciales locales (no se sube a Git)
├── db.properties.example
├── sql/
│   ├── 01_esquema.sql       ← crea la base de datos (¡la borra si existe!)
│   └── 02_datos.sql         ← datos de prueba
├── docs/                    ← diagramas E/R, relacional y UML
└── src/main/java/gal/dam/biblioteca/
    ├── App.java             ← punto de entrada para probar (solo usa servicios)
    ├── config/              ← ConexionBD
    ├── modelo/              ← Genero, Libro, Socio, Prestamo
    ├── dto/                 ← records de resultados
    ├── dao/                 ← DAO<T,K> y las interfaces de cada tabla
    ├── dao/mysql/           ← implementaciones con JDBC
    └── servicio/            ← CatalogoService, SocioService, PrestamoService
```

## Qué está hecho y qué tienes que hacer

| Entregado | Por hacer |
|---|---|
| `ConexionBD`, `DAO<T,K>`, `modelo`, `dto`, `App` | Métodos de `LibroDAO`, `SocioDAO`, `PrestamoDAO` |
| `GeneroDAOMySQL` completo (ejemplo de DAO) | `LibroDAOMySQL`, `SocioDAOMySQL`, `PrestamoDAOMySQL` |
| `CatalogoService.listarGeneros()` y `buscarGenero()` (ejemplo de servicio) | El resto de métodos de los tres servicios |

Reglas: los DAO reciben la `Connection` y no la cierran; los servicios la abren y la cierran;
`App` solo habla con los servicios.
