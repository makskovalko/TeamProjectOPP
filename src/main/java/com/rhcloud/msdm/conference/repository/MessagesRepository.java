package com.rhcloud.msdm.conference.repository;


import com.rhcloud.msdm.conference.domain.entities.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface MessagesRepository extends JpaRepository<Messages, Integer> {


    @Query(value = "select count(m.id) from messages m where m.organizer_id = ?1", nativeQuery = true)
    Integer getCountMassageForUser(Integer id);

}
