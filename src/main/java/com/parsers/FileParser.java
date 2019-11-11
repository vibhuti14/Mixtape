package com.parsers;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.models.Playlist;
import com.models.Song;
import com.models.TapeWrapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Vibhuti Gupta on 11/10/19
 */
public class FileParser {
	Map<String, Playlist> playlistMap = new HashMap<>();
	ObjectMapper mapper = new ObjectMapper();
	public TapeWrapper parseFile(String fileName){
		TapeWrapper tapeWrapper = new TapeWrapper();
		try {
			// Convert JSON string from file to Object
			File file = new File(
					getClass().getClassLoader().getResource(fileName).getFile()
			);
			tapeWrapper = mapper.readValue(file, TapeWrapper.class);
			for(Playlist playlist : tapeWrapper.getPlaylists()){
				playlistMap.put(playlist.getId(),playlist);
			}

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tapeWrapper;
	}

	public TapeWrapper parseChangesFile(String originalFile, String changesFile){
		JSONParser jsonParser = new JSONParser();
		TapeWrapper tape = new TapeWrapper();

		File file = new File(
				getClass().getClassLoader().getResource(changesFile).getFile()
		);
        try (FileReader reader = new FileReader(file))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);


            JSONObject tapeWrapper = (JSONObject) obj;
			JSONObject add = (JSONObject) tapeWrapper.get("add");
			JSONObject edit = (JSONObject) tapeWrapper.get("edit");
			JSONObject delete = (JSONObject) tapeWrapper.get("delete");
			TapeWrapper valuesToAdd = mapper.readValue(add.toJSONString(), TapeWrapper.class);
			TapeWrapper valuesToEdit = mapper.readValue(edit.toJSONString(), TapeWrapper.class);
			TapeWrapper valueToDelete = mapper.readValue(delete.toJSONString(), TapeWrapper.class);
			tape = parseFile(originalFile);
			List<Playlist> playlists =  tape.getPlaylists();
			List<Song> songs = tape.getSongs();

			//Criteria: Add a new playlist for an existing user; the playlist should contain at least one existing song.
			//Add the new song given in the changes file before adding the new playlist
			if(valuesToAdd.getSongs()!=null){
				for(Song song : valuesToAdd.getSongs()){
					songs.add(song);
				}
			}
			//Add the new playlist
			if(valuesToAdd.getPlaylists()!=null){
				for(Playlist playlist : valuesToAdd.getPlaylists()){
					playlists.add(playlist);
					playlistMap.put(playlist.getId(),playlist);
				}
			}

			//Criteria: Add an existing song to an existing playlist
			// Edit the given playlist in the changes file
			if(valuesToEdit.getPlaylists()!=null){
				for(Playlist playlistToEdit : valuesToEdit.getPlaylists()){
					//get the existing playlist
					Playlist playlist = playlistMap.get(playlistToEdit.getId());
					//Since for now we are adding only one song, we can always loop through
					//if we need to add multiple songs to the same playlist
					playlist.getSongIds().add(playlistToEdit.getSongIds().get(0));
					playlistMap.put(playlist.getId(), playlist);
				}
			}
			//Criteria: Remove an existing playlist.
			//Assuming we are deleting just one playlist at a time.
			//Find out if the playlist to be deleted exists.
			if(playlistMap.containsKey(valueToDelete.getPlaylists().get(0).getId())){
				playlists.removeIf(playlist -> playlist.getId().equals(valueToDelete.getPlaylists().get(0).getId()));
				playlistMap.remove(valueToDelete.getPlaylists().get(0).getId());
			}
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return tape;
	}

	public void writeToFile(TapeWrapper tapeWrapper, String fileName){
		File file=new File(fileName);
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			//Write the changed values into output file
			mapper.writeValue(file, tapeWrapper);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
