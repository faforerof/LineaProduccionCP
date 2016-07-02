CREATE TABLE `hilo` (
  `idhilo` int(11) NOT NULL AUTO_INCREMENT,
  `factura` varchar(45) NOT NULL,
  `peso` decimal(20,2) NOT NULL,
  `peso_usado` decimal(20,2) DEFAULT NULL,
  `valor_total` decimal(20,2) NOT NULL,
  `distribuidor` int(11) NOT NULL,
  `referencia` int(11) NOT NULL,
  `Documento` mediumblob,
  `extension` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idhilo`),
  KEY `FK_HILO_REFERENCIA_idx` (`referencia`),
  KEY `FK_HILO_PROVEEDOR_idx` (`distribuidor`),
  CONSTRAINT `FK_HILO_PROVEEDOR` FOREIGN KEY (`distribuidor`) REFERENCES `proveedor` (`idproveedor`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_HILO_REFERENCIA` FOREIGN KEY (`referencia`) REFERENCES `referencia` (`idreferencia`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

CREATE TABLE `proveedor` (
  `idproveedor` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_proveedor` varchar(200) DEFAULT NULL,
  `tipo_id_proveedor` int(11) DEFAULT NULL,
  `numero_id_proveedor` varchar(45) NOT NULL,
  PRIMARY KEY (`idproveedor`),
  UNIQUE KEY `numero_id_proveedor_UNIQUE` (`numero_id_proveedor`),
  KEY `FK_PROVEEDOR_TIPO_IDENTIFICACION_idx` (`tipo_id_proveedor`),
  CONSTRAINT `FK_PROVEEDOR_TIPOID` FOREIGN KEY (`tipo_id_proveedor`) REFERENCES `tipo_identificacion` (`idtipo_identificacion`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

CREATE TABLE `referencia` (
  `idreferencia` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_referencia` varchar(45) NOT NULL,
  `descripcion` varchar(500) NOT NULL,
  `tabla` varchar(45) NOT NULL,
  PRIMARY KEY (`idreferencia`),
  UNIQUE KEY `nombre_referencia_UNIQUE` (`nombre_referencia`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE `sequence` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL,
  PRIMARY KEY (`SEQ_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tejido` (
  `idtejido` int(11) NOT NULL,
  `factura` varchar(45) NOT NULL,
  `lote` varchar(45) DEFAULT NULL,
  `programa` varchar(45) DEFAULT NULL,
  `cantidad` decimal(20,2) DEFAULT NULL,
  `valor` decimal(20,2) DEFAULT NULL,
  `color` varchar(45) DEFAULT NULL,
  `referencia` int(11) DEFAULT NULL,
  `proveedor` int(11) DEFAULT NULL,
  `hilo` int(11) DEFAULT NULL,
  PRIMARY KEY (`idtejido`),
  UNIQUE KEY `factura_UNIQUE` (`factura`),
  KEY `FK_TEJIDO_HILO_idx` (`hilo`),
  KEY `FK_TEJIDO_PROVEEDOR_idx` (`proveedor`),
  KEY `FK_TEJIDO_REFERENCIA_idx` (`referencia`),
  CONSTRAINT `FK_TEJIDO_HILO` FOREIGN KEY (`hilo`) REFERENCES `hilo` (`idhilo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_TEJIDO_PROVEEDOR` FOREIGN KEY (`proveedor`) REFERENCES `proveedor` (`idproveedor`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_TEJIDO_REFERENCIA` FOREIGN KEY (`referencia`) REFERENCES `referencia` (`idreferencia`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tipo_identificacion` (
  `idtipo_identificacion` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(1) DEFAULT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idtipo_identificacion`),
  UNIQUE KEY `codigo_UNIQUE` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

CREATE TABLE `usercyp` (
  `ID` bigint(20) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `users` (
  `idusers` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(500) NOT NULL,
  `nombres` varchar(45) NOT NULL,
  `apellidos` varchar(45) NOT NULL,
  `rol` int(11) NOT NULL,
  PRIMARY KEY (`idusers`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;


--Inserts Tipos de Identificación
INSERT INTO `lpcypdb`.`tipo_identificacion` (`idtipo_identificacion`, `codigo`, `descripcion`) VALUES ('1', 'C', 'Cédula de Ciudadanía');
INSERT INTO `lpcypdb`.`tipo_identificacion` (`idtipo_identificacion`, `codigo`, `descripcion`) VALUES ('2', 'N', 'Nit de Empresa');
INSERT INTO `lpcypdb`.`tipo_identificacion` (`idtipo_identificacion`, `codigo`, `descripcion`) VALUES ('3', 'T', 'Tarjeta de Identidad');
INSERT INTO `lpcypdb`.`tipo_identificacion` (`idtipo_identificacion`, `codigo`, `descripcion`) VALUES ('4', 'E', 'Cédula de Extranjería');
INSERT INTO `lpcypdb`.`tipo_identificacion` (`idtipo_identificacion`, `codigo`, `descripcion`) VALUES ('5', 'P', 'Pasaporte');

--Inserts Usuarios Administradores
INSERT INTO `lpcypdb`.`users` (`idusers`, `username`, `password`, `nombres`, `apellidos`, `rol`) VALUES ('1', 'faforerof', '957e985765c3213e42128fc8618d25', 'Freddy Adrián', 'Forero Fernández', '0');
INSERT INTO `lpcypdb`.`users` (`idusers`, `username`, `password`, `nombres`, `apellidos`, `rol`) VALUES ('2', 'caforerof', '957e985765c3213e42128fc8618d25', 'Camilo Andrés', 'Forero Fernández', '0');
INSERT INTO `lpcypdb`.`users` (`idusers`, `username`, `password`, `nombres`, `apellidos`, `rol`) VALUES ('3', 'hjforerof', '957e985765c3213e42128fc8618d25', 'Henry José', 'Forero Fernández', '0');

--Inserts Proveedores
INSERT INTO `lpcypdb`.`proveedor` (`idproveedor`, `nombre_proveedor`, `tipo_id_proveedor`, `numero_id_proveedor`) VALUES ('1', 'Hermanos Forero', '2', '10229817951');
INSERT INTO `lpcypdb`.`proveedor` (`idproveedor`, `nombre_proveedor`, `tipo_id_proveedor`, `numero_id_proveedor`) VALUES ('2', 'Mi Proveedor', '2', '12039492183');

--Inserts Referencias
INSERT INTO `lpcypdb`.`referencia` (`idreferencia`, `nombre_referencia`, `descripcion`, `tabla`) VALUES ('1', 'RTF', 'Rojo Turquesa', 'hilo');
INSERT INTO `lpcypdb`.`referencia` (`idreferencia`, `nombre_referencia`, `descripcion`, `tabla`) VALUES ('2', 'RTF', 'Rojo Turquesa', 'tejido');
INSERT INTO `lpcypdb`.`referencia` (`idreferencia`, `nombre_referencia`, `descripcion`, `tabla`) VALUES ('3', 'RTA', 'Rojo Azulado', 'hijo');
