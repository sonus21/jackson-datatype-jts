# Jackson-datatype-jts

Jackson 3 module providing serializers and deserializers for [JTS Geometry](https://projects.eclipse.org/projects/locationtech.jts) objects using the [GeoJSON format](http://www.geojson.org/geojson-spec.html).

## Installation

Releases are available on Maven Central.

### Maven

```xml
<dependency>
  <groupId>io.github.sonus21</groupId>
  <artifactId>jackson-datatype-jts</artifactId>
  <version>3.0.0</version>
</dependency>
```

### Gradle

```groovy
implementation 'io.github.sonus21:jackson-datatype-jts:3.0.0'
```

## Requirements

- Java 17+
- Jackson 3.x (`tools.jackson.core:jackson-databind:3.0.0`)
- JTS 1.20.0

## Version history

| Version | Jackson    | JTS    | Java |
|---------|------------|--------|------|
| 3.0.0   | 3.0.0      | 1.20.0 | 17   |
| 2.21.0  | 2.21.0     | 1.20.0 | 8    |
| 2.19.2  | 2.x        | 1.20.0 | 8    |
| 2.14    | 2.x        | 1.19.0 | 8    |

For Jackson 2.x users, stay on the `2.x` line of releases.

## Usage

### Registering the module

Jackson 3 mappers are immutable; register the module via the builder:

```java
ObjectMapper mapper = JsonMapper.builder()
    .addModule(new JtsModule())
    .build();
```

### Reading and writing geometry types

```java
GeometryFactory gf = new GeometryFactory();
Point point = gf.createPoint(new Coordinate(1.2345678, 2.3456789));
String geojson = mapper.writeValueAsString(point);
```

```java
Point point = mapper.readValue(geojson, Point.class);
```

The module supports `Point`, `LineString`, `Polygon`, `MultiPoint`, `MultiLineString`, `MultiPolygon`, `GeometryCollection`, and the `Geometry` interface itself.

## Release

To publish to Maven Central via the [Central Portal](https://central.sonatype.com/):

By default the release profile *uploads* the bundle to the Central Portal and exits immediately — Maven does not wait for validation, and nothing is auto-published. The deployment sits on https://central.sonatype.com/publishing/deployments awaiting your **Publish** or **Drop** click:

```sh
mvn clean deploy -P release -DskipTests=true -B
```

To skip the manual step and publish automatically (waits for `PUBLISHED` before exiting):

```sh
mvn clean deploy -P release -DskipTests=true -B \
    -Dcentral.autoPublish=true \
    -Dcentral.waitUntil=published
```

Requires a `central` server entry in `~/.m2/settings.xml` with a Central Portal user token, and a GPG signing key available to `maven-gpg-plugin`.

## License

Apache License 2.0
