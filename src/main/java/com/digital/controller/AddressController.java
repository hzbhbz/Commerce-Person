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

import com.digital.schema.Address;
import com.digital.schema.ErrorMsg;
import com.digital.schema.Phone;
import com.digital.service.AddressService;
import com.digital.utils.ExceptionUtils;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "주소", description = "Address Related API")
@RequestMapping(value = "/rest/address")

public class AddressController {

	@Resource
	AddressService addressSvc;
	
	@RequestMapping(value = "/info", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "주소 정보", notes = "addressID 찾기 위한 API.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공", response = Phone.class),
		@ApiResponse(code = 500, message = "실패", response = ErrorMsg.class)
	})
	public ResponseEntity<?> addressInfo (@Parameter(name = "주소 정보", required = true) @RequestBody Address address) throws Exception {
		MultiValueMap<String, String> header = new LinkedMultiValueMap<String, String>();
		ErrorMsg errors = new ErrorMsg();
		
		Address addressRes = addressSvc.searchAddress(address.getAddressDetail());
		try {
			if (addressRes != null) {
				address = addressSvc.searchAddressById(addressRes);
			}
		} catch (Exception e) {
			return ExceptionUtils.setException(errors, 500, e.getMessage(), header);
		}
		return new ResponseEntity<Address>(address, header, HttpStatus.valueOf(200));
	}
	
	@RequestMapping(value = "/detailinfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "주소 정보", notes = "AddressDetail 찾기 위한 API.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = Phone.class),
			@ApiResponse(code = 500, message = "실패", response = ErrorMsg.class) })
	public ResponseEntity<?> addressDetailInfo(@Parameter(name = "주소 정보", required = true) @RequestBody Address address)
			throws Exception {
		MultiValueMap<String, String> header = new LinkedMultiValueMap<String, String>();
		ErrorMsg errors = new ErrorMsg();

		Address addresRes = new Address();
		try {
			addresRes = addressSvc.searchAddressById(address);
		} catch (Exception e) {
			return ExceptionUtils.setException(errors, 500, e.getMessage(), header);
		}
		return new ResponseEntity<Address>(addresRes, header, HttpStatus.valueOf(200));
	}

	@RequestMapping(value = "/write", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "주소 등록", notes = "주소 정보를 등록하기 위한 API. *입력 필드: addressDetail")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = Address.class),
			@ApiResponse(code = 500, message = "실패", response = ErrorMsg.class) })
	public ResponseEntity<?> addressWrite(@Parameter(name = "주소 정보", required = true) @RequestBody Address address) {
		MultiValueMap<String, String> header = new LinkedMultiValueMap<String, String>();
		ErrorMsg errors = new ErrorMsg();

		Address resAddress = new Address();
		try {
			if (addressSvc.insertAddresVO(address)) {
				resAddress = addressSvc.searchAddress(address.getAddressDetail());
			}
		} catch (Exception e) {
			return ExceptionUtils.setException(errors, 500, e.getMessage(), header);
		}
		return new ResponseEntity<Address>(resAddress, header, HttpStatus.valueOf(200));
	}

	@RequestMapping(value = "/inquiry/{keyword}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "주소 검색", notes = "주소 상세로 주소 정보를 검색하기 위한 API.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공", response = Address.class),
			@ApiResponse(code = 500, message = "실패", response = ErrorMsg.class) })
	public ResponseEntity<?> addressSearch(
			@Parameter(name = "주소 상세", required = true) @PathVariable String keyword) {
		MultiValueMap<String, String> header = new LinkedMultiValueMap<String, String>();
		ErrorMsg errors = new ErrorMsg();

		try {
			Address address = addressSvc.searchAddress(keyword);
			return new ResponseEntity<Address>(address, header, HttpStatus.valueOf(200));
		} catch (Exception e) {
			return ExceptionUtils.setException(errors, 500, e.getMessage(), header);
		}
	}

}
