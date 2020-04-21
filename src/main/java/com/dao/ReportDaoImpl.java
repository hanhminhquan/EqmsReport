package com.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.form.FReport;

@Repository(value = "ReportDao")
public class ReportDaoImpl implements ReportDao {

	

	@Autowired
	private SessionFactory sessionFactory;

	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<FReport> getAllInstitution() {

		Session session = sessionFactory.openSession();
		List<FReport> beansTemp = new ArrayList();
		String sql = " select InstitutionId,InstitutionName from QsoftInstitution order by InstitutionName asc ";
		Query q = session.createSQLQuery(sql);
		List dataObj = q.list();
		for (int i = 0; i < dataObj.size(); i++) {
			Object[] objects = (Object[]) dataObj.get(i);
			FReport bean = new FReport();
			bean.setInstitutionId(Integer.parseInt(objects[0].toString()));
			bean.setInstitutionName(objects[1].toString());
			beansTemp.add(bean);
		}
		session.flush();

		session.close();
		return beansTemp;
	}
	
	public List<FReport> getAllListUser() {

		Session session = sessionFactory.openSession();
		List<FReport> beansTemp = new ArrayList();
		String sql = " select userId,userName from QsoftUser order by userName asc ";
		Query q = session.createSQLQuery(sql);
		List dataObj = q.list();
		for (int i = 0; i < dataObj.size(); i++) {
			Object[] objects = (Object[]) dataObj.get(i);
			FReport bean = new FReport();
			bean.setId(Integer.parseInt(objects[0].toString()));
			bean.setName(objects[1].toString());
			beansTemp.add(bean);
		}
		session.flush();

		session.close();
		return beansTemp;
	}
	
	public List<FReport> getAllVisitType() {

		Session session = sessionFactory.openSession();
		List<FReport> beansTemp = new ArrayList();
		String sql = " select visitTypeId,visitTypeName,VisitTypeDescription from QsoftVisitType order by visitTypeName asc ";
		Query q = session.createSQLQuery(sql);
		List dataObj = q.list();
		for (int i = 0; i < dataObj.size(); i++) {
			Object[] objects = (Object[]) dataObj.get(i);
			FReport bean = new FReport();
			bean.setVisitTypeId(Integer.parseInt(objects[0].toString()));
			bean.setVisitTypeName(objects[1].toString());
			bean.setVisitTypeDescription(objects[2].toString());
			beansTemp.add(bean);
		}
		session.flush();

		session.close();
		return beansTemp;
	}


	public FReport getInstitutionById(Integer InstitutionId) {

		Session session = sessionFactory.openSession();
		FReport beansTemp = null;
		String sql = " select InstitutionId,InstitutionName from QsoftInstitution where  InstitutionId =:institutionId";
		Query q = session.createSQLQuery(sql);
		q.setParameter("institutionId", InstitutionId);
		List dataObj = q.list();
		if (dataObj != null && dataObj.size() > 0) {
			Object[] objects = (Object[]) dataObj.get(0);
			beansTemp = new FReport();
			beansTemp.setInstitutionId(Integer.parseInt(objects[0].toString()));
			beansTemp.setInstitutionName(objects[1].toString());
		}
		session.flush();
		session.close();
		return beansTemp;
	}
	
	public List<FReport> getWorkgroupByDepartmentId(Integer departmentId) {
		Session session = sessionFactory.openSession();
		List<FReport> beansTemp = new ArrayList();
		FReport bean = null;
		String sql = " select WorkgroupId,WorkgroupName from QsoftWorkgroup where  DepartmentId =:departmentId";
		Query q = session.createSQLQuery(sql);
		q.setParameter("departmentId", departmentId);
		List dataObj = q.list();
		for (int i = 0; i < dataObj.size(); i++) {
			Object[] objects = (Object[]) dataObj.get(i);
			bean = new FReport();
			bean.setWorkgroupId(Integer.parseInt(objects[0].toString()));
			bean.setWorkgroupName(objects[1].toString());
			beansTemp.add(bean);
		}
		session.flush();
		session.close();
		return beansTemp;
	}
	
	public List<FReport> getWorkgroupByMemberDepartmentId(Integer[] departmentId) {
		Session session = sessionFactory.openSession();
		List<FReport> beansTemp = new ArrayList();
		FReport bean = null;
		String sql = " select WorkgroupId,WorkgroupName from QsoftWorkgroup where  DepartmentId in(:departmentId)";
		Query q = session.createSQLQuery(sql);
		q.setParameter("departmentId", departmentId);
		List dataObj = q.list();
		for (int i = 0; i < dataObj.size(); i++) {
			Object[] objects = (Object[]) dataObj.get(i);
			bean = new FReport();
			bean.setWorkgroupId(Integer.parseInt(objects[0].toString()));
			bean.setWorkgroupName(objects[1].toString());
			beansTemp.add(bean);
		}
		session.flush();
		session.close();
		return beansTemp;
	}
	
	
	
	

	public FReport getWorkgroupById(Integer workgroupId) {

		Session session = sessionFactory.openSession();
		FReport beansTemp = null;
		String sql = " select WorkgroupId,WorkgroupName from QsoftWorkgroup where  WorkgroupId =:workgroupId";
		Query q = session.createSQLQuery(sql);
		q.setParameter("workgroupId", workgroupId);
		List dataObj = q.list();
		if (dataObj != null && dataObj.size() > 0) {
			Object[] objects = (Object[]) dataObj.get(0);
			beansTemp = new FReport();
			beansTemp.setWorkgroupId(Integer.parseInt(objects[0].toString()));
			beansTemp.setWorkgroupName(objects[1].toString());
		}

		session.flush();
		session.close();
		return beansTemp;
	}
	
	

	public FReport getDepartmentById(Integer departmentId) {

		Session session = sessionFactory.openSession();
		FReport beansTemp = null;
		String sql = " select DepartmentId,DepartmentName from QsoftDepartment where  DepartmentId =:departmentId";
		Query q = session.createSQLQuery(sql);
		q.setParameter("departmentId", departmentId);
		List dataObj = q.list();
		if (dataObj != null && dataObj.size() > 0) {
			Object[] objects = (Object[]) dataObj.get(0);
			beansTemp = new FReport();
			beansTemp.setDepartmentId(Integer.parseInt(objects[0].toString()));
			beansTemp.setDepartmentName(objects[1].toString());
		}
		session.flush();
		session.close();
		return beansTemp;
	}

	public FReport getLiveAppointmentById(Integer liveApptSocketId) {

		Session session = sessionFactory.openSession();
		FReport beansTemp = null;
		String sql = " select liveApptSocketId,patientName,visitType from LiveAppointment where  liveApptSocketId =:liveApptSocketId";
		Query q = session.createSQLQuery(sql);
		q.setParameter("liveApptSocketId", liveApptSocketId);
		List dataObj = q.list();
		if (dataObj != null && dataObj.size() > 0) {
			Object[] objects = (Object[]) dataObj.get(0);
			beansTemp = new FReport();
			beansTemp.setLiveApptSocketId(Integer.parseInt(objects[0]
					.toString()));
			// beansTemp.set (objects[1].toString());
		}
		session.flush();
		session.close();
		return beansTemp;
	}

	public List getAllWorkgroup() {
		// Reading the records from the table
		Session session = sessionFactory.openSession();
		String sql = "select WorkgroupId,WorkgroupName from QsoftWorkgroup order by WorkgroupName asc ";
		Query q = session.createSQLQuery(sql);
		List dataObj = q.list();
		List beansTemp = new ArrayList();
		for (int i = 0; i < dataObj.size(); i++) {
			Object[] objects = (Object[]) dataObj.get(i);
			FReport bean = new FReport();
			bean.setWorkgroupId(Integer.parseInt(objects[0].toString()));
			bean.setWorkgroupName(objects[1].toString());
			beansTemp.add(bean);
		}
		// session.flush is used for clear cache in the session
		session.flush();
		// it will close the particular session after completing the process
		session.close();
		return beansTemp;
	}
	
	
	
	public List<FReport> getDepartmentByMemberInstitutionId(Integer[] institutionId) {
		// Reading the records from the table
		Session session = sessionFactory.openSession();
		String sql = "select DepartmentId,DepartmentName from QsoftDepartment where InstitutionId in(:institutionId) order by DepartmentName";
		Query q = session.createSQLQuery(sql);
		q.setParameterList("institutionId", institutionId);
		List dataObj = q.list();
		List beansTemp = new ArrayList();
		for (int i = 0; i < dataObj.size(); i++) {
			Object[] objects = (Object[]) dataObj.get(i);
			FReport bean = new FReport();
			bean.setId(Integer.parseInt(objects[0].toString()));
			bean.setName(objects[1].toString());
			beansTemp.add(bean);
		}
		// session.flush is used for clear cache in the session
		session.flush();
		// it will close the particular session after completing the process
		session.close();
		return beansTemp;
	}

	
	public List getDepartmentByInstitutionId(Integer institutionId) {
		// Reading the records from the table
		Session session = sessionFactory.openSession();
		String sql = "select DepartmentId,DepartmentName from QsoftDepartment where InstitutionId =:institutionId order by DepartmentName";
		Query q = session.createSQLQuery(sql);
		q.setParameter("institutionId", institutionId);
		List dataObj = q.list();
		List beansTemp = new ArrayList();
		for (int i = 0; i < dataObj.size(); i++) {
			Object[] objects = (Object[]) dataObj.get(i);
			FReport bean = new FReport();
			bean.setDepartmentId(Integer.parseInt(objects[0].toString()));
			bean.setDepartmentName(objects[1].toString());
			beansTemp.add(bean);
		}
		// session.flush is used for clear cache in the session
		session.flush();
		// it will close the particular session after completing the process
		session.close();
		return beansTemp;
	}


