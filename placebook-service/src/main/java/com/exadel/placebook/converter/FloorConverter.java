package com.exadel.placebook.converter;

import com.exadel.placebook.model.dto.FloorDto;
import com.exadel.placebook.model.entity.Floor;
import org.springframework.stereotype.Component;

@Component
public class FloorConverter {
    public FloorDto convert(Floor floor) {
        FloorDto dto = new FloorDto();
        dto.setFloorConfiguration(floor.getFloorConfiguration());
        dto.setFloorNumber(floor.getFloorNumber());
        dto.setId(floor.getId());

        return dto;
    }
}
