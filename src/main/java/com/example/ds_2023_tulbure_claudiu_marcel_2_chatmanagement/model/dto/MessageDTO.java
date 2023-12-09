package com.example.ds_2023_tulbure_claudiu_marcel_2_chatmanagement.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@Getter
@Setter
public class MessageDTO {

    private String sender;
    private String receiver;
    private String text;
    private Long timestamp;
    private Boolean seen;
    private UUID id;

}
