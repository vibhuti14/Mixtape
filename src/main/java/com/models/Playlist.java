package com.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"id",
		"user_id",
		"song_ids"
})
public class Playlist {

	@JsonProperty("id")
	private String id;
	@JsonProperty("user_id")
	private String userId;
	@JsonProperty("song_ids")
	private List<String> songIds = null;

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("user_id")
	public String getUserId() {
		return userId;
	}

	@JsonProperty("user_id")
	public void setUserId(String userId) {
		this.userId = userId;
	}

	@JsonProperty("song_ids")
	public List<String> getSongIds() {
		return songIds;
	}

	@JsonProperty("song_ids")
	public void setSongIds(List<String> songIds) {
		this.songIds = songIds;
	}

}