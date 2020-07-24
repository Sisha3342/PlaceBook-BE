package com.exadel.placebook.converter;

import com.exadel.placebook.model.dto.MarkSubmitDto;
import com.exadel.placebook.model.entity.PlaceRate;
import org.springframework.stereotype.Component;

@Component
public class MarkConverter {
    public MarkSubmitDto convert(PlaceRate placeRate) {
        MarkSubmitDto markSubmitDto=new MarkSubmitDto();
        markSubmitDto.setFeedBack(placeRate.getFeedback());
        markSubmitDto.setMarkAir(placeRate.getMarkAir());
        markSubmitDto.setMarkCleaning(placeRate.getMarkCleaning());
        markSubmitDto.setMarkLightning(placeRate.getMarkLightning());
        markSubmitDto.setMarkLocation(placeRate.getMarkLocation());
        markSubmitDto.setMarkVolume(placeRate.getMarkVolume());
        return markSubmitDto;
    }
}
