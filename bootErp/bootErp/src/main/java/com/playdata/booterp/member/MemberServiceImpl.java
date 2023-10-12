package com.playdata.booterp.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.playdata.booterp.dto.MemberDTO;
@Service
public class MemberServiceImpl implements MemberService {
	MemberDAO dao;
	@Autowired
	public MemberServiceImpl(MemberDAO dao) {
		super();
		this.dao = dao;
	}

	@Override
	public List<MemberDTO> getTreeEmpList(String deptno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(MemberDTO user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(MemberDTO user, MultipartFile file, String realpath, String filename) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean idCheck(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<MemberDTO> getMemberList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MemberDTO read(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MemberDTO> search(String column, String search, String pass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(MemberDTO user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MemberDTO login(MemberDTO loginUser) {
		//user가 db인증 후에 받은 결과
		MemberDTO user = dao.login(loginUser);
		//db에서 가져 온 값에서 menupath를 뷰이름으로 셋팅
		if(user!=null) {
			String menupath = user.getMenupath();
			menupath = menupath.substring(menupath.indexOf("/")+1, menupath.indexOf("_"));
			//menupath로 뷰를 다시 설정
			user.setMenupath(menupath);
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^"+menupath);
			
		}
		return user;
	}

}












