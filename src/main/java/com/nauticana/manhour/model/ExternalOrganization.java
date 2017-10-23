package com.nauticana.manhour.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name= "XXADSA_EXT_ORGANIZATION")
public class ExternalOrganization implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String tableName = "XXADSA_EXT_ORGANIZATIONS";
	public static final String[] fieldNames = new String[] {"ORGANIZATION_ID","ORGANIZATION_NAME","COMPANY","LOCATION_ID", "LOCATION_CODE" };
	public static final String rootMapping = "organizationSelect";
	
	private Integer organizationId;
	private String organizationName;
	private String company;
	private Integer locationID;
	private String locationCode;

	public ExternalOrganization() {
		
	}
	
	public ExternalOrganization(Integer organizationId, String organizationName, String company, Integer locationID,
			String locationCode) {
		super();
		this.organizationId = organizationId;
		this.organizationName = organizationName;
		this.company = company;
		this.locationID = locationID;
		this.locationCode = locationCode;
	}
	@Id
	@Column(name = "ORGANIZATION_ID")
	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}
	@Column(name = "ORGANIZATION_NAME")
	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	@Column(name = "COMPANY")
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	@Column(name = "LOCATION_ID")
	public Integer getLocationID() {
		return locationID;
	}

	public void setLocationID(Integer locationID) {
		this.locationID = locationID;
	}
	@Column(name = "LOCATION_CODE")
	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}
	

}
