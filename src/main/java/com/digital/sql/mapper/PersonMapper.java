package com.digital.sql.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.digital.sql.vo.AddressVO;
import com.digital.sql.vo.PartyAddressVO;
import com.digital.sql.vo.PartyPhoneVO;
import com.digital.sql.vo.PersonVO;
import com.digital.sql.vo.PhoneVO;

@Mapper
public interface PersonMapper {

	public PersonVO getPerson(String personName);

	public PhoneVO getPhone(String phoneNumber);

	public PhoneVO getPhoneById(long phoneId);

	public AddressVO getAddress(String addressDetail);

	public AddressVO getAddressById(long addressId);

	public void setPerson(PersonVO personVO);

	public void setPhone(PhoneVO phoneVO);

	public void setAddress(AddressVO addressVO);

	public void setPartyPhone(PartyPhoneVO partyPhoneVO);

	public void setPartyAddress(PartyAddressVO partyAddressVO);

}
