package com.dao;

import java.util.List;

import com.form.FReport;

public interface ReportDao {

	List<FReport> getAllInstitution();	
	List<FReport> getDepartmentByInstitutionId(Integer institutionId);
	List<FReport> getAllWorkgroup();
	List<FReport> getWorkgroupByDepartmentId(Integer departmentId);
	List<FReport> getAllDepartment();
	List<FReport> getAllLiveAppointment();
	List<FReport> getAllVisitType();
	List<FReport> getAllListUser();
	
	FReport getInstitutionById(Integer institutionId);	
	FReport getWorkgroupById(Integer workgroupId);	
	FReport getDepartmentById(Integer departmentId);	
	FReport getLiveAppointmentById(Integer liveAppointmentId);	
	
	List<FReport> getAllReports1(FReport beanform);
	List<FReport> getAllReports2(FReport beanform);
	List<FReport> getAllReports3(FReport beanform);
	
	List<FReport> getOpsDashboard(FReport beanform);	
	List<FReport> getJourneyReversal(FReport beanform);	
	List<FReport> getAllRegistrationWaitTimeWTR1(FReport beanform);	
	List<FReport> getAllWaitTimeWTR2(FReport beanform);
	List<FReport> getConsultWaitingTime(FReport beanform);	
	List<FReport> getAllWaitTimeWTR4(FReport beanform);
	List<FReport> getAllAuditTrailReport(FReport beanform);
	
	List<FReport> getDepartmentByMemberInstitutionId(Integer[] institutionId);
	List<FReport> getWorkgroupByMemberDepartmentId(Integer[] departmentId);
	
	
	
	
	
	
	
}
