package com.github.conleyws;

import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
/**    
 * POJO for GeoJSON
 * Contains Type and Coordinates
 * 
 * @author wconley
 * https://datatracker.ietf.org/doc/html/draft-ietf-geojson for more information
 */
public class GeoJSON {
    @JsonProperty("type")
    String type;

    // I tried to parse these into a Coordinate Object with x/y but I could not get it to parse directly.
    @JsonProperty("coordinates")
    List<List<List<Double>>> coordinates;

    public GeoJSON() {
        this.coordinates = new ArrayList<List<List<Double>>>();
    }

    public GeoJSON(String type, List<List<List<Double>>> coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }

    public List<List<List<Double>>> getCoordinates() {
        return this.coordinates;
    }

    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Type: ").append(this.type);
        builder.append("| Coordinates: ").append(this.coordinates);
        return builder.toString();
    }

}