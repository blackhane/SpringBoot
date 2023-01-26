package kr.co.farmstory.repository;

import kr.co.farmstory.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    public int countByUid(String uid);
    public int countByNick(String nick);
}
