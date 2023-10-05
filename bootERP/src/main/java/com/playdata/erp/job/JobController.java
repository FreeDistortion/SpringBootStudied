package com.playdata.erp.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class JobController {
	JobService serv;

	public JobController() {
		super();
	}

	@Autowired
	public JobController(JobService serv) {
		super();
		this.serv = serv;
	}

	@GetMapping("/job/register")
	public String showPage() {
		return "job/job_register";
	}

	@PostMapping("/job/register")
	public String insert(JobDTO job) {
		System.out.println(job);
		serv.insert(job);

		return "redirect:/index.html";
	}

	@GetMapping("/job/list")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView("job/list");
		List<JobDTO> list = serv.select();
		System.out.println(list);
		mav.addObject("joblist", list);
		return mav;
	}

	@GetMapping("/job/getjob")
	public String getDept(String job_id, String cmd, Model model) {
		JobDTO job = serv.read(job_id);
		String path = "";
		if (cmd.equals("view")) {
			path = "job/read";
		} else {
			path = "job/update";
		}
		model.addAttribute("dto", job);
		return path;
	}

	@GetMapping("/job/delete")
	public String delete(String jobno) {
		serv.delete(jobno);
		return "redirect:/job/list";
	}

	@PostMapping("/job/update")
	public String update(JobDTO job) {
		serv.update(job);
		return "redirect:/job/list";
	}
}
