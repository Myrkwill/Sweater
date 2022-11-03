package ru.myrkwill.sweater.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.myrkwill.sweater.entities.Message;
import ru.myrkwill.sweater.entities.User;
import ru.myrkwill.sweater.repositories.MessageRepository;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class MessagesController {

	@Autowired
	private MessageRepository messageRepository;

	@Value("${upload.path}")
	private String uploadPath;


	@GetMapping("/messages")
	public String messages(Model model) {
		model.addAttribute("messages", messageRepository.findAll());
		return "messages";
	}

	@PostMapping("/messages")
	public String createMessage(@AuthenticationPrincipal User user,
								@RequestParam String text,
								@RequestParam String tag,
								@RequestParam MultipartFile file,
								Model model) throws IOException {

		Message message = new Message(text, tag, user);


		if (file != null && !file.getOriginalFilename().isEmpty()) {
			File directory = new File(uploadPath);

			if (!directory.exists()) {
				directory.mkdir();
			}

			String uuidFilename = UUID.randomUUID().toString();
			String filename = uuidFilename + "." + file.getOriginalFilename();

			file.transferTo(new File(uploadPath + "/" + filename));

			message.setFilename(filename);
		}

		messageRepository.save(message);
		model.addAttribute("messages", messageRepository.findAll());

		return "messages";
	}

}
