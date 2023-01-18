package kr.co.sboard.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.sboard.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {

	public int countByUid(String uid);
	public int countByNick(String nick);
	
}
