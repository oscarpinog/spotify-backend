# Proyecto Spring Boot - Configuración y Uso

## ⚙️ Configuración del JDK

- Este proyecto utiliza **Java 21**.  
- Asegúrate de configurar `jdk-21` en el **Java Build Path** de tu IDE.
- Para ello, ve a `Build Path > Add Library > JRE System Library > Installed JREs` y selecciona el JDK 21.

---

## 📦 Librería Lombok

- Se utilizó **[Lombok](https://projectlombok.org/)** para reducir el código boilerplate en clases.
- Debes integrar Lombok en tu IDE para evitar errores de compilación.
  - En Eclipse: Instala el plugin de Lombok.
  - En IntelliJ: Habilita la anotación de procesamiento y agrega el plugin desde settings.

---

## 🗄️ Base de Datos - H2

- El esquema debe ser creado previamente:

- La propiedad en `application.properties` permite la creación/eliminación automática de tablas sin necesidad de scripts SQL manuales:

```properties
spring.jpa.hibernate.ddl-auto=create-drop
```

> ⚠️ **Nota:** Esto eliminará las tablas cada vez que la aplicación se reinicie. Úsalo solo para entornos de desarrollo.

---

## 📚 Documentación con Swagger

- Se habilitó Swagger para facilitar la documentación y prueba de los endpoints REST.
- Accede a la interfaz Swagger desde:

```
http://localhost:8080/swagger-ui/index.html
```

---

## 🔐 Seguridad - Autenticación y Autorización

- Se implementó seguridad usando **Spring Security + JWT**.
- El proyecto genera automáticamente algunos usuarios por defecto al iniciar, gracias a un `@Bean`.

### Usuarios por defecto

```json
{
  "username": "admin",
  "password": "admin123"
}
```

```json
{
  "username": "aux",
  "password": "aux123"
}
```

---

## 🛠️ Logs

- Se utilizó la anotación `@Slf4j` en las clases `ServiceImpl` para registrar actividad del sistema y facilitar el monitoreo.

## OTROS
-El endpoint de Eliminar comerciantes solo se podra usar con el rol de ROL_ADMIN


## Base de datos H2
 

