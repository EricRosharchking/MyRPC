package glades.ly.registry;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoteRegistry {

    //接受心跳 监听 保持同步
    private static Map<String, List<MyURL>> map = new HashMap<>();

    public static void register(String interfaceName, MyURL myURL) {
        List<MyURL> list = map.getOrDefault(interfaceName, new ArrayList<>());
        list.add(myURL);

        map.put(interfaceName, list);
        saveFile();
    }

    public static List<MyURL> get(String interfaceName) {
        map = readFile();
        return map.getOrDefault(interfaceName, (List<MyURL>) Collections.EMPTY_LIST);
    }

    private static void saveFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("/temp.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, List<MyURL>> readFile() {
        try {
            FileInputStream fis = new FileInputStream("/temp.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            return (Map<String, List<MyURL>>) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ce) {
            ce.printStackTrace();
        }
        return null;
    }
}
