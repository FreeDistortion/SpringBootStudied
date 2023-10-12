package com.playdata.jpatest.entitymanager.exam;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="product")
@NoArgsConstructor
@Getter
@ToString
public class ProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long productNo;
	@Setter
	private String productName;
	private String company;
	@Setter
	private int price;

	@ManyToOne
	@JoinColumn(name = "categoryId")
	private Category category;
	
	@CreationTimestamp
	private Date createDate;
	@UpdateTimestamp
	private Date updateDate;
	public ProductEntity(String productName, String company, int price) {
		super();
		this.productName = productName;
		this.company = company;
		this.price = price;
	}
	public ProductEntity(String productName, String company, int price, Category category) {
		super();
		this.productName = productName;
		this.company = company;
		this.price = price;
		this.category = category;
	}
	
	
	
	
}
