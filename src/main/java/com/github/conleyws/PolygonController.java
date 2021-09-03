package com.github.conleyws;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.github.conleyws.service.PolygonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController()
@RequestMapping("/polygon")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PolygonController {

    @Autowired
    private PolygonService polygonService;

    @PostMapping("/minBounding")
    public GeoJSON calculateMinBounding(@RequestBody GeoJSON polygon) {
        return polygonService.calculateMinBounding(polygon);
    }
    
}
