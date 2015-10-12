package com.rhcloud.msdm.conference.repository;

import com.rhcloud.msdm.conference.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface UserRepository extends JpaRepository<User, Integer> {

}