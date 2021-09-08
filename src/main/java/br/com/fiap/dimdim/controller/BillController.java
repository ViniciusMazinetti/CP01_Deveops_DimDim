package br.com.fiap.dimdim.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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
		modelAndView.addObject("bills", bills);
		return modelAndView;
	}
	
	@GetMapping("/save")
    public String save(Bill bill, Model model) {
		model.addAttribute("bill", new Bill()); 
		List<Client> clients = clientRepository.findAll();
		model.addAttribute("clients", clients);
		return "bill/form";
    }
	
	
	@PostMapping("/save")
    public String save(@ModelAttribute("bill") Bill bill, BindingResult result, RedirectAttributes redirect) {
		
        if (result.hasErrors()) {
            return "bill";
        }
        
        Optional<Client> client = clientRepository.findById(bill.getClient().getId());
        

    	bill.setClient(client.get());
    	billRepository.save(bill);
        redirect.addFlashAttribute("msg", "Sucesso");
        return "redirect:";
    }

	
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable Long id, Model model) {
		
    	Optional<Bill> bill = billRepository.findById(id);
	
		List<Client> clients = clientRepository.findAll();
		
		model.addAttribute("bill",bill.get());
		model.addAttribute("clients",clients);
    	
    	return "bill/updateForm";
	}
	
	
	@GetMapping("/delete/{id}")
	public RedirectView delete(@PathVariable Long id, RedirectAttributes redirect) {
		Optional<Bill> bill = billRepository.findById(id);
		if(bill.isPresent()) {
			billRepository.delete(bill.get());
			redirect.addFlashAttribute("msg", "Excluido");
			return new RedirectView("/bill");
		}
		
		return new RedirectView("/bill");
		
	}
	

}
