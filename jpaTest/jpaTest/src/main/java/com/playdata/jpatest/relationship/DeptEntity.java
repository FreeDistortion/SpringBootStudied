package com.playdata.jpatest.relationship;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity 
@Table(name = "dept")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED )
@AllArgsConstructor
public class DeptEntity {
	@Id @GeneratedValue
	@Column(name = "dept_no")
	private Long id;
	@Setter
	private String name;
	@Setter
	private String mgr;
	
	public DeptEntity(String name, String mgr) {
		super();
		this.name = name;
		this.mgr = mgr;
	}

	@Override
	public String toString() {
		return "DeptEntity [id=" + id + ", name=" + name + ", mgr=" + mgr + "]";
	}
	
}
