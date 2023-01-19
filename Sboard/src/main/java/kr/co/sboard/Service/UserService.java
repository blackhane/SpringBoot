package kr.co.sboard.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.sboard.Repository.UserRepository;
import kr.co.sboard.dao.UserDAO;
import kr.co.sboard.vo.TermsVO;
import kr.co.sboard.vo.UserVO;

@Service
public class UserService {
	
	@Autowired
	private UserDAO dao;
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public TermsVO selectTerms () {
		return dao.selectTerms();
	}

	public int insertUser(UserVO vo) {
		//비밀번호 암호화
		vo.setPass(passwordEncoder.encode(vo.getPass2()));
		return dao.insertUser(vo);
	}
	
	//아이디 중복체크
	public int countUser(String uid) {
		return repo.countByUid(uid);
	}
	
	//별명 중복체크
	public int countNick(String nick) {
		return repo.countByNick(nick);
	}
	
}
