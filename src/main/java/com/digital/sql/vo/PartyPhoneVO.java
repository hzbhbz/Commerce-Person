package com.digital.sql.vo;

import lombok.Data;

@Data
public class PartyPhoneVO {

	private long phoneId;
	private long personId;

	public long getPhoneId() {
		return phoneId;
	}

	public void setPhoneId(long phoneId) {
		this.phoneId = phoneId;
	}

	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}

}
