DROP DATABASE IF EXISTS RARES_GYM;
CREATE DATABASE RARES_GYM;
USE RARES_GYM;

CREATE TABLE PERSONAS (
    dni VARCHAR(12) PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(80) NOT NULL,
    telefono VARCHAR(20),
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE TRABAJADORES (
    dni VARCHAR(12) PRIMARY KEY,
    fecha_alta DATE NOT NULL,
    estado ENUM('activo','baja') NOT NULL,
    tipo_contrato ENUM('completo','parcial') NOT NULL,
    salario_base DECIMAL(10,2),
    precio_hora DECIMAL(10,2),
    horas_mensuales INT,
    FOREIGN KEY (dni) REFERENCES PERSONAS(dni)
);

CREATE TABLE ADMINISTRADORES (
    dni VARCHAR(12) PRIMARY KEY,
    FOREIGN KEY (dni) REFERENCES TRABAJADORES(dni)
);

CREATE TABLE RECEPCIONISTAS (
    dni VARCHAR(12) PRIMARY KEY,
    turno VARCHAR(30),
    FOREIGN KEY (dni) REFERENCES TRABAJADORES(dni)
);

CREATE TABLE ENTRENADORES (
    dni VARCHAR(12) PRIMARY KEY,
    especialidad VARCHAR(80),
    FOREIGN KEY (dni) REFERENCES TRABAJADORES(dni)
);

CREATE TABLE NUTRICIONISTAS (
    dni VARCHAR(12) PRIMARY KEY,
    titulacion VARCHAR(120) NOT NULL,
    especialidad VARCHAR(120),
    FOREIGN KEY (dni) REFERENCES TRABAJADORES(dni)
);

CREATE TABLE SOCIOS (
    dni VARCHAR(12) PRIMARY KEY,
    fecha_alta DATE NOT NULL,
    estado ENUM('activo','baja','moroso') NOT NULL,
    tipo_plan ENUM('Full','Flexible') NOT NULL,
    alergias JSON,
    FOREIGN KEY (dni) REFERENCES PERSONAS(dni)
);

CREATE TABLE SALAS (
    id_sala INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(80) NOT NULL,
    metros_cuadrados DECIMAL(8,2) NOT NULL,
    aforo INT NOT NULL,
    tipo ENUM('normal','almacen') NOT NULL
);

CREATE TABLE ACTIVIDADES (
    id_actividad INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(80) NOT NULL,
    descripcion VARCHAR(200),
    nivel ENUM('bajo','medio','alto') NOT NULL
);

CREATE TABLE ENTRENADOR_ACTIVIDAD (
    dni_entrenador VARCHAR(12),
    id_actividad INT,
    PRIMARY KEY (dni_entrenador, id_actividad),
    FOREIGN KEY (dni_entrenador) REFERENCES ENTRENADORES(dni),
    FOREIGN KEY (id_actividad) REFERENCES ACTIVIDADES(id_actividad)
);

CREATE TABLE SALA_ACTIVIDAD (
    id_sala INT,
    id_actividad INT,
    PRIMARY KEY (id_sala, id_actividad),
    FOREIGN KEY (id_sala) REFERENCES SALAS(id_sala),
    FOREIGN KEY (id_actividad) REFERENCES ACTIVIDADES(id_actividad)
);

CREATE TABLE MAQUINARIA (
    id_maquina INT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(80) NOT NULL,
    marca VARCHAR(80),
    numero_serie VARCHAR(80) UNIQUE NOT NULL,
    fecha_compra DATE NOT NULL,
    fecha_ultimo_mantenimiento DATE NOT NULL,
    estado ENUM('operativa','revision','fuera de servicio') NOT NULL,
    id_sala INT,
    FOREIGN KEY (id_sala) REFERENCES SALAS(id_sala)
);

CREATE TABLE ACTIVIDAD_MAQUINARIA (
    id_actividad INT,
    id_maquina INT,
    PRIMARY KEY (id_actividad, id_maquina),
    FOREIGN KEY (id_actividad) REFERENCES ACTIVIDADES(id_actividad),
    FOREIGN KEY (id_maquina) REFERENCES MAQUINARIA(id_maquina)
);

CREATE TABLE ACTIVIDADES_PROGRAMADAS (
    id_programada INT AUTO_INCREMENT PRIMARY KEY,
    id_actividad INT NOT NULL,
    id_sala INT NOT NULL,
    dni_entrenador VARCHAR(12) NOT NULL,
    fecha DATE NOT NULL,
    hora_inicio TIME NOT NULL,
    hora_fin TIME NOT NULL,
    capacidad INT NOT NULL,
    FOREIGN KEY (id_actividad) REFERENCES ACTIVIDADES(id_actividad),
    FOREIGN KEY (id_sala) REFERENCES SALAS(id_sala),
    FOREIGN KEY (dni_entrenador) REFERENCES ENTRENADORES(dni)
);

CREATE TABLE PAGOS (
    id_pago INT AUTO_INCREMENT PRIMARY KEY,
    dni_socio VARCHAR(12) NOT NULL,
    fecha DATE NOT NULL,
    importe DECIMAL(10,2) NOT NULL,
    concepto VARCHAR(120),
    FOREIGN KEY (dni_socio) REFERENCES SOCIOS(dni)
);

CREATE TABLE RESERVAS (
    id_reserva INT AUTO_INCREMENT PRIMARY KEY,
    dni_socio VARCHAR(12) NOT NULL,
    id_programada INT NOT NULL,
    fecha_reserva DATE NOT NULL,
    estado ENUM('activa','cancelada') NOT NULL,
    pago_confirmado BOOLEAN NOT NULL,
    id_pago INT,
    FOREIGN KEY (dni_socio) REFERENCES SOCIOS(dni),
    FOREIGN KEY (id_programada) REFERENCES ACTIVIDADES_PROGRAMADAS(id_programada),
    FOREIGN KEY (id_pago) REFERENCES PAGOS(id_pago)
);

CREATE TABLE CONSULTAS_NUTRICIONALES (
    id_consulta INT AUTO_INCREMENT PRIMARY KEY,
    dni_socio VARCHAR(12) NOT NULL,
    dni_nutricionista VARCHAR(12) NOT NULL,
    fecha DATE NOT NULL,
    hora_inicio TIME NOT NULL,
    hora_fin TIME NOT NULL,
    observaciones VARCHAR(250),
    FOREIGN KEY (dni_socio) REFERENCES SOCIOS(dni),
    FOREIGN KEY (dni_nutricionista) REFERENCES NUTRICIONISTAS(dni)
);

CREATE TABLE PLANES_NUTRICIONALES (
    id_plan INT AUTO_INCREMENT PRIMARY KEY,
    dni_socio VARCHAR(12) NOT NULL,
    dni_nutricionista VARCHAR(12) NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE,
    objetivo VARCHAR(150),
    detalle JSON,
    FOREIGN KEY (dni_socio) REFERENCES SOCIOS(dni),
    FOREIGN KEY (dni_nutricionista) REFERENCES NUTRICIONISTAS(dni)
);

CREATE TABLE MEDICIONES_CORPORALES (
    id_medicion INT AUTO_INCREMENT PRIMARY KEY,
    dni_socio VARCHAR(12) NOT NULL,
    fecha DATE NOT NULL,
    peso DECIMAL(5,2),
    altura DECIMAL(4,2),
    grasa DECIMAL(5,2),
    FOREIGN KEY (dni_socio) REFERENCES SOCIOS(dni)
);

CREATE TABLE NOMINAS (
    id_nomina INT AUTO_INCREMENT PRIMARY KEY,
    dni_trabajador VARCHAR(12) NOT NULL,
    mes INT NOT NULL,
    anio INT NOT NULL,
    fecha DATE NOT NULL,
    importe DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (dni_trabajador) REFERENCES TRABAJADORES(dni)
);

DELIMITER //

CREATE FUNCTION fn_meses_mantenimiento(p_id_maquina INT)
RETURNS INT
DETERMINISTIC
BEGIN
    DECLARE meses INT;

    SELECT IF(TIMESTAMPDIFF(YEAR, fecha_compra, CURDATE()) < 3, 12, 6)
    INTO meses
    FROM MAQUINARIA
    WHERE id_maquina = p_id_maquina;

    RETURN meses;
END//

CREATE FUNCTION fn_mantenimiento_vencido(p_id_maquina INT)
RETURNS BOOLEAN
DETERMINISTIC
BEGIN
    DECLARE vencido BOOLEAN;

    SELECT DATE_ADD(fecha_ultimo_mantenimiento, INTERVAL fn_meses_mantenimiento(id_maquina) MONTH) < CURDATE()
    INTO vencido
    FROM MAQUINARIA
    WHERE id_maquina = p_id_maquina;

    RETURN vencido;
END//

CREATE FUNCTION fn_calcular_nomina(p_dni VARCHAR(12))
RETURNS DECIMAL(10,2)
DETERMINISTIC
BEGIN
    DECLARE total DECIMAL(10,2);

    SELECT 
        CASE 
            WHEN tipo_contrato = 'completo' THEN salario_base
            ELSE precio_hora * horas_mensuales
        END
    INTO total
    FROM TRABAJADORES
    WHERE dni = p_dni;

    RETURN total;
END//

CREATE TRIGGER trg_actividad_programada_insert
BEFORE INSERT ON ACTIVIDADES_PROGRAMADAS
FOR EACH ROW
BEGIN
    IF NEW.hora_fin <= NEW.hora_inicio THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Hora final incorrecta';
    END IF;

    IF EXISTS (
        SELECT 1
        FROM ACTIVIDADES_PROGRAMADAS
        WHERE id_sala = NEW.id_sala
        AND fecha = NEW.fecha
        AND NEW.hora_inicio < hora_fin
        AND NEW.hora_fin > hora_inicio
    ) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Sala ocupada en ese horario';
    END IF;

    IF EXISTS (
        SELECT 1
        FROM ACTIVIDADES_PROGRAMADAS
        WHERE dni_entrenador = NEW.dni_entrenador
        AND fecha = NEW.fecha
        AND NEW.hora_inicio < hora_fin
        AND NEW.hora_fin > hora_inicio
    ) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Entrenador ocupado en ese horario';
    END IF;

    IF NOT EXISTS (
        SELECT 1
        FROM ENTRENADOR_ACTIVIDAD
        WHERE dni_entrenador = NEW.dni_entrenador
        AND id_actividad = NEW.id_actividad
    ) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El entrenador no puede impartir esa actividad';
    END IF;

    IF NOT EXISTS (
        SELECT 1
        FROM SALA_ACTIVIDAD
        WHERE id_sala = NEW.id_sala
        AND id_actividad = NEW.id_actividad
    ) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'La actividad no esta permitida en esa sala';
    END IF;

    IF EXISTS (
        SELECT 1
        FROM ACTIVIDAD_MAQUINARIA am
        JOIN MAQUINARIA m ON am.id_maquina = m.id_maquina
        WHERE am.id_actividad = NEW.id_actividad
        AND (m.estado <> 'operativa' OR fn_mantenimiento_vencido(m.id_maquina) = TRUE)
    ) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Maquinaria no disponible';
    END IF;
END//

CREATE TRIGGER trg_reserva_insert
BEFORE INSERT ON RESERVAS
FOR EACH ROW
BEGIN
    DECLARE v_estado VARCHAR(20);
    DECLARE v_plan VARCHAR(20);
    DECLARE v_reservas INT;
    DECLARE v_capacidad INT;

    SELECT estado, tipo_plan
    INTO v_estado, v_plan
    FROM SOCIOS
    WHERE dni = NEW.dni_socio;

    IF v_estado = 'moroso' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El socio moroso no puede reservar';
    END IF;

    IF v_estado = 'baja' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El socio de baja no puede reservar';
    END IF;

    IF v_plan = 'Flexible' AND NEW.pago_confirmado = FALSE THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El socio Flexible debe pagar';
    END IF;

    SELECT COUNT(*)
    INTO v_reservas
    FROM RESERVAS
    WHERE id_programada = NEW.id_programada
    AND estado = 'activa';

    SELECT capacidad
    INTO v_capacidad
    FROM ACTIVIDADES_PROGRAMADAS
    WHERE id_programada = NEW.id_programada;

    IF v_reservas >= v_capacidad THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'No quedan plazas';
    END IF;

    IF EXISTS (
        SELECT 1
        FROM RESERVAS r
        JOIN ACTIVIDADES_PROGRAMADAS ap1 ON r.id_programada = ap1.id_programada
        JOIN ACTIVIDADES_PROGRAMADAS ap2 ON NEW.id_programada = ap2.id_programada
        WHERE r.dni_socio = NEW.dni_socio
        AND r.estado = 'activa'
        AND ap1.fecha = ap2.fecha
        AND ap2.hora_inicio < ap1.hora_fin
        AND ap2.hora_fin > ap1.hora_inicio
    ) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El socio ya tiene una reserva a esa hora';
    END IF;
END//

CREATE TRIGGER trg_consulta_insert
BEFORE INSERT ON CONSULTAS_NUTRICIONALES
FOR EACH ROW
BEGIN
    IF NEW.hora_fin <= NEW.hora_inicio THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Hora final incorrecta';
    END IF;

    IF EXISTS (
        SELECT 1
        FROM CONSULTAS_NUTRICIONALES
        WHERE dni_nutricionista = NEW.dni_nutricionista
        AND fecha = NEW.fecha
        AND NEW.hora_inicio < hora_fin
        AND NEW.hora_fin > hora_inicio
    ) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Nutricionista ocupado';
    END IF;
END//

CREATE TRIGGER trg_trabajador_baja
BEFORE UPDATE ON TRABAJADORES
FOR EACH ROW
BEGIN
    IF NEW.estado = 'baja' AND OLD.estado = 'activo' THEN
        IF EXISTS (
            SELECT 1
            FROM NUTRICIONISTAS n
            JOIN CONSULTAS_NUTRICIONALES c ON n.dni = c.dni_nutricionista
            WHERE n.dni = NEW.dni
            AND c.fecha BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL 7 DAY)
        ) THEN
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Nutricionista con consultas proximas';
        END IF;
    END IF;
