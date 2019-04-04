package huertix.uoc.sockets;

import java.util.HashMap;
import java.util.Map;

public enum UserOptionEnum {
    TCP_CLIENT(1),
    UDP_CLIENT(2),
    UDP_SERVER(3);

    private int value;
    private static Map map = new HashMap<>();

    private UserOptionEnum(int value) {
        this.value = value;
    }

    static {
        for (UserOptionEnum userOption : UserOptionEnum.values()) {
            map.put(userOption.value, userOption);
        }
    }

    public static UserOptionEnum valueOf(int userOption) {
        return (UserOptionEnum) map.get(userOption);
    }

    public int getValue() {
        return value;
    }
}