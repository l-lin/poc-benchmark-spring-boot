# Benchmark app using spring-boot, micrometer, prometheus & grafana

## Getting started

```bash
make
# default credentials: admin/admin
firefox http://localhost:3000
# Execute some requests and check the graphs
```

## Benchmarking tools

### Space disk used

We can find the size of a database and table with the following SQL queries:

```sql
-- db size
SELECT pg_size_pretty(pg_database_size('benchmark'));
-- table size
SELECT pg_size_pretty(pg_total_relation_size('public.cat'));
-- schema size
SELECT schema_name,
  pg_size_pretty(sum(table_size)::bigint) AS schema_size
FROM (
       SELECT pg_catalog.pg_namespace.nspname as schema_name,
              pg_total_relation_size(pg_catalog.pg_class.oid) as table_size
       FROM   pg_catalog.pg_class
         JOIN pg_catalog.pg_namespace ON relnamespace = pg_catalog.pg_namespace.oid
     ) t
GROUP BY schema_name
ORDER BY schema_name;
```

### SQL query response time

We can have get the stats on SQL queries by activating the `pg_stat_statements` on PostgreSQL and by executing the 
following query:

```sql
SELECT query, calls, total_time, min_time, max_time, mean_time FROM pg_stat_statements ORDER BY mean_time DESC;
```

### HTTP response time 

Spring Boot can integrate easily with [micrometer](https://micrometer.io/), an amazing tool that provides out of the box
instrumentation for JVM application and it addresses some of the common problems that we face while instrumenting and 
collecting metrics.

We will be using [Prometheus](https://prometheus.io) to collect the metrics and visualize the metrics using 
[Grafana](https://grafana.com/).

### Monitoring on CPU / Memory

[VisualVM](https://visualvm.github.io/) can been used to monitor the resources used of the application when the 
tests are being executed.

### Test execution

We can use [Gatling](https://gatling.io/) to perform the HTTP requests.

```bash
make gatling
# This will generate an HTML report on target/gatling/catsimulation-*/index.html that you can see on your browser
```

## Sources

- [Tutorial by bytesville](http://www.bytesville.com/springboot-micrometer-prometheus-grafana/)
- [JPA Performance benchmark](http://www.jpab.org/)
