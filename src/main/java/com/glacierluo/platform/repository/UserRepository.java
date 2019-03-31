package com.glacierluo.platform.repository;

import org.springframework.data.repository.CrudRepository;
import com.glacierluo.platform.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {


//    User findById(Long id);
    User findByName(String name);
}
