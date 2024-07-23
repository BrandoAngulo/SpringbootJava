/*SET SEARCH_PATH TO public, "$user","public";

-- *********************************************************************
-- SQL to roll back currently unexecuted changes
-- *********************************************************************
-- Change Log: db/changelog/db.changelog-master.yml
-- Ran at: 22/07/24, 5:22 p. m.
-- Against: postgres@jdbc:postgresql://localhost:5436/postgres
-- Liquibase version: 4.27.0
-- *********************************************************************

SET SEARCH_PATH TO public, "$user","public";

-- Lock Database
UPDATE public.databasechangeloglock SET LOCKED = TRUE, LOCKEDBY = 'DESKTOP-P3GSLHR (192.168.1.131)', LOCKGRANTED = NOW() WHERE ID = 1 AND LOCKED = FALSE;

SET SEARCH_PATH TO public, "$user","public";

SET SEARCH_PATH TO public, "$user","public";

-- Rolling Back ChangeSet: db/changelog/versions/01-create-tables.sql::raw::includeAll
SET SEARCH_PATH TO public, "$user","public";

-- Release Database Lock
SET SEARCH_PATH TO public, "$user","public";

UPDATE public.databasechangeloglock SET LOCKED = FALSE, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

SET SEARCH_PATH TO public, "$user","public";

*/