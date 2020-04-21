package com.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.form.FReport;
import com.service.ReportService;

@Controller
public class ReportController {

	@Autowired
	private ReportService reportService;
	
//	@Autowired
//	private InstitutionService institutionService;

	// Getters and Setters

	public ReportService getReportService() {
		return reportService;
	}

	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

	
//	@RequestMapping(value = "/institution", method = RequestMethod.GET)
//	public ResponseEntity<Institution> findAll() {
//		List<Institution> response = new ArrayList<Institution>();
//		try {
//			response = institutionService.listAll();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}		
//		return (ResponseEntity<Institution>) response;
//	}
//	
	

	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(10240000);
		return multipartResolver;
	}
	
	@RequestMapping(value = "/acountAPI")
	public ModelAndView acountAPI(
			@Valid @ModelAttribute(value = "formObj") FReport bean,
			HttpServletRequest request) {	
		return new ModelAndView("acountAPI");
	}

	// form report1 view
	@RequestMapping(value = "/QueueInfo")
	public ModelAndView getAllReports1From(
			@Valid @ModelAttribute(value = "formObj") FReport bean,
			HttpServletRequest request) {
		 List<FReport> beans = reportService.getAllInstitution();
		 int institutionIdTemp = beans != null && beans.size() > 0? beans.get(0).getInstitutionId():0;
		request.setAttribute("listInstitution",beans);
		request.setAttribute("institutionIdTemp",institutionIdTemp);
//		request.setAttribute("listDepartment", reportService.getAllDepartment());
//		request.setAttribute("listWorkgroup", reportService.getAllWorkgroup());
		return new ModelAndView("report1");
	}

	// export to excel
	@RequestMapping(value = "/QueueInfo", method = RequestMethod.POST)
	public ResponseEntity<?> getAllReports1Excel(@Valid @ModelAttribute(value = "formObj") FReport bean,	HttpServletRequest request, HttpSession session) {

		InputStreamResource resource = null;
		HttpHeaders headers = new HttpHeaders();
		XSSFWorkbook wb;
		String path = session.getServletContext().getRealPath("/");
		File file = new File(path
				+ "\\WEB-INF\\resource\\template\\report1.xlsx");
		String path1 = file.getPath();
		ByteArrayOutputStream bos = null;
		try {
			List<FReport> beans = reportService.getAllReports1(bean);
			wb = new XSSFWorkbook(new FileInputStream(path1));
			Sheet sheet = wb.getSheetAt(0);
			Row row1 = null;
			
			row1 = sheet.createRow(3);
			bean.createCell(row1, (short) 1, "Polyclinic Domain:");
			if (bean.getInstitutionId() != null && bean.getInstitutionId() > 0) {
				FReport beanTemp = reportService.getInstitutionById(bean.getInstitutionId());
				if (beanTemp != null && beanTemp.getInstitutionName() != null){
					bean.createCell(row1, (short) 3,beanTemp.getInstitutionName());
				}					
			} else {
				bean.createCell(row1, (short) 3, "All");
			}
			bean.cellStyleBoderMarge(3, 3, 1, 2, sheet, wb, row1 );
			bean.cellStyleBoderMarge(3, 3, 3, 4, sheet, wb, row1 );
			

			bean.createCell(row1, (short) 5, "Department:");
			if (bean.getDepartmentId() != null && bean.getDepartmentId() > 0) {
				FReport beanTemp = reportService.getDepartmentById(bean.getDepartmentId());
				if (beanTemp != null && beanTemp.getDepartmentName() != null)
					bean.createCell(row1, (short) 6,beanTemp.getDepartmentName());
			} else {
				bean.createCell(row1, (short) 6, "All");
			}

			bean.cellStyleBoderBold(row1, wb, (short) 5);
			bean.cellStyleBoderBold(row1, wb, (short) 6);

			
			row1 = sheet.createRow(4);
			bean.createCell(row1, (short) 1, "Workgroup:");
			if (bean.getWorkgroupId() != null && bean.getWorkgroupId() > 0) {
				FReport beanTemp = reportService.getWorkgroupById(bean.getWorkgroupId());
				if (beanTemp != null && beanTemp.getWorkgroupName() != null)
					bean.createCell(row1, (short) 3,beanTemp.getWorkgroupName());
			} else {
				bean.createCell(row1, (short) 3, "All");
			}
			
			bean.cellStyleBoderMarge(4, 4, 1, 2, sheet, wb, row1);
			bean.cellStyleBoderMarge(4, 4, 3, 4, sheet, wb, row1);

			bean.createCell(row1, (short) 5, "Extracted Date/ Time:");
			bean.cellStyleBoderBold(row1, wb, (short) 5);
			bean.createCell(row1, (short) 6, bean.dateToStringUtil(bean.getCurrentDate(), "dd-MM-yyyy HH:mm:ss"));
			bean.cellStyleBoderBold(row1, wb, (short) 6);
			Row row = null;
			int y = 7;
			for (int i = 0; i < beans.size(); i++) {
				row = sheet.createRow(y++);
				FReport beanTemp = beans.get(i);
				//bean.createCell(row, (short) 0, (i + 1) + "");
				//bean.createCell(row, (short) 1, beanTemp.getExtractedDate());
				bean.createCell(row, (short) 0, beanTemp.getDepartmentName());
				bean.createCell(row, (short) 1, beanTemp.getQueueNo());
				bean.createCell(row, (short) 2, beanTemp.getCustomerIc());
				bean.createCell(row, (short) 3, beanTemp.getCustomerName());
				bean.createCell(row, (short) 4, beanTemp.getVisitType());
				bean.createCell(row, (short) 5, beanTemp.getApptTimestamp());
				bean.createCell(row, (short) 6, beanTemp.getRegistrationTime());
				bean.createCell(row, (short) 7, beanTemp.getStatusDescription());
				String datavipNormal = "";
				if (beanTemp.getVipNormal().equals("Y"))
					datavipNormal = "Yes";
				else if (beanTemp.getVipNormal().equals("N"))
					datavipNormal = "No";
				bean.createCell(row, (short) 8, datavipNormal);
				for (int j = 0; j < 9; j++) {
					bean.cellStyleBoder(row, wb, (short) j);
				}
			}
			bos = new ByteArrayOutputStream();
			wb.write(bos);
			wb.close();
			resource = new InputStreamResource(new ByteArrayInputStream(
					bos.toByteArray()));

			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentDispositionFormData("ATTACHMENT",
					"Downtime_Queue_Info.xlsx");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity
				.ok()
				.headers(headers)
				.contentLength(bos.size())
				.contentType(
						MediaType.parseMediaType("application/octet-stream"))
				.body(resource);
	}
	
	
	@RequestMapping(value = "/getDepartmentByInstitutionId/{institutionId}", method = RequestMethod.GET)
	public @ResponseBody List<FReport> getDepartmentByInstitutionId(@PathVariable(name = "institutionId") int institutionId) {
		return reportService.getDepartmentByInstitutionId(institutionId);
	}
	
	

	@RequestMapping(value = "/getWorkgroupByDepartmentId/{departmentId}", method = RequestMethod.GET)
	public @ResponseBody List<FReport> getWorkgroupByDepartmentId(@PathVariable("departmentId") Integer departmentId) {
		return reportService.getWorkgroupByDepartmentId(departmentId);
	}
	
	@RequestMapping(value = "/getWorkgroupByMemberDepartmentId/{departmentId}", method = RequestMethod.GET)
	public @ResponseBody List<FReport> getWorkgroupByDepartmentId(@PathVariable("departmentId") Integer[] departmentId) {
		return reportService.getWorkgroupByMemberDepartmentId(departmentId);
	}
	
	@RequestMapping(value = "/getDepartmentByMemberInstitutionId/{institutionId}", method = RequestMethod.GET)
	public @ResponseBody List<FReport> getDepartmentByMemberInstitutionId(@PathVariable("institutionId") Integer[] institutionId) {
		return reportService.getDepartmentByMemberInstitutionId(institutionId);		
	}
	

	// view data in grid
	@RequestMapping(value = "/getListReport1", method = RequestMethod.GET)
	public @ResponseBody List<FReport> getListReport1(
			@Valid @ModelAttribute(value = "formObj") FReport bean,
			HttpServletRequest request) {
		return reportService.getAllReports1(bean);
	}

	// Start QueueScript
	@RequestMapping(value = "/QueueScript")
	public ModelAndView getAllReports2From(
			@Valid @ModelAttribute(value = "formObj") FReport bean,
			HttpServletRequest request) {
		request.setAttribute("listInstitution",
				reportService.getAllInstitution());
		return new ModelAndView("report2");
	}

	@RequestMapping(value = "/QueueScript", method = RequestMethod.POST)
	public ResponseEntity<?> getAllReports2Excel(
			@Valid @ModelAttribute(value = "formObj") FReport bean,
			HttpServletRequest request, HttpSession session) {

		InputStreamResource resource = null;
		HttpHeaders headers = new HttpHeaders();
		XSSFWorkbook wb;
		String path = session.getServletContext().getRealPath("/");
		File file = new File(path
				+ "\\WEB-INF\\resource\\template\\report2.xlsx");
		String path1 = file.getPath();
		ByteArrayOutputStream bos = null;
		try {
			List<FReport> beans = reportService.getAllReports2(bean);
			wb = new XSSFWorkbook(new FileInputStream(path1));
			Sheet sheet = wb.getSheetAt(0);
			Row row = null;
			int y = 4;
			for (int i = 0; i < beans.size(); i++) {
				row = sheet.createRow(y++);
				FReport beanTemp = beans.get(i);
				bean.createCell(row, (short) 0, beanTemp.getInstitutionName());
				bean.createCell(row, (short) 1, beanTemp.getWorkgroupName());
				bean.createCell(row, (short) 2, beanTemp.getDepartmentName());
				bean.createCell(row, (short) 3, beanTemp.getQueueNo());
				bean.createCell(row, (short) 4, beanTemp.getCustomerIc());
				bean.createCell(row, (short) 5, beanTemp.getCustomerName());
				bean.createCell(row, (short) 6, beanTemp.getVisitType());
				bean.createCell(row, (short) 7, beanTemp.getApptTimestamp());
				bean.createCell(row, (short) 8, beanTemp.getRegistrationTime());
				bean.createCell(row, (short) 9, beanTemp.getStatusDescription());
				String datavipNormal = "";
				if (beanTemp.getVipNormal().equals("Y"))
					datavipNormal = "Yes";
				else if (beanTemp.getVipNormal().equals("N"))
					datavipNormal = "No";
				bean.createCell(row, (short) 10, datavipNormal);
				for (int j = 0; j < 11; j++) {
					bean.cellStyleBoder(row, wb, (short) j);
				}
			}
			bos = new ByteArrayOutputStream();
			wb.write(bos);
			wb.close();
			resource = new InputStreamResource(new ByteArrayInputStream(
					bos.toByteArray()));
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentDispositionFormData("ATTACHMENT",
					"Downtime_Queue_Script.xlsx");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity
				.ok()
				.headers(headers)
				.contentLength(bos.size())
				.contentType(
						MediaType.parseMediaType("application/octet-stream"))
				.body(resource);
	}

	// view data in grid
	@RequestMapping(value = "/getListReport2", method = RequestMethod.GET)
	public @ResponseBody List<FReport> getListReport2(
			@Valid @ModelAttribute(value = "formObj") FReport bean,
			HttpServletRequest request) {
		return reportService.getAllReports2(bean);
	}
	
	// End QueueScript

	// Start waitTime
	@RequestMapping(value = "/waitTime")
	public ModelAndView getAllReports3From(
			@Valid @ModelAttribute(value = "formObj") FReport bean,
			HttpServletRequest request) {
		request.setAttribute("listVisitType",	reportService.getAllVisitType());
		request.setAttribute("listInstitution",
				reportService.getAllInstitution());
		//request.setAttribute("listDepartment", reportService.getAllDepartment());
		//request.setAttribute("listWorkgroup", reportService.getAllWorkgroup());
		//request.setAttribute("listLiveAppointment",	reportService.getAllLiveAppointment());
		request.setAttribute("curentDate",
				bean.dateToString(bean.getCurrentSqlDate(), "yyyy-MM-dd"));
		return new ModelAndView("report3");
	}

	@RequestMapping(value = "/waitTime", method = RequestMethod.POST)
	public ResponseEntity<?> getAllReports3Excel(
			@Valid @ModelAttribute(value = "formObj") FReport bean,
			HttpServletRequest request, HttpSession session) {

		InputStreamResource resource = null;
		HttpHeaders headers = new HttpHeaders();
		XSSFWorkbook wb;
		String path = session.getServletContext().getRealPath("/");
		File file = new File(path
				+ "\\WEB-INF\\resource\\template\\report3.xlsx");
		String path1 = file.getPath();
		ByteArrayOutputStream bos = null;
		try {
			List<FReport> beans = reportService.getAllReports3(bean);
			wb = new XSSFWorkbook(new FileInputStream(path1));
			Sheet sheet = wb.getSheetAt(0);

			Row row = null;
			int y = 4;
			for (int i = 0; i < beans.size(); i++) {
				row = sheet.createRow(y++);
				FReport beanTemp = beans.get(i);
				bean.createCell(row, (short) 0, beanTemp.getQueueNo());
				bean.createCell(row, (short) 1, beanTemp.getCustomerIc());
				bean.createCell(row, (short) 2, beanTemp.getVisitType());
				bean.createCell(row, (short) 3, beanTemp.getInstitutionName());
				bean.createCell(row, (short) 4, beanTemp.getDepartmentName());
				bean.createCell(row, (short) 5, beanTemp.getWorkgroupName());
				bean.createCell(row, (short) 6, beanTemp.getApptTimestamp());
				bean.createCell(row, (short) 7, beanTemp.getRegistrationTime());
				bean.createCell(row, (short) 8, beanTemp.getWaitingTimeStart());
				bean.createCell(row, (short) 9, beanTemp.getWaitingTimeEnd());
				bean.createCell(row, (short) 10, beanTemp.getWt());
				bean.createCell(row, (short) 11, beanTemp.getServingTimeStart());
				bean.createCell(row, (short) 12, beanTemp.getServingTimeEnd());
				bean.createCell(row, (short) 13, beanTemp.getSt());
				bean.createCell(row, (short) 14, beanTemp.getTurnaroundTime());
				bean.createCell(row, (short) 15, beanTemp.getSapBapiActRemark());
				for (int j = 0; j < 16; j++) {
					bean.cellStyleBoder(row, wb, (short) j);
				}
			}
			bos = new ByteArrayOutputStream();
			wb.write(bos);
			wb.close();
			resource = new InputStreamResource(new ByteArrayInputStream(
					bos.toByteArray()));
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentDispositionFormData("ATTACHMENT",
					"wait_time_reports.xlsx");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity
				.ok()
				.headers(headers)
				.contentLength(bos.size())
				.contentType(
						MediaType.parseMediaType("application/octet-stream"))
				.body(resource);
	}

	
	@RequestMapping(value = "/getListReport3", method = RequestMethod.GET)
	public @ResponseBody List<FReport> getListReport3(
			@Valid @ModelAttribute(value = "formObj") FReport bean,
			HttpServletRequest request) {
		return reportService.getAllReports3(bean);
	}
	
	////	
	@RequestMapping(value = "/registrationWaitTimeWTR1")
	public ModelAndView getAllregistrationWaitTimeWTR1From(@Valid @ModelAttribute(value = "formObj") FReport bean,HttpServletRequest request) {
		request.setAttribute("listVisitType",	reportService.getAllVisitType());
		request.setAttribute("listInstitution", reportService.getAllInstitution());
		request.setAttribute("curentDate",bean.dateToString(bean.getCurrentSqlDate(), "yyyy-MM-dd"));
		return new ModelAndView("registrationWaitTimeWTR1");
	}

	@RequestMapping(value = "/registrationWaitTimeWTR1", method = RequestMethod.POST)
	public ResponseEntity<?> getAllRegistrationWaitTimeWTR1Excel(
			@Valid @ModelAttribute(value = "formObj") FReport bean,
			HttpServletRequest request, HttpSession session) {

		InputStreamResource resource = null;
		HttpHeaders headers = new HttpHeaders();
		XSSFWorkbook wb;
		String path = session.getServletContext().getRealPath("/");
		File file = new File(path
				+ "\\WEB-INF\\resource\\template\\registrationWaitTimeWTR1.xlsx");
		String path1 = file.getPath();
		ByteArrayOutputStream bos = null;
		try {
			List<FReport> beans = reportService.getAllRegistrationWaitTimeWTR1(bean);
			wb = new XSSFWorkbook(new FileInputStream(path1));
			Sheet sheet = wb.getSheetAt(0);

			Row row = null;
			int y = 4;
			for (int i = 0; i < beans.size(); i++) {
				row = sheet.createRow(y++);
				FReport beanTemp = beans.get(i);				
				bean.createCell(row, (short) 0, beanTemp.getQueueNo());
				bean.createCell(row, (short) 1, beanTemp.getCustomerIc());				
				bean.createCell(row, (short) 2, beanTemp.getRegistrationTime());
				bean.createCell(row, (short) 3, beanTemp.getWaitingTimeStart());
				bean.createCell(row, (short) 4, beanTemp.getServingTimeStart());
				bean.createCell(row, (short) 5, beanTemp.getServingTimeEnd());			
				bean.createCell(row, (short) 6, beanTemp.getWt());
				bean.createCell(row, (short) 7, beanTemp.getSt());			
				for (int j = 0; j < 8; j++) {
					bean.cellStyleBoder(row, wb, (short) j);
				}
			}
			bos = new ByteArrayOutputStream();
			wb.write(bos);
			wb.close();
			resource = new InputStreamResource(new ByteArrayInputStream(bos.toByteArray()));
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentDispositionFormData("ATTACHMENT",
					"registration_WaitTime_WTR1.xlsx");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity
				.ok()
				.headers(headers)
				.contentLength(bos.size())
				.contentType(
						MediaType.parseMediaType("application/octet-stream"))
				.body(resource);
	}

	
	@RequestMapping(value = "/getListRegistrationWaitTimeWTR1", method = RequestMethod.GET)
	public @ResponseBody List<FReport> getListRegistrationWaitTimeWTR1(
			@Valid @ModelAttribute(value = "formObj") FReport bean,
			HttpServletRequest request) {
		return reportService.getAllRegistrationWaitTimeWTR1(bean);
	}
	
	@RequestMapping(value = "/waitTimeWTR2")
	public ModelAndView getAllWaitTimeWTR2From(@Valid @ModelAttribute(value = "formObj") FReport bean,HttpServletRequest request) {
		request.setAttribute("listInstitution",
				reportService.getAllInstitution());
		request.setAttribute("curentDate",bean.dateToString(bean.getCurrentSqlDate(), "yyyy-MM-dd"));
		return new ModelAndView("waitTimeWTR2");
	}

	@RequestMapping(value = "/waitTimeWTR2", method = RequestMethod.POST)
	public ResponseEntity<?> getAllWaitTimeWTR2Excel(
			@Valid @ModelAttribute(value = "formObj") FReport bean,
			HttpServletRequest request, HttpSession session) {

		InputStreamResource resource = null;
		HttpHeaders headers = new HttpHeaders();
		XSSFWorkbook wb;
		String path = session.getServletContext().getRealPath("/");
		File file = new File(path
				+ "\\WEB-INF\\resource\\template\\waitTimeWTR2.xlsx");
		String path1 = file.getPath();
		ByteArrayOutputStream bos = null;
		try {
			List<FReport> beans = reportService.getAllWaitTimeWTR2(bean);
			wb = new XSSFWorkbook(new FileInputStream(path1));
			Sheet sheet = wb.getSheetAt(0);

			Row row = null;
			int y = 7;
			for (int i = 0; i < beans.size(); i++) {
				row = sheet.createRow(y++);
				FReport beanTemp = beans.get(i);
				bean.createCell(row, (short) 0, beanTemp.getRegistrationTime());
				bean.createCell(row, (short) 1, beanTemp.getQueueNo());
				bean.createCell(row, (short) 2, beanTemp.getCustomerIc());
				bean.createCell(row, (short) 3, beanTemp.getVisitType());
				bean.createCell(row, (short) 4, beanTemp.getDepartmentName());
				bean.createCell(row, (short) 5, beanTemp.getWorkgroupName());
				bean.createCell(row, (short) 6, "");
				bean.createCell(row, (short) 7, beanTemp.getApptTimestamp());
				bean.createCell(row, (short) 8, beanTemp.getWaitingTimeStart());
				bean.createCell(row, (short) 9, beanTemp.getServingTimeStart());
				bean.createCell(row, (short) 10, beanTemp.getServingTimeEnd());
				bean.createCell(row, (short) 11, beanTemp.getServingTimeStart());
				bean.createCell(row, (short) 12, beanTemp.getWt());
				bean.createCell(row, (short) 13, beanTemp.getSt());
				bean.createCell(row, (short) 13, beanTemp.getSapBapiActRemark());
				for (int j = 0; j < 14; j++) {
					bean.cellStyleBoder(row, wb, (short) j);
				}
			}
			bos = new ByteArrayOutputStream();
			wb.write(bos);
			wb.close();
			resource = new InputStreamResource(new ByteArrayInputStream(
					bos.toByteArray()));
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentDispositionFormData("ATTACHMENT",
					"waitTime_WTR2.xlsx");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity
				.ok()
				.headers(headers)
				.contentLength(bos.size())
				.contentType(
						MediaType.parseMediaType("application/octet-stream"))
				.body(resource);
	}

	
	@RequestMapping(value = "/getListWaitTimeWTR2", method = RequestMethod.GET)
	public @ResponseBody List<FReport> getListWaitTimeWTR2(
			@Valid @ModelAttribute(value = "formObj") FReport bean,
			HttpServletRequest request) {
		return reportService.getAllWaitTimeWTR2(bean);
	}
	
	@RequestMapping(value = "/consultWaitingTime")
	public ModelAndView getAllConsultWaitingTimeFrom(@Valid @ModelAttribute(value = "formObj") FReport bean,HttpServletRequest request) {
		request.setAttribute("listVisitType",	reportService.getAllVisitType());
		request.setAttribute("listInstitution",	reportService.getAllInstitution());
		request.setAttribute("curentDate",bean.dateToString(bean.getCurrentSqlDate(), "yyyy-MM-dd"));
		return new ModelAndView("consultWaitingTime");
	}

	@RequestMapping(value = "/consultWaitingTime", method = RequestMethod.POST)
	public ResponseEntity<?> getAllConsultWaitingTimeExcel(@Valid @ModelAttribute(value = "formObj") FReport bean, HttpServletRequest request, HttpSession session) {

		InputStreamResource resource = null;
		HttpHeaders headers = new HttpHeaders();
		XSSFWorkbook wb;
		String path = session.getServletContext().getRealPath("/");
		File file = new File(path + "\\WEB-INF\\resource\\template\\consultWaitingTime.xlsx");
		String path1 = file.getPath();
		ByteArrayOutputStream bos = null;
		try {
			
			List<FReport> beans = reportService.getConsultWaitingTime(bean);
			wb = new XSSFWorkbook(new FileInputStream(path1));
			Sheet sheet = wb.getSheetAt(0);
			Row row = null;
			int y = 4;
			
			for (int i = 0; i < beans.size(); i++) {
				row = sheet.createRow(y++);
				FReport beanTemp = beans.get(i);
				bean.createCell(row, (short) 0, beanTemp.getApptTimestamp());
				bean.createCell(row, (short) 1, beanTemp.getRegistrationTime());
				bean.createCell(row, (short) 2, beanTemp.getWaitingTimeStart());
				bean.createCell(row, (short) 3, beanTemp.getWaitingTimeEnd());
				bean.createCell(row, (short) 4, beanTemp.getWt());
				bean.createCell(row, (short) 5, beanTemp.getVisitType());				
				bean.createCell(row, (short) 6, beanTemp.getCustomerIc());
				bean.createCell(row, (short) 7, beanTemp.getCustomerName());
				for (int j = 0; j < 8; j++) {
					bean.cellStyleBoder(row, wb, (short) j);
				}
			}
			bos = new ByteArrayOutputStream();
			wb.write(bos);
			wb.close();
			resource = new InputStreamResource(new ByteArrayInputStream(
					bos.toByteArray()));
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentDispositionFormData("ATTACHMENT",
					"consult_Waiting_Time.xlsx");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity
				.ok()
				.headers(headers)
				.contentLength(bos.size())
				.contentType(
						MediaType.parseMediaType("application/octet-stream"))
				.body(resource);
	}

	
	@RequestMapping(value = "/getListConsultWaitingTime", method = RequestMethod.GET)
	public @ResponseBody List<FReport> getListConsultWaitingTime(
			@Valid @ModelAttribute(value = "formObj") FReport bean,
			HttpServletRequest request) {
		return reportService.getConsultWaitingTime(bean);
	}
	
	
	
	@RequestMapping(value = "/visitTurnaroundTimeWTR4")
	public ModelAndView getAllVisitTurnaroundTimeWTR4From(@Valid @ModelAttribute(value = "formObj") FReport bean,HttpServletRequest request) {
		request.setAttribute("listVisitType",	reportService.getAllVisitType());
		request.setAttribute("listInstitution",	reportService.getAllInstitution());
		request.setAttribute("curentDate",bean.dateToString(bean.getCurrentSqlDate(), "yyyy-MM-dd"));
		return new ModelAndView("visitTurnaroundTimeWTR4");
	}

	@RequestMapping(value = "/visitTurnaroundTimeWTR4", method = RequestMethod.POST)
	public ResponseEntity<?> getAllVisitTurnaroundTimeWTR4Excel(
			@Valid @ModelAttribute(value = "formObj") FReport bean,
			HttpServletRequest request, HttpSession session) {

		InputStreamResource resource = null;
		HttpHeaders headers = new HttpHeaders();
		XSSFWorkbook wb;
		String path = session.getServletContext().getRealPath("/");
		File file = new File(path
				+ "\\WEB-INF\\resource\\template\\visitTurnaroundTimeWTR4.xlsx");
		String path1 = file.getPath();
		ByteArrayOutputStream bos = null;
		try {
			List<FReport> beans = reportService.getAllWaitTimeWTR4(bean);
			wb = new XSSFWorkbook(new FileInputStream(path1));
			Sheet sheet = wb.getSheetAt(0);

			Row row = null;
			int y = 4;
			for (int i = 0; i < beans.size(); i++) {
				row = sheet.createRow(y++);
				FReport beanTemp = beans.get(i);
				bean.createCell(row, (short) 0, beanTemp.getRegistrationTime());
				bean.createCell(row, (short) 1, beanTemp.getQueueNo());
				bean.createCell(row, (short) 2, beanTemp.getCustomerIc());
				bean.createCell(row, (short) 3, beanTemp.getVisitType());
				bean.createCell(row, (short) 4, beanTemp.getDepartmentName());
				bean.createCell(row, (short) 5, beanTemp.getWorkgroupName());
				bean.createCell(row, (short) 6, "");
				bean.createCell(row, (short) 7, beanTemp.getApptTimestamp());
				bean.createCell(row, (short) 8, beanTemp.getWaitingTimeStart());
				bean.createCell(row, (short) 9, beanTemp.getServingTimeStart());
				bean.createCell(row, (short) 10, beanTemp.getServingTimeEnd());
				bean.createCell(row, (short) 11, beanTemp.getServingTimeStart());
				bean.createCell(row, (short) 12, beanTemp.getWt());
				bean.createCell(row, (short) 13, beanTemp.getSt());
				bean.createCell(row, (short) 13, beanTemp.getSapBapiActRemark());
				for (int j = 0; j < 14; j++) {
					bean.cellStyleBoder(row, wb, (short) j);
				}
			}
			bos = new ByteArrayOutputStream();
			wb.write(bos);
			wb.close();
			resource = new InputStreamResource(new ByteArrayInputStream(
					bos.toByteArray()));
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentDispositionFormData("ATTACHMENT",
					"visit_turnaround_time_WTR4.xlsx");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity
				.ok()
				.headers(headers)
				.contentLength(bos.size())
				.contentType(
						MediaType.parseMediaType("application/octet-stream"))
				.body(resource);
	}

	
	@RequestMapping(value = "/getListVisitTurnaroundTimeWTR4", method = RequestMethod.GET)
	public @ResponseBody List<FReport> getListVisitTurnaroundTime(
			@Valid @ModelAttribute(value = "formObj") FReport bean,
			HttpServletRequest request) {
		return reportService.getAllWaitTimeWTR4(bean);
	}
	// End waitTime
	
	
	@RequestMapping(value = "/auditTrailReport")
	public ModelAndView getAllAuditTrailReportFrom(@Valid @ModelAttribute(value = "formObj") FReport bean,HttpServletRequest request) {
		request.setAttribute("listVisitType",	reportService.getAllVisitType());
		request.setAttribute("listInstitution",	reportService.getAllInstitution());
		request.setAttribute("listUser",	reportService.getAllListUser());
		request.setAttribute("curentDate",bean.dateToString(bean.getCurrentSqlDate(), "yyyy-MM-dd"));
		return new ModelAndView("auditTrailReport");
	}

	@RequestMapping(value = "/auditTrailReport", method = RequestMethod.POST)
	public ResponseEntity<?> getAllAuditTrailReportExcel(
			@Valid @ModelAttribute(value = "formObj") FReport bean,
			HttpServletRequest request, HttpSession session) {

		InputStreamResource resource = null;
		HttpHeaders headers = new HttpHeaders();
		XSSFWorkbook wb;
		String path = session.getServletContext().getRealPath("/");
		File file = new File(path
				+ "\\WEB-INF\\resource\\template\\auditTrailReport.xlsx");
		String path1 = file.getPath();
		ByteArrayOutputStream bos = null;
		try {
			List<FReport> beans = reportService.getAllAuditTrailReport(bean);
			wb = new XSSFWorkbook(new FileInputStream(path1));
			Sheet sheet = wb.getSheetAt(0);

			Row row = null;
			int y = 4;
			for (int i = 0; i < beans.size(); i++) {
				row = sheet.createRow(y++);
				FReport beanTemp = beans.get(i);
				bean.createCell(row, (short) 0, beanTemp.getRegistrationTime());
				bean.createCell(row, (short) 1, beanTemp.getQueueNo());
				bean.createCell(row, (short) 2, beanTemp.getDepartmentName());
				bean.createCell(row, (short) 3, beanTemp.getTerminalName());
				bean.createCell(row, (short) 4, "");
				for (int j = 0; j < 5; j++) {
					bean.cellStyleBoder(row, wb, (short) j);
				}
			}
			bos = new ByteArrayOutputStream();
			wb.write(bos);
			wb.close();
			resource = new InputStreamResource(new ByteArrayInputStream(bos.toByteArray()));headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentDispositionFormData("ATTACHMENT","audit_Trail_Report.xlsx");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity
				.ok()
				.headers(headers)
				.contentLength(bos.size())
				.contentType(
						MediaType.parseMediaType("application/octet-stream"))
				.body(resource);
	}

	
	@RequestMapping(value = "/getListauditTrailReport", method = RequestMethod.GET)
	public @ResponseBody List<FReport> getListauditTrailReport(
			@Valid @ModelAttribute(value = "formObj") FReport bean,
			HttpServletRequest request) {
		return reportService.getAllAuditTrailReport(bean);
	}
	
	// Start QueueOpsDashboard
		@RequestMapping(value = "/QueueOpsDashboard")
		public ModelAndView getReports1QueueOpsDashboard(@Valid @ModelAttribute(value = "formObj") FReport bean,HttpServletRequest request) {
			request.setAttribute("listInstitution",reportService.getAllInstitution());
			request.setAttribute("listDepartment", reportService.getAllDepartment());			
			return new ModelAndView("opsDashboard");
		}
		
		// view data in grid
		@RequestMapping(value = "/getListQueueOpsDashboard", method = RequestMethod.GET)
		public @ResponseBody List<FReport> getListQueueOpsDashboard(
				@Valid @ModelAttribute(value = "formObj") FReport bean,
				HttpServletRequest request) {
			return reportService.getOpsDashboard(bean);
		}
		
		@RequestMapping(value = "/QueueOpsDashboard", method = RequestMethod.POST)
		public ResponseEntity<?> getOpsDashboardExcel(
				@Valid @ModelAttribute(value = "formObj") FReport bean,
				HttpServletRequest request, HttpSession session) {

			InputStreamResource resource = null;
			HttpHeaders headers = new HttpHeaders();
			XSSFWorkbook wb;
			String path = session.getServletContext().getRealPath("/");
			File file = new File(path
					+ "\\WEB-INF\\resource\\template\\reportOpsDashboard.xlsx");
			String path1 = file.getPath();
			ByteArrayOutputStream bos = null;
			try {
				List<FReport> beans = reportService.getOpsDashboard(bean);
				wb = new XSSFWorkbook(new FileInputStream(path1));
				Sheet sheet = wb.getSheetAt(0);
				
				Row row1 = null;
				row1 = sheet.createRow(3);
				bean.createCell(row1, (short) 1, "Polyclinic:");
				if (bean.getInstitutionId() != null && bean.getInstitutionId() > 0) {
					FReport beanTemp = reportService.getInstitutionById(bean.getInstitutionId());
					if (beanTemp != null && beanTemp.getInstitutionName() != null){
						bean.createCell(row1, (short) 3,beanTemp.getInstitutionName());
					}					
				} else {
					bean.createCell(row1, (short) 3, "All");
				}
				bean.cellStyleBoderMarge(3, 3, 1, 2, sheet, wb, row1 );
				bean.cellStyleBoderMarge(3, 3, 3, 4, sheet, wb, row1 );
				

				bean.createCell(row1, (short) 5, "Visit Type:");
				if (bean.getDepartmentId() != null && bean.getDepartmentId() > 0) {
					FReport beanTemp = reportService.getDepartmentById(bean.getDepartmentId());
					if (beanTemp != null && beanTemp.getDepartmentName() != null)
						bean.createCell(row1, (short) 6,beanTemp.getDepartmentName());
				} else {
					bean.createCell(row1, (short) 6, "All");
				}
				bean.cellStyleBoderBold(row1, wb, (short) 5);
				bean.cellStyleBoderBold(row1, wb, (short) 6);

				Row row = null;
				int y = 8;
				for (int i = 0; i < beans.size(); i++) {
					row = sheet.createRow(y++);
					FReport beanTemp = beans.get(i);
					bean.createCell(row, (short) 0, beanTemp.getWorkgroupName());
					bean.createCell(row, (short) 1, beanTemp.getQueueNoNotINyet());
					bean.createCell(row, (short) 2, beanTemp.getTotalArrivedSofar());
					bean.createCell(row, (short) 3, beanTemp.getQueueNoIn());
					bean.createCell(row, (short) 4, beanTemp.getG5m());
					bean.createCell(row, (short) 5, beanTemp.getG5t30m());
					bean.createCell(row, (short) 6, beanTemp.getG30m());
					bean.createCell(row, (short) 7, beanTemp.getMaxWT());
					bean.createCell(row, (short) 8, beanTemp.getStAvg());
					bean.createCell(row, (short) 9, beanTemp.getStMax());
					bean.createCell(row, (short) 10, beanTemp.getPtSeen());
					for (int j = 0; j < 11; j++) {
						bean.cellStyleBoder(row, wb, (short) j);
					}
				}
				bos = new ByteArrayOutputStream();
				wb.write(bos);
				wb.close();
				resource = new InputStreamResource(new ByteArrayInputStream(
						bos.toByteArray()));
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.setContentDispositionFormData("ATTACHMENT",
						"queueOpsDashboard.xlsx");

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return ResponseEntity
					.ok()
					.headers(headers)
					.contentLength(bos.size())
					.contentType(
							MediaType.parseMediaType("application/octet-stream"))
					.body(resource);
		}

		// Start QueueOpsDashboard
		
		
		
		    // Start 4.1.5	Uncompleted Journey/ Charge Reversal Report
				@RequestMapping(value = "/JourneyReversal")
				public ModelAndView getReportsJourneyReversal(
						@Valid @ModelAttribute(value = "formObj") FReport bean,
						HttpServletRequest request) {
					request.setAttribute("listInstitution",reportService.getAllInstitution());		
					request.setAttribute("curentDate",bean.dateToString(bean.getCurrentSqlDate(), "yyyy-MM-dd"));
					return new ModelAndView("journeyReversal");
				}
				
				// view data in grid
				@RequestMapping(value = "/getListJourneyReversal", method = RequestMethod.GET)
				public @ResponseBody List<FReport> getListJourneyReversal(
						@Valid @ModelAttribute(value = "formObj") FReport bean,
						HttpServletRequest request) {
					return reportService.getJourneyReversal(bean);
				}
				
				@RequestMapping(value = "/JourneyReversal", method = RequestMethod.POST)
				public ResponseEntity<?> getJourneyReversalExcel(
						@Valid @ModelAttribute(value = "formObj") FReport bean,
						HttpServletRequest request, HttpSession session) {

					InputStreamResource resource = null;
					HttpHeaders headers = new HttpHeaders();
					XSSFWorkbook wb;
					String path = session.getServletContext().getRealPath("/");
					File file = new File(path
							+ "\\WEB-INF\\resource\\template\\journeyReversal.xlsx");
					String path1 = file.getPath();
					ByteArrayOutputStream bos = null;
					try {
						List<FReport> beans = reportService.getJourneyReversal(bean);
						wb = new XSSFWorkbook(new FileInputStream(path1));
						Sheet sheet = wb.getSheetAt(0);
						
						Row row1 = null;
						row1 = sheet.createRow(3);
						bean.createCell(row1, (short) 1, "Visit Date From:");						
						bean.createCell(row1, (short) 2, bean.getStartDate());
						bean.cellStyleBoderBold(row1, wb, (short) 1);
						bean.cellStyleBoderBold(row1, wb, (short) 2);
						

						bean.createCell(row1, (short) 3, "Visit Date End:");
						bean.createCell(row1, (short) 4, bean.getEndDate());					
						bean.cellStyleBoderBold(row1, wb, (short) 3);
						bean.cellStyleBoderBold(row1, wb, (short) 4);
						
						row1 = sheet.createRow(4);
						bean.createCell(row1, (short) 1, "Polyclinic:");
						bean.cellStyleBoderBold(row1, wb, (short) 1);
					
						if (bean.getInstitutionId() != null && bean.getInstitutionId() > 0) {
							FReport beanTemp = reportService.getInstitutionById(bean.getInstitutionId());
							if (beanTemp != null && beanTemp.getInstitutionName() != null){
								bean.createCell(row1, (short) 2,beanTemp.getInstitutionName());
							}					
						} else {
							bean.createCell(row1, (short) 2, "All");
						}
						bean.cellStyleBoderMarge(4, 4, 2, 4, sheet, wb, row1 );

						Row row = null;
						int y = 7;
						for (int i = 0; i < beans.size(); i++) {
							row = sheet.createRow(y++);
							FReport beanTemp = beans.get(i);
							bean.createCell(row, (short) 0, beanTemp.getCustomerIc());
							bean.createCell(row, (short) 1, beanTemp.getCustomerName());
							bean.createCell(row, (short) 2, beanTemp.getInstitutionName());
							bean.createCell(row, (short) 3, beanTemp.getWorkgroupName());
							bean.createCell(row, (short) 4, beanTemp.getVisitType());
							bean.createCell(row, (short) 5, beanTemp.getApptTimestamp());
							for (int j = 0; j < 6; j++) {
								bean.cellStyleBoder(row, wb, (short) j);
							}
						}
						bos = new ByteArrayOutputStream();
						wb.write(bos);
						wb.close();
						resource = new InputStreamResource(new ByteArrayInputStream(
								bos.toByteArray()));
						headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
						headers.setContentDispositionFormData("ATTACHMENT",
								"journeyReversal.xlsx");

					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return ResponseEntity
							.ok()
							.headers(headers)
							.contentLength(bos.size())
							.contentType(
									MediaType.parseMediaType("application/octet-stream"))
							.body(resource);
				}

				// Start 4.1.5	Uncompleted Journey/ Charge Reversal Report

}
