package com.sparta.msa_exam.auth;

import com.sparta.msa_exam.auth.entity.SignInRequestDto;
import com.sparta.msa_exam.auth.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    // GET /auth/signIn?user_id={string}  로그인 API
    @GetMapping("/auth/signIn")
    public ResponseEntity<?> createAuthenticationToken(
            @RequestParam (name= "user_id") String userId,
            @RequestBody SignInRequestDto requestDto){
        String token = authService.signIn(userId,requestDto.getPassword());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    // 회원 가입
    @PostMapping("/auth/signUp")
    public ResponseEntity<?> signUp(@RequestBody User user) {
        User createdUser = authService.signUp(user);
        return ResponseEntity.ok(createdUser);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class AuthResponse {
        private String access_token;
    }

}