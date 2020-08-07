package com.exadel.placebook.converter;

import com.exadel.placebook.exception.ConverterException;
import com.exadel.placebook.model.dto.DashboardElement;
import com.exadel.placebook.model.dto.FloorDto;
import com.exadel.placebook.model.entity.Floor;
import com.exadel.placebook.model.entity.Place;
import com.exadel.placebook.model.enums.FloorConfigElementType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FloorConverter {

    @Autowired
    private DashboardElementInfoConverter converter;

    @Autowired
    private ObjectMapper objectMapper;

    public FloorDto convert(Floor floor) {
        FloorDto dto = new FloorDto();
        dto.setDashboard(floor.getFloorConfiguration());
        dto.setFloorNumber(floor.getFloorNumber());
        dto.setId(floor.getId());
        dto.setHeight(floor.getHeight());
        dto.setWidth(floor.getWidth());
        return dto;
    }

    public Floor convert(FloorDto request) {
        Floor floor = new Floor();

        floor.setFloorNumber(request.getFloorNumber());
        floor.setWidth(request.getWidth());
        floor.setHeight(request.getHeight());
        floor.setFloorConfiguration(request.getDashboard());

        try {
            List<DashboardElement> dashboard = objectMapper.readValue(request.getDashboard(),
                    new TypeReference<List<DashboardElement>>(){});

            List<Place> places = getPlacesFromDashboard(dashboard);
            places.forEach(place -> place.setFloor(floor));
            floor.setPlaces(places);

        } catch (IOException e) {
            throw new ConverterException("unable to parse dashboard");
        }

        return floor;
    }

    private List<Place> getPlacesFromDashboard(List<DashboardElement> dashboard) {
        return dashboard
                .stream()
                .map(DashboardElement::getData)
                .filter(data -> FloorConfigElementType.isInTypesSimpleNames(data.getType(),
                        FloorConfigElementType.CONSTANT,
                        FloorConfigElementType.DESK,
                        FloorConfigElementType.MEETING_ROOM))
                .map(data -> converter.convert(data))
                .collect(Collectors.toList());
    }
}
