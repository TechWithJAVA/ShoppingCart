package com.yash.shopping.controller;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yash.shopping.constants.ResponseCode;
import com.yash.shopping.constants.WebConstants;
import com.yash.shopping.model.User;
import com.yash.shopping.response.CartResponse;
import com.yash.shopping.response.ProductResponse;
import com.yash.shopping.response.ServerResponse;
import com.yash.shopping.service.UserService;
import com.yash.shopping.util.Validator;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@PostMapping("/verify")
	public ResponseEntity<ServerResponse> verifyUser(@RequestBody Map<String, String> credential) {

		ServerResponse resp = new ServerResponse();
		try {
			resp = userService.checkLoginUser(credential);
			logger.info(resp.getMessage());
		} catch (Exception e) {
			logger.info(resp.getMessage());
			return new ResponseEntity<ServerResponse>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<ServerResponse>(resp, HttpStatus.OK);

	}

	@PostMapping("/signup")
	public ResponseEntity<ServerResponse> addUser(@RequestBody User user) {
		ServerResponse resp = new ServerResponse();
		try {
			resp = userService.signUpUser(user);
			logger.info(resp.getMessage());
		} catch (Exception e) {
			logger.info(resp.getMessage());
			return new ResponseEntity<ServerResponse>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<ServerResponse>(resp, HttpStatus.ACCEPTED);
	}
//
//	@PostMapping("/addAddress")
//	public ResponseEntity<UserResponse> addAddress(@Valid @RequestBody Address address,
//			@RequestHeader(name = WebConstants.USER_MOBILE) String mobile) {
//		UserResponse resp = new UserResponse();
//		Validator validator = new Validator();
////		if (validator.isAddressEmpty(address)) {
////			resp.setStatus(ResponseCode.BAD_REQUEST_CODE);
////			resp.setMessage(ResponseCode.BAD_REQUEST_MESSAGE);
////		} else if (!validator.isStringEmpty(mobile)) {
////			resp = userService.AddAddress(mobile, address);
////		} else {
////			resp.setStatus(ResponseCode.FAILURE_CODE);
////			resp.setMessage(ResponseCode.FAILURE_MESSAGE);
////		}
//		return new ResponseEntity<UserResponse>(resp, HttpStatus.ACCEPTED);
//	}

//	@PostMapping("/getAddress")
//	public ResponseEntity<Response> getAddress(@RequestHeader(name = WebConstants.USER_MOBILE) String mobile) {
//
//		Response resp = new Response();
//		Validator validator = new Validator();
//		if (!validator.isStringEmpty(mobile)) {
//			resp = userService.getAddress(mobile);
//		} else {
//			resp.setStatus(ResponseCode.FAILURE_CODE);
//			resp.setMessage(ResponseCode.FAILURE_MESSAGE);
//		}
//		return new ResponseEntity<Response>(resp, HttpStatus.ACCEPTED);
//	}

	@PostMapping("/getProducts")
	public ResponseEntity<ProductResponse> getProducts() throws IOException {
		ProductResponse resp = new ProductResponse();
		try {
			resp.setStatus(ResponseCode.SUCCESS_CODE);
			resp.setMessage(ResponseCode.LIST_SUCCESS_MESSAGE);
			resp.setOblist(userService.getAllProduct());
			logger.info(resp.getMessage());
		} catch (Exception e) {
			resp.setStatus(ResponseCode.FAILURE_CODE);
			resp.setMessage(e.getMessage());
			logger.info(resp.getMessage());
		}
		return new ResponseEntity<ProductResponse>(resp, HttpStatus.ACCEPTED);
	}

	@GetMapping("/addToCart")
	public ResponseEntity<ServerResponse> addToCart(@RequestHeader(name = WebConstants.USER_MOBILE) String mobile,
			@RequestParam(WebConstants.PROD_ID) String productId) throws IOException {

		ServerResponse resp = new ServerResponse();
		Validator validator = new Validator();
		if (!validator.isStringEmpty(mobile) && !validator.isStringEmpty(productId)) {
			resp = userService.addProductToCart(mobile, productId);

		} else {
			resp.setStatus(ResponseCode.FAILURE_CODE);
			resp.setMessage(ResponseCode.FAILURE_MESSAGE);
		}
		return new ResponseEntity<ServerResponse>(resp, HttpStatus.ACCEPTED);
	}

	@GetMapping("/viewCart")
	public ResponseEntity<CartResponse> viewCart(@RequestHeader(name = WebConstants.USER_MOBILE) String mobile)
			throws IOException {
		logger.info("Inside View cart request method");
		CartResponse resp = new CartResponse();
		Validator validator = new Validator();
		if (!validator.isStringEmpty(mobile)) {
			resp = userService.viewCart(mobile);

		} else {
			resp.setStatus(ResponseCode.FAILURE_CODE);
			resp.setMessage(ResponseCode.FAILURE_MESSAGE);
		}
		return new ResponseEntity<CartResponse>(resp, HttpStatus.ACCEPTED);
	}

	@GetMapping("/updateCart")
	public ResponseEntity<CartResponse> updateCart(@RequestHeader(name = WebConstants.USER_MOBILE) String mobile,
			@RequestParam(name = WebConstants.BUF_ID) String bufcartid,
			@RequestParam(name = WebConstants.BUF_QUANTITY) String quantity) throws IOException {

		CartResponse resp = new CartResponse();
		Validator validator = new Validator();
		if (!validator.isStringEmpty(mobile)) {
			resp = userService.updateCart(mobile, bufcartid, quantity);
		} else {
			resp.setStatus(ResponseCode.FAILURE_CODE);
			resp.setMessage(ResponseCode.FAILURE_MESSAGE);
		}
		return new ResponseEntity<CartResponse>(resp, HttpStatus.ACCEPTED);
	}

	@GetMapping("/delCart")
	public ResponseEntity<CartResponse> delCart(@RequestHeader(name = WebConstants.USER_MOBILE) String mobile,
			@RequestParam(name = WebConstants.BUF_ID) String bufcartid) throws IOException {

		CartResponse resp = new CartResponse();
		Validator validator = new Validator();
		if (!validator.isStringEmpty(mobile)) {
			resp = userService.deleteCart(mobile, bufcartid);

		} else {
			resp.setStatus(ResponseCode.FAILURE_CODE);
			resp.setMessage(ResponseCode.FAILURE_MESSAGE);
		}
		return new ResponseEntity<CartResponse>(resp, HttpStatus.ACCEPTED);
	}

	@GetMapping("/placeOrder")
	public ResponseEntity<ServerResponse> placeOrder(@RequestHeader(name = WebConstants.USER_MOBILE) String mobile)
			throws IOException {

		ServerResponse resp = new ServerResponse();
		Validator validator = new Validator();
		if (!validator.isStringEmpty(mobile)) {
			resp = userService.placeOrder(mobile);
		} else {
			resp.setStatus(ResponseCode.FAILURE_CODE);
			resp.setMessage(ResponseCode.FAILURE_MESSAGE);
		}
		return new ResponseEntity<ServerResponse>(resp, HttpStatus.ACCEPTED);
	}

}
