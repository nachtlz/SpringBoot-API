-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 16-01-2024 a las 18:19:43
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `apidatabase`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `idCliente` int(11) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `apellido` varchar(50) DEFAULT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `id` varchar(15) DEFAULT NULL,
  `tipoId` varchar(5) DEFAULT NULL,
  `nacionalidad` varchar(20) DEFAULT NULL,
  `edad` int(11) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`idCliente`, `nombre`, `apellido`, `telefono`, `id`, `tipoId`, `nacionalidad`, `edad`, `email`, `password`) VALUES
(1, 'Juan', 'Perez', '123-456-7890', '12345', 'DNI', 'Espanol', 30, 'juanperez@gmail.com', '$2y$10$YUe.XH59IWd1NykveiuVsOy0QVhBDIVxVJMNDLm0RN49wvKdIq2T2'),
(10, 'Iago', 'Palmer', '333333333', '123456789N', 'DNI', 'Alemana', 18, 'iago@gmail.com', '$2y$10$2wBxRjJH3ps/8cH3oc/njefqwydh/kKMeTEltZtsFpfzQwNHr8Hxa'),
(13, 'Isma', 'Garcia', '3929831', '9489292', 'Pas', 'Francesa', 43, 'garcia@gmail.com', '$2y$10$ldeaYFSX6cVTZ2DcwMROW.SpXoybjAegxt4YI9wy.dtBVhsgW/OCe'),
(14, 'Augusto', 'Lopez', '344892', '45224343', 'NIE', 'Portuguesa', 33, 'agus@gmail.com', '$2y$10$SNZ5vy7eSZVlD4fn3Gy7/e5/6jyphqto.fohqxSUd6wnG2YbMwnmu'),
(15, 'joan', 'joan', '82737459', '123456789N', 'DNI', 'aRGENTINO', 33, 'JOAN123@GMAIL.COM', '$2y$10$zG/E5Yt0B9ipvgTqiXGKE.QQvSWSfnbBSHDETo.jOco7H7KEX85Ay'),
(16, 'Rocco', 'Esc', '9482828', NULL, NULL, NULL, 0, 'rocs@gmail.com', NULL),
(17, 'Sheldon', 'Cooper', '94892829', NULL, NULL, NULL, 0, 'sheldon@gmail.com', NULL),
(18, 'Marc', 'Fernandez', '938282991', NULL, NULL, NULL, 0, 'fer@gmail.com', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `disponibilidad`
--

CREATE TABLE `disponibilidad` (
  `idHotel` int(11) NOT NULL,
  `codigo` char(3) NOT NULL,
  `habsDisponibles` int(11) DEFAULT NULL,
  `precio` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `disponibilidad`
--

INSERT INTO `disponibilidad` (`idHotel`, `codigo`, `habsDisponibles`, `precio`) VALUES
(1, 'DVM', 4, 180.00),
(1, 'JSU', 5, 250.00),
(1, 'SUI', 10, 300.00),
(2, 'DBL', 20, 100.00),
(2, 'DVM', 8, 150.00),
(2, 'SUI', 8, 280.00),
(3, 'IND', 12, 70.00),
(3, 'JSU', 5, 220.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hotel`
--

CREATE TABLE `hotel` (
  `idHotel` int(11) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `descripcion` text DEFAULT NULL,
  `url` varchar(50) DEFAULT NULL,
  `categoria` int(11) DEFAULT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `imagen` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `hotel`
--

INSERT INTO `hotel` (`idHotel`, `nombre`, `descripcion`, `url`, `categoria`, `telefono`, `imagen`) VALUES
(1, 'Hotel A', 'Un hotel de lujo con vista al mar.', 'www.hotela.com', 5, '987-654-3210', '5933cbcc8073df96018aa70fb0eda3404b046a34bf765cd0b15d0b045c8e8127'),
(2, 'Hotel B', 'Un hotel económico en el centro de la ciudad.', 'www.hotelb.com', 2, '555-123-4567', '659dfe808fb8c6b4682ad4a37da691d879e8560b2203878a91ed5ff8366a28c5'),
(3, 'Hotel C', 'Un hotel boutique en la montaña.', 'www.hotelc.com', 4, '123-456-7890', 'cd9703539a08f701e68f148ae5fd09147e433ef991283f46e88238e0678831c3');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reserva`
--

CREATE TABLE `reserva` (
  `idReserva` int(11) NOT NULL,
  `fechainicio` date DEFAULT NULL,
  `fechafin` date DEFAULT NULL,
  `adultos` int(11) DEFAULT NULL,
  `menores` int(11) DEFAULT NULL,
  `idCliente` int(11) DEFAULT NULL,
  `codigo` char(3) DEFAULT NULL,
  `idHotel` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `reserva`
--

INSERT INTO `reserva` (`idReserva`, `fechainicio`, `fechafin`, `adultos`, `menores`, `idCliente`, `codigo`, `idHotel`) VALUES
(4, '2023-11-10', '2023-11-12', 1, 0, 1, 'JSU', 1),
(18, '2023-12-03', '2023-12-31', 1, 1, 13, 'JSU', 3),
(19, '2023-12-03', '2023-12-31', 1, 1, 14, 'JSU', 3),
(51, '2023-12-24', '2023-12-27', 2, 1, 16, 'DVM', 2),
(52, '2023-12-24', '2023-12-27', 2, 1, 17, 'DBL', 2),
(53, '2023-12-28', '2023-12-30', 1, 1, 18, 'JSU', 3),
(56, '2024-01-21', '2024-01-24', 2, 1, 10, 'DVM', 1),
(60, '2024-01-16', '2024-01-18', 2, 1, 10, 'DVM', 1),
(61, '2024-01-16', '2024-01-19', 1, 1, 10, 'SUI', 2),
(62, '2024-01-21', '2024-01-24', 1, 0, 10, 'IND', 3),
(63, '2024-01-28', '2024-01-30', 2, 2, 10, NULL, 3),
(64, '2024-01-22', '2024-01-25', 2, 1, 10, 'SUI', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipohabitacion`
--

CREATE TABLE `tipohabitacion` (
  `codigo` char(3) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `adultos` int(11) DEFAULT NULL,
  `menores` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tipohabitacion`
--

INSERT INTO `tipohabitacion` (`codigo`, `descripcion`, `adultos`, `menores`) VALUES
('DBL', 'Doble', 2, 2),
('DVM', 'Doble Vista Mar', 2, 3),
('IND', 'Individual', 2, 0),
('JSU', 'Junior Suite', 1, 2),
('SUI', 'Suite', 2, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`idCliente`);

--
-- Indices de la tabla `disponibilidad`
--
ALTER TABLE `disponibilidad`
  ADD PRIMARY KEY (`idHotel`,`codigo`),
  ADD KEY `codigo` (`codigo`);

--
-- Indices de la tabla `hotel`
--
ALTER TABLE `hotel`
  ADD PRIMARY KEY (`idHotel`);

--
-- Indices de la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD PRIMARY KEY (`idReserva`),
  ADD KEY `idCliente` (`idCliente`),
  ADD KEY `codigo` (`codigo`),
  ADD KEY `idHotel` (`idHotel`);

--
-- Indices de la tabla `tipohabitacion`
--
ALTER TABLE `tipohabitacion`
  ADD PRIMARY KEY (`codigo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `idCliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT de la tabla `hotel`
--
ALTER TABLE `hotel`
  MODIFY `idHotel` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `reserva`
--
ALTER TABLE `reserva`
  MODIFY `idReserva` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=65;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `disponibilidad`
--
ALTER TABLE `disponibilidad`
  ADD CONSTRAINT `disponibilidad_ibfk_1` FOREIGN KEY (`idHotel`) REFERENCES `hotel` (`idHotel`),
  ADD CONSTRAINT `disponibilidad_ibfk_2` FOREIGN KEY (`codigo`) REFERENCES `tipohabitacion` (`codigo`),
  ADD CONSTRAINT `fk_idHotel` FOREIGN KEY (`idHotel`) REFERENCES `hotel` (`idHotel`);

--
-- Filtros para la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD CONSTRAINT `reserva_ibfk_1` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`idCliente`),
  ADD CONSTRAINT `reserva_ibfk_2` FOREIGN KEY (`codigo`) REFERENCES `tipohabitacion` (`codigo`),
  ADD CONSTRAINT `reserva_ibfk_3` FOREIGN KEY (`idHotel`) REFERENCES `hotel` (`idHotel`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
