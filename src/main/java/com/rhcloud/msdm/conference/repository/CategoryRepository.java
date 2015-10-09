package com.rhcloud.msdm.conference.repository;


import com.rhcloud.msdm.conference.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category, Integer> {

}