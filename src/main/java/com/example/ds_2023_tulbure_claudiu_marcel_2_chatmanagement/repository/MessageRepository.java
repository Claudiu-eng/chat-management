package com.example.ds_2023_tulbure_claudiu_marcel_2_chatmanagement.repository;

import com.example.ds_2023_tulbure_claudiu_marcel_2_chatmanagement.model.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<Message, UUID> {

    List<Message> findBySenderAndReceiverOrReceiverAndSenderOrderByTimestampAsc(String sender, String receiver,String receiver2,String sender2);

}
