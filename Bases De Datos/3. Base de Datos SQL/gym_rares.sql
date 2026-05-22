-- fitness gym · modelo físico
-- los datos demo de abajo los generó la IA

CREATE DATABASE IF NOT EXISTS fitness_gym;
USE fitness_gym;


CREATE TABLE IF NOT EXISTS persona (
    dni       VARCHAR(9)   PRIMARY KEY,
    nombre    VARCHAR(50)  NOT NULL,
    apellido  VARCHAR(100) NOT NULL,
    telefono  VARCHAR(15),
    email     VARCHAR(100),
    fech_alta VARCHAR(10)  NOT NULL
);

CREATE TABLE IF NOT EXISTS socio (
    dni       VARCHAR(9)  PRIMARY KEY,
    estado    VARCHAR(10) NOT NULL,
    tipo_plan VARCHAR(10) NOT NULL,
    FOREIGN KEY (dni) REFERENCES persona(dni)
);

CREATE TABLE IF NOT EXISTS entrenador (
    dni           VARCHAR(9)  PRIMARY KEY,
    estado        VARCHAR(10) NOT NULL,
    tipo_contrato VARCHAR(20) NOT NULL,
    tipo_jornada  VARCHAR(20) NOT NULL,
    FOREIGN KEY (dni) REFERENCES persona(dni)
);

