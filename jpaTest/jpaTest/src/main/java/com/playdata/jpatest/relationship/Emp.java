package com.playdata.jpatest.relationship;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Emp {
	@Id @GeneratedValue
	private Long id;
	@Setter
	private String name; 
	@Setter
	private String addr;
	
	// FK를 참조할 수 있도록 명시(join col 명시)
	// Join col은 실제 조인할 table의 PK name
	@ManyToOne
	@JoinColumn(name = "dept_no")
	private DeptEntity dept;

	
	public Emp(String name, String addr) {
		super();
		this.name = name;
		this.addr = addr;
	}


	public Emp(String name, String addr, DeptEntity dept) {
		super();
		this.name = name;
		this.addr = addr;
		this.dept = dept;
	}


	@Override
	public String toString() {
		return "Emp [id=" + id + ", name=" + name + ", addr=" + addr + ", dept=" + dept + "]";
	}

}
