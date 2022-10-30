package com.shahin.videos.web.dto;

import lombok.Data;

@Data
public class VideosDto {
	
	private String id;
	private String videoUserId;
	private String likeDislikeUserId;
	private String urlPath;
	private Integer likes;
	private String likesBoolean;
	private Integer dislike;
	private String disLikeBoolean;
	private String currentUserId;

	

}
