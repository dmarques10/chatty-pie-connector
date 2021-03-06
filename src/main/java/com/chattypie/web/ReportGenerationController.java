/*
 * Copyright 2017 AppDirect, Inc. and/or its affiliates
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.chattypie.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chattypie.persistence.model.ChatroomCreationRecord;
import com.chattypie.service.chattypie.chatroom.ChatroomService;
import com.chattypie.service.chattypie.greeting.NotificationService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ReportGenerationController {

	private final ChatroomService chatroomReportService;
	private final NotificationService notificationService;
	private final String receiverEmail;

	@RequestMapping(method = GET, value = "/chatrooms/daily")
	public String getChatroomReport() {
		List<ChatroomCreationRecord> chatroomsCreatedLastWeek = chatroomReportService.chatroomsCreatedOverTheLastDay();
		notificationService.sendDailyChatroomCreatedReport(receiverEmail, chatroomsCreatedLastWeek);

		return "Report notification sent";
	}
}
