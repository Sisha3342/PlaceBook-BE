package com.exadel.placebook.service.impl;

import com.exadel.placebook.converter.FloorConverter;
import com.exadel.placebook.converter.OfficeConverter;
import com.exadel.placebook.converter.PlaceConverter;
import com.exadel.placebook.dao.AddressDao;
import com.exadel.placebook.dao.BookingDao;
import com.exadel.placebook.dao.OfficeDao;
import com.exadel.placebook.dao.PlaceDao;
import com.exadel.placebook.exception.FloorException;
import com.exadel.placebook.model.dto.*;
import com.exadel.placebook.model.entity.Address;
import com.exadel.placebook.model.entity.Floor;
import com.exadel.placebook.model.entity.Office;
import com.exadel.placebook.model.entity.Place;
import com.exadel.placebook.model.enums.PlaceStatus;
import com.exadel.placebook.model.exception.EntityNotFoundException;
import com.exadel.placebook.model.sorting.OfficeSorting;
import com.exadel.placebook.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
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

    @Autowired
    private BookingDao bookingDao;

    @Override
    public List<PlaceResponse> getPlacesByFloorId(Long floorId, LocalDateTime timeStart, LocalDateTime timeEnd) {
        List<Place> places = placeDao.findPlacesByFloorId(floorId);
        List<PlaceResponse> result = new LinkedList<>();

        for(Place place: places) {
            long countBookings = bookingDao.countBookingsByPlaceIdAndTimeRange(place.getId(), timeStart, timeEnd);

            PlaceResponse placeResponse = new PlaceResponse();
            placeResponse.setPlaceNumber(place.getPlaceNumber());
            placeResponse.setPlaceId(place.getId());
            placeResponse.setOccupied(countBookings != 0);

            result.add(placeResponse);
        }

        return result;
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
    public List<OfficeDto> getOfficesByCityAndCountry(String city, String country, OfficeSorting officeSorting) {
        List<Office> list = officeDao.findOfficesByCityAndCountry(city, country, officeSorting);
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
    public OfficeDto getOffice(Long officeId) {
        Office office = officeDao.find(officeId);
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
        if (office != null) {
            office.setDeleted(true);
            officeDao.update(office);
            return true;
        }
        return false;

    }

    @Override
    public OfficeDto saveOfficeConfiguration(List<FloorDto> floors, Long officeId) {
        Office office = officeDao.find(officeId);

        if (office == null) {
            throw new EntityNotFoundException(Office.class, officeId);
        }

        editDBOfficeFloors(office, getFloorList(floors));

        return officeConverter.convert(officeDao.update(office));
    }

    private void editDBOfficeFloors(Office office, List<Floor> floorsFromRequest) {
        List<Floor> currentDBFloors = officeDao.findFloorsByOfficeId(office.getId());
        List<Floor> editedDBFloors = new LinkedList<>();

        for (Floor floorFromRequest : floorsFromRequest) {
            Optional<Floor> floorWithSameNumber = currentDBFloors
                    .stream()
                    .filter(floor -> floor.getFloorNumber()
                            .equals(floorFromRequest.getFloorNumber()))
                    .findAny();

            if (floorWithSameNumber.isPresent()) {
                if (!floorWithSameNumber.get().getFloorConfiguration()
                        .equals(floorFromRequest.getFloorConfiguration())) {
                    floorWithSameNumber.get().setFloorConfiguration(floorFromRequest.getFloorConfiguration());

                    editDBFloorPlaces(floorWithSameNumber.get(), floorFromRequest);
                }

                floorWithSameNumber.get().setHeight(floorFromRequest.getHeight());
                floorWithSameNumber.get().setWidth(floorFromRequest.getWidth());

                editedDBFloors.add(floorWithSameNumber.get());
                currentDBFloors.remove(floorWithSameNumber.get());
            } else {
                editedDBFloors.add(floorFromRequest);
            }
        }

        currentDBFloors.forEach(floor -> {
            floor.setDeleted(true);
            floor.getPlaces().forEach(place -> place.setPlaceStatus(PlaceStatus.INACTIVE));
        });

        editedDBFloors.forEach(floor -> floor.setOffice(office));

        office.setFloors(editedDBFloors);
    }

    private void editDBFloorPlaces(Floor floorFromDB, Floor floorFromRequest) {
        List<Place> currentDBPlaceList = floorFromDB.getPlaces();
        List<Place> editedDBPlaceList = new LinkedList<>();

        for (Place placeFromRequest : floorFromRequest.getPlaces()) {
            Optional<Place> placeWithSameNumber = currentDBPlaceList
                    .stream()
                    .filter(place -> place.getPlaceNumber()
                            .equals(placeFromRequest.getPlaceNumber()))
                    .findAny();

            if (placeWithSameNumber.isPresent()) {
                currentDBPlaceList.remove(placeWithSameNumber.get());

                placeWithSameNumber.get().setPlaceStatus(placeFromRequest.getPlaceStatus());

                editedDBPlaceList.add(placeWithSameNumber.get());
            } else {
                editedDBPlaceList.add(placeFromRequest);
            }
        }

        currentDBPlaceList.forEach(place -> place.setPlaceStatus(PlaceStatus.INACTIVE));

        editedDBPlaceList.forEach(place -> place.setFloor(floorFromDB));

        floorFromDB.setPlaces(editedDBPlaceList);
    }

    private List<Floor> getFloorList(List<FloorDto> floorRequestList) {
        List<Floor> floors = floorRequestList
                .stream()
                .map(dto -> floorConverter.convert(dto))
                .collect(Collectors.toList());

        long uniqueFloorsNumbersSize = floors.stream().map(Floor::getFloorNumber).distinct().count();

        if (uniqueFloorsNumbersSize < floors.size()) {
            throw new FloorException("duplicate floor numbers");
        }

        for(Floor floor: floors) {
            long uniquePlacesNumbersSize = floor.getPlaces().stream()
                    .map(Place::getPlaceNumber).distinct().count();

            if(uniquePlacesNumbersSize < floor.getPlaces().size()) {
                throw new FloorException("duplicate place numbers");
            }
        }

        return floors;
    }
}
