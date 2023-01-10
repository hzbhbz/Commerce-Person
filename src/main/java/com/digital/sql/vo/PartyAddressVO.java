package com.digital.sql.vo;

import lombok.Data;

@Data
public class PartyAddressVO {

	private long personId;
	private long addressId;

	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}

	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

}
