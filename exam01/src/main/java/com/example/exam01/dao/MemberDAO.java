package com.example.exam01.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.exam01.dto.Member;

public interface MemberDAO extends MongoRepository<Member, String> {
    
    // 아이디 찾는 메소드
    Member findByUserid(String userid);

    // 닉네임 찾는 메소드
    Member findByNickname(String nickname);
    
    // 이메일 찾는 메소드
    Member findByEmail(String email);

    // 아이디중복확인 하는 코드
    default boolean checkId(String userid) {
        return findByUserid(userid) != null;
    }
    
    // 닉네임중복확인 하는 코드
    default boolean checkNick(String nickname) {
        return findByNickname(nickname) != null;
    }
    
    // 이메일중복확인 하는 코드
    default boolean checkEmail(String email) {
        return findByEmail(email) != null;
    }
}
