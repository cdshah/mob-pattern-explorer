package com.mycompany.dbRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycompany.dbEntity.PersonInfo;
@Repository
public interface PersonRepository extends JpaRepository<PersonInfo, Long> {
	
	PersonInfo findByPhoneNumber(String phoneNumber);

}
