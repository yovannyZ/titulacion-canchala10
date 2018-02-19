-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 16-02-2018 a las 04:18:47
-- Versión del servidor: 10.1.29-MariaDB
-- Versión de PHP: 7.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `canchala10`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `campo`
--

CREATE TABLE `campo` (
  `id` int(11) NOT NULL,
  `descripcion` varchar(250) NOT NULL,
  `implementos` varchar(150) NOT NULL,
  `estacionamiento` varchar(150) NOT NULL,
  `cantidad_jugadores` varchar(20) NOT NULL,
  `vestidores` varchar(150) NOT NULL,
  `snack` varchar(150) NOT NULL,
  `imagen` varchar(100) NOT NULL,
  `id_sede` int(11) NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `campo`
--

INSERT INTO `campo` (`id`, `descripcion`, `implementos`, `estacionamiento`, `cantidad_jugadores`, `vestidores`, `snack`, `imagen`, `id_sede`, `estado`) VALUES
(4, 'Cancha 2', 'Camisetas y balón.', 'Disponible', '7 * 7', 'Si, con duchas', 'Si, mesas habilitadas. TV LCD', '4c98fa4992475a5f438b911c50ab67e3.jpg', 26, 1),
(5, 'Cancha 1', 'Camisetas y balón', 'Disponible', '7 * 7', 'Si, con duchas', 'Si, mesas habilitadas. TV LCD', 'ec96e375c663551ee39f4cecd9f7a31c.jpg', 26, 1),
(6, 'Cancha 4', 'Camisetas y balón', 'Disponible', '8 * 8', 'Si, con duchas', 'Si, mesas habilitadas. TV LCD', '1bbb5ba35740ca37b00265c6e232ea1b.jpg', 26, 0),
(7, 'Cancha 3', 'Camisetas y balón', 'Disponible', '7 * 7', 'Si, con duchas', 'Si, mesas habilitadas. TV LCD', 'a3fbe23ebab957533bb4a559c5e8c776.jpg', 27, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sede`
--

CREATE TABLE `sede` (
  `id` int(11) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `direccion` varchar(250) NOT NULL,
  `url_imagen` text,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `sede`
--

INSERT INTO `sede` (`id`, `descripcion`, `direccion`, `url_imagen`, `estado`) VALUES
(26, 'Chorrillos', 'Av. Prolongación Ariosoto Matellini S/N-AAHH:Virgen del Morro Solar.w', '71c410fa4513c4da2de77fefa81b3d71.jpg', 0),
(27, 'San Miguel', 'Av. La Marina cdra. 35 S/N – San Miguel', '985612a1600b7fedd8edbbf02e7fde6a.jpg', 0),
(28, 'San juan de lurigancho', 'JIRON LOS NARANJOS N° 160 – San Juán de Lurigancho', '670695c7dfb3e7a14ae197867c22c9a8.jpg', 0),
(29, 'San juan de miraflores / Puente alipio', 'Av. Vargas Machuca 211- SJM', 'ac08440c46d16bd1979285abcf6dcbf5.jpg', 0),
(30, 'San juan de miraflores / Pachacutec', 'AV.LOS HEROES N°921-URB.SAN JUANITO-CALLE ENRIQUE DE VILLAR CDRA 11.', '273bd4141f7657d3d1e3cd49d595428d.jpg', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_usuario`
--

CREATE TABLE `tipo_usuario` (
  `id` int(11) NOT NULL,
  `descripcion` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tipo_usuario`
--

INSERT INTO `tipo_usuario` (`id`, `descripcion`) VALUES
(1, 'cliente'),
(2, 'trabajador'),
(3, 'administrador');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `correo` varchar(100) NOT NULL,
  `clave` varchar(100) NOT NULL,
  `nombres` varchar(150) NOT NULL,
  `id_tipo_usuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`correo`, `clave`, `nombres`, `id_tipo_usuario`) VALUES
('zeballos.yovanny@gmail.com', '123', 'yovanny', 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `campo`
--
ALTER TABLE `campo`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_sede` (`id_sede`);

--
-- Indices de la tabla `sede`
--
ALTER TABLE `sede`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tipo_usuario`
--
ALTER TABLE `tipo_usuario`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`correo`),
  ADD KEY `id_tipo_usuario` (`id_tipo_usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `campo`
--
ALTER TABLE `campo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `sede`
--
ALTER TABLE `sede`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT de la tabla `tipo_usuario`
--
ALTER TABLE `tipo_usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `campo`
--
ALTER TABLE `campo`
  ADD CONSTRAINT `campo_ibfk_1` FOREIGN KEY (`id_sede`) REFERENCES `sede` (`id`);

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `fk_tipo_usuario` FOREIGN KEY (`id_tipo_usuario`) REFERENCES `tipo_usuario` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
