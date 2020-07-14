package com.mongodb.test.mongodbtest.customuser.presentation.dto;

import com.mongodb.test.mongodbtest.customuser.domain.User;
import lombok.*;

import java.time.LocalDateTime;

public class CustomDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class CustomRequestDto {
        private String name;

        private String content;

        private LocalDateTime startDate;

        private LocalDateTime endDate;

        public User toEntityConstructor(Long seq){
            return new User(seq,name,content,startDate,endDate);
        }

        public User toEntity(Long seq){
            return User.builder()
                    .seq(seq)
                    .name(name)
                    .content(content)
                    .startDate(startDate)
                    .endDate(endDate)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    @ToString
    public static class CustomResponseDto {
        private String id;

        private Long seq;

        private String name;

        private String content;

        private LocalDateTime startDate;

        private LocalDateTime endDate;

        public CustomResponseDto(User user) {
            this.id = user.getId();
            this.seq = user.getSeq();
            this.name = user.getName();
            this.content = user.getContent();
            this.startDate = user.getStartDate();
            this.endDate = user.getEndDate();
        }
    }


}
