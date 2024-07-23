#generar log de la estructura de la base de datos como archivo YML
#mvn liquibase:generateChangeLog -Dliquibase.outputChangeLogFile=src/main/resources/db/changelog/prueba.yml
#aplicar cambios cambiar el id y el author y la ruta del archivo
mvn liquibase:update -Dliquibase.changeLogFile=src/main/resources/db/changelog/versionXML/changes.xml -Dliquibase.changeSetAuthor=Bangulo -Dliquibase.changeSetId=1
mvn liquibase:update -Dliquibase.changeLogFile=src/main/resources/db/changelog/versionXML/changes.xml -Dliquibase.changeSetAuthor=Bangulo -Dliquibase.contexts=roles_t
mvn liquibase:tag -Dliquibase.tag=insert_rol
mvn liquibase:update -Dliquibase.changeLogFile=src/main/resources/db/changelog/versionXML/changes.xml -Dliquibase.changeSetAuthor=Bangulo -Dliquibase.changeSetId=2
mvn liquibase:update -Dliquibase.changeLogFile=src/main/resources/db/changelog/versionXML/changes.xml -Dliquibase.changeSetAuthor=Bangulo -Dliquibase.contexts=usuario_t
mvn liquibase:tag -Dliquibase.tag=usuario_t
#ver el historial del los chanlog
mvn liquibase:history
#añadir un tag a un changeset, darle nombre al tag
mvn liquibase:tag -Dliquibase.tag=roles_t
#rollbackTag: Revertir cambios hasta una etiqueta específica.
mvn liquibase:rollback -Dliquibase.changeLogFile=src/main/resources/db/changelog/versionXML/changes.xml -Dliquibase.rollbackTag=roles_t
#rollbackCount: Revertir un número específico de cambios. con el id del cambio
mvn liquibase:rollbackCount -Dliquibase.changeLogFile=src/main/resources/db/changelog/versionXML/changes.xml -Dliquibase.rollbackCount=1

mvn liquibase:rollbackOneChangeSet -Dliquibase.changeSetId=1 -Dliquibase.author=Bangulo
mvn liquibase:rollbackOneChangeSet -Dliquibase.changeLogFile=src/main/resources/db/changelog/versionXML/changes.xml -Dliquibase.changeSetId=1 -Dliquibase.author=Bangulo
