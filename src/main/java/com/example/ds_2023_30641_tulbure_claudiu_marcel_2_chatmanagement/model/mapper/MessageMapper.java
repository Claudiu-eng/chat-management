package com.example.ds_2023_30641_tulbure_claudiu_marcel_2_chatmanagement.model.mapper;

import com.example.ds_2023_30641_tulbure_claudiu_marcel_2_chatmanagement.model.dto.MessageDTO;
import com.example.ds_2023_30641_tulbure_claudiu_marcel_2_chatmanagement.model.entity.Message;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MessageMapper {

    public MessageDTO toDTO(Message message) {
        return MessageDTO.builder()
                .sender(message.getSender())
                .receiver(message.getReceiver())
                .text(message.getText())
                .timestamp(message.getTimestamp())
                .seen(message.getSeen())
                .id(message.getId())
                .build();
    }

    public List<MessageDTO> toDTOList (List<Message> messages) {
        return messages.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Message toEntity(MessageDTO messageDTO) {
        return Message.builder()
                .sender(messageDTO.getSender())
                .receiver(messageDTO.getReceiver())
                .text(messageDTO.getText())
                .timestamp(System.currentTimeMillis())
                .seen(false)
                .build();
    }

}
