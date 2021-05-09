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
public class User {

	@Id
	@Column(name="user_id")
	private int userId;
	@Column(name="user_name")
	private String userName;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<DocRead> docRead = new HashSet<DocRead>();

	public Set<DocRead> getDocRead() {
		return docRead;
	}

	public void setDocRead(Set<DocRead> docRead) {
		this.docRead = docRead;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((docRead == null) ? 0 : docRead.hashCode());
		result = prime * result + userId;
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		User other = (User) obj;
		if (docRead == null) {
			if (other.docRead != null)
				return false;
		} else if (!docRead.equals(other.docRead))
			return false;
		if (userId != other.userId)
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	public User(int userId, String userName, Set<DocRead> docRead) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.docRead = docRead;
	}

	public User() {
		super();
	}

	
	
}
