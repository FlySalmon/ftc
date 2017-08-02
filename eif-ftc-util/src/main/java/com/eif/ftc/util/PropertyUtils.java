package com.eif.ftc.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by bohan on 2/19/16.
 */
public class PropertyUtils {

    public static Map<String,String> getPropertyMap(String fileName) {
        Map<String,String> propertyMap = new HashMap<String,String>();
        Properties property = new Properties();
        InputStream inputFile = null;

        try {
            inputFile = PropertyUtils.class.getClassLoader().getResourceAsStream(fileName);
            // 装载配置文件
            property.load(inputFile);
            for (Object name : property.keySet()) {
                // 从配置文件中获取属性存入map中
                String data = property.getProperty(name.toString());
                propertyMap.put(name.toString(), data);
            }

        }
        catch (Exception e) {

        }
        finally {
            // 关闭输入流
            if (inputFile != null) {
                try {
                    inputFile.close();
                }
                catch (Exception e) {

                }

            }
        }

        return propertyMap;
    }
}
