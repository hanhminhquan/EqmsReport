package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ReportDao;
import com.form.FReport;


@Service(value="ReportService")
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ReportDao reportDao;

	public ReportDao getReportDao() {
		return reportDao;
	}

	public void setReportDao(ReportDao reportDao) {
		this.reportDao = reportDao;
	}

	@Transactional
	public List<FReport> getAllVisitType() {
		return reportDao.getAllVisitType();
	}
	
	@Transactional
	public List<FReport> getAllInstitution() {
		return reportDao.getAllInstitution();
	}
	
	@Transactional
	public List<FReport> getAllWorkgroup() {
		return reportDao.getAllWorkgroup();
	}
	
	@Transactional
	public List<FReport> getAllDepartment() {
		return reportDao.getAllDepartment();
	}
	
	
	@Transactional
	public List<FReport> getDepartmentByInstitutionId(Integer institutionId) {
		return reportDao.getDepartmentByInstitutionId(institutionId);
	}
	
	
	@Transactional
	public List<FReport> getWorkgroupByDepartmentId(Integer departmentId) {
		return reportDao.getWorkgroupByDepartmentId(departmentId);
	}
	
	@Transactional
	public List<FReport> getAllLiveAppointment() {
		return reportDao.getAllLiveAppointment();
	}
	
	@Transactional
	public List<FReport> getAllListUser() {
		return reportDao.getAllListUser();
	}
	
	@Transactional
	public FReport getInstitutionById(Integer institutionId) {
		return reportDao.getInstitutionById(institutionId);
	}
	
	@Transactional
	public FReport getWorkgroupById(Integer workgroupId) {
		return reportDao.getWorkgroupById(workgroupId);
	}
	
	@Transactional
	public FReport getDepartmentById(Integer departmentId) {
		return reportDao.getDepartmentById(departmentId);
	}
	
	@Transactional
	public FReport getLiveAppointmentById(Integer liveAppointmentId) {
		return reportDao.getLiveAppointmentById(liveAppointmentId);
	}
	
	@Transactional
	public List<FReport> getAllReports1(FReport beanForm) {
		return reportDao.getAllReports1(beanForm);
	}
	
	@Transactional
	public List<FReport> getAllReports2(FReport beanForm) {
		return reportDao.getAllReports2(beanForm);
	}
	@Transactional
	public List<FReport> getAllReports3(FReport beanForm) {
		return reportDao.getAllReports3(beanForm);
	}
	
	@Transactional
	public List<FReport> getOpsDashboard(FReport beanForm) {
		return reportDao.getOpsDashboard(beanForm);
	}
	
	@Transactional
	public List<FReport> getJourneyReversal(FReport beanForm) {
		return reportDao.getJourneyReversal(beanForm);
	}
	
	@Transactional
	public List<FReport> getAllRegistrationWaitTimeWTR1(FReport beanForm) {
		return reportDao.getAllRegistrationWaitTimeWTR1(beanForm);
	}
	
	@Transactional
	public List<FReport> getAllWaitTimeWTR2(FReport beanForm) {
		return reportDao.getAllWaitTimeWTR2(beanForm);
	}
	
	@Transactional
	public List<FReport> getConsultWaitingTime(FReport beanForm) {
		return reportDao.getConsultWaitingTime(beanForm);
	}
	
	@Transactional
	public List<FReport> getAllWaitTimeWTR4(FReport beanForm) {
		return reportDao.getAllWaitTimeWTR4(beanForm);
	}
	
	@Transactional
	public List<FReport> getAllAuditTrailReport(FReport beanForm) {
		return reportDao.getAllAuditTrailReport(beanForm);
	}
	
	@Transactional
	public List<FReport> getDepartmentByMemberInstitutionId(Integer[] institutionId) {
		return reportDao.getDepartmentByMemberInstitutionId(institutionId);
	}
	
	@Transactional
	public List<FReport> getWorkgroupByMemberDepartmentId(Integer[] departmentId) {
		return reportDao.getWorkgroupByMemberDepartmentId(departmentId);
	}
	
}
