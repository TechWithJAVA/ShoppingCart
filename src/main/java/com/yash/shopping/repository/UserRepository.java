package com.yash.shopping.repository;

import com.yash.shopping.model.User;

public interface UserRepository {

	User findById(Long mobileNo);

	User save(User user);

}
