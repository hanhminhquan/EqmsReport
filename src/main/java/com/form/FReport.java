package com.form;

public class FReport extends FCommon {

	private Long secureId;
	private Integer institutionId;
	private Integer departmentId;
	private Integer workgroupId;
	private String institutionName;
	private String institutionCode;
	private String departmentName;
	private String workgroupName;
	private String QueueNo;
	private String CustomerIc;
	private String CustomerName;
	private String visitType;
	private String ApptTimestamp;
	private String RegistrationTime;
	private Integer QueueStatusId;
	private String StatusDescription;
	private String vipNormal;	
	private String waitingTimeStart;
	private String waitingTimeEnd;
	private String servingTimeStart;
	private String servingTimeEnd;
	private String sapBapiActRemark;
	private String extractedDate;
	private String wt;
	private String st;
	private String turnaroundTime;
	private Integer liveApptSocketId;
	private String patientName;
	private String curentDate;
	private String startDate;
	private String endDate;
	
	private String queueNoNotINyet;
	private String totalArrivedSofar;
	private String queueNoIn;
	private String g5m;
	private String g5t30m;
	private String g30m;
	private String maxWT;
	private String stAvg;
	private String stMax;
	private String ptSeen;
	private String firstPtCallTime;
	private Integer visitTypeId;
	private String visitTypeName;
	private String visitTypeDescription;
	private String membersdepId;
	private String membersWorkgroupId;
	private String membersVisitTypeId;
	private String membersServiceType;
	private String serviceDate;	
	private Integer terminalId;
	private String terminalName;
	private int viewSelection;
	
	private Integer id;
	private String name;
	private Integer userId;
	private String userName;
	
	
	public Long getSecureId() {
		return secureId;
	}

	public void setSecureId(Long secureId) {
		this.secureId = secureId;
	}

	public Integer getInstitutionId() {
		return institutionId;
	}

	public void setInstitutionId(Integer institutionId) {
		this.institutionId = institutionId;
	}

	public Integer getWorkgroupId() {
		return workgroupId;
	}

	public void setWorkgroupId(Integer workgroupId) {
		this.workgroupId = workgroupId;
	}

	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getWorkgroupName() {
		return workgroupName;
	}

	public void setWorkgroupName(String workgroupName) {
		this.workgroupName = workgroupName;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getQueueNo() {
		return QueueNo;
	}

	public void setQueueNo(String queueNo) {
		QueueNo = queueNo;
	}

	public String getCustomerIc() {
		return CustomerIc;
	}

	public void setCustomerIc(String customerIc) {
		CustomerIc = customerIc;
	}

	public String getCustomerName() {
		return CustomerName;
	}

	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}

	public String getVisitType() {
		return visitType;
	}

	public void setVisitType(String visitType) {
		visitType = visitType;
	}

	public String getApptTimestamp() {
		return ApptTimestamp;
	}

	public void setApptTimestamp(String apptTimestamp) {
		ApptTimestamp = apptTimestamp;
	}

	public String getRegistrationTime() {
		return RegistrationTime;
	}

	public void setRegistrationTime(String registrationTime) {
		RegistrationTime = registrationTime;
	}

	public Integer getQueueStatusId() {
		return QueueStatusId;
	}

	public void setQueueStatusId(Integer queueStatusId) {
		QueueStatusId = queueStatusId;
	}

	public String getStatusDescription() {
		return StatusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		StatusDescription = statusDescription;
	}

	public String getVipNormal() {
		return vipNormal;
	}

	public void setVipNormal(String vipNormal) {
		this.vipNormal = vipNormal;
	}

	public String getExtractedDate() {
		return extractedDate;
	}

	public void setExtractedDate(String extractedDate) {
		this.extractedDate = extractedDate;
	}

	public String getWaitingTimeStart() {
		return waitingTimeStart;
	}

	public void setWaitingTimeStart(String waitingTimeStart) {
		this.waitingTimeStart = waitingTimeStart;
	}

	public String getWaitingTimeEnd() {
		return waitingTimeEnd;
	}

	public void setWaitingTimeEnd(String waitingTimeEnd) {
		this.waitingTimeEnd = waitingTimeEnd;
	}

	public String getServingTimeStart() {
		return servingTimeStart;
	}

	public void setServingTimeStart(String servingTimeStart) {
		this.servingTimeStart = servingTimeStart;
	}

	public String getServingTimeEnd() {
		return servingTimeEnd;
	}

	public void setServingTimeEnd(String servingTimeEnd) {
		this.servingTimeEnd = servingTimeEnd;
	}

	public String getSapBapiActRemark() {
		return sapBapiActRemark;
	}

