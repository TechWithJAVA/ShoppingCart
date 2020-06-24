package com.yash.shopping.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yash.shopping.model.Product;

@Repository
@Transactional
public interface ProductRepository extends CrudRepository<Product, Integer>{

	//Product findByProductid(int productid);
	
}
