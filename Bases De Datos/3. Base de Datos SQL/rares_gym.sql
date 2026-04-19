CREATE DATABASE IF NOT EXISTS RARES_GYM;
USE RARES_GYM;

CREATE TABLE PERSONAS (
    DNI VARCHAR(10) PRIMARY KEY,
    Nombre VARCHAR(50) NOT NULL,
    Apellido VARCHAR(50) NOT NULL,
    Telefono VARCHAR(15) NOT NULL,
    Email VARCHAR(50) NOT NULL UNIQUE,
    Fecha_Alta DATE NOT NULL,
    Estado ENUM('Activo', 'Baja', 'Moroso') NOT NULL
);

CREATE TABLE SOCIOS (
    DNI VARCHAR(10) PRIMARY KEY,
    Tipo_Plan ENUM('Full', 'Flexible') NOT NULL,
    FOREIGN KEY (DNI) REFERENCES PERSONAS(DNI) ON DELETE CASCADE
);

CREATE TABLE ENTRENADORES (
    DNI VARCHAR(10) PRIMARY KEY,
    Tipo_Contrato ENUM('Tiempo completo', 'Tiempo parcial') NOT NULL,
    FOREIGN KEY (DNI) REFERENCES PERSONAS(DNI) ON DELETE CASCADE
);

CREATE TABLE SALAS (
    Nombre_Sala VARCHAR(50) PRIMARY KEY,
    Metros_Cuadrados INT NOT NULL,
    Aforo_Maximo INT NOT NULL
);

CREATE TABLE ACTIVIDADES (
    ID_Actividad INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(50) NOT NULL UNIQUE,
    Descripcion TEXT,
    Nivel ENUM('Bajo', 'Medio', 'Alto') NOT NULL
);

CREATE TABLE MAQUINARIA (
    Num_Serie VARCHAR(50) PRIMARY KEY,
    Tipo VARCHAR(50) NOT NULL,
    Marca VARCHAR(50) NOT NULL,
    Fecha_Compra DATE NOT NULL,
    Fecha_Ult_Mant DATE,
    Estado ENUM('Operativa', 'En revisión', 'Fuera de servicio') NOT NULL,
    Nombre_Sala VARCHAR(50) NOT NULL,
    FOREIGN KEY (Nombre_Sala) REFERENCES SALAS(Nombre_Sala)
);

CREATE TABLE SESIONES (
    ID_Sesion INT AUTO_INCREMENT PRIMARY KEY,
    Fecha DATE NOT NULL,
    Hora_Inicio TIME NOT NULL,
    Hora_Fin TIME NOT NULL,
    ID_Actividad INT NOT NULL,
    Nombre_Sala VARCHAR(50) NOT NULL,
    DNI_Entrenador VARCHAR(10) NOT NULL,
    FOREIGN KEY (ID_Actividad) REFERENCES ACTIVIDADES(ID_Actividad),
    FOREIGN KEY (Nombre_Sala) REFERENCES SALAS(Nombre_Sala),
    FOREIGN KEY (DNI_Entrenador) REFERENCES ENTRENADORES(DNI)
);

CREATE TABLE RESERVAS (
    DNI_Socio VARCHAR(10),
    ID_Sesion INT,
    Confirmacion_Pago BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (DNI_Socio, ID_Sesion),
    FOREIGN KEY (DNI_Socio) REFERENCES SOCIOS(DNI),
    FOREIGN KEY (ID_Sesion) REFERENCES SESIONES(ID_Sesion)
);

CREATE TABLE PAGOS (
    ID_Pago INT AUTO_INCREMENT PRIMARY KEY,
    Importe DECIMAL(10,2) NOT NULL,
    Fecha DATE NOT NULL,
    Concepto VARCHAR(100) NOT NULL,
    DNI_Socio VARCHAR(10) NOT NULL,
    FOREIGN KEY (DNI_Socio) REFERENCES SOCIOS(DNI)
);