	public List getAllDepartment() {
		// Reading the records from the table
		Session session = sessionFactory.openSession();
		String sql = "select DepartmentId,DepartmentName from QsoftDepartment order by DepartmentName";
		Query q = session.createSQLQuery(sql);
		List dataObj = q.list();
		List beansTemp = new ArrayList();
		for (int i = 0; i < dataObj.size(); i++) {
			Object[] objects = (Object[]) dataObj.get(i);
			FReport bean = new FReport();
			bean.setDepartmentId(Integer.parseInt(objects[0].toString()));
			bean.setDepartmentName(objects[1].toString());
			beansTemp.add(bean);
		}
		// session.flush is used for clear cache in the session
		session.flush();
		// it will close the particular session after completing the process
		session.close();
		return beansTemp;
	}

	public List getAllLiveAppointment() {
		// Reading the records from the table
		Session session = sessionFactory.openSession();
		String sql = "select liveApptSocketId,patientName,visitType from LiveAppointment order by patientName";
		Query q = session.createSQLQuery(sql);
		List dataObj = q.list();
		List beansTemp = new ArrayList();
		for (int i = 0; i < dataObj.size(); i++) {
			Object[] objects = (Object[]) dataObj.get(i);
			FReport bean = new FReport();
			if (objects[2] != null) {
				bean.setLiveApptSocketId(Integer.parseInt(objects[0].toString()));
				// bean.setPatientName(objects[1].toString());
				bean.setVisitType(objects[2] != null ? objects[2].toString()
						: "");
				beansTemp.add(bean);
			}
		}
		// session.flush is used for clear cache in the session
		session.flush();
		// it will close the particular session after completing the process
		session.close();
		return beansTemp;
	}
	
	
	public String getDistinctName(String dataValue) {
		String result = "";
		if (dataValue != null && !dataValue.equals("")){
			String[] dataValueTemp =  dataValue.split("#");
			for (int i = 0; i < dataValueTemp.length; i++){
				String resultCheck = "," + result + ",";
				if (!result.equals("") && resultCheck.indexOf("," + dataValueTemp[i] + ",") < 0){
						result += ",";
						result += dataValueTemp[i]; 
				}else if (result.equals("")) {
					result += dataValueTemp[i]; 
				}
			}			
		}
		return result;
	}

	public List getAllReports1(FReport beanForm) {
		// Reading the records from the table
		Session session = sessionFactory.openSession();	
		String whereInstitution = "";
		if (beanForm.getInstitutionId() != null
				&& beanForm.getInstitutionId() > 0) {			
			whereInstitution = " and inton.InstitutionId = " + beanForm.getInstitutionId();
		}
		
		String whereDep = "";
		if (beanForm.getDepartmentId() != null
				&& beanForm.getDepartmentId() > 0) {
			whereDep = " and dep.DepartmentId = " + beanForm.getDepartmentId();
		}
		
		String whereWgr = "";
		if (beanForm.getWorkgroupId() != null && beanForm.getWorkgroupId() > 0) {
			whereWgr = " and grp.WorkgroupId = " + beanForm.getWorkgroupId();
		}
		
		String SQL2 = "";		
		SQL2 += " (SELECT  ### FROM LiveTransactionQueue liv left join  QsoftWorkgroup grp on  liv.WorkgroupId = grp.WorkgroupId  left join QsoftDepartment dep on  dep.DepartmentId = grp.DepartmentId";
		SQL2 += " left join QsoftInstitution inton on  inton.InstitutionId = dep.InstitutionId where v.visitId = liv.visitId " + whereInstitution + whereDep + whereWgr + ") ";
		
		String sql = " Select v.QueueNo,v.CustomerIc,v.CustomerName, poi.VisitType,poi.ApptTimestamp,v.RegistrationTime ";			
		       sql += " ,poi.vipNormal ";
		       sql += " , (SELECT STRING_AGG (tus.StatusDescription, '#') AS test  FROM LiveTransactionQueue liv left join QsoftStatus tus on  liv.QueueStatusId = tus.QueueStatusId where v.visitId = liv.visitId) statusDescription ";
		       sql += ",(SELECT STRING_AGG (grp.WorkgroupName, '#') AS test  FROM LiveTransactionQueue liv left join QsoftWorkgroup grp on  liv.WorkgroupId = grp.WorkgroupId where v.visitId = liv.visitId " + whereWgr + ") WorkgroupName ";
		       sql += " ,(SELECT STRING_AGG (dep.DepartmentName, '#') AS test  FROM LiveTransactionQueue liv left join QsoftWorkgroup grp on  liv.WorkgroupId = grp.WorkgroupId  left join QsoftDepartment dep on  dep.DepartmentId = grp.DepartmentId where v.visitId = liv.visitId " + whereDep + whereWgr + ") DepartmentName ";		
		       sql += "," + SQL2.replaceAll("###", "STRING_AGG (inton.InstitutionName, '#') AS test");
		       sql += "  from LiveTransactionVisit v  left join LiveAppointment  poi on v.CustomerIc = poi.PatientNric "; 		
		
		sql += " where 1=1 and v.visitId in (" + SQL2.replaceAll("###", "liv.visitId") + ")";
		sql += " order by  poi.vipNormal desc,v.QueueNo asc ";
        //System.out.println(sql);  
		Query q = session.createSQLQuery(sql);
		List dataObj = q.list();
		List beansTemp = new ArrayList();
		Date dateCurrent = new FReport().getCurrentDate();
		for (int i = 0; i < dataObj.size(); i++) {
			Object[] objects = (Object[]) dataObj.get(i);
			FReport bean = new FReport();
			bean.setQueueNo(objects[0] != null ? objects[0].toString() : "");
			bean.setCustomerIc(objects[1] != null ? objects[1].toString() : "");
			bean.setCustomerName(objects[2] != null ? objects[2].toString()
					: "");
			bean.setVisitType(objects[3] != null ? objects[3].toString() : "");
			bean.setApptTimestamp(objects[4] != null ? objects[4].toString()
					: "");
			bean.setApptTimestamp(!bean.getApptTimestamp().equals("") ? bean
					.dateToStringUtil(bean.stringToDate(
							bean.getApptTimestamp(), "yyyy-MM-dd HH:mm:ss"),
							"dd-MM-yyyy HH:mm:ss") : "");
			bean.setRegistrationTime(objects[5] != null ? objects[5].toString()
					: "");
			bean.setRegistrationTime(!bean.getRegistrationTime().equals("") ? bean
					.dateToStringUtil(bean.stringToDate(
							bean.getRegistrationTime(), "yyyy-MM-dd HH:mm:ss"),
							"dd-MM-yyyy HH:mm:ss") : "");			
			bean.setVipNormal(objects[6] != null ? objects[6].toString() : "");
			bean.setStatusDescription(getDistinctName(objects[7] != null ? objects[7].toString() : ""));
			bean.setWorkgroupName(getDistinctName(objects[8] != null ? objects[8].toString() : ""));
			bean.setDepartmentName(getDistinctName(objects[9] != null ? objects[9].toString() : ""));//	
			bean.setInstitutionName(getDistinctName(objects[10] != null ? objects[10].toString() : ""));//	
			bean.setExtractedDate(bean.dateToStringUtil(dateCurrent,
					"dd-MM-yyyy HH:mm:ss"));
			beansTemp.add(bean);
		}
		// session.flush is used for clear cache in the session
		session.flush();
		// it will close the particular session after completing the process
		session.close();
		return beansTemp;
	}

