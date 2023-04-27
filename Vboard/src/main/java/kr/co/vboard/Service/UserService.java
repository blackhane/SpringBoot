package kr.co.vboard.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.vboard.dao.UserDAO;
import kr.co.vboard.repository.UserEntity;
import kr.co.vboard.repository.UserRepo;
import kr.co.vboard.vo.TermsVO;
import kr.co.vboard.vo.UserVO;

@Service
public class UserService {
	
	@Autowired
	private UserDAO dao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepo repo;
	
	public TermsVO selectTerms () {
		return dao.selectTerms();
	}

	public int insertUser(UserVO vo) {
		//비밀번호 암호화
		vo.setPass(passwordEncoder.encode(vo.getPass2()));
		return dao.insertUser(vo);
	}
	
	public int countUid(String uid) {
		return repo.countByUid(uid);
	}
}
