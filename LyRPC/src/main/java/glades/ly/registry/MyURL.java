package glades.ly.registry;

import java.io.Serializable;

public class MyURL implements Serializable{

    private String hostName;
    private Integer portNum;
    public MyURL(String hostName, Integer portNum) {
        this.hostName = hostName;
        this.portNum = portNum;
    }
    public String getHostName() {
        return hostName;
    }
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }
    public Integer getPortNum() {
        return portNum;
    }
    public void setPortNum(Integer portNum) {
        this.portNum = portNum;
    }

    
}
