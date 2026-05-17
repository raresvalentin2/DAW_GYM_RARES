USE RARES_GYM;

SELECT s.dni, p.nombre, p.apellido, s.estado, s.tipo_plan
FROM SOCIOS s
JOIN PERSONAS p ON s.dni = p.dni;

SELECT ap.id_programada, a.nombre AS actividad, sa.nombre AS sala, ap.fecha, ap.hora_inicio, ap.hora_fin
FROM ACTIVIDADES_PROGRAMADAS ap
JOIN ACTIVIDADES a ON ap.id_actividad = a.id_actividad
JOIN SALAS sa ON ap.id_sala = sa.id_sala
WHERE sa.nombre = 'Sala 1';

SELECT m.tipo, m.marca, m.numero_serie, v.alerta
FROM vw_maquinaria_alertas v
JOIN MAQUINARIA m ON v.id_maquina = m.id_maquina;

SELECT p.nombre, p.apellido, n.mes, n.anio, n.importe
FROM NOMINAS n
JOIN PERSONAS p ON n.dni_trabajador = p.dni;

SELECT p.nombre, p.apellido, mc.fecha, mc.peso, mc.grasa
FROM MEDICIONES_CORPORALES mc
JOIN PERSONAS p ON mc.dni_socio = p.dni
WHERE mc.dni_socio = '12345678A'
ORDER BY mc.fecha;

SELECT a.nombre AS actividad, COUNT(r.id_reserva) AS reservas
FROM ACTIVIDADES_PROGRAMADAS ap
JOIN ACTIVIDADES a ON ap.id_actividad = a.id_actividad
LEFT JOIN RESERVAS r ON ap.id_programada = r.id_programada
GROUP BY a.nombre;

SELECT p.nombre, p.apellido, cn.fecha, cn.hora_inicio
FROM CONSULTAS_NUTRICIONALES cn
JOIN PERSONAS p ON cn.dni_socio = p.dni;

SELECT a.nombre AS actividad, p.nombre AS entrenador, p.apellido
FROM ENTRENADOR_ACTIVIDAD ea
JOIN ACTIVIDADES a ON ea.id_actividad = a.id_actividad
JOIN PERSONAS p ON ea.dni_entrenador = p.dni;

SELECT s.nombre AS sala, a.nombre AS actividad
FROM SALA_ACTIVIDAD sa
JOIN SALAS s ON sa.id_sala = s.id_sala
JOIN ACTIVIDADES a ON sa.id_actividad = a.id_actividad;

SELECT *
FROM vw_actividades_web;
