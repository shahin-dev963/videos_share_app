package com.shahin.videos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shahin.videos.entities.LikeDislikeEntity;
import com.shahin.videos.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

	User findByEmail(String email);

	
}
