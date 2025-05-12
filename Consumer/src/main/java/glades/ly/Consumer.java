package glades.ly;

import glades.ly.common.Invocation;

public class Consumer {

    public static void main(String[] args) {
//        HelloService helloService = ?;
//        String result = helloService.hello("liyuan");
//        System.out.println(result);

        Invocation invocation = new Invocation(HelloService.class.getName(), "sayHello", new Class<?>[]{String.class}, new Object[]{"liyuan"});
    }
}
