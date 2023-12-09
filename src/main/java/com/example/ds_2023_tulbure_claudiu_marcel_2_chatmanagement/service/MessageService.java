package com.example.ds_2023_tulbure_claudiu_marcel_2_chatmanagement.service;

import com.example.ds_2023_tulbure_claudiu_marcel_2_chatmanagement.model.dto.MessageDTO;
import com.example.ds_2023_tulbure_claudiu_marcel_2_chatmanagement.model.entity.Message;
import com.example.ds_2023_tulbure_claudiu_marcel_2_chatmanagement.model.mapper.MessageMapper;
import com.example.ds_2023_tulbure_claudiu_marcel_2_chatmanagement.repository.MessageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @Transactional
    public MessageDTO sendMessage(String emitter, String recipient, String text) {
        MessageDTO messageDTO = MessageDTO.builder()
                .sender(emitter)
                .receiver(recipient)
                .text(text)
                .build();
        Message message=messageRepository.save(messageMapper.toEntity(messageDTO));
        simpMessagingTemplate.convertAndSend("/topic/socket/messages/" + recipient+"/"+emitter, message);
        simpMessagingTemplate.convertAndSend("/topic/socket/messages/" + recipient+"/"+emitter+"/typing", "no");
        return messageMapper.toDTO(message);
    }

    public List<MessageDTO> getMessages(String sender, String receiver) {
        return messageMapper.toDTOList(
                messageRepository.findBySenderAndReceiverOrReceiverAndSenderOrderByTimestampAsc(
                        sender, receiver, sender,receiver));
    }

    public void markAsRead(List<UUID> uuids) {
        List<Message> messages = messageRepository.findAllById(uuids);
        messages.forEach(message -> message.setSeen(true));
        messageRepository.saveAll(messages);
        messages.forEach(message -> simpMessagingTemplate.convertAndSend(
                "/topic/socket/messages/" + message.getSender()+"/"+message.getReceiver()+"/seen", message));

    }

    public void sendTypingNotification(String emitter, String recipient, String text) {
        simpMessagingTemplate.convertAndSend("/topic/socket/messages/" + recipient+"/"+emitter+"/typing", text);
    }
}
