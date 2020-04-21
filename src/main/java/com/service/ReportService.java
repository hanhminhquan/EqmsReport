package com.service;

import java.util.List;

import com.form.FReport;

public interface ReportService { 
   
	public List<FReport> getAllInstitution();
	public List<FReport> getAllWorkgroup();
	public List<FReport> getAllDepartment();
	public List<FReport> getAllListUser();
	public List<FReport> getDepartmentByInstitutionId(Integer institutionId);
	public List<FReport> getWorkgroupByDepartmentId(Integer departmentId);
	public List<FReport> getAllLiveAppointment();
	public List<FReport> getAllVisitType();
	public FReport getInstitutionById(Integer institutionId);	
	public FReport getWorkgroupById(Integer workgroupId);	
	public FReport getDepartmentById(Integer departmentId);	
	public FReport getLiveAppointmentById(Integer liveAppointmentId);	
	
	
	public List<FReport> getAllReports1(FReport bean);
	public List<FReport> getAllReports2(FReport bean);
	public List<FReport> getAllReports3(FReport bean);
	public List<FReport> getAllWaitTimeWTR2(FReport bean);
	public List<FReport> getAllRegistrationWaitTimeWTR1(FReport bean);
	public List<FReport> getConsultWaitingTime(FReport bean);	
	public List<FReport> getAllWaitTimeWTR4(FReport bean);	
	public List<FReport> getAllAuditTrailReport(FReport beanform);
	public List<FReport> getOpsDashboard(FReport bean);
	public List<FReport> getJourneyReversal(FReport bean);
	
	public List<FReport> getDepartmentByMemberInstitutionId(Integer[] institutionId);
	public List<FReport> getWorkgroupByMemberDepartmentId(Integer[] departmentId);
	
	
}
