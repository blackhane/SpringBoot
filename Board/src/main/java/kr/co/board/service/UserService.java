package kr.co.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.board.dao.UserDAO;
import kr.co.board.vo.TermsVO;
import kr.co.board.vo.UserVO;

@Service
public class UserService {

	@Autowired
	private UserDAO dao;
	
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
		dao.insertUser(vo);
	}

}
