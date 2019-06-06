package com.stackroute.muzixservice.service;

import com.stackroute.muzixservice.domain.Music;
import com.stackroute.muzixservice.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixservice.exceptions.TrackNotFoundException;
import com.stackroute.muzixservice.repository.MusicRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class MusicServiceTest {

    private Music music;
    Optional optional;

    //Create a mock for UserRepository
    @Mock
    private MusicRepository musicRepository;

    //Inject the mocks as dependencies into UserServiceImpl
    @InjectMocks
    private MusicServiceImpl musicService;
    List<Music> list= null;


    @Before
    public void setUp(){
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        music = new Music();
        music.setTrackId(1);
        music.setTrackName("Bekhayali Mein Bhi Tera");
        music.setTrackComments("Very Good");
        list = new ArrayList<>();
        list.add(music);
        optional=optional.of(music);
    }

    @Test
    public void saveTracksTestSuccess() throws TrackAlreadyExistsException {

        when(musicRepository.save((Music) any())).thenReturn(music);
        Music saveMusic = musicService.saveTracks(music);
        Assert.assertEquals(music,saveMusic);
        //verify here verifies that userRepository save method is only called once
        verify(musicRepository,times(1)).save(music);

    }

    @Test(expected = TrackAlreadyExistsException.class)
    public void saveTracksTestFailure() throws TrackAlreadyExistsException {

        when(musicRepository.save((Music) any())).thenReturn(null);
        Music saveMusic = musicService.saveTracks(music);
        Assert.assertNotEquals(music,saveMusic);
    }
    @Test
    public void updateTrackTest() throws TrackNotFoundException{

        when(musicRepository.findById(music.getTrackId())).thenReturn(optional);
        Music m2=new Music(music.getTrackId(),music.getTrackName(),music.getTrackComments());
        musicRepository.save(m2);
        Music musiclist=musicService.updateTrack(m2);
        Assert.assertEquals(m2,musiclist);
    }

    @Test
    public void deleteTrackTest() throws TrackNotFoundException {
        when(musicRepository.findById(music.getTrackId())).thenReturn(optional);
        Music deletemusic=musicService.deleteTrack(music.getTrackId());
        Assert.assertEquals(1,deletemusic.getTrackId());
        verify(musicRepository,times(1)).deleteById(music.getTrackId());
    }

    @Test
    public void displayAllTracksTest(){
        musicRepository.save(music);
        when(musicRepository.findAll()).thenReturn(list);
        List<Music> musiclist = musicService.displayAllTracks();
        System.out.println(musiclist);
        Assert.assertEquals(list,musiclist);
    }

}
