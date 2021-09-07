package br.com.fiap.dimdim.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.fiap.dimdim.model.Bill;
import br.com.fiap.dimdim.model.Client;
import br.com.fiap.dimdim.repository.BillRepository;
import br.com.fiap.dimdim.repository.ClientRepository;

@Controller
@RequestMapping("/bill")
public class BillController {
	
	@Autowired
	private BillRepository billRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@GetMapping
	public ModelAndView index() {
		List<Bill> bills = billRepository.findAll();
		ModelAndView modelAndView = new ModelAndView("bill/list");
		modelAndView.addObject("bill/list", bills);
		return modelAndView;
	}
	
	@GetMapping("/save")
	public ModelAndView save() {
		List<Client> clients = clientRepository.findAll();
		ModelAndView modelAndView = new ModelAndView("bill/form");
		modelAndView.addObject("bill/form", clients);
		return modelAndView;
	}
	
	@PostMapping("/save")
	public String save(Bill bill, BindingResult result) {
		if (result.hasErrors()) {
			return "bill/form";
		}
		billRepository.save(bill);
		return "bill/list";
	}

}