	public List getAllReports2(FReport beanForm) {
		// Reading the records from the table
		Session session = sessionFactory.openSession();
		String whereInstitution = "";
		if (beanForm.getInstitutionId() != null
				&& beanForm.getInstitutionId() > 0) {			
			whereInstitution = " and inton.InstitutionId = " + beanForm.getInstitutionId();
		}
		
		String SQL2 = "";		
		SQL2 += " (SELECT  ### FROM LiveTransactionQueue liv left join  QsoftWorkgroup grp on  liv.WorkgroupId = grp.WorkgroupId  left join QsoftDepartment dep on  dep.DepartmentId = grp.DepartmentId";
		SQL2 += " left join QsoftInstitution inton on  inton.InstitutionId = dep.InstitutionId where v.visitId = liv.visitId " + whereInstitution + ") ";
		
		String sql = " Select v.QueueNo,v.CustomerIc,v.CustomerName, poi.VisitType,poi.ApptTimestamp,v.RegistrationTime ";			
	       sql += " ,poi.vipNormal ";
	       sql += " , (SELECT STRING_AGG (tus.StatusDescription, '#') AS test  FROM LiveTransactionQueue liv left join QsoftStatus tus on  liv.QueueStatusId = tus.QueueStatusId where v.visitId = liv.visitId) statusDescription ";
	       sql += ", " + SQL2.replaceAll("###", "STRING_AGG (grp.WorkgroupName, '#') AS test");	    
	       sql += ", " + SQL2.replaceAll("###", "STRING_AGG (dep.DepartmentName, '#') AS test") + " as departmentName";
	       sql += ", " + SQL2.replaceAll("###", "STRING_AGG (inton.institutionCode, '#') AS test") + " as institutionCode";
	       sql += "  from LiveTransactionVisit v  left join LiveAppointment  poi on v.CustomerIc = poi.PatientNric "; 		
	
	sql += " where 1=1 and v.visitId in (" + SQL2.replaceAll("###", "liv.visitId") + ")";
	sql += " order by  poi.vipNormal desc ,  v.QueueNo asc ";
	
	    System.out.println(sql);	
		Query q = session.createSQLQuery(sql);
		List dataObj = q.list();
		List beansTemp = new ArrayList();
		Date dateCurrent = new FReport().getCurrentDate();
		for (int i = 0; i < dataObj.size(); i++) {
			Object[] objects = (Object[]) dataObj.get(i);
			FReport bean = new FReport();
			bean.setQueueNo(objects[0] != null ? objects[0].toString() : "");
			bean.setCustomerIc(objects[1] != null ? objects[1].toString() : "");
			bean.setCustomerName(objects[2] != null ? objects[2].toString()	: "");
			bean.setVisitType(objects[3] != null ? objects[3].toString() : "");
			bean.setApptTimestamp(objects[4] != null ? objects[4].toString(): "");
			bean.setApptTimestamp(!bean.getApptTimestamp().equals("") ? bean.dateToStringUtil(bean.stringToDate(bean.getApptTimestamp(), "yyyy-MM-dd HH:mm:ss"),"dd-MM-yyyy HH:mm:ss") : "");
			bean.setRegistrationTime(objects[5] != null ? objects[5].toString(): "");
			bean.setRegistrationTime(!bean.getRegistrationTime().equals("") ? bean.dateToStringUtil(bean.stringToDate(bean.getRegistrationTime(), "yyyy-MM-dd HH:mm:ss"),"dd-MM-yyyy HH:mm:ss") : "");			
			bean.setVipNormal(objects[6] != null ? objects[6].toString() : "");
			bean.setStatusDescription(getDistinctName(objects[7] != null ? objects[7].toString() : ""));
			bean.setWorkgroupName(getDistinctName(objects[8] != null ? objects[8].toString() : ""));
			bean.setDepartmentName(getDistinctName(objects[9] != null ? objects[9].toString() : ""));//
			bean.setInstitutionCode(getDistinctName(objects[10] != null ? objects[10].toString() : ""));//
			bean.setExtractedDate(bean.dateToStringUtil(dateCurrent,
					"dd-MM-yyyy HH:mm:ss"));
			beansTemp.add(bean);
		}
		// session.flush is used for clear cache in the session
		session.flush();
		// it will close the particular session after completing the process
		session.close();
		return beansTemp;
	}

	public List getAllReports3(FReport beanForm) {
		// Reading the records from the table
		Session session = sessionFactory.openSession();
		String sql = " select v.QueueNo,v.CustomerIc, poi.VisitType,inton.InstitutionName , dep.DepartmentName,grp.WorkgroupName,poi.ApptTimestamp,";
		sql += " v.RegistrationTime, liv.WaitingTimeStart, liv.WaitingTimeEnd,DATEDIFF(ss,liv.WaitingTimeStart,liv.WaitingTimeEnd) as WaitTime ,";
		sql += " liv.ServingTimeStart,liv.ServingTimeEnd,DATEDIFF(ss,liv.ServingTimeStart,liv.ServingTimeEnd) as ServingTime ,poi.SapBapiActRemark";
		sql += " from LiveTransactionVisit v left join LiveTransactionQueue  liv on v.visitId = liv.visitId";
		sql += " left join LiveAppointment  poi on v.CustomerIc = poi.PatientNric ";
		sql += " left join QsoftStatus tus on  liv.QueueStatusId = tus.QueueStatusId";
		sql += " left join QsoftWorkgroup grp on  liv.WorkgroupId = grp.WorkgroupId ";
		sql += " left join QsoftDepartment dep on  dep.DepartmentId = grp.DepartmentId ";
		sql += " left join QsoftInstitution inton on  inton.InstitutionId = dep.InstitutionId ";
		sql += " where 1=1 ";

		if (beanForm.getStartDate() != null
				&& !beanForm.getStartDate().equals("")
				&& beanForm.getEndDate() != null
				&& !beanForm.getEndDate().equals("")) {
			sql += " and v.RegistrationTime >= '" + beanForm.getStartDate()
					+ " 00:00:00' AND  v.RegistrationTime <= '"
					+ beanForm.getEndDate() + " 23:59:59'";

		} else if (beanForm.getStartDate() != null
				&& !beanForm.getStartDate().equals("")) {
			sql += "  and v.RegistrationTime >= '" + beanForm.getStartDate()
					+ " 00:00:00' AND  v.RegistrationTime <= '"
					+ beanForm.getStartDate() + " 23:59:59'";
		}

		if (beanForm.getVisitType() != null
				&& !beanForm.getVisitType().equals("")) {
			sql += " and poi.visitType =:visitType";
		}

		if (beanForm.getInstitutionId() != null
				&& beanForm.getInstitutionId() > 0) {
			sql += " and inton.InstitutionId =:institutionId";
		}

		if (beanForm.getDepartmentId() != null
				&& beanForm.getDepartmentId() > 0) {
			sql += " and dep.departmentId =:departmentId";
		}

		if (beanForm.getWorkgroupId() != null && beanForm.getWorkgroupId() > 0) {
			sql += " and grp.workgroupId =:workgroupId";
		}
		

		
		if (beanForm.getViewSelection() == 1){
			sql += " order by inton.InstitutionName asc ";
			
		}else if (beanForm.getViewSelection() == 2){
			sql += " order by dep.DepartmentName asc ";
			
		}else if (beanForm.getViewSelection() == 3){
			
			sql += " order by grp.WorkgroupName asc ";
		}else if (beanForm.getViewSelection() == 4){
			sql += " order by v.CustomerIc asc ";
		}
		
		Query q = session.createSQLQuery(sql);
		if (beanForm.getVisitType() != null
				&& !beanForm.getVisitType().equals("")) {
			q.setParameter("visitType", beanForm.getVisitType());
		}

		if (beanForm.getInstitutionId() != null
				&& beanForm.getInstitutionId() > 0) {
			q.setParameter("institutionId", beanForm.getInstitutionId());
		}

		if (beanForm.getDepartmentId() != null
				&& beanForm.getDepartmentId() > 0) {
			q.setParameter("departmentId", beanForm.getDepartmentId());
		}

		if (beanForm.getWorkgroupId() != null && beanForm.getWorkgroupId() > 0) {
			q.setParameter("workgroupId", beanForm.getWorkgroupId());
		}
		List dataObj = q.list();
		List beansTemp = new ArrayList();
		Date dateCurrent = new FReport().getCurrentDate();
		for (int i = 0; i < dataObj.size(); i++) {
			Object[] objects = (Object[]) dataObj.get(i);
			FReport bean = new FReport();
			bean.setQueueNo(objects[0] != null ? objects[0].toString() : "");
			bean.setCustomerIc(objects[1] != null ? objects[1].toString() : "");
			bean.setVisitType(objects[2] != null ? objects[2].toString() : "");
			bean.setInstitutionName(objects[3] != null ? objects[3].toString()
					: "");
			bean.setDepartmentName(objects[4] != null ? objects[4].toString()
					: "");
			bean.setWorkgroupName(objects[5] != null ? objects[5].toString()
					: "");
			bean.setApptTimestamp(objects[6] != null ? objects[6].toString()
					: "");
			bean.setApptTimestamp(!bean.getApptTimestamp().equals("") ? bean
					.dateToStringUtil(bean.stringToDate(
							bean.getApptTimestamp(), "yyyy-MM-dd HH:mm:ss"),
							"dd-MM-yyyy HH:mm:ss") : "");
			bean.setRegistrationTime(objects[7] != null ? objects[7].toString()
					: "");
			bean.setRegistrationTime(!bean.getRegistrationTime().equals("") ? bean
					.dateToStringUtil(bean.stringToDate(
							bean.getRegistrationTime(), "yyyy-MM-dd HH:mm:ss"),
							"dd-MM-yyyy HH:mm:ss") : "");
			bean.setWaitingTimeStart(objects[8] != null ? objects[8].toString()
					: "");
			bean.setWaitingTimeStart(!bean.getWaitingTimeStart().equals("") ? bean
					.dateToStringUtil(bean.stringToDate(
							bean.getWaitingTimeStart(), "yyyy-MM-dd HH:mm:ss"),
							"dd-MM-yyyy HH:mm:ss") : "");
			bean.setWaitingTimeEnd(objects[9] != null ? objects[9].toString()
					: "");
			bean.setWaitingTimeEnd(!bean.getWaitingTimeEnd().equals("") ? bean
					.dateToStringUtil(bean.stringToDate(
							bean.getWaitingTimeEnd(), "yyyy-MM-dd HH:mm:ss"),
							"dd-MM-yyyy HH:mm:ss") : "");
			int wt = Integer.parseInt(objects[10] != null ? objects[10]
					.toString() : "0");
			bean.setWt(bean.changeToHHMMSS(wt));
			bean.setServingTimeStart(objects[11] != null ? objects[11]
					.toString() : "");
			bean.setServingTimeStart(!bean.getServingTimeStart().equals("") ? bean
					.dateToStringUtil(bean.stringToDate(
							bean.getServingTimeStart(), "yyyy-MM-dd HH:mm:ss"),
							"dd-MM-yyyy HH:mm:ss") : "");
			bean.setServingTimeEnd(objects[12] != null ? objects[12].toString()
					: "");
			bean.setServingTimeEnd(!bean.getServingTimeEnd().equals("") ? bean
					.dateToStringUtil(bean.stringToDate(
							bean.getServingTimeEnd(), "yyyy-MM-dd HH:mm:ss"),
							"dd-MM-yyyy HH:mm:ss") : "");
			int st = Integer.parseInt(objects[13] != null ? objects[13]
					.toString() : "0");
			bean.setSt(bean.changeToHHMMSS(st));
			bean.setSapBapiActRemark(objects[14] != null ? objects[14]
					.toString() : "");
			bean.setTurnaroundTime(bean.changeToHHMMSS(st + wt));

			bean.setExtractedDate(bean.dateToStringUtil(dateCurrent,
					"dd-MM-yyyy HH:mm:ss"));
			beansTemp.add(bean);
		}
		// session.flush is used for clear cache in the session
		session.flush();
		// it will close the particular session after completing the process
		session.close();
		return beansTemp;
	}
	
	
	public List getOpsDashboard(FReport beanForm) {
		// Reading the records from the table
		Session session = sessionFactory.openSession();
		String sql = " select count(grp.workgroupId), grp.workgroupId, grp.WorkgroupName ";
		sql += " from QsoftWorkgroup grp left join LiveTransactionQueue liv on grp.WorkgroupId = liv.WorkgroupId";
		sql += " left join LiveTransactionVisit v  on v.visitId = liv.visitId";
		sql += " left join QsoftDepartment dep on  dep.DepartmentId = grp.DepartmentId ";
		sql += " left join QsoftInstitution inton on  inton.InstitutionId = dep.InstitutionId ";
		sql += " where 1=1";

		if (beanForm.getInstitutionId() != null
				&& beanForm.getInstitutionId() > 0) {
			sql += " and inton.InstitutionId =:institutionId";
		}

		if (beanForm.getDepartmentId() != null
				&& beanForm.getDepartmentId() > 0) {
			sql += " and grp.DepartmentId =:departmentId";
		}

		
		sql += " group by grp.workgroupId ,grp.WorkgroupName ";
		System.out.println(sql);
		Query q = session.createSQLQuery(sql);

		if (beanForm.getInstitutionId() != null
				&& beanForm.getInstitutionId() > 0) {
			q.setParameter("institutionId", beanForm.getInstitutionId());
		}

		if (beanForm.getDepartmentId() != null
				&& beanForm.getDepartmentId() > 0) {
			q.setParameter("departmentId", beanForm.getDepartmentId());
		}

		List dataObj = q.list();
		List beansTemp = new ArrayList();
		Date dateCurrent = new FReport().getCurrentDate();
		for (int i = 0; i < dataObj.size(); i++) {
			Object[] objects = (Object[]) dataObj.get(i);
			FReport bean = new FReport();
			bean.setWorkgroupId(objects[1] != null ? Integer.parseInt(objects[1].toString()): 0);
			bean.setWorkgroupName(objects[2] != null ? objects[2].toString() : "");
			bean = getOpsDashboardByWorkgroup(session, bean);
			beansTemp.add(bean);
		}
		// session.flush is used for clear cache in the session
		session.flush();
		// it will close the particular session after completing the process
		session.close();
		return beansTemp;
	}
	
