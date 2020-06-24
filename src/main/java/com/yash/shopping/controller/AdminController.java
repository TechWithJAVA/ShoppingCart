package com.yash.shopping.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yash.shopping.constants.ResponseCode;
import com.yash.shopping.constants.WebConstants;
import com.yash.shopping.model.Order;
import com.yash.shopping.model.Product;
import com.yash.shopping.response.ProductResponse;
import com.yash.shopping.response.ServerResponse;
import com.yash.shopping.response.ViewOrderResponse;
import com.yash.shopping.service.UserService;
import com.yash.shopping.util.Validator;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/admin")
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

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

	/*
	 * @PostMapping("/signup") public ResponseEntity<ServerResponse>
	 * addUser(@RequestBody User user) { ServerResponse resp = null; try { resp
	 * = userService.signUpUser(user); logger.info(resp.getMessage()); } catch
	 * (Exception e) { logger.info(resp.getMessage()); return new
	 * ResponseEntity<ServerResponse>(resp, HttpStatus.INTERNAL_SERVER_ERROR); }
	 * finally { resp = null; }
	 * 
	 * return new ResponseEntity<ServerResponse>(resp, HttpStatus.ACCEPTED); }
	 */

	@PostMapping("/addProduct")
	public ResponseEntity<ProductResponse> addProduct(
			@RequestParam(name = WebConstants.PROD_FILE) MultipartFile prodImage,
			@RequestParam(name = WebConstants.PROD_DESC) String description,
			@RequestParam(name = WebConstants.PROD_PRICE) String price,
			@RequestParam(name = WebConstants.PROD_NAME) String productname,
			@RequestParam(name = WebConstants.PROD_QUANITY) String quantity) throws IOException {
		ProductResponse resp = new ProductResponse();
		Validator validator = new Validator();
		if (!validator.isStringEmpty(productname) || !validator.isStringEmpty(description)
				|| !validator.isStringEmpty(price) || !validator.isStringEmpty(quantity)) {
			try {

				Product prod = new Product();
				prod.setDescription(description);
				prod.setPrice(Double.parseDouble(price));
				prod.setProductname(productname);
				prod.setQuantity(Integer.parseInt(quantity));
				prod.setProductimage(prodImage.getBytes());

				resp = userService.saveProduct(prod);
				logger.info(resp.getMessage());
			} catch (Exception e) {
				new ResponseEntity<ProductResponse>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			resp.setStatus(ResponseCode.BAD_REQUEST_CODE);
			resp.setMessage(ResponseCode.BAD_REQUEST_MESSAGE);
			logger.info(resp.getMessage());
		}
		return new ResponseEntity<ProductResponse>(resp, HttpStatus.ACCEPTED);
	}

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

	@GetMapping("/delProduct")
	public ResponseEntity<ProductResponse> delProduct(@RequestParam(name = WebConstants.PROD_ID) String productid)
			throws IOException {
		ProductResponse resp = new ProductResponse();
		Validator validator = new Validator();
		if (!validator.isStringEmpty(productid)) {
			try {
				userService.deleteProduct(Integer.parseInt(productid));

				resp.setStatus(ResponseCode.SUCCESS_CODE);
				resp.setMessage(ResponseCode.DEL_SUCCESS_MESSAGE);
				resp.setOblist(userService.getAllProduct());
				logger.info(resp.getMessage());
			} catch (Exception e) {
				resp.setStatus(ResponseCode.FAILURE_CODE);
				resp.setMessage(e.toString());
			}
		} else {
			resp.setStatus(ResponseCode.FAILURE_CODE);
			resp.setMessage(ResponseCode.FAILURE_MESSAGE);
			logger.info(resp.getMessage());
		}
		return new ResponseEntity<ProductResponse>(resp, HttpStatus.ACCEPTED);
	}

	@PostMapping("/updateProducts")
	public ResponseEntity<ServerResponse> updateProducts(
			@RequestParam(name = WebConstants.PROD_FILE, required = false) MultipartFile prodImage,
			@RequestParam(name = WebConstants.PROD_DESC) String description,
			@RequestParam(name = WebConstants.PROD_PRICE) String price,
			@RequestParam(name = WebConstants.PROD_NAME) String productname,
			@RequestParam(name = WebConstants.PROD_QUANITY) String quantity,
			@RequestParam(name = WebConstants.PROD_ID) String productid) throws IOException {
		ServerResponse resp = new ServerResponse();
		Validator validator = new Validator();
		if (!validator.isStringEmpty(productid) || !validator.isStringEmpty(productname)
				|| !validator.isStringEmpty(description) || !validator.isStringEmpty(price)
				|| !validator.isStringEmpty(quantity)) {

			resp = userService.updateProducts(productid, prodImage, description, price, productname, quantity,
					productid);

		} else {
			resp.setStatus(ResponseCode.FAILURE_CODE);
			resp.setMessage(ResponseCode.FAILURE_MESSAGE);
			logger.info(resp.getMessage());
		}
		return new ResponseEntity<ServerResponse>(resp, HttpStatus.ACCEPTED);
	}

	@GetMapping("/viewOrders")
	public ResponseEntity<ViewOrderResponse> viewOrders() throws IOException {

		ViewOrderResponse resp = new ViewOrderResponse();
		try {
			List<Order> orderList = new ArrayList<>();
			orderList = userService.viewOrders(orderList);
			resp.setOrderlist(orderList);
			resp.setStatus(ResponseCode.SUCCESS_CODE);
			resp.setMessage(ResponseCode.VIEW_SUCCESS_MESSAGE);
		} catch (Exception e) {
			resp.setStatus(ResponseCode.FAILURE_CODE);
			resp.setMessage(e.getMessage());
		}
		return new ResponseEntity<ViewOrderResponse>(resp, HttpStatus.ACCEPTED);
	}

	@PostMapping("/updateOrder")
	public ResponseEntity<ServerResponse> updateOrders(@RequestParam(name = WebConstants.ORD_ID) String orderId,
			@RequestParam(name = WebConstants.ORD_STATUS) String orderStatus) throws IOException {

		ServerResponse resp = new ServerResponse();
		Validator validator = new Validator();
		if (validator.isStringEmpty(orderId) || validator.isStringEmpty(orderStatus)) {

			resp = userService.updateOrder(orderId, orderStatus);

		} else {
			resp.setStatus(ResponseCode.FAILURE_CODE);
			resp.setMessage(ResponseCode.FAILURE_MESSAGE);
		}
		return new ResponseEntity<ServerResponse>(resp, HttpStatus.ACCEPTED);
	}

}
