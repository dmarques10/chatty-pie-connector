package com.chattypie.handler;

import static com.appdirect.sdk.appmarket.api.AccountStatus.CANCELLED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.appdirect.sdk.appmarket.api.APIResult;
import com.appdirect.sdk.appmarket.api.AccountInfo;
import com.appdirect.sdk.appmarket.api.SubscriptionClosed;
import com.chattypie.service.chattypie.chatroom.ChatroomService;

@RunWith(MockitoJUnitRunner.class)
public class SubscriptionClosedHandlerTest {

	@Mock
	private ChatroomService mockChatroomService;

	private SubscriptionClosedHandler testedHandler;

	@Before
	public void setUp() throws Exception {
		testedHandler = new SubscriptionClosedHandler(mockChatroomService);
	}

	@Test
	public void testHandle_whenSubscriptionClosedEventHandled_thenTheCorrespondingChatroomIsRemoved() throws Exception {
		//Given
		String testChatroomIdentifier = "testAccountIdentifier";
		SubscriptionClosed testSubscriptionEvent = new SubscriptionClosed(
			new AccountInfo(testChatroomIdentifier, CANCELLED)
		);

		//When
		APIResult result = testedHandler.handle(testSubscriptionEvent);

		//Then
		assertThat(result.isSuccess()).isTrue();
		verify(mockChatroomService).removeChatroom(testChatroomIdentifier);
	}

}