	public FReport getOpsDashboardByWorkgroup(Session session, FReport beanForm) {
		
		String sql = "select (select count(*) from LiveAppointment lap where lap.departmentCode in ( ";
		sql += " select dep.departmentCode  from LiveTransactionQueue liv2  left join QsoftWorkgroup grp on  liv2.WorkgroupId = grp.WorkgroupId ";
		sql += " left join QsoftDepartment dep on  dep.DepartmentId = grp.DepartmentId and grp.WorkgroupId= " + beanForm.getWorkgroupId();
		sql += " ) and lap.PatientNric not in ( select v.CustomerIc  from LiveTransactionVisit v left join LiveTransactionQueue  liv3 on v.visitId = liv3.visitId   left join QsoftWorkgroup grp on  liv3.WorkgroupId = grp.WorkgroupId ";	
		sql += " left join QsoftDepartment dep on  dep.DepartmentId = grp.DepartmentId and grp.WorkgroupId=" + beanForm.getWorkgroupId();
		sql += " )) as QueueNoNotInyet ";		
		sql += ",(select count(*) from LiveTransactionQueue tg1 where (tg1.QueueStatusId = 6) and tg1.WorkgroupId=" + beanForm.getWorkgroupId() + ") as Totalarrivedsofar";
		sql += ",(select count(*) from LiveTransactionQueue tg1 where (tg1.QueueStatusId = 1 or tg1.QueueStatusId = 2) and tg1.WorkgroupId=" + beanForm.getWorkgroupId() + ") as queueNoin";
		sql += " ,(SELECT sum(DATEDIFF(minute,tq.waitingTimeStart,tq.waitingTimeEnd)) FROM LiveTransactionQueue tq where DATEDIFF(minute,tq.waitingTimeStart,tq.waitingTimeEnd) < 15 and tq.WorkgroupId=" + beanForm.getWorkgroupId() + ") as mn5 ";
		sql += ",(SELECT sum(DATEDIFF(minute,tq.waitingTimeStart,tq.waitingTimeEnd)) FROM LiveTransactionQueue tq where DATEDIFF(minute,tq.waitingTimeStart,tq.waitingTimeEnd) >= 15 and  DATEDIFF(minute,tq.waitingTimeStart,tq.waitingTimeEnd) <= 30 and tq.WorkgroupId=" + beanForm.getWorkgroupId() + ") as m530";
		sql += ",( SELECT sum(DATEDIFF(minute,tq.waitingTimeStart,tq.waitingTimeEnd)) FROM LiveTransactionQueue tq where  DATEDIFF(minute,tq.waitingTimeStart,tq.waitingTimeEnd) > 30 and tq.WorkgroupId=" + beanForm.getWorkgroupId() + ") as m31 ";
		sql += ", SUM(DATEDIFF(ss,tq.ServingTimeStart,tq.ServingTimeEnd))/(COUNT(transactionId)) stavg ";
		sql += ", MAX(DATEDIFF(ss,tq.ServingTimeStart,tq.ServingTimeEnd))/(COUNT(transactionId)) maxst ";
		sql += ", (select count(*) from LiveTransactionQueue tg1 where (tg1.QueueStatusId = 3 or tg1.QueueStatusId = 20) and tg1.WorkgroupId=" + beanForm.getWorkgroupId() + ") as ptseen ";
		sql += ",(MAX(DATEDIFF(ss,tq.waitingTimeStart,tq.waitingTimeEnd)) ) maxt ";
		sql += " FROM LiveTransactionQueue tq where  tq.WorkgroupId=" + beanForm.getWorkgroupId();
		System.out.println(sql);
		Query q = session.createSQLQuery(sql);
		List dataObj = q.list();
		if (dataObj != null && dataObj.size() > 0){
			Object[] objects = (Object[]) dataObj.get(0);
			beanForm.setQueueNoNotINyet(objects[0].toString());
			beanForm.setTotalArrivedSofar(objects[1] != null?objects[1].toString():"0");
			beanForm.setQueueNoIn(objects[2] != null?objects[2].toString():"0");
			beanForm.setG5m(objects[3] != null?objects[3].toString():"0");
			beanForm.setG5t30m(objects[4] != null?objects[4].toString():"0");
			beanForm.setG30m(objects[5] != null?objects[5].toString():"0");
			beanForm.setStAvg(objects[6] != null?objects[6].toString():"0");
			beanForm.setStMax(objects[7] != null?objects[7].toString():"0");
			beanForm.setPtSeen(objects[8] != null?objects[8].toString():"0");
			beanForm.setMaxWT(objects[9] != null?objects[9].toString():"0");
		}
		return beanForm;
		
	}
	
	
	
