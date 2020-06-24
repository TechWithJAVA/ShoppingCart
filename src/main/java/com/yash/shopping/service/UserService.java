package com.yash.shopping.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.web.multipart.MultipartFile;

import com.yash.shopping.model.Address;
import com.yash.shopping.model.Bufcart;
import com.yash.shopping.model.Order;
import com.yash.shopping.model.Product;
import com.yash.shopping.model.User;
import com.yash.shopping.response.CartResponse;
import com.yash.shopping.response.ProductResponse;
import com.yash.shopping.response.Response;
import com.yash.shopping.response.ServerResponse;
import com.yash.shopping.response.UserResponse;

public interface UserService {

	ServerResponse checkLoginUser(Map<String, String> credential);

	ServerResponse signUpUser(User user);

	ProductResponse saveProduct(Product prod);

	UserResponse AddAddress(String mobile, @Valid Address address);

	Response getAddress(String mobile);

	Iterable<Product> getAllProduct();

	Optional<Product> getByProductId(int productId);

	void updateProduct(Product prod);

	void deleteProduct(int productid);

	User checkLoginUser(long mobile);

	void saveBufCart(Bufcart buf);

	ServerResponse addProductToCart(String mobile, String productId);

	ServerResponse updateProducts(String productid, MultipartFile prodImage, String description, String price,
			String productname, String quantity, String productid2);

	CartResponse viewCart(String mobile);

	CartResponse deleteCart(String mobile, String bufcartid);

	CartResponse updateCart(String mobile, String bufcartid, String quantity);

	ServerResponse placeOrder(String mobile);

	ServerResponse updateOrder(String orderId, String orderStatus);

	List<Order> viewOrders(List<Order> orderList);

}
