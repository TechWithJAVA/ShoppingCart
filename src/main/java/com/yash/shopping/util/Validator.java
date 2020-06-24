package com.yash.shopping.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.yash.shopping.model.Address;
import com.yash.shopping.model.User;


public class Validator {

	public boolean isAlphaNumerical(String input) {

		if (input != null && input != "") {
			if (input.matches("[a-zA-Z0-9]*")) {
				return true;
			}
		}
		return false;
	}

	public boolean isNumerical(String input) {
		if (input != null && input != "") {
			if (input.matches("[0-9]*")) {
				return true;
			}
		}
		return false;
	}

	public boolean isValidEmail(String input) {
		if (input != null && input != "") {
			if (input.matches("^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$")) {
				return true;
			}
		}
		return false;
	}

	public boolean isImageFile(String input) {
		if (input != null && input != "") {
			input = input.toLowerCase();
			if (input.endsWith(".png") || input.endsWith(".jpg") || input.endsWith(".jpeg") || input.endsWith(".gif")) {
				return true;
			}
		}
		return false;
	}

	public String removeSpaces(String input) {
		String filterInput = "";
		if (input != null && input != "") {
			filterInput = input.replace(" ", "");
		}
		return filterInput;
	}

	public boolean isUserEmpty(User user) {
		if (user.getMobile() == 0) {
			return true;
		}
		if (user.getUsername() == null || user.getUsername() == "") {
			return true;
		}
		if (user.getPassword() == null || user.getPassword() == "") {
			return true;
		}
		if (user.getName() == null || user.getName() == "") {
			return true;
		}
		if (user.getLastname() == null || user.getLastname() == "") {
			return true;
		}
		if (user.getEmail() == null || user.getEmail() == "") {
			return true;
		}
		/*
		 * if (user.getCreateddate() == null) { return true; } if
		 * (user.getUpdateddate() == null) { return true; }
		 */
		if (user.getActive() == 0) {
			return true;
		}

		return false;
	}

	public boolean isValidPhone(Long number) {
		String mobileNo = String.valueOf(number);
		String regex = "\\d{10}";
		Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(mobileNo);

		if (!matcher.matches() || mobileNo.length() != 10) {
			return true;
		}
		return false;

	}

//	public static boolean isAddressEmpty(Address address) {
//		if (address.getAddress() == null || address.getAddress() == "") {
//			return true;
//		}
//		if (address.getCity() == null || address.getCity() == "") {
//			return true;
//		}
//		if (address.getState() == null || address.getState() == "") {
//			return true;
//		}
//		if (address.getCountry() == null || address.getCountry() == "") {
//			return true;
//		}
//		if (address.getPhonenumber() == null || address.getPhonenumber() == "") {
//			return true;
//		}
//		if (address.getZipcode() == 0) {
//			return true;
//		}
//		return false;
//	}
	 
	/*
	 * public static boolean isProductEmpty(Product prod) {
	 * 
	 * if (prod.getProductname() == null || prod.getProductname() == "") {
	 * return true; } if (prod.getDescription() == null || prod.getDescription()
	 * == "") { return true; } if (prod.getPrice() == 0) { return true; } if
	 * (prod.getQuantity() == 0) { return true; } return false; }
	 */
	/*
	 * public static boolean isPlaceOrderEmpty(PlaceOrder plaOrd) {
	 * 
	 * if (plaOrd.getEmail() == null || plaOrd.getEmail() == "") { return true;
	 * } if (plaOrd.getOrderDate() == null) { return true; } if
	 * (plaOrd.getTotalCost() == 0) { return true; } if (plaOrd.getOrderStatus()
	 * == null || plaOrd.getOrderStatus() == "") { return true; } return false;
	 * }
	 */

	/*
	 * public static boolean isCartEmpty(Bufcart cart) {
	 * 
	 * if (cart.getEmail() == null || cart.getEmail() == "") { return true; } if
	 * (cart.getProductname() == null || cart.getProductname() == "") { return
	 * true; } if (cart.getDateAdded() == null) { return true; } if
	 * (cart.getQuantity() == 0) { return true; } if (cart.getPrice() == 0) {
	 * return true; } return false; }
	 */
	public boolean isStringEmpty(String input) {
		if (input == null || input.equals("")) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 1. at least 6 digits
	 2. up to 18 digits
	 3. one number
	 4. one lowercase
	 5. one uppercase
	 6. can contain all special characters
	 *
	 */
	public boolean isValidPassword(String password) {
		String regExpn = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?=\\S+$).{6,18}";
		
		CharSequence inputStr = password;

		Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(inputStr);

		if (!matcher.matches())
			return true;
		else
			return false;
	}

}