	public List<FReport> getJourneyReversal(FReport beanForm) {
		// Reading the records from the table
		Session session = sessionFactory.openSession();
		String sql = " Select v.CustomerIc, v.CustomerName, inton.InstitutionName, grp.WorkgroupName, poi.VisitType, poi.ApptTimestamp from LiveTransactionVisit v left join LiveTransactionQueue "; 
		sql += " liv on v.visitId = liv.visitId left join LiveAppointment  poi on v.CustomerIc = poi.PatientNric "; 
		sql += " left join QsoftWorkgroup grp on  liv.WorkgroupId = grp.WorkgroupId ";
		sql += " left join QsoftDepartment dep on  dep.DepartmentId = grp.DepartmentId ";
		sql += " left join QsoftInstitution inton on  inton.InstitutionId = dep.InstitutionId where 1=1 ";
		
		if (beanForm.getStartDate() != null  && !beanForm.getStartDate().equals("") && beanForm.getEndDate() != null && !beanForm.getEndDate().equals("")) {
			sql += " and v.RegistrationTime >= '" + beanForm.getStartDate() + " 00:00:00' AND  v.RegistrationTime <= '" + beanForm.getEndDate() + " 23:59:59'";

		} else if (beanForm.getStartDate() != null && !beanForm.getStartDate().equals("")) {
			   sql += "  and v.RegistrationTime >= '" + beanForm.getStartDate()	+ " 00:00:00' AND  v.RegistrationTime <= '" + beanForm.getStartDate() + " 23:59:59'";
		}

		if (beanForm.getInstitutionId() != null && beanForm.getInstitutionId() > 0) {
			sql += " and inton.InstitutionId =:institutionId";
		}

		sql += " order by inton.InstitutionId ,v.CustomerIc, liv.WorkgroupId ";
		System.out.println(sql);
		Query q = session.createSQLQuery(sql);

		if (beanForm.getInstitutionId() != null
				&& beanForm.getInstitutionId() > 0) {
			q.setParameter("institutionId", beanForm.getInstitutionId());
		}

	
		List dataObj = q.list();
		List beansTemp = new ArrayList();
		Date dateCurrent = new FReport().getCurrentDate();
		for (int i = 0; i < dataObj.size(); i++) {
			Object[] objects = (Object[]) dataObj.get(i);
			FReport bean = new FReport();
			bean.setCustomerIc(objects[0] != null ? objects[0].toString(): "");
			bean.setCustomerName(objects[1] != null ? objects[1].toString() : "");
			bean.setInstitutionName(objects[2] != null ? objects[2].toString() : "");
			bean.setWorkgroupName(objects[3] != null ? objects[3].toString() : "");
			bean.setVisitType(objects[4] != null ? objects[4].toString() : "");
			bean.setApptTimestamp(objects[5] != null ? objects[5].toString() : "");
			
			bean.setApptTimestamp(!bean.getApptTimestamp().equals("") ? bean
					.dateToStringUtil(bean.stringToDate(
							bean.getApptTimestamp(), "yyyy-MM-dd HH:mm:ss"),
							"dd-MM-yyyy HH:mm:ss") : "");
			beansTemp.add(bean);
		}	
		session.flush();
		session.close();
		return beansTemp;
	}
	
	
	public List<FReport> getAllRegistrationWaitTimeWTR1(FReport beanForm) {
		
		Session session = sessionFactory.openSession();
		String sql = "  Select v.RegistrationTime,v.QueueNo,v.CustomerIc, poi.VisitType,dep.departmentName,grp.workgroupName,liv.AppointmentTime,";
		sql+= " liv.WaitingTimeStart, liv.ServingTimeStart,ServingTimeEnd,DATEDIFF(minute,liv.waitingTimeStart,liv.waitingTimeEnd) as WaitTime";
		sql+= ",DATEDIFF(minute,liv.ServingTimeStart,liv.ServingTimeEnd) as ServingTime,poi.SapBapiActRemark ";
		sql+= "	 from LiveTransactionVisit v left join LiveTransactionQueue  liv on v.visitId = liv.visitId left join LiveAppointment  poi on v.CustomerIc = poi.PatientNric "; 
		sql+= " left join QsoftStatus tus on  liv.QueueStatusId = tus.QueueStatusId ";
		sql+= " left join QsoftWorkgroup grp on  liv.WorkgroupId = grp.WorkgroupId ";
		sql+= " left join QsoftDepartment dep on  dep.DepartmentId = grp.DepartmentId ";
		sql+= " left join QsoftInstitution inton on  inton.InstitutionId = dep.InstitutionId ";
        sql+= " where 1=1 ";
		
		if (beanForm.getStartDate() != null  && !beanForm.getStartDate().equals("") && beanForm.getEndDate() != null && !beanForm.getEndDate().equals("")) {
			sql += " and v.RegistrationTime >= '" + beanForm.getStartDate() + " 00:00:00' AND  v.RegistrationTime <= '" + beanForm.getEndDate() + " 23:59:59'";

		} else if (beanForm.getStartDate() != null && !beanForm.getStartDate().equals("")) {
			   sql += "  and v.RegistrationTime >= '" + beanForm.getStartDate()	+ " 00:00:00' AND  v.RegistrationTime <= '" + beanForm.getStartDate() + " 23:59:59'";
		}
		
		if (beanForm.getMembersVisitTypeId() != null && !beanForm.getMembersVisitTypeId().equals("")) {
			sql += " and poi.VisitType in ("+ beanForm.getMembersVisitTypeId() +")";
		}
		
		
		if (beanForm.getInstitutionId() != null && beanForm.getInstitutionId() > 0) {
			sql += " and inton.InstitutionId =:institutionId";
		}

		if (beanForm.getDepartmentId() != null
				&& beanForm.getDepartmentId() > 0) {
			sql += " and dep.departmentId =:departmentId";
		}

		if (beanForm.getWorkgroupId() != null && beanForm.getWorkgroupId() > 0) {
			sql += " and grp.workgroupId =:workgroupId";
		}
		
		if (beanForm.getViewSelection() == 1){
			sql += " order by inton.InstitutionName asc ";
			
		}else if (beanForm.getViewSelection() == 2){
			sql += " order by dep.DepartmentName asc ";
			
		}else if (beanForm.getViewSelection() == 3){
			
			sql += " order by v.CustomerIc asc ";
		}
	
		Query q = session.createSQLQuery(sql);

		if (beanForm.getInstitutionId() != null && beanForm.getInstitutionId() > 0) {
			q.setParameter("institutionId", beanForm.getInstitutionId());
		}

		if (beanForm.getDepartmentId() != null 	&& beanForm.getDepartmentId() > 0) {
			q.setParameter("departmentId", beanForm.getDepartmentId());
		}

		if (beanForm.getWorkgroupId() != null && beanForm.getWorkgroupId() > 0) {
			q.setParameter("workgroupId", beanForm.getWorkgroupId());
		}
	
		List dataObj = q.list();
		List beansTemp = new ArrayList();
		Date dateCurrent = new FReport().getCurrentDate();
		for (int i = 0; i < dataObj.size(); i++) {
			Object[] objects = (Object[]) dataObj.get(i);
			FReport bean = new FReport();
			
			bean.setRegistrationTime(objects[0] != null ? objects[0].toString(): "");
			bean.setRegistrationTime(!bean.getRegistrationTime().equals("") ? bean.dateToStringUtil(bean.stringToDate(bean.getRegistrationTime(), "yyyy-MM-dd HH:mm:ss"),"dd-MM-yyyy HH:mm:ss") : "");
			bean.setQueueNo(objects[1] != null ? objects[1].toString(): "");
			bean.setCustomerIc(objects[2] != null ? objects[2].toString(): "");
			bean.setVisitType(objects[3] != null ? objects[3].toString(): "");
			bean.setDepartmentName(objects[4] != null ? objects[4].toString(): "");
			bean.setWorkgroupName(objects[5] != null ? objects[5].toString(): "");
			bean.setApptTimestamp(objects[6] != null ? objects[6].toString(): "");
			bean.setApptTimestamp(!bean.getApptTimestamp().equals("") ? bean.dateToStringUtil(bean.stringToDate(bean.getApptTimestamp(), "yyyy-MM-dd HH:mm:ss"),"dd-MM-yyyy HH:mm:ss") : "");
			bean.setWaitingTimeStart(objects[7] != null ? objects[7].toString(): "");
			bean.setWaitingTimeStart(!bean.getWaitingTimeStart().equals("") ? bean.dateToStringUtil(bean.stringToDate(bean.getWaitingTimeStart(), "yyyy-MM-dd HH:mm:ss"),"dd-MM-yyyy HH:mm:ss") : "");
			bean.setServingTimeStart(objects[8] != null ? objects[8].toString(): "");
			bean.setServingTimeStart(!bean.getServingTimeStart().equals("") ? bean.dateToStringUtil(bean.stringToDate(bean.getServingTimeStart(), "yyyy-MM-dd HH:mm:ss"),"dd-MM-yyyy HH:mm:ss") : "");
			bean.setServingTimeEnd(objects[9] != null ? objects[9].toString(): "");
			bean.setServingTimeEnd(!bean.getServingTimeEnd().equals("") ? bean.dateToStringUtil(bean.stringToDate(bean.getServingTimeEnd(), "yyyy-MM-dd HH:mm:ss"),"dd-MM-yyyy HH:mm:ss") : "");
			int wt = Integer.parseInt(objects[10] != null ? objects[10].toString() : "0");
			bean.setWt(bean.changeToHHMMSS(wt));			
			int st = Integer.parseInt(objects[11] != null ? objects[11].toString() : "0");
			bean.setSt(bean.changeToHHMMSS(st));			
			bean.setSapBapiActRemark(objects[12] != null ? objects[12].toString() : "");			
			beansTemp.add(bean);
		}	
		session.flush();
		session.close();
		return beansTemp;
	}
	
	
	
