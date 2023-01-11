package kr.co.ch07.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ch07.dao.User3DAO;
import kr.co.ch07.repository.User3Repository;
import kr.co.ch07.vo.User3VO;

@Service
public class User3Service {

	@Autowired
	private User3DAO dao;

	@Autowired
	private User3Repository repo;
	
	public void insertUser3(User3VO vo) {
		//dao.insertUser3(vo);
		repo.save(vo);
	}
	
	public User3VO selectUser3(String uid) {
		//return dao.selectUser3(uid);
		return repo.findById(uid).get();
	}
	
	public List<User3VO> selectUser3s() {
		//return dao.selectUser3s();
		return repo.findAll();
	}
	
	public void updateUser3(User3VO vo) {
		//dao.updateUser3(vo);
		repo.save(vo);
	}
	
	public void deleteUser3(String uid) {
		//dao.deleteUser3(uid);
		repo.deleteById(uid);
	}
	
}
