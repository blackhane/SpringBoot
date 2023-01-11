package kr.co.ch07.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ch07.dao.User1DAO;
import kr.co.ch07.repository.User1Repo;
import kr.co.ch07.vo.User1VO;

@Service
public class User1Service {

	@Autowired
	private User1DAO dao;
	
	@Autowired
	private User1Repo repo;
	
	public void insertUser1(User1VO vo) {
		//dao.insertUser1(vo);
		repo.save(vo);
	}
	public User1VO selectUser1(String uid) {
		//User1VO user = dao.selectUser1(uid);
		User1VO user = repo.findById(uid).get();
		return user;
	}
	public List<User1VO> selectUser1s() {
		//List<User1DAO> users = dao.selectUser1s();
		List<User1VO> users = repo.findAll();
		return users; 
		
	}
	public void updateUser1(User1VO vo) {
		//dao.updateUser1(vo);
		repo.save(vo);
	}
	public void deleteUser1(String uid) {
		//dao.deleteUser1(uid);
		repo.deleteById(uid);
	}
	
}
