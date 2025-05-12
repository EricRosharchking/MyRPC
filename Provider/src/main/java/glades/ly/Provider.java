package glades.ly;

import glades.ly.protocol.MyHttpServer;
import glades.ly.registry.LocalRegistry;

public class Provider {

    public static void main(String[] args) {
        //Server component接受网络请求 可调配的做法

        LocalRegistry.register(HelloService.class.getName(), HelloServiceImpl.class);

        MyHttpServer myHttpServer = new MyHttpServer();
        myHttpServer.start("localhost", 8080);
    }
}
