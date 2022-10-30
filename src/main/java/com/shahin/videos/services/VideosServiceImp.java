package com.shahin.videos.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Tuple;

import com.shahin.videos.entities.User;
import com.shahin.videos.repositories.UserRepository;
import com.shahin.videos.web.dto.DetailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.shahin.videos.entities.Videos;
import com.shahin.videos.repositories.VideosRepository;
import com.shahin.videos.web.dto.VideosDto;

@Service
public class VideosServiceImp implements VideosService{
	
	@Autowired
	private VideosRepository videosRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Videos save(Videos videos) {
		
		return videosRepository.save(videos);
	}

	@Override
	public List<Videos> findAll() {
		// TODO Auto-generated method stub
		return videosRepository.findAll();
	}

	@Override
	public Videos findById(Long id) {
		// TODO Auto-generated method stub
		return videosRepository.getById(id);
	}

	@Override
	public List<VideosDto> findByUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String name = authentication.getName();

		User userData = userRepository.findByEmail(name);
		
		List<Tuple> tuples = videosRepository.getData();
		
		List<VideosDto> vidieosDtoList = new ArrayList<>();
		
		for(Tuple tuple : tuples) {
			
			VideosDto vid = new VideosDto();
			
			vid.setId(tuple.get("ID").toString());
			vid.setVideoUserId(tuple.get("VIDEO_USER_ID") ==null?"":tuple.get("VIDEO_USER_ID").toString());
			vid.setLikeDislikeUserId(tuple.get("LIKE_DISLIKE_USER_ID")==null?"null":tuple.get("LIKE_DISLIKE_USER_ID").toString());
			vid.setUrlPath(tuple.get("URL_PATH").toString());
			vid.setLikes(tuple.get("LIKES")==null?0:Integer.parseInt(tuple.get("LIKES").toString()));
			vid.setLikesBoolean(tuple.get("LIKE_BOOLEAN")==null?"":tuple.get("LIKE_BOOLEAN").toString());
			vid.setDislike(tuple.get("DISLIKE")==null?0:Integer.parseInt(tuple.get("DISLIKE").toString()));
			vid.setDisLikeBoolean(tuple.get("DISLIKE_BOOLEAN")==null?"":tuple.get("DISLIKE_BOOLEAN").toString());
			if (userData !=null)
			vid.setCurrentUserId(userData.getId().toString());

			vidieosDtoList.add(vid);
			
		}
		
		// TODO Auto-generated method stub
		return vidieosDtoList;
	}

	@Override
	public VideosDto findByUrlPath(String urlPath) {
		Tuple tuple = videosRepository.getDataByUrlPath(urlPath);

		VideosDto vid = new VideosDto();

		vid.setId(tuple.get("ID").toString());
		vid.setUrlPath(tuple.get("URL_PATH").toString());
		vid.setLikes(tuple.get("LIKES")==null?0:Integer.parseInt(tuple.get("LIKES").toString()));
		//vid.setLikesBoolean(tuple.get("LIKE_BOOLEAN").toString());
		vid.setDislike(tuple.get("DISLIKE")==null?0:Integer.parseInt(tuple.get("DISLIKE").toString()));
		//vid.setDisLikeBoolean(tuple.get("DISLIKE_BOOLEAN").toString());

		return vid;
	}

	@Override
	public List<DetailDto> getDetailData(Long id) {
		List<Tuple> tuples = videosRepository.getDetailData(id);

		List<DetailDto> detailDtoList = new ArrayList<>();

		for(Tuple tuple : tuples) {
			DetailDto detailDto = new DetailDto();
			detailDto.setUploaderName(tuple.get("UPLOADER_NAME").toString());
			detailDto.setUserName(tuple.get("USER_NAME").toString());
			detailDto.setLikes(tuple.get("LIKES")==null?0:Integer.parseInt(tuple.get("LIKES").toString()));
			detailDto.setDislikes(tuple.get("DIS_LIKE")==null?0:Integer.parseInt(tuple.get("DIS_LIKE").toString()));

			detailDtoList.add(detailDto);
		}

		return detailDtoList;
	}

	@Override
	public DetailDto getDetailSingleData(Long id) {

		Tuple tuple = videosRepository.getDetailSingleData(id);

		DetailDto detailDto = new DetailDto();
		detailDto.setUploaderName(tuple.get("UPLOADER_NAME").toString());


		return detailDto;
	}

}
