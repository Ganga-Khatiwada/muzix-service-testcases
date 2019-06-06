package com.stackroute.muzixservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;


@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Music {

    @Id
    private int trackId;
    private String trackName;
    private String trackComments;
}
