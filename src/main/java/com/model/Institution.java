package com.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Institution {
	
    @Id
    @GeneratedValue
    @Basic(optional=true)
    @Column(name="institutionId")
	private Long institutionId;
    
    @Basic(optional=true)
    @Column(name="institutionName", columnDefinition = "nvarchar(255)")
	private String institutionName;
    
    @Basic(optional=true)
    @Column(name="institutionDesc", columnDefinition = "nvarchar(255)")
	private String institutionDesc;
    
    @Basic(optional=true)
    @Column(name="institutionCode", columnDefinition = "nvarchar(255)")
	private String institutionCode;
    
    @Basic(optional=true)
    @Column(name="LocationId")
	private Long LocationId;
    
    @Basic(optional=true)
    @Column(name="InstitutionCodeName", columnDefinition = "nvarchar(255)")
	private String InstitutionCodeName;
	


	
	protected Institution() {
	}




	public Long getInstitutionId() {
		return institutionId;
	}




	public void setInstitutionId(Long institutionId) {
		this.institutionId = institutionId;
	}




	public String getInstitutionName() {
		return institutionName;
	}




	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}




	public String getInstitutionDesc() {
		return institutionDesc;
	}




	public void setInstitutionDesc(String institutionDesc) {
		this.institutionDesc = institutionDesc;
	}




	public String getInstitutionCode() {
		return institutionCode;
	}




	public void setInstitutionCode(String institutionCode) {
		this.institutionCode = institutionCode;
	}




	public Long getLocationId() {
		return LocationId;
	}




	public void setLocationId(Long locationId) {
		LocationId = locationId;
	}




	public String getInstitutionCodeName() {
		return InstitutionCodeName;
	}




	public void setInstitutionCodeName(String institutionCodeName) {
		InstitutionCodeName = institutionCodeName;
	}

	
}