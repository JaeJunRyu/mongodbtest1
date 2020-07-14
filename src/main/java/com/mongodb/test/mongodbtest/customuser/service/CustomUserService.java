package com.mongodb.test.mongodbtest.customuser.service;

import com.mongodb.test.mongodbtest.customuser.domain.UserRepository;
import com.mongodb.test.mongodbtest.customuser.presentation.dto.CustomDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserService {

    private final UserRepository userRepository;

    public Long insert(CustomDto.CustomRequestDto dto){
        return userRepository.save(dto.toEntity(1L)).getSeq();
    }

    public Long customInsert(CustomDto.CustomRequestDto dto){
        return userRepository.customInsert(dto);
    }

    public String findById(String id) {
        final var customUser = userRepository.findById(id).orElse(null);
        final var bySeq = userRepository.findBySeq(1L);

        final CustomDto.CustomResponseDto responseDto = new CustomDto.CustomResponseDto(customUser);

        return responseDto.getId();
    }


    public void findById(){
//        userRepository.customMethod();

//        userRepository.save()
    }






}
