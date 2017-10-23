package org.springframework.samples.websocket.echo;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pc on 2016/8/12.
 */
public class common {

    private static Logger log = Logger.getLogger(common.class);

    public static Map<String, String> getUserMsg(String msg){
        Map<String, String> map = new HashMap<>();
        if(StringUtils.isNotBlank(msg)){
            String[] arr = msg.split("&");
            for (String str : arr){
                map.put(str.split("=")[0], str.split("=")[1]);
            }
            log.info(map);
        }
        return map;
    }
}
