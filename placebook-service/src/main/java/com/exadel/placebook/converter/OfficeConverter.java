package com.exadel.placebook.converter;

import com.exadel.placebook.model.dto.OfficeDto;
import com.exadel.placebook.model.dto.UserDto;
import com.exadel.placebook.model.entity.Office;
import com.exadel.placebook.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class OfficeConverter {
    public OfficeDto convert(Office of) {
        OfficeDto f = new OfficeDto();
        f.setId(of.getId());
        f.setAdress(of.getAddress());
        f.setWorktimeStart(of.getWorkTimeStart());
        f.setWorktimeEnd(of.getWorkTimeEnd());
        return f;
    }
}
