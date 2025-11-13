package com.mjc813.network.chatpgm.client;

import com.mjc813.network.chatpgm.ChatCommand;
import com.mjc813.network.chatpgm.ChatData;
import com.mjc813.network.chatpgm.json.JsonProcesser;
import lombok.Getter;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

@Getter
public class ChatClientApp implements Runnable {
    private ClientCommand clientCommand = new ClientCommand();
    private JsonProcesser jsonProcesser = new JsonProcesser();

    /**
     * 소캣통신을 위한 객체 (클라이언트끼리는 연결이 안됨)
     * 클라이언트 Socket connect ====> 서버의 ServerSocket accept
     * 클라이언트 Socket ====== 서버의 accept 에서 리턴된 Socket (통신이 시작)
     */
    private Socket socket;
    /**
     * InputStream 으로 읽기(수신) 편한 메소드들을 구현한 버퍼링 클래스
     */
    private BufferedReader br;
    /**
     * OutStream 으로 쓰기(전송) 편한 메소드들을 구현한 버퍼링 클래스
     */
    private BufferedWriter bw;

    public void connect( String ip, int port ) throws IOException {
        socket = new Socket();
        socket.connect(new InetSocketAddress(ip, port));
        // 서버IP, 포트로 접속한다. 접속되면 이 소켓으로 통신가능하다.
        this.bw = new BufferedWriter(
                new OutputStreamWriter(this.socket.getOutputStream())
        );
        this.br = new BufferedReader(
                new InputStreamReader(this.socket.getInputStream())
        );
    }

