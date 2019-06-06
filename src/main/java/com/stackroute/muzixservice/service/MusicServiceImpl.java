package com.stackroute.muzixservice.service;

import com.stackroute.muzixservice.domain.Music;
import com.stackroute.muzixservice.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixservice.exceptions.TrackNotFoundException;
import com.stackroute.muzixservice.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@CacheConfig(cacheNames = "music")
@Primary
@Service
public class MusicServiceImpl implements MusicService {
    MusicRepository musicRepository;
    Music music=null;
    public void simulateDelay(){
        try {
            Thread.sleep(3000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Autowired
    public MusicServiceImpl(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }

    @Override
    public Music saveTracks(Music music) throws TrackAlreadyExistsException {
        if (musicRepository.existsById(music.getTrackId()))
            throw new TrackAlreadyExistsException("Track Already Exists");
        Music music1=musicRepository.save(music);
        if(music1==null)
        {
            throw new TrackAlreadyExistsException("Track Already Exists");
        }
        return music1;
    }
@Cacheable
    @Override
    public List<Music> displayAllTracks()
    {
        simulateDelay();
        List<Music> musicList =(List<Music>)musicRepository.findAll();
        return musicList;

    }

    @Override
    public Music deleteTrack(int trackId)
    {
        musicRepository.deleteById(trackId);
        return music;
    }

    @CachePut
    @Override
    public Music updateTrack(Music music) throws TrackNotFoundException {
        if(musicRepository.existsById(music.getTrackId()))
        {
            Music music2=musicRepository.findById(music.getTrackId()).get();
            music2.setTrackComments(music.getTrackComments());
            musicRepository.save(music2);
            return music2;
        }
        else
        {
            throw new TrackNotFoundException("Track Not Found");
        }
    }

    @Override
    public Music getTrackByName(String trackName){

        List<Music> lists = null;
        lists = musicRepository.getTrackByName(trackName);
        if (lists.equals(null))
        {
            try {
                throw new TrackNotFoundException("exception");
            } catch (TrackNotFoundException trackNotFoundException) {
                trackNotFoundException.printStackTrace();
            }
        }
        return music;
    }

    @PostConstruct
    public void loadData(){
       musicRepository.save(Music.builder().trackId(1).trackName("Album1").trackComments("New").build());
        musicRepository.save(Music.builder().trackId(2).trackName("Album2").trackComments("Old").build());
        musicRepository.save(Music.builder().trackId(3).trackName("Album3").trackComments("New-Old").build());
    }


}
