package kr.co.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.todo.service.AppService;
import kr.co.todo.vo.TodoVO;
import lombok.extern.log4j.Log4j2;

@Log4j2
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AppController {
	
	@Autowired
	private AppService service;
	
	@GetMapping("todos")
	public List<TodoVO> selectTodos() {
		return service.selectTodos();
	}
	
	@PostMapping("todo")
	public TodoVO todo(@RequestBody TodoVO vo) {
		log.info("content : " + vo.getContent());
		service.insertTodo(vo);
		return vo;
	}
	
	@DeleteMapping("remove")
	public void deleteTodo(int no) {
		log.info("no : " + no);
		service.deleteTodo(no);
	}
	
	@DeleteMapping("clear")
	public void deleteTodos() {
		service.deleteTodos();
	}
	
}
