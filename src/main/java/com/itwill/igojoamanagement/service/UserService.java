package com.itwill.igojoamanagement.service;

import com.itwill.igojoamanagement.domain.BlackUser;
import com.itwill.igojoamanagement.domain.ReportLog;
import com.itwill.igojoamanagement.domain.User;
import com.itwill.igojoamanagement.dto.ChangeReportedNickNameRequest;
import com.itwill.igojoamanagement.dto.ReportedUserDto;
import com.itwill.igojoamanagement.dto.UserDto;
import com.itwill.igojoamanagement.repository.BlackUserRepository;
import com.itwill.igojoamanagement.repository.ReportLogRepository;
import com.itwill.igojoamanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ReportLogRepository reportLogRepository;
    private final BlackUserRepository blackUserRepository;

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
        Page<ReportLog> reportLogs = reportLogRepository.findReportedUser(pageable);
        return reportLogs.map(log -> {
            User user = userRepository.findById(log.getReportedId()).orElseThrow();
            return new ReportedUserDto(log.getLogId(), log.getReportedId(), user.getNickName(), log.getReportedNickname());
        });
    }

    // 신고 취소
    @Transactional
    public void cancelReportNickName(String logId) {
        ReportLog reportLog = reportLogRepository.findById(logId).orElseThrow();

        reportLog.cancelReport();
    }


    // 블랙리스트 유저 정보
    @Transactional(readOnly = true)
    public Page<BlackUser> getBlackList(Pageable pageable) {
        Page<BlackUser> blackList = blackUserRepository.findBlackUsers(pageable);

        if (blackList == null) {
            return null;
        } else {
            return blackList;
        }

    }

    @Transactional
    public String changeReportedNickName(Authentication auth, ChangeReportedNickNameRequest requestBody) {
        // (1) user테이블에서 정보 수정(userId를 이용해서 nickName 수정)
        try {
            String reportedId = requestBody.getReportedId();
            User targetUser = userRepository.findById(reportedId)
                    .orElseThrow(() -> new RuntimeException("User not found: " + reportedId));
            String newNickName = requestBody.getNickName();

            targetUser.setNickName(newNickName);
            userRepository.save(targetUser);

            // (2) reportLogs 테이블의 confirm 컬럼의 값을 "처리완료"로 수정
            String logId = requestBody.getLogId();
            ReportLog reportLog = reportLogRepository.findById(logId).orElseThrow();
            reportLog.confirmReport();

            return "변경 성공";
        } catch (Exception e) {
            return "변경 실패: " + e.getMessage();
        }
    }

    @Transactional
    public boolean reportBlackList(Authentication auth, Map<String, Object> logId) {
        String adminId = auth.getName();
        String usedLogId = (String) logId.get("logId");

        Optional<ReportLog> reportLog = reportLogRepository.findById(usedLogId);

        BlackUser blackUser = BlackUser.builder()
                .userId(reportLog.get().getReportedId())
                .adminId(adminId)
                .reasonCode(reportLog.get().getReasonCode())
                .detail(reportLog.get().getReportedNickname())
                .confirm("블랙리스트")
                .build();

        if (reportLog.isPresent()) {
            blackUserRepository.save(blackUser);
            ReportLog a = reportLog.get();
            a.confirmReport();
            return true;
        }

        return false;
    }

    @Transactional
    public boolean cancelBlacklist(Map<String, Object> requestBody) {
        String userId = (String) requestBody.get("userId");

        BlackUser blackUser = blackUserRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found: " + userId));
        blackUser.confirmCancel();

        if (blackUser.getConfirm().equals("제재철회")) {
            return true;
        }

        return false;
    }

}