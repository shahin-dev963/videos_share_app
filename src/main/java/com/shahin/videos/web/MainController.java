package com.shahin.videos.web;

import java.util.ArrayList;
import java.util.List;

import com.shahin.videos.web.dto.DetailDto;
import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.shahin.videos.entities.User;
import com.shahin.videos.entities.Videos;
import com.shahin.videos.repositories.UserRepository;
import com.shahin.videos.services.VideosService;
import com.shahin.videos.web.dto.VideosDto;


@Controller
public class MainController {
	
	@Autowired
	private VideosService videosService;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/")
	public String home(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String name = authentication.getName();
		
		User userData = userRepository.findByEmail(name);

		List<VideosDto> videos = new ArrayList<>();

		videos = videosService.findByUserId();

		model.addAttribute("videos", videos);

		model.addAttribute("videosObject", new Videos());
		
		return "index";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@PostMapping("/upload")
	public String upload(@ModelAttribute("videosObject") Videos videos) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String name = authentication.getName();
		
		User userData = userRepository.findByEmail(name);

		videos.setUser(userData);
		videos.setUploaderName(userData.getFirsName() +""+ userData.getLastName());
		
		videosService.save(videos);
		
		
		return "redirect:/";
	}


	

}
