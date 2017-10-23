package org.springframework.samples.websocket.echo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.yundao.service.IUserService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Echo messages by implementing a Spring {@link WebSocketHandler} abstraction.
 */
public class EchoWebSocketHandler extends TextWebSocketHandler {
	
	@Autowired
	IUserService userService;
	
	private final EchoService echoService;
	private Logger log = Logger.getLogger(getClass());

    private Map<String, Map<String, Map<String, Object>>> roomsMap; // 房间集合
    private Map<String, Map<String, Object>> usersMap; // 用户集合

	
	@Autowired
	public EchoWebSocketHandler(EchoService echoService) {
		this.echoService = echoService;
	}

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		//接收到客户端消息时调用
//        log.info("text message: " + session.getId() + "-" + message.getPayload());
//        String reply = this.echoService.getMessage(message.getPayload());
//        session.sendMessage(new TextMessage(reply));

        String uri = session.getUri().toString();
        int index = uri.indexOf("?");
        String param = index == -1 ? "" : uri.substring(index + 1, uri.length());
        Map<String, String> map = common.getUserMsg(param);
        String openid = map.get("openid");
        Map<String, Object> user = usersMap.get(openid);
        String roomid = (String) user.get("roomid");

        log.info("text message: " + session.getId() + "-" + message.getPayload());
        String reply = openid + " : " + this.echoService.getMessage(message.getPayload());
        log.info("reply = " + reply);
        for (String key : roomsMap.get(roomid).keySet()) {
            System.out.println("Key = " + key);
            WebSocketSession s = (WebSocketSession) usersMap.get(key).get("session");
            s.sendMessage(new TextMessage(reply));
        }
//        session.sendMessage(new TextMessage(reply));
	}

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 与客户端完成连接后调用
//        log.info("afterConnectionEstablished");
//        log.info("getId:" + session.getId());
//        log.info("getLocalAddress:" + session.getLocalAddress().toString());
//        log.info("getTextMessageSizeLimit:" + session.getTextMessageSizeLimit());
//        log.info("getUri:" + session.getUri().toString());
//        log.info("getPrincipal:" + session.getPrincipal());

        String uri = session.getUri().toString();
        int index = uri.indexOf("?");
        String param = index == -1 ? "" : uri.substring(index + 1, uri.length());
        Map<String, String> map = common.getUserMsg(param);
        String openid = map.get("openid");
        String roomid = map.get("roomid");
        roomid = roomid.equals("-1") ? UUID.randomUUID().toString().replaceAll("-", "") : roomid;
        usersMap = usersMap == null ? new HashMap<String, Map<String, Object>>() : usersMap;
        Map<String, Object> user = usersMap.get(openid);
        user = user == null ? new HashMap<String, Object>() : user;
        user.put("openid", openid);
        user.put("roomid", roomid);
        user.put("session", session);
        usersMap.put(openid, user);
        roomsMap = roomsMap == null ? new HashMap<String, Map<String, Map<String, Object>>>() : roomsMap;
        Map<String, Map<String, Object>> room =  roomsMap.get(roomid);
        room = room == null ? new HashMap<String, Map<String, Object>>() : room;
        room.put(openid, user);
        roomsMap.put(roomid, room);
        log.info(usersMap);
        log.info(roomsMap.get(roomid));
//        String text = "您的openid是" + openid + "\n\t\r您所在的房间号为" + roomid;
//        session.sendMessage(new TextMessage(text.getBytes("UTF-8")));
        log.info("打开连接用户的openid:" + openid + "    所在房间roomid:" + roomid);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        // 消息传输出错时调用
        log.info("handleTransportError");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        // 一个客户端连接断开时关闭
//        log.info("getId:" + session.getId());
//        log.info("getLocalAddress:" + session.getLocalAddress().toString());
//        log.info("afterConnectionClosed");

        String uri = session.getUri().toString();
        int index = uri.indexOf("?");
        String param = index == -1 ? "" : uri.substring(index + 1, uri.length());
        Map<String, String> map = common.getUserMsg(param);
        String openid = map.get("openid");
        Map<String, Object> user = usersMap.get(openid);
        String roomid = (String) user.get("roomid");
        log.info("关闭连接用户的openid:" + openid + "    所在房间roomid:" + roomid);
        usersMap.remove(openid);
        roomsMap.get(roomid).remove(openid);
        log.info(usersMap);
        log.info(roomsMap.get(roomid));
    }

    @Override
    public boolean supportsPartialMessages() {
        // TODO Auto-generated method stub
        return false;
    }

}
