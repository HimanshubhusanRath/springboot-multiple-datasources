package com.hr.microservices;

import com.hr.microservices.entity.User;
import com.hr.microservices.repository.master.UserMasterRepository;
import com.hr.microservices.repository.slave.UserSlaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class MasterSlaveSprignBootApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MasterSlaveSprignBootApplication.class, args);
    }

    @Autowired
    private UserMasterRepository userMasterRepository;

    @Autowired
    private UserSlaveRepository userSlaveRepository;

    /**
     * Loads initial data in both databases for testing purpose
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        User u1 = new User("master-1","blor");
        User u2 = new User("slave-1","hybd");
        userMasterRepository.save(u1);
        userSlaveRepository.save(u2);
    }
}
