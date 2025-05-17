package glades.ly;

import glades.ly.proxy.ProxyFactory;

public class Consumer {

    public static void main(String[] args) {

        //需要一个service map的本地缓存
       HelloService helloService = ProxyFactory.getProxy(HelloService.class);
       String result = helloService.hello("liyuan");
       System.out.println(result);

    }
}
