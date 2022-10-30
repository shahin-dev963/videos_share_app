package com.shahin.videos.services;

import java.util.List;

import com.shahin.videos.entities.Videos;
import com.shahin.videos.web.dto.DetailDto;
import com.shahin.videos.web.dto.VideosDto;

public interface VideosService {

	Videos save(Videos videos);
	
	List<Videos> findAll();

	Videos findById(Long id);

	List<VideosDto> findByUserId();

	VideosDto findByUrlPath(String urlPath);

	List<DetailDto> getDetailData(Long id);

	DetailDto getDetailSingleData(Long id);
}
