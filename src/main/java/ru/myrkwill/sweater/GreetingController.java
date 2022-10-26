package ru.myrkwill.sweater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.myrkwill.sweater.domain.Message;
import ru.myrkwill.sweater.repositories.MessageRepository;

@Controller
public class GreetingController {

	@Autowired
	private MessageRepository messageRepository;

	@GetMapping("/messages")
	public String greeting(Model model) {
		model.addAttribute("messages", messageRepository.findAll());
		System.out.println(model);
		return "messages";
	}

	@PostMapping("/messages")
	public String create(@ModelAttribute("message") Message message, Model model) {
		messageRepository.save(message);

		model.addAttribute("messages", messageRepository.findAll());

		return "messages";
	}

}
