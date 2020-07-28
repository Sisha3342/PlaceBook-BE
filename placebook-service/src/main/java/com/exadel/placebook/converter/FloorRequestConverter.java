package com.exadel.placebook.converter;

import com.exadel.placebook.exception.ConverterException;
import com.exadel.placebook.model.dto.FloorConfigElement;
import com.exadel.placebook.model.dto.FloorRequest;
import com.exadel.placebook.model.entity.Floor;
import com.exadel.placebook.model.entity.Place;
import com.exadel.placebook.model.enums.FloorConfigElementType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FloorRequestConverter {

    @Autowired
    private FloorConfigElementInfoConverter converter;

    @Autowired
    private ObjectMapper objectMapper;

    public Floor convert(FloorRequest request) {
        Floor floor = new Floor();

        floor.setFloorNumber(request.getFloorNumber());

        List<Place> places = request.getConfigurationList()
                .stream()
                .map(FloorConfigElement::getData)
                .filter(data -> FloorConfigElementType.isInTypesSimpleNames(data.getType(),
                        FloorConfigElementType.CONSTANT,
                        FloorConfigElementType.DESK,
                        FloorConfigElementType.MEETING_ROOM))
                .map(data -> converter.convert(data))
                .collect(Collectors.toList());

        places.forEach(place -> place.setFloor(floor));

        floor.setPlaces(places);

        try {
            String floorConfig = objectMapper.writeValueAsString(request.getConfigurationList());
            floor.setFloorConfiguration(floorConfig);
        } catch (JsonProcessingException e) {
            throw new ConverterException(e.getMessage());
        }

        return floor;
    }
}