    private void write(String str) throws IOException {
        if ( str == null || "".equals(str) ) {
            return;
        }
        this.bw.write(str);
        this.bw.newLine();
        this.bw.flush();
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.read();
            } catch (ParseException ex) {
                ex.printStackTrace();
            } catch (Exception ex) {
                System.out.println("exit run Thread");
            }
        }
    }

    private void read() throws IOException, ParseException {
        String strFromServer = this.br.readLine();  // 통신소켓에서 데이터를 입력받기 대기 (블로킹 상태 발생)
        // 새로운대화방을 만들면 서버에서 {"command":"CREATEDROOM", "room":새로운방정수값, "roomName":"", "userName":"", "data":""} 문자열이 온다.
        if ( strFromServer == null || "".equals(strFromServer) ) {
            return;
        }
        try {
            ChatData cd = this.jsonProcesser.jsonStringToChatData(strFromServer);
            if( cd.getCommand() == ChatCommand.SERVERCREATEDROOM
                    || cd.getCommand() == ChatCommand.CREATEROOM
                    || cd.getCommand() == ChatCommand.ENTERROOM ) {
                // 대화방에 입장했을때
                this.clientCommand.getChatData().setRoom(cd.getRoom());
                this.clientCommand.getChatData().setRoomName(cd.getRoomName());
                this.clientCommand.setClientMode(ClientMode.CHATTING);
                this.printChatMenu();
            } else if( cd.getCommand() == ChatCommand.EMPTYROOM ) {
                System.out.printf("(%d) 대화방이 존재하지 않습니다.\n", cd.getRoom());
                this.clientCommand.setClientMode(ClientMode.MENU);
            }
        } catch (ParseException e) {
            // JSON 파싱 예외가 발생한 일반 문자열이다.
            if ( this.clientCommand.getChatData().getCommand() == ChatCommand.ROOMLIST ) {
                this.printMenu2(strFromServer);
                this.clientCommand.setClientMode(ClientMode.MENU);
            } else {
//                this.clientCommand.setClientMode(ClientMode.CHATTING);
                System.out.println(strFromServer);
            }
        }
    }

    public void close() throws IOException {
        this.br.close();
        this.bw.close();
        this.socket.close();
    }

    /**
     * 키보드에서 입력받은 str 을 parsing 해서 각각명령어 MENU 또는 SUBMENU 또는 채팅글자등을 처리
     * @param str
     * @throws IOException
     */
    private void parseInput(String str) throws IOException {
        if ( str == null || "".equals(str) ) {
            return;
        }
        if ( this.clientCommand.getClientMode() == ClientMode.MENU ) {
            // 첫번째 메뉴일경우에는 키보드에서 입력 {1,2,3,4,q} 가 눌러진다.
            switch( str ) {
                case "1":
                    this.clientCommand.getChatData().setCommand(ChatCommand.CHANGENAME);
                    this.clientCommand.setClientMode(ClientMode.SUBMENU);
                    System.out.print("대화명변경 : ");
                    break;
                case "2":
                    this.clientCommand.getChatData().setCommand(ChatCommand.ROOMLIST);
                    String sendData = this.jsonProcesser.chatDataToJsonString(this.clientCommand.getChatData());
                    this.write(sendData);
                    break;
                case "3":
                    if ( !this.checkExistUserName() ) {
                        break;
                    }
                    this.clientCommand.getChatData().setCommand(ChatCommand.CREATEROOM);
                    this.clientCommand.setClientMode(ClientMode.SUBMENU);
                    System.out.print("대화방명을 입력 : ");
                    break;
                case "4":
                    if ( !this.checkExistUserName() ) {
                        break;
                    }
                    this.clientCommand.getChatData().setCommand(ChatCommand.ENTERROOM);
                    this.clientCommand.setClientMode(ClientMode.SUBMENU);
                    System.out.print("대화방번호를 입력 : ");
                    break;
                case "q":
                    this.clientCommand.getChatData().setCommand(ChatCommand.EXITPGM);
                    System.exit(0);
                    break;
            }
        } else if ( this.clientCommand.getClientMode() == ClientMode.SUBMENU ) {
            // 서브메뉴 모드인경우에 키보드에서 입력받은 str 문자열을 처리한다.
            if ( this.clientCommand.getChatData().getCommand() == ChatCommand.CHANGENAME ) {
                this.clientCommand.getChatData().setUserName(str);
                printMenu1();
                this.clientCommand.setClientMode(ClientMode.MENU);
                return;
            } else if ( this.clientCommand.getChatData().getCommand() == ChatCommand.CREATEROOM ) {
                this.clientCommand.getChatData().setRoomName(str);
                this.clientCommand.setClientMode(ClientMode.CHATTING);
            } else if ( this.clientCommand.getChatData().getCommand() == ChatCommand.ENTERROOM ) {
                this.clientCommand.getChatData().setRoom(Integer.parseInt(str));
                this.clientCommand.setClientMode(ClientMode.CHATTING);
            } else {
                // 서버로 완성된 명령어를 전송해야 한다.
                this.clientCommand.getChatData().setData(str);
            }
            String sendData = this.jsonProcesser.chatDataToJsonString(this.clientCommand.getChatData());
            this.write(sendData);
        } else if ( this.clientCommand.getClientMode() == ClientMode.CHATTING ) {
            this.clientCommand.getChatData().setCommand(ChatCommand.CHAT);
            this.clientCommand.getChatData().setData(str);
            String sendData = this.jsonProcesser.chatDataToJsonString(this.clientCommand.getChatData());
            this.write(sendData);
            // 대화방정보와 대화메세지를 서버로 전송해야 한다.
            // 대화중에 대화방을 빠져나가면 MENU 상황으로 가야 한다.
        }
    }

    public void exit() throws IOException {
        // {"command":"EXITROOM", "room":2}
        this.getClientCommand().getChatData().setCommand(ChatCommand.EXITROOM);
        String str = this.jsonProcesser.chatDataToJsonString(this.getClientCommand().getChatData());
        this.write(str);
        this.clientCommand.setClientMode(ClientMode.MENU);
        this.clientCommand.getChatData().setRoom(0);
        this.printMenu1();
    }

    public void printMenu1() {
        System.out.println("===========================================================================");
        System.out.printf("1.대화명변경(%s), 2.방목록보기, 3.방 생성, 4.방 입장, q.종료\n", this.clientCommand.getChatData().getUserName());
        System.out.println("===========================================================================");
    }

    private void printMenu2(String str) {
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("대화방목록");
        System.out.println(str);
        System.out.println("-----------------------------------------------------------------------");
    }

    private void printChatMenu() {
        System.out.println("-----------------------------------------------------------------------");
        System.out.printf("[%d:%s] (exit 입력시 퇴장합니다.)\n"
                , this.clientCommand.getChatData().getRoom()
                , this.clientCommand.getChatData().getRoomName()
        );
        System.out.println("-----------------------------------------------------------------------");
        System.out.printf("(%s): 입장 했습니다.\n", this.clientCommand.getChatData().getUserName());
    }

    /**
     *
     * @return
     */
    private Boolean checkExistUserName() {
        Boolean bResult = false;
        if( this.clientCommand.getChatData().getUserName() != null
            && !"".equals(this.clientCommand.getChatData().getUserName()) ) {
            bResult = true;
        } else {
            System.out.println("사용자의 대화명이 없습니다.");
        }
        return bResult;
    }

    public static void main(String[] args) {
        if(args.length <= 0) {
            System.out.println("java 옵션 ChatClientApp 접속할IP(10.10.10.10)");
            return;
        }
        System.out.println("Chat Client App start");
        ChatClientApp cca = new ChatClientApp();
        Thread th = new Thread(cca);
        Scanner scan = new Scanner(System.in);
        try {
            cca.connect(args[0], 58989);
            th.start();
            cca.printMenu1();
            while(true) {
                String str = scan.nextLine();
                // 클라이언트의 키보드 입력을 기다리는 블로킹 상태, 루프를 해서 계속 입력받도록, 스레드에서 실행
                cca.parseInput(str);
                if (cca.getClientCommand().getClientMode() == ClientMode.CHATTING) {
                    if ("exit".equals(str)) {
                        cca.exit();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}
