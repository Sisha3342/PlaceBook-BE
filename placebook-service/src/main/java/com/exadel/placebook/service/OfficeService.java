package com.exadel.placebook.service;

import com.exadel.placebook.model.dto.FloorDto;
import com.exadel.placebook.model.dto.OfficeParams;
import com.exadel.placebook.model.dto.OfficeDto;

import java.util.List;

public interface OfficeService {
    OfficeDto addOffice(OfficeParams officeParams);
    OfficeDto editOffice(Long officeId,OfficeParams officeParams);
    List<FloorDto> getFloorsByOfficeId(Long officeId);
}
