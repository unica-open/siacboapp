# Configurations
All configuration MUST be set in the `buildfiles/<env>.properties` file used for compilation
- current.env = the currently executing environment
- nome.ambiente = the name of the environment
- datasource.jndi-url = Must be set to the datasource JNDI name
- messageSources.cacheSeconds = no more used. May be left to -1
- remincl.resource.provider = URL to the remote resources
- remincl.cache.time = caching time for the remote resources (default: 8 hours)
- portal.home = portal home for the logout
- sso.filter.name = Name for the SSO filter
- sso.filter.url.pattern = URL pattern for the SSO filter
    (specify a non-existing extension to prevent SSO checks)
- sso.loginHandler = fully-qualified class name for the SSO handler
- endpoint.url.service.core = Endpoint for the COR backend service
- endpoint.url.service.bil = Endpoint for the BIL backend service
- endpoint.url.service.fin = Endpoint for the FIN backend service
- endpoint.url.service.integ = Endpoint for the INTEG backend service
- endpoint.url.service.rep = Endpoint for the REP backend service
- persistence.unit.showSql = Specifies whether the JPA-generated SQL should be logged
- persistence.unit.formatSql = Specifies whether the JPA-generated SQL should be formatted
- persistence.unit.use_get_generated_keys = Tells the JPA provider to retrieve the
    generated keys
- persistence.unit.use_jdbc_metadata_defaults = Tells the JPA provider not to connect to
    the database to retrieve metadata informations
- jspath = the path for the local JavaScript files (for proxying support)
- jspathexternal = the path for the external JavaScript files (for proxying support)
