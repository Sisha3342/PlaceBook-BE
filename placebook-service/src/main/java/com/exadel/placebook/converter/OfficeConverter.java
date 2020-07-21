package com.exadel.placebook.converter;

import com.exadel.placebook.model.dto.OfficeDto;
import com.exadel.placebook.model.entity.Office;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OfficeConverter {

    @Autowired
    private AddressConverter addressConverter;

    public OfficeDto convert(Office of) {
        OfficeDto f = new OfficeDto();
        f.setId(of.getId());
        f.setAddress(addressConverter.convert(of.getAddress()));
        f.setWorktimeStart(of.getWorkTimeStart());
        f.setWorktimeEnd(of.getWorkTimeEnd());
        return f;
    }
}