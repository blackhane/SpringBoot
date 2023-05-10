package kr.co.board.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.board.vo.TermsVO;
import kr.co.board.vo.UserVO;

@Repository
@Mapper
public interface UserDAO {

	public TermsVO selectTerms();
	
	public int selectUid(String uid);

	public int selectNick(String nick);
	
	public void insertUser(UserVO vo);
	
}
