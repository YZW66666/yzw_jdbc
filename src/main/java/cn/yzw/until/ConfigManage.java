package cn.yzw.until;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
public class ConfigManage {
    //01.静态变量(单例)
    private static ConfigManage configManage;
    //04.创建properties对象，专门解析mysql.properties文件
    private static Properties properties;
    //02.私有化构造
    static {
        String path = "jdbc.properties";
        //实例化properties对象
        properties = new Properties();
        InputStream stream = ConfigManage.class.getClassLoader().getResourceAsStream(path);
        //加载我们的properties文件
        try {
            properties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭流
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
//03.创建对外的接口
    public static synchronized ConfigManage getInstence() {
        return configManage;
    }


     //通过key获取value

    public static String getValue(String key) {
        String property = properties.getProperty(key);
        return property;
    }
}
