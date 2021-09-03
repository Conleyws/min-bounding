package com.github.conleyws.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.github.conleyws.GeoJSON;

import org.springframework.stereotype.Service;

/**
 * Service to contain any logic surrounds Polygons
 * 
 */
@Service
public class PolygonService {
    
    /**
     * Calculates the minimum bounds of passed in GeoJSON polygon
     * and creates a new GeoJSON polygon using those bounds
     * 
     * @param polygon Shape which bounds are to be found
     * @return Minimum Bounding Shape
     * @see GeoJSON
     */
    public GeoJSON calculateMinBounding(GeoJSON polygon) {
        if (polygon == null || polygon.getCoordinates() == null) {
            return polygon;
        }
        
        // 0 = minX
        // 1 = maxX
        // 2 = minY 
        // 3 = maxY
        Double[] bounds = new Double[4];
        // Traverse each coordinate and check if X/Y is new bound
        for (List<List<Double>> shape : polygon.getCoordinates()){
            for (List<Double> coord : shape) {
                // Min X
                if (bounds[0] == null || coord.get(0) < bounds[0]) {
                    bounds[0] = coord.get(0);
                }
                // Max X
                if (bounds[1] == null || coord.get(0) > bounds[1]) {
                    bounds[1] = coord.get(0);
                }
                // Min Y
                if (bounds[2] == null || coord.get(1) < bounds[2]) {
                    bounds[2] = coord.get(1);
                }
                // Max Y
                if (bounds[3] == null || coord.get(1) > bounds[3]) {
                    bounds[3] = coord.get(1);
                }
            }
        }
        
        // Starting at Top Left and continuing clockwise
        List<List<Double>> boundingCoords = new ArrayList<List<Double>>();
        // MinX MaxY
        boundingCoords.add(Arrays.asList(bounds[0], bounds[3]));
        // MaxX MaxY
        boundingCoords.add(Arrays.asList(bounds[1], bounds[3]));
        // MaxX MinY
        boundingCoords.add(Arrays.asList(bounds[1], bounds[2]));
        // MinX MinY
        boundingCoords.add(Arrays.asList(bounds[0], bounds[2]));
        // MinX MaxY - Have to return to start
        boundingCoords.add(Arrays.asList(bounds[0], bounds[3]));
        
        return new GeoJSON(polygon.getType(), new ArrayList<>(Arrays.asList(boundingCoords)));
    }
}