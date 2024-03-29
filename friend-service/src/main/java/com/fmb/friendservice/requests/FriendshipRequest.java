package com.fmb.friendservice.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FriendshipRequest {

    private Long userId;
    private Long friendId;
}
