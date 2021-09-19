package com.tiozao.repositories;

import com.tiozao.entities.MessageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends CrudRepository<MessageEntity, UUID> {

    List<MessageEntity> findByToAndReaded(@Param("to") String to, @Param("readed") boolean readed);

}
