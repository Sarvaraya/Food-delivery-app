package com.fda.dao;

import java.util.List;

import com.fda.model.User;

public interface UseDAO {

	boolean addUser(User user);

    
    User getUserById(int id);

    
    List<User> getAllUsers();

    
    boolean updateUser(User user);

    
    boolean deleteUser(int id);

    
    


	boolean registerUser(User user);


	boolean validUser(String usernameOrEmail, String password);


	User getUserByUsernameOrEmail(String usernameOrEmail);




	

}
