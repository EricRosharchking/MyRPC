package glades.ly.loadbalance;

import java.util.List;
import java.util.Random;

import glades.ly.registry.MyURL;

public class MyLoadBalancer {

    public static MyURL random(List<MyURL> urls) {
        Random random = new Random();
        int i = random.nextInt(urls.size());
        return urls.get(i);
    }
}
