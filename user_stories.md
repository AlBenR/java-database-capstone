# Historias de Usuario - Sistema de Gestión Médica

## Historias de Administrador

**Título: Inicio de Sesión de Administrador**
_Como administrador, quiero iniciar sesión en el portal con mi nombre de usuario y contraseña, para gestionar la plataforma de manera segura._
**Criterios de Aceptación:**
1. El sistema valida las credenciales del administrador contra la base de datos
2. El inicio de sesión exitoso redirige al panel de administración
3. El inicio de sesión fallido muestra un mensaje de error apropiado
4. La sesión expira después de 30 minutos de inactividad
**Prioridad:** Alta
**Puntos de Historia:** 3
**Notas:**
- La contraseña debe tener al menos 8 caracteres con combinación de letras y números

---

**Título: Cierre de Sesión de Administrador**
_Como administrador, quiero cerrar sesión en el portal, para proteger el acceso al sistema._
**Criterios de Aceptación:**
1. El botón de cierre de sesión es visible en todas las páginas de administración
2. La sesión se termina correctamente al cerrar sesión
3. El usuario es redirigido a la página de inicio de sesión
4. No se puede acceder a páginas restringidas después del cierre de sesión
**Prioridad:** Alta
**Puntos de Historia:** 2
**Notas:**
- Limpiar token de autenticación y datos de sesión

---

**Título: Agregar Nuevo Doctor**
_Como administrador, quiero agregar doctores al portal, para expandir el personal médico disponible._
**Criterios de Aceptación:**
1. Formulario incluye campos: nombre, especialización, email, teléfono, credenciales
2. Validación de formato de email y teléfono
3. Verificación de que el doctor no existe previamente en el sistema
4. Confirmación visual de creación exitosa
**Prioridad:** Media
**Puntos de Historia:** 5
**Notas:**
- Enviar email de bienvenida al doctor con credenciales temporales

---

**Título: Eliminar Perfil de Doctor**
_Como administrador, quiero eliminar el perfil de un doctor del portal, para gestionar el personal médico eficazmente._
**Criterios de Aceptación:**
1. Búsqueda de doctores por nombre o especialización
2. Modal de confirmación con información del doctor a eliminar
3. Archivar datos en lugar de eliminación permanente
4. Notificar al doctor sobre la eliminación de su cuenta
**Prioridad:** Media
**Puntos de Historia:** 4
**Notas:**
- Reasignar citas futuras a otros doctores de la misma especialización

---

**Título: Estadísticas de Citas Mensuales**
_Como administrador, quiero ejecutar un procedimiento almacenado en MySQL CLI para obtener el número de citas por mes, para rastrear las estadísticas de uso._
**Criterios de Aceptación:**
1. Procedimiento almacenado 'sp_citas_por_mes' creado en la base de datos
2. Retorna cantidad de citas agrupadas por mes y año
3. Incluye filtro opcional por año específico
4. Formato de salida: Mes, Año, Cantidad de Citas
**Prioridad:** Baja
**Puntos de Historia:** 8
**Notas:**
- Considerar implementar interfaz gráfica en futuras iteraciones

---

## Historias de Paciente

**Título: Explorar Doctores Sin Registro**
_Como paciente, quiero ver una lista de doctores sin iniciar sesión, para explorar opciones antes de registrarme._
**Criterios de Aceptación:**
1. Lista pública de doctores disponible en página principal
2. Información visible: nombre, especialización, foto, descripción
3. Filtrado por especialización y ubicación
4. No se requiere autenticación para acceder a esta funcionalidad
**Prioridad:** Alta
**Puntos de Historia:** 3
**Notas:**
- Mostrar doctores activos únicamente

---

**Título: Registro de Paciente**
_Como paciente, quiero registrarme usando mi correo electrónico y contraseña, para reservar citas._
**Criterios de Aceptación:**
1. Formulario de registro con: nombre completo, email, contraseña, teléfono
2. Validación de email único en el sistema
3. Confirmación de cuenta vía email
4. Redirección automática al login después del registro exitoso
**Prioridad:** Alta
**Puntos de Historia:** 5
**Notas:**
- Requerir verificación de email antes de permitir reservas

---

**Título: Inicio de Sesión de Paciente**
_Como paciente, quiero iniciar sesión en el portal, para gestionar mis reservas._
**Criterios de Aceptación:**
1. Acceso con email y contraseña
2. Opción "Recordar contraseña" disponible
3. Redirección a dashboard de paciente después del login
4. Mostrar mensaje de error específico para credenciales incorrectas
**Prioridad:** Alta
**Puntos de Historia:** 3
**Notas:**
- Bloquear cuenta después de 5 intentos fallidos

