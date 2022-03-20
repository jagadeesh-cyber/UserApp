package com.upgrad.userApp.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upgrad.userApp.dto.UserDTO;
import com.upgrad.userApp.entities.User;
import com.upgrad.userApp.service.UserService;

@RestController
@RequestMapping(value = "/user_app/v1")
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	ModelMapper modelMapper;

	@PostMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
		User newUser = modelMapper.map(userDTO, User.class);
		User savedUser = userService.createUser(newUser);
		UserDTO savedUserDTO = modelMapper.map(savedUser, UserDTO.class);
		return new ResponseEntity<UserDTO>(savedUserDTO, HttpStatus.CREATED);

	}

	@GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		List<User> userList = userService.getAllUsers();
		List<UserDTO> UserDTOList = new ArrayList<>();
		userList.stream().forEach(user -> UserDTOList.add(modelMapper.map(user, UserDTO.class)));
		return new ResponseEntity<List<UserDTO>>(UserDTOList, HttpStatus.OK);

	}

	@GetMapping(value = "/users/{id}")
	public ResponseEntity<UserDTO> getUser(@PathVariable(name = "id") int id) {
		User user = userService.getUserBasedOnId(id);
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
	}

	@PutMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {
		User userToBeUpdated = modelMapper.map(userDTO, User.class);
		User savedUpdatedUser = userService.updateUser(userToBeUpdated);
		UserDTO savedUserDTO = modelMapper.map(savedUpdatedUser, UserDTO.class);
		return new ResponseEntity<UserDTO>(savedUserDTO, HttpStatus.ACCEPTED);

	}

	@DeleteMapping(value = "/users/{id}")
	public ResponseEntity deleteUserById(@PathVariable(name = "id") int id) {
		User user = userService.getUserBasedOnId(id);
		userService.deleteUser(user);
		return new ResponseEntity(null, HttpStatus.OK);

	}

}