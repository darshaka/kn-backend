package com.kn.booking.domain.supportive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Box {
    private Integer length;
    private Integer height;
    private Integer weight;
}
