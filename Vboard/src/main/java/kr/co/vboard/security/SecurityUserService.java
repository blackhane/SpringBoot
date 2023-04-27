package kr.co.vboard.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.co.vboard.repository.UserEntity;
import kr.co.vboard.repository.UserRepo;


@Service
public class SecurityUserService implements UserDetailsService {

	@Autowired
	private UserRepo repo;
	
	@Override
	public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserEntity user = repo.findById(username).get();
		
		if(user == null) {
			throw new UsernameNotFoundException(username); 
		}
		
		MyUserDetails myUserDetails = MyUserDetails.builder()
								.user(user)
								.build();
		
		return myUserDetails;
	}

}
