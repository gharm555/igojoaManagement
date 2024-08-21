package com.itwill.igojoamanagement.service;

import com.itwill.igojoamanagement.domain.Place;
import com.itwill.igojoamanagement.dto.PlaceNameDto;
import com.itwill.igojoamanagement.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class PlaceService {

    @Autowired
    private  PlaceRepository placeRepo;

    @Transactional(readOnly = true)
    public List<PlaceNameDto> read() {
        List<Place> list = placeRepo.findAll();
        log.info("Place 엔티티 개수: {}", list.size());

        List<PlaceNameDto> placeNames = list.stream()
                .map(PlaceNameDto::fromEntity)
                .collect(Collectors.toList());

        log.info("PlaceNameDto 변환 후 개수: {}", placeNames.size());
        return placeNames;
    }

}
