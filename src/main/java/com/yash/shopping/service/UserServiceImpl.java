package com.yash.shopping.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.yash.shopping.constants.ResponseCode;
import com.yash.shopping.constants.WebConstants;
import com.yash.shopping.model.Address;
import com.yash.shopping.model.Bufcart;
import com.yash.shopping.model.Order;
import com.yash.shopping.model.PlaceOrder;
import com.yash.shopping.model.Product;
import com.yash.shopping.model.User;
import com.yash.shopping.repository.CartRepository;
import com.yash.shopping.repository.OrderRepository;
import com.yash.shopping.repository.ProductRepository;
import com.yash.shopping.repository.UserRepository;
import com.yash.shopping.response.CartResponse;
import com.yash.shopping.response.ProductResponse;
import com.yash.shopping.response.Response;
import com.yash.shopping.response.ServerResponse;
import com.yash.shopping.response.UserResponse;
import com.yash.shopping.util.Validator;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private OrderRepository orderRepository;
	
//	@Autowired
//	private AddressRepository addressRepository;

	@Override
	public ServerResponse checkLoginUser(Map<String, String> credential) {
		Long mobileNo = 0L;
		Gson gson = new Gson();
		ServerResponse resp = new ServerResponse();
		try {
			if (credential.containsKey(WebConstants.USER_MOBILE)) {
				mobileNo = Long.parseLong(credential.get(WebConstants.USER_MOBILE));
			}
			if (credential.containsKey(WebConstants.USER_PASSWORD)) {
				credential.get(WebConstants.USER_PASSWORD);
			}

			User user = userRepository.findById(mobileNo);
			logger.info(gson.toJson(user));
			if (user != null) {
				resp.setStatus(ResponseCode.SUCCESS_CODE);
				resp.setMessage(ResponseCode.SUCCESS_MESSAGE);
				resp.setUserType(user.getUsertype());
			} else {
				resp.setStatus(ResponseCode.FAILURE_CODE);
				resp.setMessage(ResponseCode.FAILURE_MESSAGE);
			}
		} catch (Exception e) {
			logger.info(resp.getMessage());
			resp.setStatus(ResponseCode.FAILURE_CODE);
			resp.setMessage(e.getMessage());
			return resp;
		}
		return resp;
	}

	@Override
	public ServerResponse signUpUser(User user) {
		ServerResponse resp = new ServerResponse();
		Validator validator = new Validator();
		try {
			if (validator.isUserEmpty(user)) {
				resp.setStatus(ResponseCode.BAD_REQUEST_CODE);
				resp.setMessage(ResponseCode.BAD_REQUEST_MESSAGE);
			} else if (validator.isValidPhone(user.getMobile())) {
				resp.setStatus(ResponseCode.BAD_REQUEST_CODE);
				resp.setMessage(ResponseCode.INVALID_PHONE_NO_FAIL_MSG);
			} else if (!validator.isValidEmail(user.getEmail())) {
				resp.setStatus(ResponseCode.BAD_REQUEST_CODE);
				resp.setMessage(ResponseCode.INVALID_EMAIL_FAIL_MSG);
			} else if (validator.isValidPassword(user.getPassword())) {
				resp.setStatus(ResponseCode.BAD_REQUEST_CODE);
				resp.setMessage(ResponseCode.INVALID_PASSWORD_FAIL_MSG);

			} else {
				resp.setStatus(ResponseCode.SUCCESS_CODE);
				resp.setMessage(ResponseCode.CUST_REG);
				User reg = userRepository.save(user);
				resp.setObject(reg);
			}
		} catch (Exception e) {
			logger.info(resp.getMessage());
			resp.setStatus(ResponseCode.FAILURE_CODE);
			resp.setMessage(e.getMessage());
			return resp;
		}
		return resp;
	}

	@Override
	public ProductResponse saveProduct(Product prod) {
		ProductResponse resp = new ProductResponse();
		try {
			productRepository.save(prod);
			resp.setStatus(ResponseCode.SUCCESS_CODE);
			resp.setMessage(ResponseCode.ADD_SUCCESS_MESSAGE);
			resp.setOblist(productRepository.findAll());
		} catch (Exception e) {
			resp.setStatus(ResponseCode.FAILURE_CODE);
			resp.setMessage(e.getMessage());
			return resp;
		}
		return resp;
	}
	
	@Override
	public UserResponse AddAddress(String mobile, @Valid Address address) {
		UserResponse resp=new UserResponse();
		try {
			User user = checkLoginUser(Long.parseLong(mobile));
			//user.setAddress(address);
			//address.setUser(user);
			//Address adr = addressRepository.save(address);
			resp.setStatus(ResponseCode.SUCCESS_CODE);
			resp.setMessage(ResponseCode.CUST_ADR_ADD);
			resp.setUser(user);
			//resp.setAddress(adr);
		} catch (Exception e) {
			resp.setStatus(ResponseCode.FAILURE_CODE);
			resp.setMessage(e.getMessage());
		}
		return resp;
	}
	
	@Override
	public Response getAddress(String mobile) {
		Response resp=new Response();
		try {
//			User user = checkLoginUser(Long.parseLong(mobile));
//			Address adr = addressRepository.findByUser(user);
//
//			HashMap<String, String> map = new HashMap<>();
//			map.put(WebConstants.ADR_NAME, adr.getAddress());
//			map.put(WebConstants.ADR_CITY, adr.getCity());
//			map.put(WebConstants.ADR_STATE, adr.getState());
//			map.put(WebConstants.ADR_COUNTRY, adr.getCountry());
//			map.put(WebConstants.ADR_ZP, String.valueOf(adr.getZipcode()));
//			map.put(WebConstants.PHONE, adr.getPhonenumber());
//
//			resp.setStatus(ResponseCode.SUCCESS_CODE);
//			resp.setMessage(ResponseCode.CUST_ADR_ADD);
//			resp.setMap(map);
			// resp.setAddress(adr);
			
		} catch (Exception e) {
			resp.setStatus(ResponseCode.FAILURE_CODE);
			resp.setMessage(e.getMessage());
		}
		return null;
	}

	@Override
	public Iterable<Product> getAllProduct() {

		return productRepository.findAll();
	}

	@Override
	public Optional<Product> getByProductId(int productid) {

		return productRepository.findById(productid);
	}

	@Override
	public void updateProduct(Product prod) {
		productRepository.save(prod);

	}

	@Override
	public void deleteProduct(int productid) {
		productRepository.deleteById(productid);

	}

	@Override
	public User checkLoginUser(long mobile) {
		User user = userRepository.findById(mobile);
		return user;
	}

	@Override
	public void saveBufCart(Bufcart buf) {
		cartRepository.save(buf);

	}

	@Override
	public ServerResponse addProductToCart(String mobile, String productId) {
		ServerResponse resp = new ServerResponse();
		try {

			User loggedUser = checkLoginUser(Long.parseLong(mobile));
			Optional<Product> cartItem = getByProductId(Integer.parseInt(productId));
			Bufcart buf = new Bufcart();
			buf.setMobile(loggedUser.getMobile());
			buf.setQuantity(1);
			Product product = cartItem.get();
			buf.setPrice(product.getPrice());
			buf.setProductId(Integer.parseInt(productId));
			buf.setProductname(product.getProductname());
			Date date = new Date();
			buf.setDateAdded(date);
			saveBufCart(buf);
			resp.setStatus(ResponseCode.SUCCESS_CODE);
			resp.setMessage(ResponseCode.CART_UPD_MESSAGE_CODE);
		} catch (Exception e) {
			resp.setStatus(ResponseCode.FAILURE_CODE);
			resp.setMessage(e.getMessage());

		}
		return resp;
	}
	
	@Override
	public ServerResponse updateProducts(String productid, MultipartFile prodImage, String description, String price,
			String productname, String quantity, String productid2) {
		ServerResponse resp= new ServerResponse();
		try {
			Optional<Product> prodOrg;
			Product prod;
			if (prodImage != null) {
				prod = new Product(Integer.parseInt(productid), description, productname, Double.parseDouble(price),
						Integer.parseInt(quantity), prodImage.getBytes());
			} else {
				prodOrg = getByProductId(Integer.parseInt(productid));

				prod = new Product(Integer.parseInt(productid), description, productname, Double.parseDouble(price),
						Integer.parseInt(quantity), prodOrg.get().getProductimage());
			}
			updateProduct(prod);
			resp.setStatus(ResponseCode.SUCCESS_CODE);
			resp.setMessage(ResponseCode.UPD_SUCCESS_MESSAGE);
			logger.info(resp.getMessage());

		} catch (Exception e) {
			resp.setStatus(ResponseCode.FAILURE_CODE);
			resp.setMessage(e.getMessage());
			logger.info(resp.getMessage());
		}
		return resp;
	}


	@Override
	public CartResponse viewCart(String mobile) {
		CartResponse resp = new CartResponse();
		try {
			Long mob = Long.parseLong(mobile);
			User user = checkLoginUser(mob);
			if (user.getMobile() != 0) {
				resp.setStatus(ResponseCode.SUCCESS_CODE);
				resp.setMessage(ResponseCode.VW_CART_MESSAGE);
				resp.setOblist(cartRepository.findByMobile(user.getMobile()));
			}
		} catch (Exception e) {
			resp.setStatus(ResponseCode.FAILURE_CODE);
			resp.setMessage(e.getMessage());
		}
		return resp;
	}

	@Override
	public CartResponse updateCart(String mobile, String bufcartid, String quantity) {
		CartResponse resp = new CartResponse();
		try {

			User loggedUser = checkLoginUser(Long.parseLong(mobile));
			Bufcart selCart = cartRepository.findByBufcartIdAndMobile(Integer.parseInt(bufcartid),
					loggedUser.getMobile());
			selCart.setQuantity(Integer.parseInt(quantity));
			cartRepository.save(selCart);
			Optional<Bufcart> bufcartlist = cartRepository.findByMobile(loggedUser.getMobile());
			resp.setStatus(ResponseCode.SUCCESS_CODE);
			resp.setMessage(ResponseCode.UPD_CART_MESSAGE);
			resp.setOblist(bufcartlist);
		} catch (Exception e) {
			resp.setStatus(ResponseCode.FAILURE_CODE);
			resp.setMessage(e.getMessage());

		}
		return resp;
	}

	@Override
	public CartResponse deleteCart(String mobile, String bufcartid) {
		CartResponse resp = new CartResponse();
		try {
			Long mob = Long.parseLong(mobile);
			User loggedUser = checkLoginUser(mob);
			cartRepository.deleteByBufcartIdAndMobile(Integer.parseInt(bufcartid), loggedUser.getMobile());
			Optional<Bufcart> bufcartlist = cartRepository.findByMobile(mob);
			resp.setStatus(ResponseCode.SUCCESS_CODE);
			resp.setMessage(ResponseCode.DEL_CART_SUCCESS_MESSAGE);
			resp.setOblist(bufcartlist);
		} catch (Exception e) {
			resp.setStatus(ResponseCode.FAILURE_CODE);
			resp.setMessage(e.getMessage());
		}
		return resp;
	}

	@Override
	public ServerResponse placeOrder(String mobile) {
		ServerResponse resp = new ServerResponse();
		try {
			User loggedUser = checkLoginUser(Long.parseLong(mobile));
			PlaceOrder po = new PlaceOrder();
			po.setEmail(loggedUser.getEmail());
			Date date = new Date();
			po.setOrderDate(date);
			po.setOrderStatus(ResponseCode.ORD_STATUS_CODE);
			double total = 0;
			List<Bufcart> buflist = cartRepository.findAllByMobile(loggedUser.getMobile());
			for (Bufcart buf : buflist) {
				total = +(buf.getQuantity() * buf.getPrice());
			}
			po.setTotalCost(total);
			PlaceOrder res = orderRepository.save(po);
			buflist.forEach(bufcart -> {
				bufcart.setOrderId(res.getOrderId());
				cartRepository.save(bufcart);

			});
			resp.setStatus(ResponseCode.SUCCESS_CODE);
			resp.setMessage(ResponseCode.ORD_SUCCESS_MESSAGE);
		} catch (Exception e) {
			resp.setStatus(ResponseCode.FAILURE_CODE);
			resp.setMessage(e.getMessage());

		}
		return resp;

	}

	@Override
	public ServerResponse updateOrder(String orderId, String orderStatus) {
		ServerResponse resp = new ServerResponse();
		try {
			PlaceOrder pc = orderRepository.findByOrderId(Integer.parseInt(orderId));
			pc.setOrderStatus(orderStatus);
			orderRepository.save(pc);
			resp.setStatus(ResponseCode.SUCCESS_CODE);
			resp.setMessage(ResponseCode.UPD_ORD_SUCCESS_MESSAGE);

		} catch (Exception e) {
			resp.setStatus(ResponseCode.FAILURE_CODE);
			resp.setMessage(e.toString());

		}
		return resp;
	}

	@Override
	public List<Order> viewOrders(List<Order> orderList) {
		List<PlaceOrder> poList = orderRepository.findAll();
		poList.forEach((po) -> {
			Order ord = new Order();
			ord.setOrderBy(po.getEmail());
			ord.setOrderId(po.getOrderId());
			ord.setOrderStatus(po.getOrderStatus());
			ord.setProducts(cartRepository.findAllByOrderId(po.getOrderId()));
			orderList.add(ord);
		});

		return orderList;
	}

	
}
