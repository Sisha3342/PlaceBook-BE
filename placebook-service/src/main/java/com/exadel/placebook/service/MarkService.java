package com.exadel.placebook.service;

import com.exadel.placebook.model.dto.MarkParams;
import com.exadel.placebook.model.dto.MarkSubmitDto;

public interface MarkService {

   MarkSubmitDto submitMark(Long id,MarkParams markParams);

   MarkSubmitDto getMarksByBookingId(Long bookingId);
}
