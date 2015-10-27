package com.rhcloud.msdm.conference.repository;


import com.rhcloud.msdm.conference.domain.entities.Messages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessagesRepository extends JpaRepository<Messages, Integer> {
}