END//

CREATE PROCEDURE sp_calcular_nominas(IN p_mes INT, IN p_anio INT)
BEGIN
    DECLARE fin INT DEFAULT 0;
    DECLARE v_dni VARCHAR(12);
    DECLARE v_importe DECIMAL(10,2);

    DECLARE c_trabajadores CURSOR FOR
        SELECT dni FROM TRABAJADORES WHERE estado = 'activo';

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET fin = 1;

    OPEN c_trabajadores;

    bucle: LOOP
        FETCH c_trabajadores INTO v_dni;

        IF fin = 1 THEN
            LEAVE bucle;
        END IF;

        SET v_importe = fn_calcular_nomina(v_dni);

        INSERT INTO NOMINAS(dni_trabajador, mes, anio, fecha, importe)
        VALUES(v_dni, p_mes, p_anio, CURDATE(), v_importe);
    END LOOP;

    CLOSE c_trabajadores;
END//

CREATE PROCEDURE sp_insertar_pago(IN p_dni VARCHAR(12), IN p_importe DECIMAL(10,2), IN p_concepto VARCHAR(120))
BEGIN
    DECLARE EXIT HANDLER FOR SQLSTATE '23000'
    BEGIN
        SELECT 'Error al insertar pago' AS mensaje;
    END;

    INSERT INTO PAGOS(dni_socio, fecha, importe, concepto)
    VALUES(p_dni, CURDATE(), p_importe, p_concepto);
