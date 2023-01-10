package com.digital.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.digital.schema.Address;
import com.digital.schema.Person;
import com.digital.schema.Phone;
import com.digital.sql.mapper.PersonMapper;
import com.digital.sql.vo.AddressVO;
import com.digital.sql.vo.PartyAddressVO;
import com.digital.sql.vo.PartyPhoneVO;
import com.digital.sql.vo.PersonVO;
import com.digital.sql.vo.PhoneVO;

@Component
public class PersonService {

	@Resource
	AddressService addressSvc;
	@Resource
	PhoneService phoneSvc;
	@Resource
	PersonMapper personMapper;

	// 회원 검색
	public Person searchPerson(String personName) throws Exception {

		Person person = new Person();
		Phone phone = new Phone();
		Address address = new Address();
		List<Phone> phoneList = new ArrayList<Phone>();
		List<Address> addressList = new ArrayList<Address>();

		PersonVO personVO = personMapper.getPerson(personName);

		if (personVO != null) {
			person.setPersonId(personVO.getPersonId());
			person.setPersonName(personVO.getPersonName());
			person.setPasswd(personVO.getPassword());
			person.setGender(personVO.getGender());

			for (PhoneVO phoneVO : personVO.getPhoneList()) {
				phone = phoneSvc.insertPhone(phoneVO);
				phoneList.add(phone);
			}
			person.setPhoneList(phoneList);

			for (AddressVO addressVO : personVO.getAddressList()) {
				address = addressSvc.insertAddress(addressVO);
				addressList.add(address);
			}
			person.setAddressList(addressList);
		}
		return person;
	}

	// 회원가입
	public boolean signUp(Person person) throws Exception {

		PersonVO personVO = new PersonVO();
		PartyPhoneVO ppVO = new PartyPhoneVO();
		PartyAddressVO paVO = new PartyAddressVO();

		try {
			// 회원 중복 여부
			if (personMapper.getPerson(person.getPersonName()) != null) {
				throw new Exception("가입된 회원입니다.");
			}
			person.setPersonId(System.currentTimeMillis());

			personVO.setPersonId(person.getPersonId());
			personVO.setPassword(person.getPasswd());
			personVO.setGender(person.getGender());
			personVO.setPersonName(person.getPersonName());

			personMapper.setPerson(personVO);

			// phone
			List<Phone> phoneList = person.getPhoneList();
			for (Phone phone : phoneList) {
				try {
					if (phoneSvc.insertPhoneVO(phone)) {
						// partyphone
						ppVO.setPhoneId(phone.getPhoneId());
						ppVO.setPersonId(person.getPersonId());
						personMapper.setPartyPhone(ppVO);
					}
				} catch (Exception e) {
					ppVO.setPhoneId(phone.getPhoneId());
					ppVO.setPersonId(person.getPersonId());
					personMapper.setPartyPhone(ppVO);
				}
			}
			// address
			List<Address> addressList = person.getAddressList();
			for (Address address : addressList) {
				try {
					if (addressSvc.insertAddresVO(address)) {
						// partyaddress
						paVO.setAddressId(address.getAddressId());
						paVO.setPersonId(person.getPersonId());
						personMapper.setPartyAddress(paVO);
					}
				} catch (Exception e) {
					paVO.setAddressId(address.getAddressId());
					paVO.setPersonId(person.getPersonId());
					personMapper.setPartyAddress(paVO);
				}
			}
			return true;

		} catch (Exception e) {
			throw e;
		}
	}

	// 로그인
	public Person login(Person person) throws Exception {

		Person person_res = searchPerson(person.getPersonName());

		if (person_res.getPersonName().equals(person.getPersonName())) {
			if (person_res.getPasswd().equals(person.getPasswd())) {
				person.setPersonId(person_res.getPersonId());				
				return person;
			}
		}
		throw new Exception("회원 이름/비밀번호가 잘못되었습니다.");
	}

}
