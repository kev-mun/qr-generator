-- 1. Crear el tipo ENUM (Postgres necesita que el ENUM se defina antes de usarlo)
CREATE TYPE estado_envio AS ENUM (
    'ENTREGADO',
    'EN_CAMINO',
    'INCIDENCIA',
    'PENDIENTE'
);
-- 2. Crear la tabla ENVIO
CREATE TABLE ENVIO (
    ID BIGSERIAL PRIMARY KEY,
    CIUDAD_DESTINO VARCHAR(255),
    DESCRIPCION VARCHAR(255),
    DESTINATARIO VARCHAR(255),
    ESTADO estado_envio,
    EVIDENCIA_URL VARCHAR(255),
    FECHA_CREACION TIMESTAMP(6),
    FECHA_ESTIMADA_ENTREGA TIMESTAMP(6),
    TRACKING_NUMBER VARCHAR(255)
);
-- 3. Crear la tabla HISTORIAL_ESTADO
CREATE TABLE HISTORIAL_ESTADO (
    ID BIGSERIAL PRIMARY KEY,
    ESTADO_ANTERIOR estado_envio,
    ESTADO_NUEVO estado_envio,
    FECHA_CAMBIO TIMESTAMP(6),
    NOTAS VARCHAR(255),
    ENVIO_ID BIGINT,
    CONSTRAINT FK_ENVIO FOREIGN KEY (ENVIO_ID) REFERENCES ENVIO(ID)
);
-- 4. Insertar los datos (Ajustado a sintaxis Postgres)
INSERT INTO ENVIO (
        ID,
        CIUDAD_DESTINO,
        DESCRIPCION,
        DESTINATARIO,
        ESTADO,
        EVIDENCIA_URL,
        FECHA_CREACION,
        FECHA_ESTIMADA_ENTREGA,
        TRACKING_NUMBER
    )
VALUES (
        1,
        'cali',
        'celular',
        'javier',
        'ENTREGADO',
        '',
        '2026-05-08 10:57:19.489595',
        '2026-05-11 10:57:19.489595',
        '9B37FD83'
    );
-- Reiniciar el contador del ID para que el siguiente sea el 2
SELECT setval(pg_get_serial_sequence('envio', 'id'), 1);
INSERT INTO HISTORIAL_ESTADO (
        ID,
        ESTADO_ANTERIOR,
        ESTADO_NUEVO,
        FECHA_CAMBIO,
        NOTAS,
        ENVIO_ID
    )
VALUES (
        1,
        NULL,
        'PENDIENTE',
        '2026-05-08 10:57:19.526582',
        'Envio registrado en el sistema',
        1
    ),
    (
        2,
        'PENDIENTE',
        'ENTREGADO',
        '2026-05-08 10:58:20.286248',
        '',
        1
    );
-- Reiniciar el contador del ID para que el siguiente sea el 3
SELECT setval(
        pg_get_serial_sequence('historial_estado', 'id'),
        2
    );