package com.stackroute.muzixservice.seedlistener;

import com.stackroute.muzixservice.domain.Music;
import com.stackroute.muzixservice.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLine implements CommandLineRunner {
        @Value("${music.trackId}")
        private int trackId;
        @Value("${music.trackName}")
        private String trackName;
        @Value("${music.trackComments}")
        private String trackComments;

        Music music =new Music();
        @Autowired
        MusicRepository musicRepository;
        @Override
        public void run(String... args) throws Exception {
            music.setTrackId(trackId);
            music.setTrackName(trackName);
            music.setTrackComments(trackComments);
            musicRepository.save(music);
        }
}