package com.exadel.placebook.model.dto;

import com.exadel.placebook.model.entity.Address;
import com.exadel.placebook.model.enums.Role;
import lombok.Data;


import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class OfficeDto {
    private Long id;
    private Address adress;
    private LocalTime worktimeStart;
    private LocalTime worktimeEnd;
}
