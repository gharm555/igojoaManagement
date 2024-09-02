package com.itwill.igojoamanagement.dto;

import com.itwill.igojoamanagement.domain.Authority;
import com.itwill.igojoamanagement.domain.Button;
import com.itwill.igojoamanagement.domain.Tab;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto {

    private String authorityName;
    private String accessibleTab;
    private String accessibleButton;

}