INSERT INTO PERSONAS VALUES 
('1001A', 'Carlos', 'Ruiz', '600101101', 'carlos@mail.com', '2025-01-10', 'Activo'),
('1002B', 'Lucía', 'García', '600101102', 'lucia@mail.com', '2025-01-12', 'Activo'),
('1003C', 'Marcos', 'Pérez', '600101103', 'marcos@mail.com', '2025-01-15', 'Moroso'),
('1004D', 'Elena', 'Sanz', '600101104', 'elena@mail.com', '2025-02-01', 'Baja'),
('1005E', 'Raúl', 'López', '600101105', 'raul@mail.com', '2025-02-05', 'Activo'),
('1006F', 'Sara', 'Mora', '600101106', 'sara@mail.com', '2025-02-10', 'Activo'),
('1007G', 'Ivan', 'Blanco', '600101107', 'ivan@mail.com', '2025-02-15', 'Activo'),
('1008H', 'Ana', 'Torres', '600101108', 'ana@mail.com', '2025-03-01', 'Activo'),
('1009I', 'Jorge', 'Cano', '600101109', 'jorge@mail.com', '2025-03-05', 'Activo'),
('1010J', 'Rocío', 'Gil', '600101110', 'rocio@mail.com', '2025-03-10', 'Activo'),
('2001T', 'Andrés', 'Fernández', '611222333', 'pro1@fit.com', '2024-01-01', 'Activo'),
('2002T', 'Marta', 'Sánchez', '611222444', 'pro2@fit.com', '2024-01-01', 'Activo');

INSERT INTO SOCIOS VALUES 
('1001A', 'Full'), ('1002B', 'Full'), ('1003C', 'Flexible'), ('1004D', 'Flexible'),
('1005E', 'Full'), ('1006F', 'Flexible'), ('1007G', 'Full'), ('1008H', 'Flexible'),
('1009I', 'Full'), ('1010J', 'Full');

INSERT INTO ENTRENADORES VALUES 
('2001T', 'Tiempo completo'), ('2002T', 'Tiempo parcial');

INSERT INTO SALAS VALUES 
('Sala Cardio', 150, 40), ('Sala Zen', 80, 20), ('Sala Pesas', 200, 50), ('Almacén', 40, 0);

INSERT INTO ACTIVIDADES (Nombre, Descripcion, Nivel) VALUES 
('Yoga', 'Relajación y estiramiento', 'Bajo'),
('Spinning', 'Cardio de alta intensidad', 'Alto'),
('Crossfit', 'Entrenamiento funcional', 'Alto'),
('Pilates', 'Core y flexibilidad', 'Medio'),
('Zumba', 'Baile aeróbico', 'Bajo');

INSERT INTO MAQUINARIA VALUES 
('SN-9901', 'Cinta Correr', 'Technogym', '2023-01-01', '2024-01-01', 'Operativa', 'Sala Cardio'),
('SN-9902', 'Bici Estática', 'Matrix', '2022-05-10', '2024-11-01', 'En revisión', 'Sala Cardio'),
('SN-9903', 'Mancuernas 10kg', 'Eleiko', '2024-01-01', NULL, 'Operativa', 'Sala Pesas'),
('SN-9904', 'Prensa', 'LifeFitness', '2021-06-15', '2023-12-15', 'Fuera de servicio', 'Almacén');

INSERT INTO SESIONES (Fecha, Hora_Inicio, Hora_Fin, ID_Actividad, Nombre_Sala, DNI_Entrenador) VALUES 
('2026-05-01', '09:00', '10:00', 1, 'Sala Zen', '2001T'),
('2026-05-01', '10:00', '11:00', 2, 'Sala Cardio', '2002T'),
('2026-05-01', '11:00', '12:00', 3, 'Sala Pesas', '2001T'),
('2026-05-02', '09:00', '10:00', 4, 'Sala Zen', '2002T'),
('2026-05-02', '18:00', '19:00', 5, 'Sala Cardio', '2001T');

INSERT INTO RESERVAS VALUES 
('1001A', 1, TRUE), ('1002B', 1, TRUE), ('1005E', 2, TRUE), ('1006F', 2, TRUE),
('1007G', 3, TRUE), ('1009I', 3, TRUE), ('1010J', 4, TRUE), ('1001A', 5, TRUE),
('1002B', 5, TRUE), ('1005E', 5, TRUE);

INSERT INTO PAGOS (Importe, Fecha, Concepto, DNI_Socio) VALUES 
(50.00, '2026-04-01', 'Cuota Full Abril', '1001A'),
(50.00, '2026-04-01', 'Cuota Full Abril', '1002B'),
(10.00, '2026-04-05', 'Sesión Suelta Zumba', '1006F'),
(50.00, '2026-04-01', 'Cuota Full Abril', '1005E'),
(50.00, '2026-04-01', 'Cuota Full Abril', '1007G'),
(50.00, '2026-04-01', 'Cuota Full Abril', '1009I'),
(50.00, '2026-04-01', 'Cuota Full Abril', '1010J'),
(10.00, '2026-04-06', 'Sesión Pilates', '1008H'),
(10.00, '2026-04-07', 'Sesión Yoga', '1003C'),
(10.00, '2026-04-08', 'Sesión Spinning', '1004D');