#  JSimular - Componente personalizado de simulación MRU/MRUA

Este proyecto contiene un componente visual llamado `JSimular`, diseñado para simular movimientos rectilíneos (MRU y MRUA), mostrando la posición, velocidad, energía cinética y una animación del movimiento.

---

##  Capturas de pantalla
<img width="518" height="427" alt="Image" src="https://github.com/user-attachments/assets/c016e1b7-8682-4e32-88ba-5890a9d9892e" />

### Interfaz gráfica del componente en ejecución
<img width="499" height="452" alt="ejecucionSimulador" src="https://github.com/user-attachments/assets/392307ff-c98a-4f83-9f71-c48be535a755" />




> En la interfaz se observan los campos de entrada para:
> - Posición inicial (`2`)
> - Velocidad inicial (`10`)
> - Aceleración (`5`)
> - Tiempo (`4`)
> - Masa  (´1´)

Los botones permiten calcular:
- Posición
- Velocidad
- Energía cinética
- Ejecutar una animación con una partícula en movimiento (punto rojo).

---

##  Métodos y propiedades relevantes

### Propiedades:

- `txtX0`, `txtV0`, `txtA`, `txtT`, `txtMasa` → Entradas para los parámetros del movimiento.
- `btnPosicion`, `btnVelocidad`, `btnEnergia`, `btnAnimar` → Botones de acción.
- `lblResultado`, `lblTipoMovimiento` → Etiquetas que muestran los resultados.
- `panelAnimacion` → Área de animación donde se mueve una partícula.

### Métodos relevantes del componente:


// Calcula la posición usando la ecuación: x = x0 + v0*t + ½*a*t²
public double calcularPosicion() {
    return movimiento.posicion(t);
}

// Calcula la velocidad en el instante t
public double calcularVelocidad() {
    return movimiento.velocidad(t);
}

// Determina si el movimiento es uniforme
public boolean esMRU() {
    return movimiento.esMRU();
}


## Instrucciones de uso
### 1. Importar la librería:
Agrega el .jar que contiene JSimular a tu proyecto en NetBeans:

Clic derecho sobre el proyecto → Propiedades → Bibliotecas → Añadir JAR/Carpeta.

### 2. Agregar el componente a la paleta :
Ve a Herramientas > Paleta > Administrar Paleta.

Añade el JAR y selecciona el componente JSimular.

El componente aparecerá en la paleta para ser arrastrado al formulario.

### 3. Ejecución del componente
Ejecuta el ocmponente en un JFrame y a continuacion asigna valores a los campos 

X0 = Posicion inicial

 V0 = Velocidad inicial

 a = Aceleración

 masa = Masa del objeto

Por ultimo selecicona la acción a realizar con el boton correspondiente


## Creditos del equipo
### Integrantes:
Gael Martinez Cruz

Jonathan Eleazar Diaz Reyes

## Enlace del JavaDoc (GitHub Pages) 

https://gaelito-dev.github.io/Componente-JSimulador/

## Video de Youtube explicacion uso del componente

https://youtu.be/2rYRFJuwPtQ?si=eX-7GD2oEPff6FmX




