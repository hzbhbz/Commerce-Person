package com.digital.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.digital.schema.Phone;
import com.digital.sql.mapper.PersonMapper;
import com.digital.sql.vo.PhoneVO;

@Component
public class PhoneService {

	@Resource
	PersonMapper personMapper;

	// 회원 정보에 등록된 Phone 검색
	public Phone searchPhone(String phoneNumber) throws Exception {

		Phone phone = new Phone();
		PhoneVO phoneVO = personMapper.getPhone(phoneNumber);
		try {
			if (phoneVO != null) {
				phone.setPhoneId(phoneVO.getPhoneId());
				phone.setPhoneNumber(phoneVO.getPhoneNumber());
			}
		} catch (Exception e) {
			throw e;
		}
		return phone;
	}

	// PhoneId 찾기
	public Phone searchPhoneById(Phone phone) throws Exception {

		Phone phoneRes = new Phone();
		PhoneVO phoneVO = personMapper.getPhoneById(phone.getPhoneId());
		try {
			if (phoneVO != null) {
				phoneRes.setPhoneId(phoneVO.getPhoneId());
				phoneRes.setPhoneNumber(phoneVO.getPhoneNumber());
			}
		} catch (Exception e) {
			throw e;
		}
		return phoneRes;
	}

	// phoneVO 저장
	public boolean insertPhoneVO(Phone phone) throws Exception {

		PhoneVO phoneVO = new PhoneVO();

		try {
			phone.setPhoneId(System.currentTimeMillis());

			phoneVO.setPhoneId(phone.getPhoneId());
			phoneVO.setPhoneNumber(phone.getPhoneNumber());

			personMapper.setPhone(phoneVO);

			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	// phone 저장
	public Phone insertPhone(PhoneVO phoneVO) throws Exception {

		Phone phone = new Phone();

		try {
			phone.setPhoneId(phoneVO.getPhoneId());
			phone.setPhoneNumber(phoneVO.getPhoneNumber());
		} catch (Exception e) {
			throw e;
		}
		return phone;
	}

}
