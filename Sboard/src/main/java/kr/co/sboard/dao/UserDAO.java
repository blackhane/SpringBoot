package kr.co.sboard.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.sboard.vo.TermsVO;
import kr.co.sboard.vo.UserVO;

@Repository
@Mapper
public interface UserDAO {

	public TermsVO selectTerms();
	public int insertUser(UserVO vo);
	
}