END//

CREATE VIEW vw_maquinaria_alertas AS
SELECT
    id_maquina,
    tipo,
    marca,
    numero_serie,
    estado,
    fecha_ultimo_mantenimiento,
    DATE_ADD(fecha_ultimo_mantenimiento, INTERVAL fn_meses_mantenimiento(id_maquina) MONTH) AS proximo_mantenimiento,
    CASE
        WHEN DATE_ADD(fecha_ultimo_mantenimiento, INTERVAL fn_meses_mantenimiento(id_maquina) MONTH) < CURDATE() THEN 'roja'
        WHEN DATE_ADD(fecha_ultimo_mantenimiento, INTERVAL fn_meses_mantenimiento(id_maquina) MONTH) <= DATE_ADD(CURDATE(), INTERVAL 1 MONTH) THEN 'naranja'
        ELSE 'correcta'
    END AS alerta
FROM MAQUINARIA//

CREATE VIEW vw_actividades_web AS
SELECT
    s.nombre AS sala,
    a.nombre AS actividad,
    CONCAT(p.nombre, ' ', p.apellido) AS entrenador,
    ap.fecha,
    ap.hora_inicio,
    ap.capacidad,
    ap.capacidad - (
        SELECT COUNT(*)
        FROM RESERVAS r
        WHERE r.id_programada = ap.id_programada
        AND r.estado = 'activa'
    ) AS plazas_libres
