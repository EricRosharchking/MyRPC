package glades.ly.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

import glades.ly.common.Invocation;
import glades.ly.loadbalance.MyLoadBalancer;
import glades.ly.protocol.MyHttpClient;
import glades.ly.registry.MyURL;
import glades.ly.registry.RemoteRegistry;

public class ProxyFactory {

    public static <T> T getProxy(Class<?> interfaceClass) {
        Object proxyInstance = Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] params) throws Throwable {
                String mock = System.getProperty("mock");
                if (mock != null && mock.startsWith("return:")) {
                    return mock.replace("return:", "hello") + params[0];
                }
                Invocation invocation = new Invocation(interfaceClass.getName(), method.getName(), method.getParameterTypes(), params);

                MyHttpClient myHttpClient = new MyHttpClient();

                //服务发现 需要从注册中心拿到Map或者List，直接启动无法拿到另一个provider进程的map和list
                List<MyURL> list = RemoteRegistry.get(interfaceClass.getName());

                //负载均衡
                MyURL url = MyLoadBalancer.random(list);
                //服务调用
                String result = null;
                try {
                    myHttpClient.send(url.getHostName(), url.getPortNum(), invocation);
                } catch (Exception e) {
                    // error-callback 服务容错
                    // MyHelloServiceErrorCallback
                    // 容错限度以内重新尝试调用另一个服务端
                    // 否则报错
                    return "报错了";
                }
                return result;
            }
        });
        return (T) proxyInstance;
    }
}
