Proyecto Java Swing - Inventario de Computadoras
===============================================

Estructura sugerida (abrir con IntelliJ como proyecto Maven).

Configurar:
1. Editar `src/main/resources/db.properties` con la URL, usuario y contraseña de su instancia MySQL (RDS AWS).
   Ejemplo: db.url=jdbc:mysql://mi-rds-endpoint:3306/inventario_db
2. Ejecutar el script SQL en `sql/create_table_mysql.sql` desde MySQL Workbench para crear la base de datos y tablas.
3. Importar proyecto Maven en IntelliJ y ejecutar la clase `pe.vallegrande.Main`.

Notas:
- El proyecto contiene un formulario con JTextField, JComboBox, JRadioButton, JCheckBox, JButton, JTable.
- El DAO usa `DBConnection` para conectarse mediante properties.
- Ajuste y valide los campos antes de producción.