FROM ACTIVIDADES_PROGRAMADAS ap
JOIN ACTIVIDADES a ON ap.id_actividad = a.id_actividad
JOIN SALAS s ON ap.id_sala = s.id_sala
JOIN PERSONAS p ON ap.dni_entrenador = p.dni//

DELIMITER ;

INSERT INTO PERSONAS VALUES
('11111111A','Ana','Admin','600111111','admin@gym.com','1234'),
('22222222B','Rosa','Recepcion','600222222','recepcion@gym.com','1234'),
('33333333C','Mario','Lozano','600333333','mario@gym.com','1234'),
('44444444D','Carlos','Ruiz','600444444','carlos@gym.com','1234'),
('55555555E','Laura','Perez','600555555','laura@gym.com','1234'),
('66666666F','Marta','Sanchez','600666666','marta@gym.com','1234'),
('77777777G','Pedro','Nutri','600777777','pedro@gym.com','1234'),
('88888888H','Elena','Nutri','600888888','elena@gym.com','1234'),
('12345678A','Juan','Garcia','600000001','juan@gym.com','1234'),
('87654321B','Lucia','Martin','600000002','lucia@gym.com','1234'),
('99999999C','Pablo','Moroso','600000003','pablo@gym.com','1234');

INSERT INTO TRABAJADORES VALUES
('11111111A','2026-01-01','activo','completo',1600,NULL,NULL),
('22222222B','2026-01-01','activo','parcial',NULL,10,80),
('33333333C','2026-01-01','activo','completo',1500,NULL,NULL),
('44444444D','2026-01-01','activo','completo',1500,NULL,NULL),
('55555555E','2026-01-01','activo','parcial',NULL,12,90),
('66666666F','2026-01-01','activo','parcial',NULL,12,80),
('77777777G','2026-01-01','activo','completo',1550,NULL,NULL),
('88888888H','2026-01-01','activo','parcial',NULL,14,70);

