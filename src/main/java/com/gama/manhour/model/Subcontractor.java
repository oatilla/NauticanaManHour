package com.gama.manhour.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "SUBCONTRACTOR", schema = "ADSAAT")
public class Subcontractor implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int subcontractorId;
	private String caption;
	private Set<Worker> workers = new HashSet<Worker>(0);

	public Subcontractor() {
	}

	public Subcontractor(int subcontractorId, String caption) {
        this.subcontractorId = subcontractorId;
        this.caption = caption;
    }

	public Subcontractor(int subcontractorId, String caption, Set<Worker> workers) {
       this.subcontractorId = subcontractorId;
       this.caption = caption;
       this.workers = workers;
    }

	@Id

	@Column(name = "SUBCONTRACTOR_ID", unique = true, nullable = false, precision = 8, scale = 0)
	public int getSubcontractorId() {
		return this.subcontractorId;
	}

	public void setSubcontractorId(int subcontractorId) {
		this.subcontractorId = subcontractorId;
	}

	@Column(name="CAPTION", nullable=false, length=50)
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

}
