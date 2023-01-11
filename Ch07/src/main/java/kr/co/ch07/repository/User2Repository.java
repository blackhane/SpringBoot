package kr.co.ch07.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.ch07.vo.User2VO;

public interface User2Repository extends JpaRepository<User2VO, String> {

}