INSERT INTO ADMINISTRADORES VALUES
('11111111A');

INSERT INTO RECEPCIONISTAS VALUES
('22222222B','manana');

INSERT INTO ENTRENADORES VALUES
('33333333C','Spinning'),
('44444444D','Funcional'),
('55555555E','Yoga'),
('66666666F','Pilates');

INSERT INTO NUTRICIONISTAS VALUES
('77777777G','Grado en Nutricion','Deportiva'),
('88888888H','Grado en Nutricion','Perdida de peso');

INSERT INTO SOCIOS VALUES
('12345678A','2026-05-01','activo','Full','["gluten"]'),
('87654321B','2026-05-02','activo','Flexible','[]'),
('99999999C','2026-05-03','moroso','Full','["lactosa"]');

INSERT INTO SALAS(nombre, metros_cuadrados, aforo, tipo) VALUES
('Sala 1',80,20,'normal'),
('Sala 2',60,15,'normal'),
('Sala 3',90,25,'normal'),
('Sala 4',70,20,'normal'),
('Almacen',40,0,'almacen');

INSERT INTO ACTIVIDADES(nombre, descripcion, nivel) VALUES
('Spinning','Clase de bicicleta estatica','medio'),
('Yoga','Actividad de movilidad y relajacion','bajo'),
('Crossfit','Entrenamiento funcional intenso','alto'),
('Pilates','Trabajo de fuerza y control','bajo'),
('HIIT','Entrenamiento intenso','alto'),
('Zumba','Actividad coreografiada','medio'),
('Boxeo','Entrenamiento de boxeo','alto');

INSERT INTO ENTRENADOR_ACTIVIDAD VALUES
('33333333C',1),
('33333333C',5),
('44444444D',3),
('44444444D',7),
('55555555E',2),
('55555555E',6),
('66666666F',4),
('66666666F',2);

