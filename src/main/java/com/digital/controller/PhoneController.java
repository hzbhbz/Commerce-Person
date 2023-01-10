package com.digital.controller;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.digital.schema.ErrorMsg;
import com.digital.schema.Phone;
import com.digital.service.PhoneService;
import com.digital.utils.ExceptionUtils;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "전화번호", description = "Phone Related API")
@RequestMapping(value = "/rest/phone")

public class PhoneController {

	@Resource
	PhoneService phoneSvc;

	@RequestMapping(value = "/info", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "전화번호 정보", notes = "PhoneID 찾기 위한 API.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = Phone.class),
			@ApiResponse(code = 500, message = "실패", response = ErrorMsg.class) })
	public ResponseEntity<?> phoneInfo(@Parameter(name = "전화번호 정보", required = true) @RequestBody Phone phone)
			throws Exception {
		MultiValueMap<String, String> header = new LinkedMultiValueMap<String, String>();
		ErrorMsg errors = new ErrorMsg();

		Phone phoneRes = phoneSvc.searchPhone(phone.getPhoneNumber());
		try {
			if (phoneRes != null) {
				phone = phoneSvc.searchPhoneById(phoneRes);
			}
		} catch (Exception e) {
			return ExceptionUtils.setException(errors, 500, e.getMessage(), header);
		}
		return new ResponseEntity<Phone>(phone, header, HttpStatus.valueOf(200));
	}

	@RequestMapping(value = "/detailinfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "전화번호 정보", notes = "PhoneNumber 찾기 위한 API.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = Phone.class),
			@ApiResponse(code = 500, message = "실패", response = ErrorMsg.class) })
	public ResponseEntity<?> phoneNumberInfo(@Parameter(name = "전화번호 정보", required = true) @RequestBody Phone phone)
			throws Exception {
		MultiValueMap<String, String> header = new LinkedMultiValueMap<String, String>();
		ErrorMsg errors = new ErrorMsg();

		Phone phoneRes = new Phone();
		try {
			phoneRes = phoneSvc.searchPhoneById(phone);
		} catch (Exception e) {
			return ExceptionUtils.setException(errors, 500, e.getMessage(), header);
		}
		return new ResponseEntity<Phone>(phoneRes, header, HttpStatus.valueOf(200));
	}

	@RequestMapping(value = "/write", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "전화번호 등록", notes = "전화번호를 등록하기 위한 API. *입력 필드: phoneNumber")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = Phone.class),
			@ApiResponse(code = 500, message = "실패", response = ErrorMsg.class) })
	public ResponseEntity<?> phoneWrite(@Parameter(name = "전화번호 정보", required = true) @RequestBody Phone phone) {
		MultiValueMap<String, String> header = new LinkedMultiValueMap<String, String>();
		ErrorMsg errors = new ErrorMsg();

		Phone resPhone = new Phone();
		try {
			if (phoneSvc.insertPhoneVO(phone)) {
				resPhone = phoneSvc.searchPhone(phone.getPhoneNumber());
			}
		} catch (Exception e) {
			return ExceptionUtils.setException(errors, 500, e.getMessage(), header);
		}

		return new ResponseEntity<Phone>(resPhone, header, HttpStatus.valueOf(200));
	}

	@RequestMapping(value = "/inquiry/{keyword}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "전화번호 검색", notes = "전화번호 상세로 전화번호 정보를 검색하기 위한 API.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = Phone.class),
			@ApiResponse(code = 500, message = "실패", response = ErrorMsg.class) })
	public ResponseEntity<?> phoneSearch(@Parameter(name = "전화번호 상세", required = true) @PathVariable String keyword) {
		MultiValueMap<String, String> header = new LinkedMultiValueMap<String, String>();
		ErrorMsg errors = new ErrorMsg();

		try {
			Phone phone = phoneSvc.searchPhone(keyword);
			return new ResponseEntity<Phone>(phone, header, HttpStatus.valueOf(200));
		} catch (Exception e) {
			return ExceptionUtils.setException(errors, 500, e.getMessage(), header);
		}
	}
}
