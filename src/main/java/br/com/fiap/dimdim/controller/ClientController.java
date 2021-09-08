package br.com.fiap.dimdim.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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
        
        Optional<Client> updateClient = clientRepository.findById(client.getId());
        if (updateClient.isEmpty()) {
        	clientRepository.save(client);
            redirect.addFlashAttribute("msg", "Cadastrado");
            return "redirect:save";
        }
        System.out.println("Entrei aqui");
        updateClient.get().setId(client.getId());
        updateClient.get().setName(client.getName());
        updateClient.get().setEmail(client.getEmail());
        updateClient.get().setCpf(client.getCpf());
        clientRepository.save(updateClient.get());
        redirect.addFlashAttribute("msg", "Atualizado");
        return "redirect:save";
        
    }
    
    @RequestMapping("/update/{id}")
	public ModelAndView update(@PathVariable Long id) {
    	Optional<Client> client = clientRepository.findById(id);
    	
    	if(client.isPresent()) {	
    		ModelAndView modelAndView = new ModelAndView("client/form");
    		modelAndView.addObject("client", client.get());
    		return modelAndView;
    	}
    	ModelAndView modelAndView = new ModelAndView("client/list");
		return modelAndView;
	}
	
	@RequestMapping("/delete/{id}")
	public RedirectView delete(@PathVariable(value = "id") Long id, RedirectAttributes redirect) {
		Optional<Client> client = clientRepository.findById(id);
		System.out.println("Entrei Aqui");
		if(client.isPresent()) {
			clientRepository.delete(client.get());
			redirect.addFlashAttribute("msg", "Excluido");
			return new RedirectView("/client");
		}
		
		return new RedirectView("/client");
		
	}

}
