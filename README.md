# 🚀 E-Commerce Distribuido - Proyecto Completo

## Arquitectura Final (Opción 2 - Una API Central)

Este proyecto implementa un e-commerce distribuido con:
- **1 API Central** en Laptop 1 (192.168.0.111)
- **3 Bases de Datos** replicadas en 3 laptops diferentes
- **Tolerancia a fallos** automática
- **Replicación** en tiempo real

### IPs Confirmadas:
- 🟦 Laptop 1 (PRINCIPAL): **192.168.0.111**
- 🟩 Laptop 2 (NODO): **192.168.0.36**
- 🟪 Laptop 3 (NODO): **192.168.0.37**

## Estructura de Carpetas

```
E-Commerce-Distribuido/
├── Backend-API/              # Proyecto Maven principal
├── docker-compose-laptop1.yml
├── docker-compose-laptop2.yml
├── docker-compose-laptop3.yml
├── Frontend/                 # Archivos HTML
├── scripts/                  # Scripts de instalación
└── README.md
```

## Estado: Generando código...

Este proyecto está siendo generado completamente con:
✅ Backend Java con replicación automática
✅ 3 docker-compose.yml optimizados
✅ Frontend mejorado con paneles de demostración
✅ Scripts de instalación automatizados
✅ Documentación paso a paso
