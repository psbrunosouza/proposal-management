package com.moeb.proposalmanager.user.mapper;

import com.moeb.proposalmanager.user.dto.CreateUserRequestDTO;
import com.moeb.proposalmanager.user.dto.UserResponseDTO;
import com.moeb.proposalmanager.user.model.User;

public class UserMapper {
  public static User toEntity(CreateUserRequestDTO dto) {
    if (dto == null)
      return null;
    return new User(null, dto.getName(), dto.getEmail(), dto.getPassword(), null);
  }

  public static User toEntity(UserResponseDTO dto) {
    if (dto == null)
      return null;
    return new User(dto.getId(), dto.getName(), dto.getEmail(), null, dto.getCreatedAt());
  }

  public static UserResponseDTO toDTO(User entity) {
    if (entity == null)
      return null;
    return new UserResponseDTO(entity.getId(), entity.getName(), entity.getEmail(), entity.getCreatedAt());
  }
}
