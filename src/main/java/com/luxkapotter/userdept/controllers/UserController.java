package com.luxkapotter.userdept.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.luxkapotter.userdept.entities.User;
import com.luxkapotter.userdept.repositories.UserRepository;

@RestController
@RequestMapping(value="/users")
public class UserController {
	
	@Autowired
	private UserRepository repository;
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<User>> findAll(){
		List<User> users = repository.findAll();
		
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}")
	@ResponseBody
	public ResponseEntity<User> findById(@PathVariable Long id){
		User user = repository.findById(id).get();
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<User> insertUser(@RequestBody User user){
		User result = repository.save(user);
		return new ResponseEntity<User>(result, HttpStatus.CREATED);
	}
	
	@PutMapping
	@ResponseBody
	public ResponseEntity<User> updateUser(@RequestBody User user){
		User result = repository.saveAndFlush(user);
		return new ResponseEntity<User>(result, HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{id}")
	@ResponseBody
	public ResponseEntity<String> deleteUser(@PathVariable Long id) {
		repository.deleteById(id);
		return new ResponseEntity<String>("User removido com sucesso!!", HttpStatus.OK);
	}
}
