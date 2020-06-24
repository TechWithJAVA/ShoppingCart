package com.yash.shopping.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yash.shopping.model.PlaceOrder;

@Repository
@Transactional
public interface OrderRepository extends CrudRepository<PlaceOrder, Integer> {

	PlaceOrder findByOrderId(int orderId);

	List<PlaceOrder> findAll();
}