	public void setSapBapiActRemark(String sapBapiActRemark) {
		this.sapBapiActRemark = sapBapiActRemark;
	}

	public String getWt() {
		return wt;
	}

	public void setWt(String wt) {
		this.wt = wt;
	}

	public String getSt() {
		return st;
	}

	public void setSt(String st) {
		this.st = st;
	}

	public String getTurnaroundTime() {
		return turnaroundTime;
	}

	public void setTurnaroundTime(String turnaroundTime) {
		this.turnaroundTime = turnaroundTime;
	}

	public Integer getLiveApptSocketId() {
		return liveApptSocketId;
	}

	public void setLiveApptSocketId(Integer liveApptSocketId) {
		this.liveApptSocketId = liveApptSocketId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getCurentDate() {
		return curentDate;
	}

	public void setCurentDate(String curentDate) {
		this.curentDate = curentDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getQueueNoNotINyet() {
		return queueNoNotINyet;
	}

	public void setQueueNoNotINyet(String queueNoNotINyet) {
		this.queueNoNotINyet = queueNoNotINyet;
	}

	public String getTotalArrivedSofar() {
		return totalArrivedSofar;
	}

	public void setTotalArrivedSofar(String totalArrivedSofar) {
		this.totalArrivedSofar = totalArrivedSofar;
	}

	public String getQueueNoIn() {
		return queueNoIn;
	}

	public void setQueueNoIn(String queueNoIn) {
		this.queueNoIn = queueNoIn;
	}

	public String getG5m() {
		return g5m;
	}

	public void setG5m(String g5m) {
		this.g5m = g5m;
	}

	public String getG5t30m() {
		return g5t30m;
	}

	public void setG5t30m(String g5t30m) {
		this.g5t30m = g5t30m;
	}

	public String getG30m() {
		return g30m;
	}

	public void setG30m(String g30m) {
		this.g30m = g30m;
	}

	public String getMaxWT() {
		return maxWT;
	}

	public void setMaxWT(String maxWT) {
		this.maxWT = maxWT;
	}

	public String getStAvg() {
		return stAvg;
	}

	public void setStAvg(String stAvg) {
		this.stAvg = stAvg;
	}

	public String getStMax() {
		return stMax;
	}

	public void setStMax(String stMax) {
		this.stMax = stMax;
	}

	public String getPtSeen() {
		return ptSeen;
	}

	public void setPtSeen(String ptSeen) {
		this.ptSeen = ptSeen;
	}

	public String getFirstPtCallTime() {
		return firstPtCallTime;
	}

	public void setFirstPtCallTime(String firstPtCallTime) {
		this.firstPtCallTime = firstPtCallTime;
	}

	public Integer getVisitTypeId() {
		return visitTypeId;
	}

	public void setVisitTypeId(Integer visitTypeId) {
		this.visitTypeId = visitTypeId;
	}

	public String getVisitTypeName() {
		return visitTypeName;
	}

	public void setVisitTypeName(String visitTypeName) {
		this.visitTypeName = visitTypeName;
	}

	public String getMembersdepId() {
		return membersdepId;
	}

	public void setMembersdepId(String membersdepId) {
		this.membersdepId = membersdepId;
	}

	public String getMembersWorkgroupId() {
		return membersWorkgroupId;
	}

	public void setMembersWorkgroupId(String membersWorkgroupId) {
		this.membersWorkgroupId = membersWorkgroupId;
	}

	public String getMembersVisitTypeId() {
		return membersVisitTypeId;
	}

	public void setMembersVisitTypeId(String membersVisitTypeId) {
		this.membersVisitTypeId = membersVisitTypeId;
	}

	public String getMembersServiceType() {
		return membersServiceType;
	}

	public void setMembersServiceType(String membersServiceType) {
		this.membersServiceType = membersServiceType;
	}

	public String getVisitTypeDescription() {
		return visitTypeDescription;
	}

	public void setVisitTypeDescription(String visitTypeDescription) {
		this.visitTypeDescription = visitTypeDescription;
	}

	public String getServiceDate() {
		return serviceDate;
	}

	public void setServiceDate(String serviceDate) {
		this.serviceDate = serviceDate;
	}

	
	public String getTerminalName() {
		return terminalName;
	}

	public void setTerminalName(String terminalName) {
		this.terminalName = terminalName;
	}

	public Integer getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(Integer terminalId) {
		this.terminalId = terminalId;
	}

	public int getViewSelection() {
		return viewSelection;
	}

	public void setViewSelection(int viewSelection) {
		this.viewSelection = viewSelection;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getInstitutionCode() {
		return institutionCode;
	}

	public void setInstitutionCode(String institutionCode) {
		this.institutionCode = institutionCode;
	}

	

}
