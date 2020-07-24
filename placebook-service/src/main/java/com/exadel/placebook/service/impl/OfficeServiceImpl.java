package com.exadel.placebook.service.impl;

import com.exadel.placebook.converter.FloorDtoConverter;
import com.exadel.placebook.converter.OfficeConverter;
import com.exadel.placebook.dao.OfficeDao;
import com.exadel.placebook.model.dto.FloorDto;
import com.exadel.placebook.model.dto.OfficeDto;
import com.exadel.placebook.model.dto.OfficeParams;
import com.exadel.placebook.model.entity.Address;
import com.exadel.placebook.model.entity.Office;
import com.exadel.placebook.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OfficeServiceImpl implements OfficeService {
    @Autowired
    private OfficeDao officeDao;

    @Autowired
    private OfficeConverter officeConverter;

    @Autowired
    private FloorDtoConverter floorConverter;

    @Override
    public OfficeDto addOffice(OfficeParams officeParams) {

        Address address = new Address();
        Office office = new Office();
        address.setAddress(officeParams.getAddress());
        address.setCity(officeParams.getCity());
        address.setCountry(officeParams.getCountry());
        office.setAddress(address);
        office.setWorkTimeEnd(officeParams.getWorktimeEnd());
        office.setWorkTimeStart(officeParams.getWorktimeStart());
        officeDao.save(office);
        return officeConverter.convert(office);
    }

    @Override
    public OfficeDto editOffice(Long officeId, OfficeParams officeParams) {
        Office office = officeDao.find(officeId);
        Address address = office.getAddress();
        address.setCity(officeParams.getCity());
        address.setCountry(officeParams.getCountry());
        address.setAddress(officeParams.getAddress());
        office.setWorkTimeEnd(officeParams.getWorktimeEnd());
        office.setWorkTimeStart(officeParams.getWorktimeStart());
        officeDao.update(office);
        return officeConverter.convert(office);
    }

    @Override
    public List<FloorDto> getFloorsByOfficeId(Long officeId) {
        return officeDao.findFloorsByOfficeId(officeId)
                .stream()
                .map(floor -> floorConverter.convert(floor))
                .collect(Collectors.toList());
    }
}
