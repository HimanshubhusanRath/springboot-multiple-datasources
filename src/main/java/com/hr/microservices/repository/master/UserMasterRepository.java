package com.hr.microservices.repository.master;

import com.hr.microservices.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMasterRepository extends JpaRepository<User, Integer> {
    User findByName(final String userName);

}
