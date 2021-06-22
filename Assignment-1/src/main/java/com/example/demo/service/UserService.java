package com.example.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.model.UserPage;
import com.example.demo.model.UserSearchCriteria;
import com.example.demo.repository.UserCriteriaRepository;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
	private  UserRepository userrepository ;
	private  UserCriteriaRepository criteriarepository;
	public UserService(UserRepository userrepository, UserCriteriaRepository criteriarepository) {
		super();
		this.userrepository = userrepository;
		this.criteriarepository = criteriarepository;
	}
	public Page<User>getUser(UserPage userpage,UserSearchCriteria searchcriteria){
		return criteriarepository.findAllfilters(userpage, searchcriteria);
	}
   public User adduser(User user) {
	   return userrepository.save(user);
   }
}
