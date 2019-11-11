package com.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"users",
		"playlists",
		"songs"
})
public class TapeWrapper {

	@JsonProperty("users")
	private List<User> users = null;
	@JsonProperty("playlists")
	private List<Playlist> playlists = null;
	@JsonProperty("songs")
	private List<Song> songs = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("users")
	public List<User> getUsers() {
		return users;
	}

	@JsonProperty("users")
	public void setUsers(List<User> users) {
		this.users = users;
	}

	@JsonProperty("playlists")
	public List<Playlist> getPlaylists() {
		return playlists;
	}

	@JsonProperty("playlists")
	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}

	@JsonProperty("songs")
	public List<Song> getSongs() {
		return songs;
	}

	@JsonProperty("songs")
	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}

