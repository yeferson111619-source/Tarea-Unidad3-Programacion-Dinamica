# Tarea Unidad 3 – Programación Dinámica

Este repositorio contiene la solución de la **Tarea #1 de la Unidad 3** de la asignatura **Algoritmos y Estructura de Datos** de la **Universidad Politécnica Salesiana**.

El problema consiste en seleccionar la combinación óptima de productos que maximice el beneficio total de una empresa, considerando restricciones de **capacidad de almacenamiento (peso)** y **presupuesto**, utilizando técnicas de **Programación Dinámica**.

---

## Problema abordado

Cada producto cuenta con:
- Valor (beneficio)
- Peso
- Costo

El objetivo es **maximizar el beneficio total** sin exceder:
- La capacidad máxima de almacenamiento
- El presupuesto disponible

## Enfoques implementados

Se desarrollaron tres enfoques para resolver el problema:

1. **Enfoque Recursivo**
   - Explora todas las combinaciones posibles.
   - Correcto pero poco eficiente para conjuntos grandes.

2. **Enfoque Top-Down (Programación Dinámica con Memoización)**
   - Utiliza recursión con almacenamiento de resultados intermedios.
   - Reduce cálculos repetidos y mejora el rendimiento.

3. **Enfoque Bottom-Up (Programación Dinámica Iterativa)**
   - Construye una tabla de soluciones desde los casos base.
   - Es el enfoque más estable y eficiente.

## 📂 Estructura del proyecto
