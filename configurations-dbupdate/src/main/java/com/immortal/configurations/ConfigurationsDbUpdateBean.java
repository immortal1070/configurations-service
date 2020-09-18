package com.immortal.configurations;

import java.io.File;
import java.util.Collections;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;

@ApplicationScoped
public class ConfigurationsDbUpdateBean
{
    private static final Logger logger = Logger.getLogger(ConfigurationsDbUpdateBean.class.getName());

    private static final String SCHEMA_HISTORY_TABLE = "configurations_schema_history";
    private static final String DEFAULT_SCRIPTS_FOLDER =
            "db" + File.separator + "scripts" + File.separator + "dbupdate" + File.separator;

    @Resource(mappedName = "java:jboss/datasources/MySQLDS")
    private DataSource dataSource;

    public void migrate(@Observes @Initialized(ApplicationScoped.class) Object o)
    {
        {
            logger.info("migrating DB...");

            FluentConfiguration flywayConfig = new FluentConfiguration();
            flywayConfig.dataSource(dataSource);
            flywayConfig.table(SCHEMA_HISTORY_TABLE);
            flywayConfig.baselineOnMigrate(true);
            flywayConfig.baselineDescription("Updates will be executed from this version");

            Set<String> scriptLocations = Collections.singleton(DEFAULT_SCRIPTS_FOLDER);

            Set<String> prefixedScriptLocations = scriptLocations.stream()
                    .map(scriptLocation -> "classpath:" + scriptLocation).collect(Collectors.toSet());

            flywayConfig.locations(prefixedScriptLocations.toArray(new String[] {}));

            Flyway flyway = new Flyway(flywayConfig);
            flyway.repair();
            flyway.migrate();
        }
    }
}