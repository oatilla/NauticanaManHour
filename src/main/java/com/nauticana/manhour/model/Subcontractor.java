package com.nauticana.manhour.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SUBCONTRACTOR")
public class Subcontractor implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String tableName = "SUBCONTRACTOR";
	public static final String[] fieldNames = new String[] { "SUBCONTRACTOR_ID", "CAPTION", "EXT_SUBCONTRACTOR" };
	public static final String rootMapping = "subcontractor";

	private int subcontractorId;
	private String caption;
	private Set<Worker> workers = new HashSet<Worker>(0);
	private String extSubcontractor;

	public Subcontractor() {
	}

	public Subcontractor(int subcontractorId, String caption) {
		this.subcontractorId = subcontractorId;
		this.caption = caption;
	}

	public Subcontractor(int subcontractorId, String caption, Set<Worker> workers, String extSubcontractor) {
		this.subcontractorId = subcontractorId;
		this.caption = caption;
		this.workers = workers;
		this.extSubcontractor = extSubcontractor;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SUBCONTRACTOR_ID_SEQ")
	@SequenceGenerator(name="SUBCONTRACTOR_ID_SEQ", sequenceName="SUBCONTRACTOR_ID_SEQ")
	@Column(name = "SUBCONTRACTOR_ID", unique = true, nullable = false, precision = 8, scale = 0)
	public int getId() {
		return this.subcontractorId;
	}

	public void setId(int subcontractorId) {
		this.subcontractorId = subcontractorId;
	}

	@Column(name = "CAPTION", nullable = false, length = 50)
	public String getCaption() {
		return this.caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "subcontractor")
	public Set<Worker> getWorkers() {
		return this.workers;
	}

	public void setWorkers(Set<Worker> workers) {
		this.workers = workers;
	}
	
	
	@Column(name = "EXT_SUBCONTRACTOR")
	public String getExtSubcontractor() {
		return this.extSubcontractor;
	}

	public void setExtSubcontractor(String extSubcontractor) {
		this.extSubcontractor = extSubcontractor;
	}

}
