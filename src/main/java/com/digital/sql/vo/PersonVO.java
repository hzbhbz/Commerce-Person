package com.digital.sql.vo;

import java.util.List;

import lombok.Data;

@Data
public class PersonVO {

	private long personId;
	private String password;
	private String gender;
	private String personName;
	private List<PhoneVO> phoneList;
	private List<AddressVO> addressList;

	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public List<PhoneVO> getPhoneList() {
		return phoneList;
	}

	public void setPhoneList(List<PhoneVO> phoneList) {
		this.phoneList = phoneList;
	}

	public List<AddressVO> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<AddressVO> addressList) {
		this.addressList = addressList;
	}

}
