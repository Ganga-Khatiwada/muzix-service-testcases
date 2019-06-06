package com.stackroute.muzixservice.service;

import com.stackroute.muzixservice.domain.Music;
import com.stackroute.muzixservice.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixservice.exceptions.TrackNotFoundException;

import java.util.List;

public interface MusicService {

    public Music saveTracks(Music music)throws TrackAlreadyExistsException;

    public List<Music> displayAllTracks();

    public Music deleteTrack(int trackId)throws TrackNotFoundException;

    public Music updateTrack(Music music) throws TrackNotFoundException;

    public Music getTrackByName(String trackName)throws TrackNotFoundException;
}

