package com.rhcloud.msdm.conference.repository;

import com.rhcloud.msdm.conference.domain.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TicketRepository extends JpaRepository<Ticket, Integer> {

}