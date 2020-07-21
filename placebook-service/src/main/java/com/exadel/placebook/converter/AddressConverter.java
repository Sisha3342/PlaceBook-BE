package com.exadel.placebook.converter;

import com.exadel.placebook.model.dto.AddressDto;
import com.exadel.placebook.model.entity.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressConverter {
    public AddressDto convert(Address address) {
        AddressDto addressDto = new AddressDto();
        addressDto.setAddress(address.getAddress());
        addressDto.setCity(address.getCity());
        addressDto.setCountry(address.getCountry());
        return addressDto;
    }
}
