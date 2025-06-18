package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {
    public static Properties properties;
    static {
        String filePath = "configuration.properties";
        try {
            FileInputStream fis = new FileInputStream(filePath);
            properties = new Properties(); //objeyi oluşturduk ve atamasını gerçekleştirdik
            properties.load(fis);//fis'in okuduğu bilgileri properties'e yükler
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //properties dosyasındaki key değerini alıp test methodumda kullanabilmek için bir method oluşturmalıyız
    public static String getProperty(String Key){//String bir değer döndürmesi için String bir parametre atarız
        /*
            Test method'undan gönderdiğimiz string key değerini alıp ConfigReader class'ından
        getProperty() methodunu kullanarak bu key'e ait value'yu bize getirir.
         */


        return properties.getProperty(Key);
    }
}