	public List<FReport> getAllWaitTimeWTR2(FReport beanForm) {
		// Reading the records from the table
		Session session = sessionFactory.openSession();
		String sql = "  Select v.RegistrationTime,v.QueueNo,v.CustomerIc, poi.VisitType,dep.departmentName,grp.workgroupName,liv.AppointmentTime,";
		sql+= " liv.WaitingTimeStart, liv.ServingTimeStart,ServingTimeEnd,DATEDIFF(minute,liv.waitingTimeStart,liv.waitingTimeEnd) as WaitTime";
		sql+= ",DATEDIFF(minute,liv.ServingTimeStart,liv.ServingTimeEnd) as ServingTime,poi.SapBapiActRemark ";
		sql+= "	 from LiveTransactionVisit v left join LiveTransactionQueue  liv on v.visitId = liv.visitId left join LiveAppointment  poi on v.CustomerIc = poi.PatientNric "; 
		sql+= " left join QsoftStatus tus on  liv.QueueStatusId = tus.QueueStatusId ";
		sql+= " left join QsoftWorkgroup grp on  liv.WorkgroupId = grp.WorkgroupId ";
		sql+= " left join QsoftDepartment dep on  dep.DepartmentId = grp.DepartmentId ";
        sql+= " where 1=1 ";
		
		if (beanForm.getStartDate() != null  && !beanForm.getStartDate().equals("") && beanForm.getEndDate() != null && !beanForm.getEndDate().equals("")) {
			sql += " and v.RegistrationTime >= '" + beanForm.getStartDate() + " 00:00:00' AND  v.RegistrationTime <= '" + beanForm.getEndDate() + " 23:59:59'";

		} else if (beanForm.getStartDate() != null && !beanForm.getStartDate().equals("")) {
			   sql += "  and v.RegistrationTime >= '" + beanForm.getStartDate()	+ " 00:00:00' AND  v.RegistrationTime <= '" + beanForm.getStartDate() + " 23:59:59'";
		}
		
		if (beanForm.getMembersVisitTypeId() != null && !beanForm.getMembersVisitTypeId().equals("")) {
			sql += " and poi.VisitType in ("+ beanForm.getMembersVisitTypeId() +")";
		}

	
		if (beanForm.getMembersdepId() != null && !beanForm.getMembersdepId().equals("")) {
			sql += " and dep.DepartmentId in ("+ beanForm.getMembersdepId() +")";
		}
		
		
		if (beanForm.getMembersWorkgroupId() != null && !beanForm.getMembersWorkgroupId().equals("")) {
			sql += " and grp.WorkgroupId in ("+ beanForm.getMembersWorkgroupId() +")";
		}
		
		if (beanForm.getViewSelection() == 4){
			sql += " order by liv.WaitingTimeStart, liv.ServingTimeStart asc ";
		}else if (beanForm.getViewSelection() == 5){
			sql += " order by v.CustomerIc, liv.WorkgroupId asc";
		} 

		
		System.out.println(sql);
		Query q = session.createSQLQuery(sql);

		

	
		List dataObj = q.list();
		List beansTemp = new ArrayList();
		Date dateCurrent = new FReport().getCurrentDate();
		for (int i = 0; i < dataObj.size(); i++) {
			Object[] objects = (Object[]) dataObj.get(i);
			FReport bean = new FReport();
			
			bean.setRegistrationTime(objects[0] != null ? objects[0].toString(): "");
			if (beanForm.getViewSelection() == 5) bean.setRegistrationTime(!bean.getRegistrationTime().equals("") ? bean.dateToStringUtil(bean.stringToDate(bean.getRegistrationTime(), "yyyy-MM-dd HH:mm:ss"),"dd-MM-yyyy HH:mm:ss") : "");
			bean.setQueueNo(objects[1] != null ? objects[1].toString(): "");
			bean.setCustomerIc(objects[2] != null ? objects[2].toString(): "");
			bean.setVisitType(objects[3] != null ? objects[3].toString(): "");
			bean.setDepartmentName(objects[4] != null ? objects[4].toString(): "");
			bean.setWorkgroupName(objects[5] != null ? objects[5].toString(): "");
			bean.setApptTimestamp(objects[6] != null ? objects[6].toString(): "");
			if (beanForm.getViewSelection() == 5) bean.setApptTimestamp(!bean.getApptTimestamp().equals("") ? bean.dateToStringUtil(bean.stringToDate(bean.getApptTimestamp(), "yyyy-MM-dd HH:mm:ss"),"dd-MM-yyyy HH:mm:ss") : "");
			bean.setWaitingTimeStart(objects[7] != null ? objects[7].toString(): "");
			if (beanForm.getViewSelection() == 5) bean.setWaitingTimeStart(!bean.getWaitingTimeStart().equals("") ? bean.dateToStringUtil(bean.stringToDate(bean.getWaitingTimeStart(), "yyyy-MM-dd HH:mm:ss"),"dd-MM-yyyy HH:mm:ss") : "");
			bean.setServingTimeStart(objects[8] != null ? objects[8].toString(): "");
			if (beanForm.getViewSelection() == 5) bean.setServingTimeStart(!bean.getServingTimeStart().equals("") ? bean.dateToStringUtil(bean.stringToDate(bean.getServingTimeStart(), "yyyy-MM-dd HH:mm:ss"),"dd-MM-yyyy HH:mm:ss") : "");
			bean.setServingTimeEnd(objects[9] != null ? objects[9].toString(): "");
			if (beanForm.getViewSelection() == 5) bean.setServingTimeEnd(!bean.getServingTimeEnd().equals("") ? bean.dateToStringUtil(bean.stringToDate(bean.getServingTimeEnd(), "yyyy-MM-dd HH:mm:ss"),"dd-MM-yyyy HH:mm:ss") : "");
			int wt = Integer.parseInt(objects[10] != null ? objects[10].toString() : "0");
			bean.setWt(bean.changeToHHMMSS(wt));			
			int st = Integer.parseInt(objects[11] != null ? objects[11].toString() : "0");
			bean.setSt(bean.changeToHHMMSS(st));			
			bean.setSapBapiActRemark(objects[12] != null ? objects[12].toString() : "");			
			beansTemp.add(bean);
		}	
		session.flush();
		session.close();
		return beansTemp;
	}
	
	
	public List<FReport> getConsultWaitingTime(FReport beanForm) {
		// Reading the records from the table
		Session session = sessionFactory.openSession();
		String sql = "  Select v.RegistrationTime,v.QueueNo,v.CustomerIc, poi.VisitType,dep.departmentName,grp.workgroupName,liv.AppointmentTime,";
		sql+= " liv.WaitingTimeStart, liv.ServingTimeStart,ServingTimeEnd,DATEDIFF(minute,liv.waitingTimeStart,liv.waitingTimeEnd) as WaitTime";
		sql+= ",DATEDIFF(minute,liv.ServingTimeStart,liv.ServingTimeEnd) as ServingTime,poi.SapBapiActRemark ,liv.WaitingTimeEnd,v.customerName";
		sql+= "	 from LiveTransactionVisit v left join LiveTransactionQueue  liv on v.visitId = liv.visitId left join LiveAppointment  poi on v.CustomerIc = poi.PatientNric "; 
		sql+= " left join QsoftStatus tus on  liv.QueueStatusId = tus.QueueStatusId ";
		sql+= " left join QsoftWorkgroup grp on  liv.WorkgroupId = grp.WorkgroupId ";
		sql+= " left join QsoftDepartment dep on  dep.DepartmentId = grp.DepartmentId ";
		sql+= " left join QsoftInstitution inton on  inton.InstitutionId = dep.InstitutionId ";
        sql+= " where 1=1 ";        
        if (beanForm.getServiceDate() != null && !beanForm.getServiceDate().equals("")){
        	if ((beanForm.getStartDate() == null  || beanForm.getStartDate().equals("")) && (beanForm.getEndDate() == null  || beanForm.getEndDate().equals(""))){
        		sql += " and v.RegistrationTime >= '" + beanForm.getServiceDate() + " 00:00:00' AND  v.RegistrationTime <= '" + beanForm.getServiceDate() + " 23:59:59'";
        	}else if (beanForm.getStartDate() != null  && !beanForm.getStartDate().equals("") && beanForm.getEndDate() != null  && !beanForm.getEndDate().equals("")){
        		sql += " and v.RegistrationTime >= '" + beanForm.getServiceDate() + " " + beanForm.getStartDate() + ":00'";
        		sql += " and v.RegistrationTime <= '" + beanForm.getServiceDate() + " " + beanForm.getEndDate() + ":00'";
        	}else if (beanForm.getStartDate() != null  && !beanForm.getStartDate().equals("")){
        		sql += " and v.RegistrationTime >= '" + beanForm.getServiceDate() + " " + beanForm.getStartDate() +":00' AND  v.RegistrationTime <= '" + beanForm.getServiceDate() + " 23:59:59'";
        	}else if (beanForm.getEndDate() != null  && !beanForm.getEndDate().equals("")){
        		sql += " and v.RegistrationTime >= '" + beanForm.getServiceDate() + " 00:00:00' AND  v.RegistrationTime <= '" + beanForm.getServiceDate() + " " + beanForm.getEndDate() + ":00'";
        	}
        	
        }
			
		if (beanForm.getMembersVisitTypeId() != null && !beanForm.getMembersVisitTypeId().equals("")) {
			sql += " and poi.VisitType in ("+ beanForm.getMembersVisitTypeId() +")";
		}

	
		if (beanForm.getInstitutionId() != null	&& beanForm.getInstitutionId() > 0) {
			sql += " and inton.InstitutionId =:institutionId";
		}

		if (beanForm.getDepartmentId() != null 	&& beanForm.getDepartmentId() > 0) {
			sql += " and dep.departmentId =:departmentId";
		}

		if (beanForm.getWorkgroupId() != null && beanForm.getWorkgroupId() > 0) {
			sql += " and grp.workgroupId =:workgroupId";
		}

		sql += " order by v.CustomerIc, liv.WorkgroupId ";
		Query q = session.createSQLQuery(sql);
		if (beanForm.getInstitutionId() != null && beanForm.getInstitutionId() > 0) {
			q.setParameter("institutionId", beanForm.getInstitutionId());
		}

		if (beanForm.getDepartmentId() != null 	&& beanForm.getDepartmentId() > 0) {
			q.setParameter("departmentId", beanForm.getDepartmentId());
		}

		if (beanForm.getWorkgroupId() != null && beanForm.getWorkgroupId() > 0) {
			q.setParameter("workgroupId", beanForm.getWorkgroupId());
		}
		List dataObj = q.list();
		List beansTemp = new ArrayList();
		Date dateCurrent = new FReport().getCurrentDate();
		for (int i = 0; i < dataObj.size(); i++) {
			Object[] objects = (Object[]) dataObj.get(i);
			FReport bean = new FReport();
			
			bean.setRegistrationTime(objects[0] != null ? objects[0].toString(): "");
			bean.setRegistrationTime(!bean.getRegistrationTime().equals("") ? bean.dateToStringUtil(bean.stringToDate(bean.getRegistrationTime(), "yyyy-MM-dd HH:mm:ss"),"dd-MM-yyyy HH:mm:ss") : "");
			bean.setQueueNo(objects[1] != null ? objects[1].toString(): "");
			bean.setCustomerIc(objects[2] != null ? objects[2].toString(): "");
			bean.setVisitType(objects[3] != null ? objects[3].toString(): "");
			bean.setDepartmentName(objects[4] != null ? objects[4].toString(): "");
			bean.setWorkgroupName(objects[5] != null ? objects[5].toString(): "");
			bean.setApptTimestamp(objects[6] != null ? objects[6].toString(): "");
			bean.setApptTimestamp(!bean.getApptTimestamp().equals("") ? bean.dateToStringUtil(bean.stringToDate(bean.getApptTimestamp(), "yyyy-MM-dd HH:mm:ss"),"dd-MM-yyyy HH:mm:ss") : "");
			bean.setWaitingTimeStart(objects[7] != null ? objects[7].toString(): "");
			bean.setWaitingTimeStart(!bean.getWaitingTimeStart().equals("") ? bean.dateToStringUtil(bean.stringToDate(bean.getWaitingTimeStart(), "yyyy-MM-dd HH:mm:ss"),"dd-MM-yyyy HH:mm:ss") : "");
			bean.setServingTimeStart(objects[8] != null ? objects[8].toString(): "");
			bean.setServingTimeStart(!bean.getServingTimeStart().equals("") ? bean.dateToStringUtil(bean.stringToDate(bean.getServingTimeStart(), "yyyy-MM-dd HH:mm:ss"),"dd-MM-yyyy HH:mm:ss") : "");
			bean.setServingTimeEnd(objects[9] != null ? objects[9].toString(): "");
			bean.setServingTimeEnd(!bean.getServingTimeEnd().equals("") ? bean.dateToStringUtil(bean.stringToDate(bean.getServingTimeEnd(), "yyyy-MM-dd HH:mm:ss"),"dd-MM-yyyy HH:mm:ss") : "");
			int wt = Integer.parseInt(objects[10] != null ? objects[10].toString() : "0");
			bean.setWt(bean.changeToHHMMSS(wt));			
			int st = Integer.parseInt(objects[11] != null ? objects[11].toString() : "0");
			bean.setSt(bean.changeToHHMMSS(st));			
			bean.setSapBapiActRemark(objects[12] != null ? objects[12].toString() : "");	
			bean.setWaitingTimeEnd(objects[13] != null ? objects[13].toString(): "");
			bean.setWaitingTimeEnd(!bean.getWaitingTimeEnd().equals("") ? bean.dateToStringUtil(bean.stringToDate(bean.getWaitingTimeEnd(), "yyyy-MM-dd HH:mm:ss"),"dd-MM-yyyy HH:mm:ss") : "");
			bean.setCustomerName(objects[14] != null ? objects[14].toString(): "");
			beansTemp.add(bean);
		}	
		session.flush();
		session.close();
		return beansTemp;
	}
	
	
	public List<FReport> getAllWaitTimeWTR4(FReport beanForm) {
		// Reading the records from the table
		Session session = sessionFactory.openSession();
		String sql = "  Select v.RegistrationTime,v.QueueNo,v.CustomerIc, poi.VisitType,dep.departmentName,grp.workgroupName,liv.AppointmentTime,";
		sql+= " liv.WaitingTimeStart, liv.ServingTimeStart,ServingTimeEnd,DATEDIFF(minute,liv.waitingTimeStart,liv.waitingTimeEnd) as WaitTime";
		sql+= ",DATEDIFF(minute,liv.ServingTimeStart,liv.ServingTimeEnd) as ServingTime,poi.SapBapiActRemark,liv.WaitingTimeEnd ";
		sql+= "	 from LiveTransactionVisit v left join LiveTransactionQueue  liv on v.visitId = liv.visitId left join LiveAppointment  poi on v.CustomerIc = poi.PatientNric "; 
		sql+= " left join QsoftStatus tus on  liv.QueueStatusId = tus.QueueStatusId ";
		sql+= " left join QsoftWorkgroup grp on  liv.WorkgroupId = grp.WorkgroupId ";
		sql+= " left join QsoftDepartment dep on  dep.DepartmentId = grp.DepartmentId ";
        sql+= " where 1=1 ";
		
		if (beanForm.getStartDate() != null  && !beanForm.getStartDate().equals("") && beanForm.getEndDate() != null && !beanForm.getEndDate().equals("")) {
			sql += " and v.RegistrationTime >= '" + beanForm.getStartDate() + " 00:00:00' AND  v.RegistrationTime <= '" + beanForm.getEndDate() + " 23:59:59'";

		} else if (beanForm.getStartDate() != null && !beanForm.getStartDate().equals("")) {
			   sql += "  and v.RegistrationTime >= '" + beanForm.getStartDate()	+ " 00:00:00' AND  v.RegistrationTime <= '" + beanForm.getStartDate() + " 23:59:59'";
		}
		
		if (beanForm.getMembersVisitTypeId() != null && !beanForm.getMembersVisitTypeId().equals("")) {
			sql += " and poi.VisitType in ("+ beanForm.getMembersVisitTypeId() +")";
		}

	
		if (beanForm.getMembersdepId() != null && !beanForm.getMembersdepId().equals("")) {
			sql += " and dep.DepartmentId in ("+ beanForm.getMembersdepId() +")";
		}
		
		
		if (beanForm.getMembersWorkgroupId() != null && !beanForm.getMembersWorkgroupId().equals("")) {
			sql += " and grp.WorkgroupId in ("+ beanForm.getMembersWorkgroupId() +")";
		}
		
		

		sql += " order by v.CustomerIc, liv.WorkgroupId ";
		System.out.println(sql);
		Query q = session.createSQLQuery(sql);
		List dataObj = q.list();
		List beansTemp = new ArrayList();
		Date dateCurrent = new FReport().getCurrentDate();
		for (int i = 0; i < dataObj.size(); i++) {
			Object[] objects = (Object[]) dataObj.get(i);
			FReport bean = new FReport();
			
			bean.setRegistrationTime(objects[0] != null ? objects[0].toString(): "");
			bean.setRegistrationTime(!bean.getRegistrationTime().equals("") ? bean.dateToStringUtil(bean.stringToDate(bean.getRegistrationTime(), "yyyy-MM-dd HH:mm:ss"),"dd-MM-yyyy HH:mm:ss") : "");
			bean.setQueueNo(objects[1] != null ? objects[1].toString(): "");
			bean.setCustomerIc(objects[2] != null ? objects[2].toString(): "");
			bean.setVisitType(objects[3] != null ? objects[3].toString(): "");
			bean.setDepartmentName(objects[4] != null ? objects[4].toString(): "");
			bean.setWorkgroupName(objects[5] != null ? objects[5].toString(): "");
			bean.setApptTimestamp(objects[6] != null ? objects[6].toString(): "");
			bean.setApptTimestamp(!bean.getApptTimestamp().equals("") ? bean.dateToStringUtil(bean.stringToDate(bean.getApptTimestamp(), "yyyy-MM-dd HH:mm:ss"),"dd-MM-yyyy HH:mm:ss") : "");
			bean.setWaitingTimeStart(objects[7] != null ? objects[7].toString(): "");
			bean.setWaitingTimeStart(!bean.getWaitingTimeStart().equals("") ? bean.dateToStringUtil(bean.stringToDate(bean.getWaitingTimeStart(), "yyyy-MM-dd HH:mm:ss"),"dd-MM-yyyy HH:mm:ss") : "");
			bean.setServingTimeStart(objects[8] != null ? objects[8].toString(): "");
			bean.setServingTimeStart(!bean.getServingTimeStart().equals("") ? bean.dateToStringUtil(bean.stringToDate(bean.getServingTimeStart(), "yyyy-MM-dd HH:mm:ss"),"dd-MM-yyyy HH:mm:ss") : "");
			bean.setServingTimeEnd(objects[9] != null ? objects[9].toString(): "");
			bean.setServingTimeEnd(!bean.getServingTimeEnd().equals("") ? bean.dateToStringUtil(bean.stringToDate(bean.getServingTimeEnd(), "yyyy-MM-dd HH:mm:ss"),"dd-MM-yyyy HH:mm:ss") : "");
			int wt = Integer.parseInt(objects[10] != null ? objects[10].toString() : "0");
			bean.setWt(bean.changeToHHMMSS(wt));			
			int st = Integer.parseInt(objects[11] != null ? objects[11].toString() : "0");
			bean.setSt(bean.changeToHHMMSS(st));			
			bean.setSapBapiActRemark(objects[12] != null ? objects[12].toString() : "");		
			bean.setWaitingTimeEnd(objects[13] != null ? objects[13].toString(): "");
			bean.setWaitingTimeEnd(!bean.getWaitingTimeEnd().equals("") ? bean.dateToStringUtil(bean.stringToDate(bean.getWaitingTimeEnd(), "yyyy-MM-dd HH:mm:ss"),"dd-MM-yyyy HH:mm:ss") : "");
			beansTemp.add(bean);
		}	
		session.flush();
		session.close();
		return beansTemp;
	}
	
