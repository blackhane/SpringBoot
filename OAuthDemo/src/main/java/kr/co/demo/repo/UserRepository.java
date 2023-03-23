package kr.co.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.demo.vo.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{

	public UserEntity findByUid(long no);
	
}
