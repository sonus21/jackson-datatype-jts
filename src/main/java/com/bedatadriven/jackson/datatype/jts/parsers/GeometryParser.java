package com.bedatadriven.jackson.datatype.jts.parsers;

import tools.jackson.databind.JsonNode;
import org.locationtech.jts.geom.Geometry;

public interface GeometryParser<T extends Geometry> {

    T geometryFromJson(JsonNode node);

}
