package com.shahin.videos.repositories;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shahin.videos.entities.Videos;

@Repository
public interface VideosRepository extends JpaRepository<Videos, Long> {

	List<Videos> findByUserId(Long id);
	
    @Query(value = "select v.id as ID, " +
    		" v.url_path as URL_PATH,  " +
    		" sum(l.likes) as LIKES,   " +
    		" l.like_boolean as LIKE_BOOLEAN, " +
    		"  sum(l.dis_like) as DISLIKE, " +
			" u.id as video_user_id, " +
			" l.user_id as like_dislike_user_id, " +
    		" l.dis_like_boolean as DISLIKE_BOOLEAN " +
    		" from videos v left join like_dislike_entity l on l.video_id = v.id " +
			" left join tb_user u on u.id = l.user_id " +
            " group by v.id", nativeQuery = true)
    List<Tuple> getData();



	@Query(value = "select v.id as ID, " +
			" v.url_path as URL_PATH, " +
			" sum(l.likes) as LIKES,  " +
			" l.like_boolean as LIKE_BOOLEAN, " +
			" sum(l.dis_like) DISLIKE, " +
			" l.dis_like_boolean as DISLIKE_BOOLEAN " +
			" from videos v left join like_dislike_entity l " +
			"on l.video_id = v.id where l.url_path=?1 group by v.id, l.url_path", nativeQuery = true)
	Tuple getDataByUrlPath(@Param("urlPath") String urlPath);


	@Query(value = "select v.uploader_name  as UPLOADER_NAME, " +
			" CONCAT(u.first_name, u.last_name)  as USER_NAME,  " +
			" sum(l.likes) as LIKES,   " +
			" sum(l.dis_like) as DIS_LIKE " +
			" from like_dislike_entity l left join videos v on v.id = l.video_id " +
			" left join videossharingschema.tb_user u on u.id = l.user_id  " +
			" where l.video_id = ?1 group by u.id", nativeQuery = true)
	List<Tuple> getDetailData(@Param("id") Long id);


	@Query(value = "select uploader_name from videos where id = ?1", nativeQuery = true)
	Tuple getDetailSingleData(@Param("id") Long id);



}
