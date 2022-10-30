package com.shahin.videos.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shahin.videos.web.dto.DetailDto;
import com.shahin.videos.web.dto.VideosDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.shahin.videos.entities.LikeDislikeEntity;
import com.shahin.videos.entities.User;
import com.shahin.videos.entities.Videos;
import com.shahin.videos.repositories.LikeDislikeRepository;
import com.shahin.videos.repositories.UserRepository;
import com.shahin.videos.services.VideosService;

@RestController  
@RequestMapping("/common")
public class LikeController {
	
	@Autowired
	private VideosService videosService;
	
	@Autowired
	private LikeDislikeRepository repository;
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping(value = "/like/save")
	public ResponseEntity<Map<String, Object>> like(@RequestParam(value="id") String id) throws IOException{
		Map<String, Object> resultMap = new HashMap<>();

		Videos oldVideos = videosService.findById(Long.parseLong(id));

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String name = authentication.getName();

		User userData = userRepository.findByEmail(name);

		LikeDislikeEntity like = repository.findByVideoIdAndUserId(oldVideos.getId(), userData.getId());

		LikeDislikeEntity vidObj;
		LikeDislikeEntity likeNew= new LikeDislikeEntity();
		if(like ==null) {
			likeNew.setUrlPath(oldVideos.getUrlPath());
			likeNew.setLikes(1);
			likeNew.setDisLike(0);
			likeNew.setLikeBoolean(true);
			likeNew.setDisLikeBoolean(false);
			likeNew.setUser(userData);
			likeNew.setVideos(oldVideos);

			vidObj = repository.save(likeNew);
		}else {

			like.setUrlPath(oldVideos.getUrlPath());
			like.setLikes(1);
			like.setDisLike(0);
			like.setLikeBoolean(true);
			like.setDisLikeBoolean(false);
			like.setUser(userData);
			like.setVideos(oldVideos);

			vidObj = repository.save(like);
		}

		VideosDto videosDto = videosService.findByUrlPath(oldVideos.getUrlPath());


		resultMap.put("videosDto", videosDto);
		return  new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}


	
	@PostMapping(value = "/dislike/save")
    public ResponseEntity<Map<String, Object>> disLike(@RequestParam(value="id") String id) throws IOException {
        Map<String, Object> resultMap = new HashMap<>();
        
 		Videos oldVideos = videosService.findById(Long.parseLong(id));
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String name = authentication.getName();
		
		User userData = userRepository.findByEmail(name);

		LikeDislikeEntity likeDislikeEntity = repository.findByVideoIdAndUserId(oldVideos.getId(),userData.getId());

		LikeDislikeEntity vidObj;
    	LikeDislikeEntity dislikeEntity= new LikeDislikeEntity();
    	if(likeDislikeEntity ==null) {
			dislikeEntity.setUrlPath(oldVideos.getUrlPath());
			dislikeEntity.setLikeBoolean(false);
			dislikeEntity.setLikes(0);
			dislikeEntity.setDisLike(1);
			dislikeEntity.setDisLikeBoolean(true);
			dislikeEntity.setUser(userData);
			dislikeEntity.setVideos(oldVideos);
    		
    		vidObj = repository.save(dislikeEntity);
    	}else {
			likeDislikeEntity.setUrlPath(oldVideos.getUrlPath());
			likeDislikeEntity.setLikeBoolean(false);
			likeDislikeEntity.setLikes(0);
			likeDislikeEntity.setDisLike(1);
			likeDislikeEntity.setDisLikeBoolean(true);
			likeDislikeEntity.setUser(userData);
			likeDislikeEntity.setVideos(oldVideos);
    		
			vidObj = repository.save(likeDislikeEntity);
    	}

		VideosDto videosDto = videosService.findByUrlPath(oldVideos.getUrlPath());

		resultMap.put("videosDto", videosDto);
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }

	@GetMapping(value = "/detail")
    @ResponseBody
	public List<DetailDto> getDetailData(@RequestParam(value="id") String id){

        List<DetailDto> detailDtoList  = videosService.getDetailData(Long.parseLong(id));
		return detailDtoList;
	}

    @GetMapping(value = "/detail/single/data")
    @ResponseBody
    public DetailDto getDetailSingleData(@RequestParam(value="id") String id){

        DetailDto detailDto  = videosService.getDetailSingleData(Long.parseLong(id));
        return detailDto;
    }

}