INSERT INTO SALA_ACTIVIDAD VALUES
(1,1),
(1,5),
(2,2),
(2,6),
(3,3),
(3,7),
(4,4),
(4,2);

INSERT INTO MAQUINARIA(tipo, marca, numero_serie, fecha_compra, fecha_ultimo_mantenimiento, estado, id_sala) VALUES
('Bicicleta','BH','BIC001','2024-01-01','2026-01-01','operativa',1),
('Cinta','Technogym','CIN001','2020-01-01','2026-03-01','operativa',3),
('Banco','Matrix','BAN001','2025-01-01','2026-01-15','revision',5);

INSERT INTO ACTIVIDAD_MAQUINARIA VALUES
(1,1),
(3,2),
(5,2);

INSERT INTO ACTIVIDADES_PROGRAMADAS(id_actividad, id_sala, dni_entrenador, fecha, hora_inicio, hora_fin, capacidad) VALUES
(1,1,'33333333C','2026-05-05','07:00:00','08:00:00',20),
(2,2,'55555555E','2026-05-05','09:30:00','10:30:00',15),
(3,3,'44444444D','2026-05-05','10:00:00','11:00:00',25),
(4,4,'66666666F','2026-05-06','11:00:00','12:00:00',12),
(5,1,'33333333C','2026-05-06','18:00:00','19:00:00',20),
(6,2,'55555555E','2026-05-06','19:30:00','20:30:00',30),
(7,3,'44444444D','2026-05-08','17:00:00','18:00:00',16);

INSERT INTO PAGOS(dni_socio, fecha, importe, concepto) VALUES
('12345678A','2026-05-01',40,'Cuota mensual'),
('87654321B','2026-05-05',5,'Reserva actividad');

INSERT INTO RESERVAS(dni_socio, id_programada, fecha_reserva, estado, pago_confirmado, id_pago) VALUES
('12345678A',1,'2026-05-04','activa',TRUE,1),
('87654321B',2,'2026-05-04','activa',TRUE,2);

INSERT INTO CONSULTAS_NUTRICIONALES(dni_socio, dni_nutricionista, fecha, hora_inicio, hora_fin, observaciones) VALUES
('12345678A','77777777G','2026-05-20','10:00:00','10:30:00','Primera consulta');

INSERT INTO PLANES_NUTRICIONALES(dni_socio, dni_nutricionista, fecha_inicio, fecha_fin, objetivo, detalle) VALUES
('12345678A','77777777G','2026-05-20','2026-06-20','Mejorar rendimiento','{"desayuno":"avena","comida":"pollo y arroz","cena":"pescado"}');

INSERT INTO MEDICIONES_CORPORALES(dni_socio, fecha, peso, altura, grasa) VALUES
('12345678A','2026-05-01',78.5,1.78,18.2),
('12345678A','2026-05-15',77.8,1.78,17.9),
('87654321B','2026-05-10',64.0,1.65,22.5);

CALL sp_calcular_nominas(5,2026);

SELECT s.dni, p.nombre, p.apellido, s.estado, s.tipo_plan
FROM SOCIOS s
JOIN PERSONAS p ON s.dni = p.dni;

SELECT ap.id_programada, a.nombre AS actividad, sa.nombre AS sala, ap.fecha, ap.hora_inicio, ap.hora_fin
FROM ACTIVIDADES_PROGRAMADAS ap
JOIN ACTIVIDADES a ON ap.id_actividad = a.id_actividad
JOIN SALAS sa ON ap.id_sala = sa.id_sala
WHERE sa.nombre = 'Sala 1';

SELECT tipo, marca, numero_serie, alerta
FROM vw_maquinaria_alertas;

SELECT p.nombre, p.apellido, n.mes, n.anio, n.importe
FROM NOMINAS n
JOIN PERSONAS p ON n.dni_trabajador = p.dni;

SELECT p.nombre, p.apellido, mc.fecha, mc.peso, mc.grasa
FROM MEDICIONES_CORPORALES mc
JOIN PERSONAS p ON mc.dni_socio = p.dni
WHERE mc.dni_socio = '12345678A';

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