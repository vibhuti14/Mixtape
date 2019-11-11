package com.models;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"id",
		"artist",
		"title"
})
public class Song {

	@JsonProperty("id")
	private String id;
	@JsonProperty("artist")
	private String artist;
	@JsonProperty("title")
	private String title;

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("artist")
	public String getArtist() {
		return artist;
	}

	@JsonProperty("artist")
	public void setArtist(String artist) {
		this.artist = artist;
	}

	@JsonProperty("title")
	public String getTitle() {
		return title;
	}

	@JsonProperty("title")
	public void setTitle(String title) {
		this.title = title;
	}

}
