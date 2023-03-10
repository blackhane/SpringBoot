package kr.co.ch07.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ch07.dao.User2DAO;
import kr.co.ch07.repository.User2Repository;
import kr.co.ch07.vo.User2VO;

@Service
public class User2Service {

	@Autowired
	private User2DAO dao;
	
	@Autowired
	private User2Repository repo;
	
	public void insertUser2(User2VO vo) {
		//dao.insertUser2(vo);
		repo.save(vo);
	}
	public User2VO selectUser2(String uid) {
		//return dao.selectUser2(uid);
		return repo.findById(uid).get();
	}
	public List<User2VO> selectUser2s() {
		//return dao.selectUser2s();
		return repo.findAll();
	}
	public void updateUser2(User2VO vo) {
		//dao.updateUser2(vo);
		repo.save(vo);
	}
	public void deleteUser2(String uid) {
		//dao.deleteUser2(uid);
		repo.deleteById(uid);
	}
	
}
