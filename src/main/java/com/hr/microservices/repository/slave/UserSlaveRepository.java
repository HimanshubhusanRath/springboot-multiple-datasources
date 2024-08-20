package com.hr.microservices.repository.slave;

import com.hr.microservices.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSlaveRepository extends JpaRepository<User, Integer> {
    User findByName(final String userName);
}
