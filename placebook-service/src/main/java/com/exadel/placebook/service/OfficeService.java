package com.exadel.placebook.service;

import com.exadel.placebook.model.dto.OfficeParams;
import com.exadel.placebook.model.dto.OfficeDto;

public interface OfficeService {
    OfficeDto addOffice(OfficeParams officeParams);
    OfficeDto editOffice(Long officeId,OfficeParams officeParams);
    List<FloorDto> getFloorsByOfficeId(Long officeId);
    boolean deleteOffice(Long officeId);
}
