package com.mjc813.network.chatpgm.client;

import com.mjc813.network.chatpgm.ChatCommand;
import com.mjc813.network.chatpgm.ChatData;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**
 * 클라이언트의 키보드 입력시 어떤모드인지 (TOPMENU, SUBMENU, CHATTING)
 * 클라이언트와 서버사이의 명령어가 전송되는 ChatData
 */
public class ClientCommand {
    /**
     * 클라이언트의 키보드 입력시 모드
     */
    private ClientMode clientMode = ClientMode.MENU;
    /**
     * 클라이언트와 서버사이의 데이터 통신시에 명령어, 방번호, 사용자명, 대화방명, 데이터
     */
    private ChatData chatData = new ChatData();
}
