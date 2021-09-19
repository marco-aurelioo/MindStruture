package com.tiozao.repositories;

import com.tiozao.entities.FriendListEntity;
import com.tiozao.entities.MessageEntity;
import com.tiozao.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FriendListRepository extends CrudRepository<FriendListEntity, UUID> {

    FriendListEntity findByUser(UserEntity user);

}
