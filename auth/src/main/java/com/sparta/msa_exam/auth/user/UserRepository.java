package com.sparta.msa_exam.auth.user;

import com.sparta.msa_exam.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

// 고유 아이디 부여 대신 로그인 시 사용하는 id라서 String
public interface UserRepository extends JpaRepository<User,String> {
}
