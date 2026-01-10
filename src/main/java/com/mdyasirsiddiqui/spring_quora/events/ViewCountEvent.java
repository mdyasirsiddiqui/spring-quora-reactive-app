package com.mdyasirsiddiqui.spring_quora.events;

import java.time.LocalDateTime;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ViewCountEvent {

    private String targetId;
    private String targetType;
    private LocalDateTime timestamp;

}
