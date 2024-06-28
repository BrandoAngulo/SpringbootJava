INSERT INTO roles (rol,activo) VALUES ('ADMINISTRADOR',true),('SOPORTE',true),('FINANCIERO',true),('ANALISTA',true);
INSERT INTO tipo_documento (descripcion) VALUES ('CC'),('CE'),('TI');
INSERT INTO servidores (puerto,tipo,ip,nombre,password,usuario) VALUES (10,'tipo1','10.1.1.1','servidor1','123456','usuario1'),(20,'tipo2','10.1.1.2','servidor2','123456','usuario2');
INSERT INTO zona_horaria (activo,orden,descripcion,utc,ciudad,pais) VALUES (true,'1','zona1','uct','cali','colombia');
INSERT INTO permisos (descripcion) VALUES ('consultar clientes'),('registrar cliente'),('actualizar cliente'),('inactivar cliente'),('registrar usuario'),('consultar usuarios'),('actualizar usuario'),('inactivar usuario'),('consultar usuario por id');
INSERT INTO roles_permisos (permiso_id,rol_id) VALUES (1,1),(2,1),(3,1),(4,1),(5,1),(6,1),(7,1),(8,1),(9,1);
INSERT INTO modulos(icono,ruta, titulo)VALUES ('dashboard','dashboard','Dashboard'),('settings', '','Configuraciones'),('manage_accounts', 'users','Usuarios'),('people', 'customers','Clientes'),('query_stats', '','Consultas SQL'),('dvr', '','Monitoreo'),('receipt_long', 'billing','Facturación'),('feed', '','Reportes');
INSERT INTO submodulos(modulo_id, icono, ruta, titulo)VALUES (2, 'label','settings/versions', 'Versiones'), (2, 'label', 'settings/timezone', 'Zona Horaria'),(2, 'label', 'settings/processes', 'Procesos'),(2, 'label', 'settings/servers', 'Servidores'),(5, 'label', 'queries/massive', 'Rutas Transadas'),(6, 'label', 'monitoring/logs', 'Consultar Log'),(6, 'label', 'monitoring/process_validations', 'Validación de Procesos'),(8, 'label', 'reports/reports', 'Reporte 1'),(2, 'label', 'settings/roles', 'Roles'),(1, 'dashboard', 'dashboard', 'Dashboard'), (3, 'manage_accounts', 'users', 'Usuarios'), (4, 'people', 'customers', 'Clientes'), (7, 'receipt_long', 'billing', 'Facturación');
INSERT INTO roles_submodulos(rol_id, submodulos_id) VALUES (1,2), (1,3), (1,10), (1,9), (1,11), (2,2), (2,3), (2,9);

INSERT INTO procesos (id, cliente, hora_fecha_actual, descripcion) VALUES (1, 'afica1', '2024-06-14 08:00:12', 'Creditos');
INSERT INTO procesos (id, cliente, hora_fecha_actual, descripcion) VALUES (2, 'afica2', '2024-06-14 08:00:12', 'Con vales');
INSERT INTO procesos (id, cliente, hora_fecha_actual, descripcion) VALUES (3, 'afica2', '2024-06-14 08:00:12', 'Sin sobrantes');
INSERT INTO procesos (id, cliente, hora_fecha_actual, descripcion) VALUES (4, 'afica2', '2024-06-14 08:00:12', 'Sin porcentaje');
INSERT INTO procesos (id, cliente, hora_fecha_actual, descripcion) VALUES (5, 'afica2', '2024-06-14 08:00:12', ' estadisticas');
INSERT INTO procesos (id, cliente, hora_fecha_actual, descripcion) VALUES (6, 'afica4', '2024-06-14 08:00:12', ' creditos activos');
