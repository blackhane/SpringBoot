package kr.co.sboard.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.co.sboard.Repository.UserRepository;
import kr.co.sboard.entity.UserEntity;


@Service
public class SecurtyUserService implements UserDetailsService {

	@Autowired
	private UserRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		//시큐리티가 얘를 실행해서 인증처리를 함
		
		//DB에 해당 아이디가 있는지 확인
		UserEntity user = repo.findById(username).get();
		
		if(user == null) {
			//회원 데이터 없음
			throw new UsernameNotFoundException(username);
		}
		
		//회원 데이터 있음
		//커스텀
		UserDetails userDts = MyUserDetails.builder().user(user).build();
		
		return userDts;
	}

}
