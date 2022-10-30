package com.shahin.videos.web.dto;

import lombok.Data;

@Data
public class DetailDto {

    private String uploaderName;
    private String userName;
    private Integer likes;
    private Integer dislikes;
}
