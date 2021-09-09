package com.tiozao.repositories;

import com.tiozao.entities.MessageEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface MessageRepository extends CrudRepository<MessageEntity, UUID> {
}
