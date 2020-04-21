//package com.repository;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import com.model.Institution;
//
//public interface InstitutionRepository extends JpaRepository<Institution, Long> {
//	
//	@Query("select s from Institution s order by s.institutionId")
//	public List<Institution> findAllinstitution();
//}