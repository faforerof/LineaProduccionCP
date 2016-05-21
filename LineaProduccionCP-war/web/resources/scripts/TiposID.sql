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
