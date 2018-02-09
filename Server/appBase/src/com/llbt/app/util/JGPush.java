package com.llbt.app.util;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

public class JGPush {
	
	static int t = 0;

	public static void main(String[] args) throws Exception
    {
		JPushClient jpushClient = new JPushClient("08906475d82195fe4c8324ef", "44b4de5694b9bc56883b5ae6", null, ClientConfig.getInstance());

	    // For push, all you need do is to build PushPayload object.
	    PushPayload payload = buildPushObject_all_all_alert();

	    try {
	        PushResult result = jpushClient.sendPush(payload);
	        System.out.println("Got result - " + result);

	    } catch (APIConnectionException e) {
	        // Connection error, should retry later
	    	 System.out.println("Connection error, should retry later");

	    } catch (APIRequestException e) {
	        // Should review the error, and fix the request
	    	 System.out.println("Should review the error, and fix the request");
	    }

    }
	
	public static PushPayload buildPushObject_all_all_alert(){
		t++;
		return PushPayload.newBuilder().setPlatform(Platform.ios()).setAudience(Audience.all()).setNotification(Notification.newBuilder()
                .addPlatformNotification(IosNotification.newBuilder().setAlert("ses"+t).setBadge(t).setSound("default").addExtra("from", "JPush").build()).build()).build();

	}
}
