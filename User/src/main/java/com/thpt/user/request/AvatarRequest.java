package com.thpt.user.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AvatarRequest {

	private String avatarId;
	private String avatarUrl;
	private String thumbnailId;
	private String thumbnailUrl;
}
