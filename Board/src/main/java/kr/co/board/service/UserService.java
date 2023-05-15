package kr.co.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.board.dao.UserDAO;
import kr.co.board.vo.TermsVO;
import kr.co.board.vo.UserVO;

@Service
public class UserService {

	@Autowired
	private UserDAO dao;
	
	@Autowired
	private PasswordEncoder encoder;
	
	public TermsVO selectTerms() {
		return dao.selectTerms();
	}
	
	public int selectUid(String uid) {
		return dao.selectUid(uid);
	}

	public int selectNick(String nick) {
		return dao.selectNick(nick);
	}
	
	public void insertUser(UserVO vo) {
		vo.setPass(encoder.encode(vo.getPass1()));
		dao.insertUser(vo);
	}

}
