package com.moeb.proposal_manager.user.mapper;

import com.moeb.proposal_manager.user.dto.UserDTO;
import com.moeb.proposal_manager.user.model.User;

public class UserMapper {
  public static User toEntity(UserDTO dto) {
    if (dto == null)
      return null;
    return new User(dto.getId(), dto.getName(), dto.getEmail());
  }

  public static UserDTO toDTO(User entity) {
    if (entity == null)
      return null;
    return new UserDTO(entity.getId(), entity.getName(), entity.getEmail(), entity.getCreatedAt());
  }
}
