package com.playdata.jpatest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.playdata.jpatest.entitymanager.exam.Category;
import com.playdata.jpatest.entitymanager.exam.ProductDAO;
import com.playdata.jpatest.entitymanager.exam.ProductEntity;

@SpringBootTest
@Transactional
@Rollback(false)
class ProductTest {

	@Autowired
	ProductDAO dao;

	@Test
	@Disabled
	void c() {
		dao.insert(new ProductEntity("A", "A llc.", 10000));
		dao.insert(new ProductEntity("B", "B llc.", 20000));
		dao.insert(new ProductEntity("C", "C llc.", 30000));
		
	}

	@Test
	void l() {
		dao.list();
	}

	@Test
	void r() {
		System.out.println(dao.read(Long.parseLong("1")));
	}

	@Test
	void u() {
		ProductEntity p= dao.read(Long.parseLong("2"));
		System.out.println(p);
		p.setProductName("B mutant");
		p.setPrice(22222);
		dao.update(p);
	}
	
	@Test
	@Disabled
	void d() {
	dao.delete(Long.parseLong("3"));	
	}
	
	@PersistenceContext
	EntityManager em;
	
	@Test
	void test() {
		Category category1= new Category("Keyboard", "input unit");
		Category category2 = new Category("RAM","memory unit");
		Category category3 = new Category("CPU","control unit");
		Category category4 = new Category("Mornitor","output unit");
		
		ProductEntity pr1 = new ProductEntity("kb1", "Samsung", 111111, category1);
		ProductEntity pr2 = new ProductEntity("kb2", "Apple", 222222, category1);
		ProductEntity pr3 = new ProductEntity("DDR4", "Samsung", 50000, category2);
		ProductEntity pr4 = new ProductEntity("DDR5", "Micron", 80000, category2);
		ProductEntity pr5 = new ProductEntity("I7", "Intel", 700007, category3);
		ProductEntity pr6 = new ProductEntity("Ryzen5", "AMD", 500005, category3);
		ProductEntity pr7 = new ProductEntity("PERFECT MORNITOR EVER", "DELL", 990099, category4);
		
		em.persist(category1);
		em.persist(category2);
		em.persist(category3);
		em.persist(category4);
		
		em.persist(pr1);
		em.persist(pr2);
		em.persist(pr3);
		em.persist(pr4);
		em.persist(pr5);
		em.persist(pr6);
		em.persist(pr7);
		
		em.flush(); em.clear();
		
		List<ProductEntity> prList = em.createQuery("SELECT P FROM ProductEntity P", ProductEntity.class).getResultList();
		
		for (ProductEntity productEntity : prList) {
			System.out.println("@@@@@@@@@@@@@@@\n"+productEntity.toString()+"\n@@@@@@@@@@@@@@@");
		}
	}
}
