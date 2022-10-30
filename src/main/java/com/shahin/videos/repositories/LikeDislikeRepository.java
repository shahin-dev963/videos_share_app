package com.shahin.videos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shahin.videos.entities.LikeDislikeEntity;

@Repository
public interface LikeDislikeRepository extends JpaRepository<LikeDislikeEntity, Long>{

	LikeDislikeEntity findByUserId(Long long1);

	@Query(value = "SELECT * " +
			" from videossharingschema.like_dislike_entity where video_id=?1 and user_id=?2", nativeQuery = true)
	LikeDislikeEntity findByVideoIdAndUserId(Long id, Long userId);



}
