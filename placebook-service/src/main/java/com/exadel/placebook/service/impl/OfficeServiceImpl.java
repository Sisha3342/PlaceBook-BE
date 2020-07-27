package com.exadel.placebook.service.impl;

import com.exadel.placebook.converter.FloorConverter;
import com.exadel.placebook.converter.OfficeConverter;
import com.exadel.placebook.converter.PlaceConverter;
import com.exadel.placebook.dao.AddressDao;
import com.exadel.placebook.dao.OfficeDao;
import com.exadel.placebook.dao.PlaceDao;
import com.exadel.placebook.model.dto.FloorDto;
import com.exadel.placebook.model.dto.OfficeDto;
import com.exadel.placebook.model.dto.OfficeParams;
import com.exadel.placebook.model.dto.PlaceDto;
import com.exadel.placebook.model.entity.Address;
import com.exadel.placebook.model.entity.Office;
import com.exadel.placebook.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
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
    private FloorConverter floorConverter;

    @Autowired
    private PlaceDao placeDao;

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private PlaceConverter placeConverter;

    @Override
    public List<PlaceDto> getPlacesByFloorId(Long floorId) {
        return placeDao.findPlacesByFloorId(floorId)
                .stream()
                .map(place -> placeConverter.convert(place))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllCountries() {
        return addressDao.findAllCountries();
    }

    @Override
    public List<String> getCitiesByCountry(String country) {
        return addressDao.findCitiesByCountry(country);
    }

    @Override
    public List<OfficeDto> getOfficesByCityAndCountry(String city, String country) {
        List<Office> list = officeDao.findOfficesByCityAndCountry(city, country);
        return list.stream().map(officeConverter::convert).collect(Collectors.toList());
    }

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
    public List<PlaceDto> getFreePlacesByFloorIdAndTimeRange(Long floorId, LocalDateTime start, LocalDateTime end) {
        return placeDao.getFreePlacesByFloorIdAndTimeRange(floorId, start, end)
                .stream()
                .map(place -> placeConverter.convert(place))
                .collect(Collectors.toList());
    }

    @Override
    public List<FloorDto> getFloorsByOfficeId(Long officeId) {
        return officeDao.findFloorsByOfficeId(officeId)
                .stream()
                .map(floor -> floorConverter.convert(floor))
                .collect(Collectors.toList());
    }
    @Override
    public boolean deleteOffice(Long officeId) {
        Office office = officeDao.find(officeId);
        if(office!=null) {
            office.setDeleted(true);
            officeDao.update(office);
            return true;
        }
        return false;

    }
}
