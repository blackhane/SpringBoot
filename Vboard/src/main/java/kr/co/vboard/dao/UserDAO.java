package kr.co.vboard.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.vboard.vo.TermsVO;
import kr.co.vboard.vo.UserVO;

@Repository
@Mapper
public interface UserDAO {

	public TermsVO selectTerms();
	public int insertUser(UserVO vo);
	
}
