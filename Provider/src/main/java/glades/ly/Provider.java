package glades.ly;

import glades.ly.protocol.MyHttpServer;
import glades.ly.registry.LocalRegistry;
import glades.ly.registry.MyURL;
import glades.ly.registry.RemoteRegistry;

public class Provider {

    public static void main(String[] args) {
        //Server component接受网络请求 可调配的做法

        //本地注册
        LocalRegistry.register(HelloService.class.getName(), HelloServiceImpl.class);

        //注册中心注册
        //服务注册
        MyURL url = new MyURL("localhost", 8080);
        RemoteRegistry.register(HelloService.class.getName(), url);
        

        //可以将初始化的过程封装到另一个类里
        MyHttpServer myHttpServer = new MyHttpServer();
        myHttpServer.start(url.getHostName(), url.getPortNum());
    }
}
