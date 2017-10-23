package com.nauticana.manhour.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ExternalPerson {

	public static final String tableName = "XXADSA_EXT_PERSON";
	public static final String[] fieldNames = new String[] {"PERSON_ID", "FIRST_NAME", "LAST_NAME", "NATIONALITY_ID", "EMAIL", "EMPLOYEE_NUMBER", "SUPERVISOR", "ORGANIZATION_ID", "ORGANIZATION", "POSITION", "SUBCONTRACTOR"};
	public static final String[] fieldAttrs = new String[] {"personId",  "firstName",  "lastName",  "nationalityId",  "email", "employeeNumber",  "supervisor", "organizationId",  "organization", "position", "subcontractor"};

	private int personId;
	private String firstName;
	private String lastName;
	private String nationalityId;
	private String email;
	private String employeeNumber;
	private Integer supervisor;
	private Integer organizationId;
	private String organization;
	private String position;
	private String subcontractor;
	
	public ExternalPerson() {
	}
	

	public ExternalPerson(int personId, String firstName, String lastName, String nationalityId, String email, String employeeNumber, Integer supervisor, Integer organizationId, String organization, String position, String subcontractor) {
		super();
		this.personId = personId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.nationalityId = nationalityId;
		this.email = email;
		this.employeeNumber = employeeNumber;
		this.supervisor = supervisor;
		this.organizationId = organizationId;
		this.organization = organization;
		this.position = position;
		this.subcontractor = subcontractor;
	}

	@Id
	@Column(name="PERSON_ID")
	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}


	@Column(name="FIRST_NAME")
	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	@Column(name="LAST_NAME")
	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	@Column(name="NATIONALITY_ID")
	public String getNationalityId() {
		return nationalityId;
	}


	public void setNationalityId(String nationalityId) {
		this.nationalityId = nationalityId;
	}


	@Column(name="EMAIL")
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	@Column(name="EMPLOYEE_NUMBER")
	public String getEmployeeNumber() {
		return employeeNumber;
	}


	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}


	@Column(name="SUPERVISOR")
	public Integer getSupervisor() {
		return supervisor;
	}


	public void setSupervisor(Integer supervisor) {
		this.supervisor = supervisor;
	}


	@Column(name="ORGANIZATION_ID")
	public Integer getOrganizationId() {
		return organizationId;
	}


	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}


	@Column(name="ORGANIZATION")
	public String getOrganization() {
		return organization;
	}


	public void setOrganization(String organization) {
		this.organization = organization;
	}


	@Column(name="POSITION")
	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;
	}


	@Column(name="SUBCONTRACTOR")
	public String getSubcontractor() {
		return subcontractor;
	}


	public void setSubcontractor(String subcontractor) {
		this.subcontractor = subcontractor;
	}

	
}
