package kr.co.kimga.service.dto;

import kr.co.kimga.domain.Notification;

import java.util.List;

public record GetUserNotificationByPivotResult(List<Notification> notifications, boolean hasNext) {}
