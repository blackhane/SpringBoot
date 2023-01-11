package kr.co.ch07.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ch07.dao.User5DAO;
import kr.co.ch07.repository.User5Repository;
import kr.co.ch07.vo.User5VO;

@Service
public class User5Service {

	@Autowired
	private User5DAO dao;
	
	@Autowired
	private User5Repository repo;
	
	public void insertUser5(User5VO vo) {
		//dao.insertUser5(vo);
		repo.save(vo);
	}
	
	public User5VO selectUser5(String uid) {
		//return dao.selectUser5(uid);
		return repo.findById(uid).get();
	}
	
	public List<User5VO> selectUser5s(){
		//return dao.selectUser5s();
		return repo.findAll();
	}
	
	public void updateUser5(User5VO vo) {
		//dao.updateUser5(vo);
		repo.save(vo);
	}
	
	public void deleteUser5(String uid) {
		//dao.deleteUser5(uid);
		repo.deleteById(uid);
	}
}
