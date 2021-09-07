package br.com.fiap.dimdim.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.dimdim.model.Client;
import br.com.fiap.dimdim.repository.ClientRepository;

@Controller
@RequestMapping("/client")
public class ClientController {
	
	@Autowired
    private ClientRepository clientRepository;
    
    @GetMapping
    public ModelAndView index() {
        List<Client> clients = clientRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("client/list");
        modelAndView.addObject("clients", clients);
        return modelAndView;
    }
    
    @GetMapping("/save")
    public String save( Client client ) {
        return "client/form";
    }
    
    @PostMapping("/save")
    public String save(Client client, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "client/form";
        }
        clientRepository.save(client);
        redirect.addFlashAttribute("msg", "Cadastrado");
        return "redirect:save";
    }
    
    @PutMapping("/update")
	public String update(Client client) {
		clientRepository.save(client);
		return "client/list";
	}
	
	@DeleteMapping("/delete")
	public String delete(Client client) {
		clientRepository.delete(client);
		return "client/list";
	}

}
