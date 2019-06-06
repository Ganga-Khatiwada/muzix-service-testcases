package com.stackroute.muzixservice.controller;

import com.stackroute.muzixservice.domain.Music;
import com.stackroute.muzixservice.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixservice.exceptions.TrackNotFoundException;
import com.stackroute.muzixservice.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(value="api/v1")
public class MusicController
{
    private MusicService musicService;
    @Autowired
    public MusicController(MusicService musicService)
    {
        this.musicService = musicService;
    }
    @PostMapping("/music")
    public ResponseEntity<?> saveTracks(@RequestBody Music music) throws TrackAlreadyExistsException
    {
        ResponseEntity responseEntity;
        try{
            musicService.saveTracks(music);
            responseEntity=new ResponseEntity<String>("Successfully saved", HttpStatus.CREATED);
           }catch(Exception exception)
            {
                responseEntity=new ResponseEntity<String>(exception.getMessage(),HttpStatus.CONFLICT);
            }
            return responseEntity;
    }
    @GetMapping("music")
    public ResponseEntity<?> displayAllTracks()
    {
        return new ResponseEntity<List<Music>>(musicService.displayAllTracks(),HttpStatus.OK);
    }

    @DeleteMapping("/music/{trackId}")
    public ResponseEntity<?> deleteTrack(@PathVariable int trackId) throws TrackNotFoundException {
        musicService.deleteTrack(trackId);
        return new ResponseEntity<List<Music>>(musicService.displayAllTracks(),HttpStatus.OK);
    }
    @PutMapping("music")
    public ResponseEntity<String> updateTrack(@RequestBody Music music) throws TrackNotFoundException
    {
        ResponseEntity responseEntity;
        Music music1=musicService.updateTrack(music);
        return new ResponseEntity<String>("Successfully Updated",HttpStatus.OK);
    }
}