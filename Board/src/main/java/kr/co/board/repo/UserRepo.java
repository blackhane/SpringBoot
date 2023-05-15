package kr.co.board.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.board.entity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, String> {

}
