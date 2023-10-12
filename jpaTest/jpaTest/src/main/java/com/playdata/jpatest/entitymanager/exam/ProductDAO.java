package com.playdata.jpatest.entitymanager.exam;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class ProductDAO {
	EntityManager em;
	
	@Autowired
	public ProductDAO(EntityManager em) {
		super();
		this.em = em;
	}

	//c
	public void insert(ProductEntity entity) {
		em.persist(entity);
		em.flush();
		em.clear();
	}
	
	//l
	public List<ProductEntity> list(){
		return em.createQuery("SELECT p FROM ProductEntity as p", ProductEntity.class).getResultList();
	}
	
	//r
	public ProductEntity read(Long productNo) {
		return em.find(ProductEntity.class, productNo);
	}
	
	//u
	public void update(ProductEntity entity) {
		ProductEntity product = em.find(ProductEntity.class, entity.getProductNo());
		product.setPrice(entity.getPrice());
		product.setProductName(entity.getProductName());
	}
	
	//d
	public void delete(Long productNo) {
		ProductEntity product = em.find(ProductEntity.class, productNo);
		em.remove(product);
	}
}
