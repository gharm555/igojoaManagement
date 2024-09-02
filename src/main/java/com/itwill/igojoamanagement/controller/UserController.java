package com.itwill.igojoamanagement.controller;

import com.itwill.igojoamanagement.domain.RestrictionLog;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.itwill.igojoamanagement.dto.ReportedUserDto;
import com.itwill.igojoamanagement.dto.UserDto;
import com.itwill.igojoamanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/change-nickname")
    public ResponseEntity<?> changeUserNickName(@RequestBody UserDto userDto) {
        log.info("받은 데이터 = {}", userDto);

        try {
            userService.changeUserNickName(userDto);
            return ResponseEntity.ok().body("변경 성공");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("변경 실패");
        }
    }

    // New / 2024-08-27
    @GetMapping("/reported-users")
    @ResponseBody
    public ResponseEntity<Page<ReportedUserDto>> getReportedUsers(
            @PageableDefault(page = 0, size = 10, sort = "reportTime", direction = Sort.Direction.DESC) Pageable pageable) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() &&
                (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_회원_팀장")) ||
                        auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_회원_팀원")))) {
            Page<ReportedUserDto> reportedUsers = userService.getReportedUsers(pageable);
            return ResponseEntity.ok(reportedUsers);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    // New / 2024-08-27
    @GetMapping("/user-management")
    @ResponseBody
    public ResponseEntity<Page<UserDto>> getUserManagement(
            @PageableDefault(page = 0, size = 10, sort = "userId", direction = Sort.Direction.ASC) Pageable pageable) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() &&
                (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_회원_팀장")) ||
                        auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_회원_팀원")))) {
            Page<UserDto> users = userService.getUsersIdPage(pageable);
            return ResponseEntity.ok(users);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    // New 2024-08-27
    @GetMapping("/user-details/{userId}")
    @ResponseBody
    public ResponseEntity<?> getUserDetails(@PathVariable String userId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_회원_팀장"))) {
                UserDto userDetails = userService.getUserDetailsForTeamLeader(userId);
                return ResponseEntity.ok(userDetails);
            } else if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_회원_팀원"))) {
                UserDto userDetails = userService.getUserDetailsForTeamMember(userId);
                return ResponseEntity.ok(userDetails);
            }
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    // 신고 취소
    @DeleteMapping("/cancelReport")
    public ResponseEntity<String> cancelReport(@RequestBody Map<String, Object> logId) {
        log.info("cancelReport(logId: {}", logId);
        String nicknameLog = (String) logId.get("logId");

        userService.cancelReportNickName(nicknameLog);

        return ResponseEntity.ok(nicknameLog);
    }

    // 블랙리스트 매핑
    @GetMapping("/blacklist")
    @ResponseBody
    public ResponseEntity<Page<RestrictionLog>> getBlacklist(
            @PageableDefault(page = 0, size = 10, sort = "reportTime", direction = Sort.Direction.DESC) Pageable pageable) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() &&
                (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_회원_팀장")) ||
                        auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_회원_팀원")) ||
                        auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_리뷰_팀장")) ||
                        auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_명소_팀장")))) {
            Page<RestrictionLog> blackList = userService.getBlackList(pageable);
            return ResponseEntity.ok(blackList);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

}