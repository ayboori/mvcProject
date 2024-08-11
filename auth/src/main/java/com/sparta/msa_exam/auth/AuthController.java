package com.sparta.msa_exam.auth;

import com.sparta.msa_exam.auth.entity.SignInRequestDto;
import com.sparta.msa_exam.auth.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @Value("${server.port}") // 애플리케이션이 실행 중인 포트를 주입받습니다.
    private String serverPort;

    // GET /auth/signIn?user_id={string}  로그인 API
    @GetMapping("/auth/signIn")
    public ResponseEntity<?> createAuthenticationToken(
            @RequestParam (name= "user_id") String userId,
            @RequestBody SignInRequestDto requestDto){
        // 헤더에 서버 포트 정보 추가
        HttpHeaders headers = new HttpHeaders();
        headers.set("Server-Port", serverPort);

        String token = authService.signIn(userId,requestDto.getPassword());
        return new ResponseEntity<>(new AuthResponse(token),headers, HttpStatus.OK);
    }

    // 회원 가입
    @PostMapping("/auth/signUp")
    public ResponseEntity<?> signUp(@RequestBody User user) {
        // 헤더에 서버 포트 정보 추가
        HttpHeaders headers = new HttpHeaders();
        headers.set("Server-Port", serverPort);

        User createdUser = authService.signUp(user);
        return new ResponseEntity<>(createdUser,headers, HttpStatus.OK);

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class AuthResponse {
        private String access_token;

    }

}