---

**Título: Cierre de Sesión de Paciente**
_Como paciente, quiero cerrar sesión en el portal, para asegurar mi cuenta._
**Criterios de Aceptación:**
1. Botón de logout visible en el header de la aplicación
2. Terminación de todas las sesiones activas
3. Redirección a página principal después del logout
4. Invalidación del token de autenticación
**Prioridad:** Media
**Puntos de Historia:** 2
**Notas:**
- Confirmación opcional antes del cierre de sesión

---

**Título: Reserva de Cita Médica**
_Como paciente, quiero iniciar sesión y reservar una cita de una hora, para consultar con un doctor._
**Criterios de Aceptación:**
1. Selección de doctor de lista disponible
2. Visualización de horarios disponibles del doctor
3. Confirmación de cita con resumen: doctor, fecha, hora, consultorio
4. Envío de email de confirmación al paciente
**Prioridad:** Alta
**Puntos de Historia:** 8
**Notas:**
- Permitir cancelación hasta 24 horas antes

---

**Título: Visualización de Citas Programadas**
_Como paciente, quiero ver mis próximas citas, para poder prepararme adecuadamente._
**Criterios de Aceptación:**
1. Lista de citas ordenadas por fecha más próxima
2. Información mostrada: doctor, fecha, hora, estado
3. Opción para cancelar citas futuras
4. Recordatorio automático 24 horas antes de la cita
**Prioridad:** Media
**Puntos de Historia:** 5
**Notas:**
- Incluir link para unirse a consulta virtual si aplica

---

## Historias de Doctor

**Título: Inicio de Sesión de Doctor**
_Como doctor, quiero iniciar sesión en el portal, para gestionar mis citas._
**Criterios de Aceptación:**
1. Acceso con email profesional y contraseña
2. Dashboard personalizado con resumen de citas del día
3. Acceso restringido a funcionalidades de doctor
4. Recuperación de contraseña disponible
**Prioridad:** Alta
**Puntos de Historia:** 3
**Notas:**
- Credenciales proporcionadas por administrador

---

**Título: Cierre de Sesión de Doctor**
_Como doctor, quiero cerrar sesión en el portal, para proteger mis datos._
**Criterios de Aceptación:**
1. Botón de logout accesible desde cualquier página
2. Invalidación inmediata de la sesión
3. Redirección a página de login
4. Limpieza de datos sensibles en el cliente
**Prioridad:** Alta
**Puntos de Historia:** 2
**Notas:**
- Especialmente importante por la sensibilidad de datos médicos

---

**Título: Calendario de Citas**
_Como doctor, quiero ver mi calendario de citas, para mantenerme organizado._
**Criterios de Aceptación:**
1. Vista semanal y mensual de citas programadas
2. Información de paciente y motivo de consulta
3. Filtrado por estado de cita (confirmada, completada, cancelada)
4. Integración con calendarios externos (opcional)
**Prioridad:** Alta
**Puntos de Historia:** 8
**Notas:**
- Soporte para diferentes zonas horarias

---

**Título: Gestión de Disponibilidad**
_Como doctor, quiero marcar mi indisponibilidad, para informar a los pacientes solo sobre los horarios disponibles._
**Criterios de Aceptación:**
1. Interfaz para bloquear horarios específicos
2. Posibilidad de bloquear rangos de fechas (vacaciones)
3. Bloqueo recurrente (ej: todos los miércoles por la tarde)
4. Actualización en tiempo real de disponibilidad para pacientes
**Prioridad:** Media
**Puntos de Historia:** 6
**Notas:**
- No permitir bloqueo de citas ya confirmadas

---

**Título: Actualización de Perfil Profesional**
_Como doctor, quiero actualizar mi perfil con especialización e información de contacto, para que los pacientes tengan información actualizada._
**Criterios de Aceptación:**
1. Edición de: biografía, especializaciones, experiencia, educación
2. Actualización de información de contacto: teléfono, email secundario
3. Carga de foto profesional y documentos de certificación
4. Aprobación de cambios por administrador (opcional)
**Prioridad:** Media
**Puntos de Historia:** 5
**Notas:**
- Historial de cambios en el perfil

---

**Título: Información de Paciente para Citas**
_Como doctor, quiero ver los detalles del paciente para las citas próximas, para poder estar preparado._
**Criterios de Aceptación:**
1. Acceso a información básica del paciente: nombre, edad, contacto
2. Historial médico previo (si disponible)
3. Motivo de consulta específico para la cita programada
4. Alertas o notas importantes sobre el paciente
**Prioridad:** Alta
**Puntos de Historia:** 5
**Notas:**
- Cumplir con regulaciones de privacidad de datos médicos