	public List<FReport> getAllAuditTrailReport(FReport beanForm) {
		// Reading the records from the table		
		Session session = sessionFactory.openSession();
		String sql = "  Select v.RegistrationTime,u.userName,v.CustomerName, v.QueueNo, dep.departmentName,liv.ServingTerminalId, ter.TerminalName,inton.institutionName    from ";
		sql += " LiveTransactionVisit v left join LiveTransactionQueue  liv on v.visitId = liv.visitId "; 	
		sql += " left join QsoftTerminal  ter  on ter.terminalId = liv.ServingTerminalId ";
		sql += " left join QsoftWorkgroup grp  on  liv.WorkgroupId = grp.WorkgroupId left join QsoftDepartment dep on  dep.DepartmentId = grp.DepartmentId ";
		sql += " left join QsoftUser u on  u.userId = liv.ServingUserId ";
		sql += "   left join QsoftInstitution inton on  inton.InstitutionId = dep.InstitutionId ";
		sql += " where 1=1   ";
		
		if (beanForm.getStartDate() != null  && !beanForm.getStartDate().equals("") && beanForm.getEndDate() != null && !beanForm.getEndDate().equals("")) {
			sql += " and v.RegistrationTime >= '" + beanForm.getStartDate() + " 00:00:00' AND  v.RegistrationTime <= '" + beanForm.getEndDate() + " 23:59:59'";

		}else if (beanForm.getStartDate() != null && !beanForm.getStartDate().equals("")) {
			   sql += "  and v.RegistrationTime >= '" + beanForm.getStartDate()	+ " 00:00:00' AND  v.RegistrationTime <= '" + beanForm.getStartDate() + " 23:59:59'";
		}
			
		
		
		if (beanForm.getInstitutionId() != null && beanForm.getInstitutionId() > 0) {
			sql += " and inton.institutionId =:institutionId";
		}
		
		if (beanForm.getDepartmentId() != null && beanForm.getDepartmentId() > 0) {
			sql += " and dep.DepartmentId =:departmentId";
		}
		
		if (beanForm.getWorkgroupId() != null && beanForm.getWorkgroupId() > 0) {
			sql += " and liv.WorkgroupId =:workgroupId";
		}
		
		if (beanForm.getUserId() != null && beanForm.getUserId() > 0) {
			sql += " and liv.ServingUserId =:servingUserId ";
		}
		
		if (beanForm.getQueueNo() != null && !beanForm.getQueueNo().equals("")) {
			sql += " and v.QueueNo =:QueueNo ";
		}
		
		if (beanForm.getCustomerIc() != null && !beanForm.getCustomerIc().equals("")) {
			sql += " and v.CustomerIc =:CustomerIc ";
		}
		
		sql += " order by  v.registrationTime desc ";		
		System.out.println(sql);
		Query q = session.createSQLQuery(sql);
		
		if (beanForm.getInstitutionId() != null && beanForm.getInstitutionId() > 0) {
			q.setParameter("institutionId", beanForm.getInstitutionId());
		}
		
		if (beanForm.getDepartmentId() != null && beanForm.getDepartmentId() > 0) {			
			q.setParameter("departmentId", beanForm.getDepartmentId());
		}
		
		if (beanForm.getWorkgroupId() != null && beanForm.getWorkgroupId() > 0) {			
			q.setParameter("workgroupId", beanForm.getWorkgroupId());
		}
		
		if (beanForm.getUserId() != null && beanForm.getUserId() > 0) {			
			q.setParameter("servingUserId", beanForm.getUserId());
		}
		
		if (beanForm.getQueueNo() != null && !beanForm.getQueueNo().equals("")) {
			q.setParameter("QueueNo", beanForm.getQueueNo());
		}
		
		if (beanForm.getCustomerIc() != null && !beanForm.getCustomerIc().equals("")) {
			q.setParameter("CustomerIc", beanForm.getCustomerIc());
		}
		
		List dataObj = q.list();
		List beansTemp = new ArrayList();
		Date dateCurrent = new FReport().getCurrentDate();
		for (int i = 0; i < dataObj.size(); i++) {
			Object[] objects = (Object[]) dataObj.get(i);
			FReport bean = new FReport();
			bean.setRegistrationTime(objects[0] != null ? objects[0].toString(): "");	
			bean.setRegistrationTime(!bean.getRegistrationTime().equals("") ? bean.dateToStringUtil(bean.stringToDate(bean.getRegistrationTime(), "yyyy-MM-dd HH:mm:ss"),"dd-MM-yyyy HH:mm:ss") : "");
				
			bean.setUserName(objects[1] != null ? objects[1].toString(): "");	
			bean.setCustomerName(objects[2] != null ? objects[2].toString(): "");				
			bean.setQueueNo(objects[3] != null ? objects[3].toString(): "");
			bean.setDepartmentName(objects[4] != null ? objects[4].toString(): "");			
			bean.setTerminalId(objects[5] != null ? Integer.parseInt(objects[5].toString()): 0);
			bean.setTerminalName(objects[6] != null ? objects[6].toString(): "");	
			bean.setInstitutionName(objects[7] != null ? objects[7].toString(): "");	
			beansTemp.add(bean);
		}	
		session.flush();
		session.close();
		return beansTemp;
	}
}
