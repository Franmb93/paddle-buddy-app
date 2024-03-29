package com.fmb.friendservice.services.impl;

import com.fmb.friendservice.enums.FriendshipStatus;
import com.fmb.friendservice.models.Friendships;
import com.fmb.friendservice.repositories.FriendshipRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)

class FriendshipServiceImplTest {
    @Mock
    private FriendshipRepository friendshipRepository;

    @InjectMocks
    private FriendshipServiceImpl friendshipService;
    @Captor
    private ArgumentCaptor<Friendships> friendshipsCaptor;

    @Test
    void sendFriendRequestCreatesPendingFriendship() {
        Long requesterId = 1L;
        Long addresseeId = 2L;

        friendshipService.sendFriendRequest(requesterId, addresseeId);

        verify(friendshipRepository).save(friendshipsCaptor.capture());
        Friendships capturedFriendship = friendshipsCaptor.getValue();

        Assertions.assertThat(capturedFriendship.getRequesterId()).isEqualTo(requesterId);
        Assertions.assertThat(capturedFriendship.getAddresseeId()).isEqualTo(addresseeId);
        Assertions.assertThat(capturedFriendship.getStatus()).isEqualTo(FriendshipStatus.PENDING);
    }
}