package com.challenge.test.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Doc {

	@Id
	@Column(name = "doc_id")
	private int docId;
	@Column(name = "doc_title")
	private String docTitle;

	@OneToMany(mappedBy = "doc", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<DocRead> docRead = new HashSet<DocRead>();

	public Set<DocRead> getDocRead() {
		return docRead;
	}

	public void setDocRead(Set<DocRead> docRead) {
		this.docRead = docRead;
	}

	public int getDocId() {
		return docId;
	}

	public void setDocId(int docId) {
		this.docId = docId;
	}

	public String getDocTitle() {
		return docTitle;
	}

	public void setDocTitle(String docTitle) {
		this.docTitle = docTitle;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + docId;
		result = prime * result + ((docTitle == null) ? 0 : docTitle.hashCode());
		result = prime * result + ((docRead == null) ? 0 : docRead.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Doc other = (Doc) obj;
		if (docId != other.docId)
			return false;
		if (docTitle == null) {
			if (other.docTitle != null)
				return false;
		} else if (!docTitle.equals(other.docTitle))
			return false;
		if (docRead == null) {
			if (other.docRead != null)
				return false;
		} else if (!docRead.equals(other.docRead))
			return false;
		return true;
	}

	public Doc(int docId, String docTitle, Set<DocRead> docRead) {
		super();
		this.docId = docId;
		this.docTitle = docTitle;
		this.docRead = docRead;
	}

	public Doc() {
		super();
	}

}
