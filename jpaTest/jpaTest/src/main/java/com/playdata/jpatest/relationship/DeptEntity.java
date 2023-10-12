package com.playdata.jpatest.relationship;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	
	// @OneToMany인 경우, 대상 table의 어떤 col과 mapping되어 있는지 entity class의 col name을 명시한다.
	// @OneToMany로 연결된 table의 표현 entity의 어떤 col과 연결되어 있는지 명시.
	// mappedBy가 정의된 entity에서는 대부분 조회만 가능하도록 한다.
	// 기준이 되는 entity는 FK에 해당되는 entity
	// 즉, 1:N, N:1에서 기준이 되는 entity는 N에 해당하는 entity이고 해당 entity에서 변경 작업을 한다.
	// 양방향 관계에서의 기준이 되는 entity는 항상 FK table를 표현한 entity 
	@OneToMany(mappedBy = "dept")
	
	private List<Emp> list = new ArrayList<>();
	
	public DeptEntity(String name, String mgr) {
		super();
		this.name = name;
		this.mgr = mgr;
	}

	
	
	@Override
	public String toString() {
		return "DeptEntity [id=" + id + ", name=" + name + ", mgr=" + mgr + "]";
	}



	public DeptEntity(String name, String mgr, List<Emp> list) {
		super();
		this.name = name;
		this.mgr = mgr;
		this.list = list;
	}
	
}
