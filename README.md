# Quiz Spring Boot y JPA — Productos

Aplicación básica en capas (Entidad, Repositorio, Servicio, Controlador) que administra productos.

## Estructura

```
src/main/java/com/example/productos/
├── ProductosApplication.java        # Clase principal
├── model/Producto.java              # Entidad JPA
├── repository/ProductoRepository.java  # JpaRepository + consulta JPQL
├── service/ProductoService.java        # Interfaz del servicio
├── service/ProductoServiceImpl.java    # Lógica de negocio
└── controller/ProductoController.java  # Endpoints REST
src/main/resources/application.properties  # Configuración (H2 en memoria)
```

## Cómo ejecutar

1. Requisitos: Java 17+ y Maven.
2. Desde la carpeta del proyecto:
   ```bash
   mvn spring-boot:run
   ```
3. La aplicación queda disponible en `http://localhost:8080`.
   La base de datos es H2 en memoria (se reinicia cada vez que apagas la app),
   consola en `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:productosdb`, usuario `sa`, sin contraseña).

## Endpoints

### 1. Registrar un producto — `POST /productos`

```bash
curl -X POST http://localhost:8080/productos \
  -H "Content-Type: application/json" \
  -d '{
        "nombre": "Teclado mecánico",
        "categoria": "Electrónica",
        "precio": 250000,
        "activo": true
      }'
```

Respuesta (201 Created):
```json
{
  "id": 1,
  "nombre": "Teclado mecánico",
  "categoria": "Electrónica",
  "precio": 250000,
  "activo": true
}
```

### 2. Consultar productos por categoría (JPQL) — `GET /productos/buscar/{categoria}`

```bash
curl http://localhost:8080/productos/buscar/Electrónica
```

Respuesta (200 OK):
```json
[
  {
    "id": 1,
    "nombre": "Teclado mecánico",
    "categoria": "Electrónica",
    "precio": 250000,
    "activo": true
  }
]
```

## Detalle de la consulta JPQL

En `ProductoRepository`:

```java
@Query("SELECT p FROM Producto p WHERE p.categoria = :categoria")
List<Producto> buscarPorCategoria(@Param("categoria") String categoria);
```

Nótese que la consulta opera sobre la **entidad** `Producto` y sus atributos
(`p.categoria`), no sobre el nombre de la tabla ni columnas SQL: eso es lo que
la distingue de una consulta SQL nativa.
