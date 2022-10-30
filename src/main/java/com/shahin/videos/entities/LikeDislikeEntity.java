package com.shahin.videos.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikeDislikeEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	private String urlPath;
	
	private Integer likes;
	
	private Boolean likeBoolean;
	
	private Integer disLike;
	
	private Boolean disLikeBoolean;

	
	@OneToOne
    @JoinColumn(name = "userId")
    private User user;
	
	@ManyToOne
    @JoinColumn(name = "videoId")
    private Videos videos;

}
