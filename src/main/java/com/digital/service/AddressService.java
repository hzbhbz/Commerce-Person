package com.digital.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.digital.schema.Address;
import com.digital.sql.mapper.PersonMapper;
import com.digital.sql.vo.AddressVO;

@Component
public class AddressService {

	@Resource
	PersonMapper personMapper;

	// 회원 정보에 등록된 Address 검색
	public Address searchAddress(String addressDetail) throws Exception {

		Address address = new Address();
		AddressVO addressVO = personMapper.getAddress(addressDetail);
		try {
			if (addressVO != null) {
				address.setAddressId(addressVO.getAddressId());
				address.setAddressDetail(addressVO.getAddressDetail());
			}
		} catch (Exception e) {
			throw e;
		}
		return address;
	}

	// AddressId 찾기
	public Address searchAddressById(Address address) throws Exception {

		AddressVO addressVO = personMapper.getAddressById(address.getAddressId());
		Address addressRes = new Address();
		try {
			if (addressVO != null) {
				addressRes.setAddressId(addressVO.getAddressId());
				addressRes.setAddressDetail(addressVO.getAddressDetail());
			}
		} catch (Exception e) {
			throw e;
		}
		return addressRes;
	}

	// addressVO 저장
	public boolean insertAddresVO(Address address) throws Exception {

		AddressVO addressVO = new AddressVO();

		try {
			address.setAddressId(System.currentTimeMillis());

			addressVO.setAddressId(address.getAddressId());
			addressVO.setAddressDetail(address.getAddressDetail());

			personMapper.setAddress(addressVO);

			return true;

		} catch (Exception e) {
			throw e;
		}
	}

	// address 저장
	public Address insertAddress(AddressVO addressVO) {

		Address address = new Address();

		try {
			address.setAddressId(addressVO.getAddressId());
			address.setAddressDetail(addressVO.getAddressDetail());
		} catch (Exception e) {
			throw e;
		}
		return address;
	}
}