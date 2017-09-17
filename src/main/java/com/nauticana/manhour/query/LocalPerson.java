package com.nauticana.manhour.query;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LocalPerson {

	public static final String tableName = "PERSONNEL";
	public static final String[] fieldNames = new String[] {"PERSON_ID","NAME","SURNAME","SICIL"};

	private int personId;
	private String name;
	private String surname;
	private String sicil;
	
	public LocalPerson() {
	}
	
	public LocalPerson(int pERSON_ID, String nAME, String sURNAME, String sICIL) {
		super();
		personId = pERSON_ID;
		name = nAME;
		surname = sURNAME;
		sicil = sICIL;
	}

	@Id
	@Column(name="PERSON_ID")
	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int pERSON_ID) {
		personId = pERSON_ID;
	}

	@Column(name="NAME")
	public String getName() {
		return name;
	}

	public void setName(String nAME) {
		name = nAME;
	}

	@Column(name="SURNAME")
	public String getSurname() {
		return surname;
	}

	public void setSurname(String sURNAME) {
		surname = sURNAME;
	}

	@Column(name="SICIL")
	public String getSicil() {
		return sicil;
	}

	public void setSicil(String sICIL) {
		sicil = sICIL;
	}
	
	
	
	
}
