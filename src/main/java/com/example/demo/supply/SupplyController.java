package com.example.demo.supply;

import java.util.*;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class SupplyController {
	private List<Supply> supplies = new ArrayList<>();
	private int sno = 1;
	
	@GetMapping("/supply/list")
	public ModelAndView list() {
		return new ModelAndView("supply/list").addObject("supplies", supplies);
	}
	
	@GetMapping("/supply/write")
	public void write() {
	}
	
	@PostMapping("/supply/write")
	public ModelAndView write(Supply supply) {
		supply.setSno(sno++);
		supplies.add(supply);
		return new ModelAndView("redirect:/supply/list");
	}
	
	@GetMapping("/supply/read")
	public ModelAndView read(Integer sno) {
		for (Supply supply : supplies) {
			if (supply.getSno() == sno)
				return new ModelAndView("supply/read").addObject(supply);
		}
		return new ModelAndView("redirect:/supply/list");
	}
	
	@PostMapping("/supply/inc")
	public ModelAndView inc(int sno) {
		for (Supply supply : supplies) {
			if (supply.getSno() == sno)
				supply.setCount(supply.getCount() + 1);
		}
		return new ModelAndView("redirect:/supply/list");
	}
	
	@PostMapping("/supply/dec")
	public ModelAndView dec(int sno) {
		for (Supply supply : supplies) {
			if (supply.getSno() == sno)
				supply.setCount(supply.getCount() - 1);
		}
		return new ModelAndView("redirect:/supply/list");
	}
	
	@PostMapping("/supply/delete")
	public ModelAndView delete(Integer sno) {
		for (Supply supply : supplies) {
			if (supply.getSno() == sno)
				supplies.remove(supply);
		}
		return new ModelAndView("redirect:/supply/list");
	}
}
