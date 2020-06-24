package com.yash.shopping.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yash.shopping.model.Bufcart;

@Repository
@Transactional
public interface CartRepository extends CrudRepository<Bufcart, Long> {

	Optional<Bufcart> findByMobile(Long mobile);

	Bufcart findByBufcartIdAndMobile(int bufcartId, Long Mobile);

	void deleteByBufcartIdAndMobile(int bufcartId, Long mobile);

	List<Bufcart> findAllByMobile(Long mobile);

	List<Bufcart> findAllByOrderId(int orderId);

}