CREATE TABLE IF NOT EXISTS actividad (
    id_actividad INT         PRIMARY KEY,
    nom_act      VARCHAR(100) NOT NULL,
    nivel        VARCHAR(20)  NOT NULL,
    descripcion  VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS especializar (
    dni_entrenador VARCHAR(9) NOT NULL,
    id_actividad   INT        NOT NULL,
    PRIMARY KEY (dni_entrenador, id_actividad),
    FOREIGN KEY (dni_entrenador) REFERENCES entrenador(dni),
    FOREIGN KEY (id_actividad)   REFERENCES actividad(id_actividad)
);

CREATE TABLE IF NOT EXISTS sala (
    id_sala   INT         PRIMARY KEY,
    nombre    VARCHAR(100) NOT NULL,
    met_cua   INT          NOT NULL,
    aforo_max INT          NOT NULL
);

CREATE TABLE IF NOT EXISTS permitir (
    id_sala      INT NOT NULL,
    id_actividad INT NOT NULL,
    PRIMARY KEY (id_sala, id_actividad),
    FOREIGN KEY (id_sala)      REFERENCES sala(id_sala),
    FOREIGN KEY (id_actividad) REFERENCES actividad(id_actividad)
);

CREATE TABLE IF NOT EXISTS maquinaria (
    num_serie     VARCHAR(50)  PRIMARY KEY,
    tipo          VARCHAR(50)  NOT NULL,
    marca         VARCHAR(50)  NOT NULL,
    fech_compra   VARCHAR(10)  NOT NULL,
    fech_ult_mant VARCHAR(10),
    estado        VARCHAR(20)  NOT NULL,
    id_sala       INT,
    FOREIGN KEY (id_sala) REFERENCES sala(id_sala)
);

CREATE TABLE IF NOT EXISTS sesion (
    id_sesion    INT         PRIMARY KEY,
    fecha        VARCHAR(10) NOT NULL,
    hora_inicio  VARCHAR(5)  NOT NULL,
    hora_fin     VARCHAR(5)  NOT NULL,
    id_actividad INT         NOT NULL,
    id_sala      INT         NOT NULL,
    FOREIGN KEY (id_actividad) REFERENCES actividad(id_actividad),
    FOREIGN KEY (id_sala)      REFERENCES sala(id_sala)
);

CREATE TABLE IF NOT EXISTS impartir (
    dni_entrenador VARCHAR(9) NOT NULL,
    id_sesion      INT        NOT NULL,
    PRIMARY KEY (dni_entrenador, id_sesion),
    FOREIGN KEY (dni_entrenador) REFERENCES entrenador(dni),
    FOREIGN KEY (id_sesion)      REFERENCES sesion(id_sesion)
);

CREATE TABLE IF NOT EXISTS reservar (
    id_reserva   INT         PRIMARY KEY,
    fech_reserva VARCHAR(10) NOT NULL,
    estado       VARCHAR(20) NOT NULL,
    dni_socio    VARCHAR(9)  NOT NULL,
    id_sesion    INT         NOT NULL,
    FOREIGN KEY (dni_socio)  REFERENCES socio(dni),
    FOREIGN KEY (id_sesion)  REFERENCES sesion(id_sesion)
);

CREATE TABLE IF NOT EXISTS pago (
    id_pago   INT         PRIMARY KEY,
    fech_pago VARCHAR(10) NOT NULL,
    concepto  VARCHAR(50) NOT NULL,
    importe   INT         NOT NULL,
    dni_socio VARCHAR(9)  NOT NULL,
    FOREIGN KEY (dni_socio) REFERENCES socio(dni)
);

CREATE TABLE IF NOT EXISTS nomina (
    id_nom         INT         PRIMARY KEY,
    fech_pago      VARCHAR(10) NOT NULL,
    importe        INT         NOT NULL,
    tipo_salario   VARCHAR(20) NOT NULL,
    dni_entrenador VARCHAR(9)  NOT NULL,
    FOREIGN KEY (dni_entrenador) REFERENCES entrenador(dni)
);


-- --------------------------------
-- datos demo (generados por IA)
-- --------------------------------

INSERT IGNORE INTO persona VALUES
('12345678A', 'Carlos', 'García López',   '611111111', 'carlos@gmail.com', '2023-01-10'),
('23456789B', 'Lucía',  'Martínez Ruiz',  '622222222', 'lucia@gmail.com',  '2023-03-15'),
('34567890C', 'Pedro',  'Sánchez Gil',    '633333333', 'pedro@gmail.com',  '2022-06-01'),
('45678901D', 'Ana',    'Fernández Mora', '644444444', 'ana@gmail.com',    '2023-07-20'),
('56789012E', 'Jorge',  'López Vega',     '655555555', 'jorge@gmail.com',  '2021-11-05'),
('67890123F', 'María',  'Jiménez Pardo',  '666666666', 'maria@gmail.com',  '2022-09-12'),
('78901234G', 'Raúl',   'Torres Blanco',  '677777777', 'raul@gmail.com',   '2023-02-28'),
('89012345H', 'Elena',  'Romero Castro',  '688888888', 'elena@gmail.com',  '2022-04-17');

INSERT IGNORE INTO socio VALUES
('12345678A', 'activo', 'Full'),
('23456789B', 'activo', 'Flexible'),
('34567890C', 'moroso', 'Full'),
('45678901D', 'activo', 'Flexible'),
('56789012E', 'baja',   'Full');

INSERT IGNORE INTO entrenador VALUES
('67890123F', 'activo', 'indefinido', 'completo'),
('78901234G', 'activo', 'temporal',   'parcial'),
('89012345H', 'baja',   'indefinido', 'completo');

INSERT IGNORE INTO actividad VALUES
(1, 'Spinning',    'medio',      'Cardio intenso en bicicleta estática'),
(2, 'Yoga',        'iniciacion', 'Estiramientos y relajación'),
(3, 'Pilates',     'medio',      'Fortalecimiento del core'),
(4, 'Zumba',       'iniciacion', 'Baile fitness con ritmos latinos'),
(5, 'Musculacion', 'avanzado',   'Entrenamiento de fuerza con pesas');

INSERT IGNORE INTO especializar VALUES
('67890123F', 1),
('67890123F', 3),
('78901234G', 2),
('78901234G', 4),
('89012345H', 5);

INSERT IGNORE INTO sala VALUES
(1, 'Sala Cardio',  80,  20),
(2, 'Sala Yoga',    60,  15),
(3, 'Sala Pesas',  120,  30),
(4, 'Almacén',      25,   0);

INSERT IGNORE INTO permitir VALUES
(1, 1),
(1, 4),
(2, 2),
(2, 3),
(3, 5);

INSERT IGNORE INTO maquinaria VALUES
('SN-001', 'Bicicleta estática', 'BH',        '2021-03-10', '2024-09-10', 'operativa',         1),
('SN-002', 'Bicicleta estática', 'BH',        '2021-03-10', '2024-09-10', 'operativa',         1),
('SN-003', 'Cinta de correr',    'Technogym', '2020-05-20', '2023-11-20', 'en_revision',       1),
('SN-004', 'Colchoneta',         'Decathlon', '2022-08-01', '2025-02-01', 'operativa',         2),
('SN-005', 'Banco press',        'Hammer',    '2019-07-15', '2024-01-15', 'operativa',         3),
('SN-006', 'Rack sentadillas',   'Hammer',    '2019-07-15', '2023-07-15', 'fuera_de_servicio', 4);

INSERT IGNORE INTO sesion VALUES
(1, '2026-05-05', '09:00', '10:00', 1, 1),
(2, '2026-05-05', '10:30', '11:30', 2, 2),
(3, '2026-05-06', '18:00', '19:00', 1, 1),
(4, '2026-05-07', '17:00', '18:00', 4, 1),
(5, '2026-05-08', '11:00', '12:00', 3, 2);

INSERT IGNORE INTO impartir VALUES
('67890123F', 1),
('67890123F', 3),
('78901234G', 2),
('78901234G', 4),
('78901234G', 5);

INSERT IGNORE INTO reservar VALUES
(1, '2026-04-28', 'confirmada', '12345678A', 1),
(2, '2026-04-28', 'confirmada', '23456789B', 1),
(3, '2026-04-29', 'confirmada', '45678901D', 2),
(4, '2026-04-29', 'cancelada',  '12345678A', 4),
(5, '2026-04-30', 'confirmada', '23456789B', 5);

INSERT IGNORE INTO pago VALUES
(1, '2026-04-01', 'cuota_mensual',  45, '12345678A'),
(2, '2026-04-01', 'cuota_mensual',  45, '34567890C'),
(3, '2026-04-28', 'pago_actividad',  8, '23456789B'),
(4, '2026-04-29', 'pago_actividad',  8, '45678901D'),
(5, '2026-04-30', 'pago_actividad',  8, '23456789B');

INSERT IGNORE INTO nomina VALUES
(1, '2026-04-30', 1800, 'mensual',  '67890123F'),
(2, '2026-04-30',  620, 'por_hora', '78901234G');