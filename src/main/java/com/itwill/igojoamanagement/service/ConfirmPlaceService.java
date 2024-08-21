package com.itwill.igojoamanagement.service;

import com.itwill.igojoamanagement.dto.ConfirmPlaceNameDto;
import com.itwill.igojoamanagement.repository.ConfirmPlaceRepoisitory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ConfirmPlaceService {

    @Autowired
    private ConfirmPlaceRepoisitory confirmPlaceRepoisitory;

    public List<ConfirmPlaceNameDto> confirmRead(){
        // QueryDsl 메서드를 직접 호출합니다.
        return confirmPlaceRepoisitory.findByConfirmPlaceName("");
    }
}
