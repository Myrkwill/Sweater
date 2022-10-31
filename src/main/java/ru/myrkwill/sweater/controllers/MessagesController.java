package ru.myrkwill.sweater.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.myrkwill.sweater.entities.Message;
import ru.myrkwill.sweater.entities.User;
import ru.myrkwill.sweater.repositories.MessageRepository;

@Controller
public class MessagesController {

	private MessageRepository messageRepository;

	@Autowired
	public MessagesController(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

	@GetMapping("/messages")
	public String messages(Model model) {
		model.addAttribute("message", new Message());
		model.addAttribute("messages", messageRepository.findAll());
		return "messages";
	}

	@PostMapping("/messages")
	public String createMessage(@AuthenticationPrincipal User user,
								@ModelAttribute("message") Message message,
								BindingResult result,
								Model model) {
		if (result.hasErrors()) {
			return "messages";
		}

		Message newMessage = new Message(message.getText(), message.getTag(), user);
		messageRepository.save(newMessage);
		model.addAttribute("message", new Message());
		model.addAttribute("messages", messageRepository.findAll());

		return "messages";
	}

}
