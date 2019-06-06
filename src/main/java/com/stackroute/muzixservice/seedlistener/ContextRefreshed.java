package com.stackroute.muzixservice.seedlistener;

import com.stackroute.muzixservice.domain.Music;
import com.stackroute.muzixservice.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:configur.properties")
public class ContextRefreshed implements ApplicationListener<ContextRefreshedEvent> {

        Music music =new Music();
        @Autowired
        MusicRepository musicRepository;
        @Autowired
        private Environment environment;

        @Override
        public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
            music.setTrackId(Integer.parseInt(environment.getProperty("music.trackId")));
            music.setTrackName(environment.getProperty("music.trackName"));
            music.setTrackComments(environment.getProperty("music.trackComments"));
            musicRepository.save(music);

        }
}
