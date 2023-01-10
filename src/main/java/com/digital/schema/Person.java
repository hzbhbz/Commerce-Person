package com.digital.schema;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class Person {

	@ApiModelProperty(required = false, position = 1, example = "1001", dataType = "long", notes = "고객 ID")
	private long personId;
	@ApiModelProperty(required = true, position = 2, example = "고객명", dataType = "string", notes = "고객명")
	private String personName;
	@ApiModelProperty(required = true, position = 3, example = "비밀번호", dataType = "string", notes = "비밀번호")
	private String passwd;
	@ApiModelProperty(required = true, position = 4, example = "남/여", dataType = "string", notes = "성별")
	private String gender;
	@ApiModelProperty(required = true, position = 5, example = "[{\"phoneId\":2001,\"phoneNumber\":\"010-3192-0379\"}]", dataType = "array", notes = "전화번호 목록")
	private List<Phone> phoneList;
	@ApiModelProperty(required = true, position = 6, example = "[{\"addressId\":3001,\"addressDetail\":\"경기도 성남시\"}]", dataType = "array", notes = "주소 목록")
	private List<Address> addressList;

	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public List<Phone> getPhoneList() {
		return phoneList;
	}

	public void setPhoneList(List<Phone> phoneList) {
		this.phoneList = phoneList;
	}

	public List<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}

}
