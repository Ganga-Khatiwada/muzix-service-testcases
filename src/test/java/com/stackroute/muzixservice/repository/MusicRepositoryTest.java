package com.stackroute.muzixservice.repository;

import com.stackroute.muzixservice.domain.Music;
import com.stackroute.muzixservice.exceptions.TrackNotFoundException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MusicRepositoryTest {
    @Autowired
    private MusicRepository musicRepository;
    private Music music;

        @Before
        public void setUp()
        {
            music = new Music();
            music.setTrackId(1);
            music.setTrackName("Hoshwalo ko khabar kya");
            music.setTrackComments("Awesome");
        }

        @After
        public void tearDown(){

            musicRepository.deleteAll();
        }


        @Test
        public void testSaveTracks(){
            musicRepository.save(music);
            Music fetchMusic = musicRepository.findById(music.getTrackId()).get();
            Assert.assertEquals(1, fetchMusic.getTrackId());
        }

        @Test
        public void testSaveTracksFailure(){
            Music testMusic = new Music(2,"Tum Bin Jiya Jaye","Nice Song");
            musicRepository.save(music);
            Music fetchMusic = musicRepository.findById(music.getTrackId()).get();
            Assert.assertNotSame(fetchMusic, music);
        }

        @Test
        public void testdisplayAllTracks(){
            Music music = new Music(2,"Mashallah","nice one");
            Music music1 = new Music(3,"Agar Tum Sath Ho","soothing song");
            musicRepository.save(music);
            musicRepository.save(music1);
            List<Music> list = musicRepository.findAll();
            Assert.assertEquals("Agar Tum Sath Ho",list.get(1).getTrackName());
        }

        @Test
        public void testdisplayAllTracksFailure(){
            Music music = new Music(2,"Mashallah","nice one");
            Music music1 = new Music(3,"Agar Tum Sath Ho","soothing song");
            musicRepository.save(music);
            musicRepository.save(music1);
            List<Music> list = musicRepository.findAll();
            Assert.assertNotEquals("Agar Tum Sath Ho",list.get(0).getTrackName());
        }

        @Test
        public void deleteTrack()
        {
            Music music=new Music(4,"Vaaste","awesome");
            musicRepository.save(music);
            musicRepository.deleteById(music.getTrackId());
            Assert.assertEquals(0, musicRepository.count());
        }

        @Test
        public void deleteTrackFailure()
        {
            Music music=new Music(4,"Vaaste","awesome");
            musicRepository.save(music);
            musicRepository.deleteById(music.getTrackId());
            Assert.assertNotEquals(1, musicRepository.count());
        }
}
