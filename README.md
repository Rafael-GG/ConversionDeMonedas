# 💱 ConversorDeMonedas - JavaFX

Este es un conversor de divisas desarrollado en **Java 17** utilizando **JavaFX** para la interfaz gráfica. A diferencia de las aplicaciones de consola convencionales, este proyecto ofrece una interfaz visual intuitiva que permite realizar conversiones dinámicas entre cualquier par de divisas soportado por la [ExchangeRate-API](https://www.exchangerate-api.com/).

Proyecto desarrollado como parte del **Challenge de Alura Latam y Oracle Next Education (ONE)**.

---

## 🚀 Características

* **Interfaz Gráfica Moderna:** Implementada con **JavaFX** y **FXML**, proporcionando una experiencia de usuario limpia y profesional.
* **Conversión Dinámica:** Permite elegir libremente entre una amplia variedad de monedas mediante selectores (ComboBox).
* **Seguridad de Datos:** Gestión de la API Key a través de archivos de propiedades (`config.properties`), siguiendo las mejores prácticas para evitar la exposición de claves en repositorios públicos.
* **Consumo de API:** Integración fluida con servicios REST utilizando `HttpClient` de Java.

## 🛠️ Tecnologías Utilizadas

* **Lenguaje:** Java 17
* **Interfaz:** JavaFX (FXML)
* **Gestión de Dependencias:** Maven
* **Librerías:** * [Gson](https://github.com/google/gson): Para la conversión de JSON a objetos Java.
* **API Externa:** ExchangeRate-API

## 📁 Estructura del Proyecto

El proyecto está organizado de la siguiente manera:

* **`src/main/java`**: Contiene la lógica central.
    * `Main.java` / `Launcher.java`: Clases de inicio.
    * `ConversorViewController.java`: Controlador de la interfaz y eventos.
    * `ConsultaApi.java`: Gestión de peticiones HTTP.
    * `ApiKeyProvider.java`: Proveedor de la clave de acceso.
* **`src/main/resources`**: Contiene los archivos visuales y de configuración.
    * `ConversorView.fxml`: Definición de la interfaz gráfica.
    * `config.properties.example`: Plantilla para la configuración de la API.
* **`videos/`**: Contiene un video donde se busca un convertidor de divisas en línea.

## ⚙️ Configuración e Instalación

1.  **Clona el repositorio:**
    ```bash
    git clone https://github.com/Rafael-GG/ConversionDeMonedas.git
    ```
2.  **Configura tu API Key:**
    * En la carpeta `src/main/resources`, renombra el archivo `config.properties.example` a `config.properties`.
    * Edita el archivo y pega tu clave personal obtenida en [ExchangeRate-API](https://www.exchangerate-api.com/).
3.  **Ejecución:**
    Si utilizas un IDE como IntelliJ, simplemente corre la clase `Launcher.java`. Si usas Maven:
    ```bash
    mvn javafx:run
    ```

## 🖥️ Vista Previa

La aplicación cuenta con un campo de entrada para el monto, selectores para las divisas "De" y "a", y un botón central para procesar el cambio instantáneamente.

---

## ✒️ Autor

**Rafael-GG** - [GitHub](https://github.com/Rafael-GG) - [LinkedIn](https://www.linkedin.com/in/rafael-gerson-gimenez)
