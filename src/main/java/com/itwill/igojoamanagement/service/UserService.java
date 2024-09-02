package com.itwill.igojoamanagement.service;

import com.itwill.igojoamanagement.domain.ReportLog;
import com.itwill.igojoamanagement.domain.RestrictionLog;
import com.itwill.igojoamanagement.domain.User;
import com.itwill.igojoamanagement.dto.ReportedUserDto;
import com.itwill.igojoamanagement.dto.UserDto;
import com.itwill.igojoamanagement.repository.ReportLogRepository;
import com.itwill.igojoamanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ReportLogRepository reportLogRepository;

    // 유저 전체 목록 service
    public Page<UserDto> getUsersIdPage(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users.map(user -> new UserDto(user.getUserId()));
    }

    // New 2024-08-27
    public UserDto getUserDetailsForTeamLeader(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return new UserDto(user.getUserId(), user.getEmail(), user.getPhoneNumber(), user.getNickName());
    }

    public UserDto getUserDetailsForTeamMember(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return new UserDto(user.getUserId(), null, null, user.getNickName());
    }

    public void changeUserNickName(UserDto userDto) {
        User user = userRepository.findById(userDto.getUserId()).orElseThrow(() -> new RuntimeException("해당 유저를 찾을 수 없습니다. 유저아이디: " + userDto.getUserId()));

        user.setNickName(userDto.getNickName());
        userRepository.save(user);
    }

    // 신고 유저 service
    // New / 2024-08-27
    @Transactional(readOnly = true)
    public Page<ReportedUserDto> getReportedUsers(Pageable pageable) {
        Page<ReportLog> reportLogs = reportLogRepository.findByReasonCode(102, pageable);
        return reportLogs.map(log -> {
            User user = userRepository.findById(log.getReportedId()).orElseThrow();
            return new ReportedUserDto(log.getLogId(), log.getReportedId(), user.getNickName(), log.getReportedNickname());
        });
    }

    /// 옛날코드? 2024-09-02
//    public ReportedUserDto getReportedUserDetails(String userId) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        ReportLog reportLog = reportLogRepository.findFirstByReportedIdOrderByReportTimeDesc(userId)
//                .orElseThrow(() -> new RuntimeException("Report log not found"));
//        return new ReportedUserDto(userId, user.getNickName(), reportLog.getReportedNickname());
//    }

    // 신고 취소
    @Transactional
    public void cancelReportNickName(String logId) {
        reportLogRepository.deleteById(logId);
    }


    // 블랙리스트 유저 정보
    @Transactional(readOnly = true)
    public Page<RestrictionLog> getBlackList(Pageable pageable) {
        // (1): 블랙리스트가 있는지 체크
        Page<RestrictionLog> blackList = userRepository.findBlackList(pageable);

        // (2) : 없다면 -> null -> 컨트롤러에서 응답메시지를 없다고 줌

        // (3) : 블랙리스트가 있다면 page 처리해서 return

        if (blackList == null) {
            return null;
        } else {
            return blackList;
        }

    }

}
