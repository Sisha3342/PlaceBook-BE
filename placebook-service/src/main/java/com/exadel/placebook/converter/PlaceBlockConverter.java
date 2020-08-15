package com.exadel.placebook.converter;

import com.exadel.placebook.model.dto.PlaceBlockResponse;
import com.exadel.placebook.model.entity.PlaceBlock;
import org.springframework.stereotype.Component;

@Component
public class PlaceBlockConverter {

    public PlaceBlockResponse convert(PlaceBlock placeBlock) {
        PlaceBlockResponse pbr = new PlaceBlockResponse();

        pbr.setBlockEnd(placeBlock.getBlockEnd());
        pbr.setPlaceId(placeBlock.getPlace().getId());
        pbr.setUserId(placeBlock.getUser().getId());

        return pbr;
    }
}
