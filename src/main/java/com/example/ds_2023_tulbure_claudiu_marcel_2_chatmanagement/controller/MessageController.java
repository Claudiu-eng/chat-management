package com.example.ds_2023_tulbure_claudiu_marcel_2_chatmanagement.controller;

import com.example.ds_2023_tulbure_claudiu_marcel_2_chatmanagement.model.dto.MessageDTO;
import com.example.ds_2023_tulbure_claudiu_marcel_2_chatmanagement.model.entity.Message;
import com.example.ds_2023_tulbure_claudiu_marcel_2_chatmanagement.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/message")
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/{emitter}/{recipient}")
    public ResponseEntity<MessageDTO> sendMessage(@PathVariable String emitter, @PathVariable String recipient, @RequestBody String text) {
        return ResponseEntity.status(HttpStatus.OK).body(messageService.sendMessage(emitter, recipient, text));
    }
    @PostMapping("/typing/{emitter}/{recipient}")
    public ResponseEntity<MessageDTO> sendTypingNotification(@PathVariable String emitter, @PathVariable String recipient, @RequestBody String text) {
        messageService.sendTypingNotification(emitter, recipient, text);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{emitter}/{recipient}")
    public ResponseEntity<List<MessageDTO>> getMessages(@PathVariable String emitter, @PathVariable String recipient) {
        return ResponseEntity.ok(messageService.getMessages(emitter, recipient));
    }
    @PostMapping("")
    public ResponseEntity<?> markAsRead(@RequestBody List<UUID> uuids) {
        messageService.markAsRead(uuids);
        return ResponseEntity.ok().build();
    }
}
