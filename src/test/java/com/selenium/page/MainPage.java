package com.selenium.page;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.selenium.utils.GetCookie;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainPage extends BasePage{

    public MainPage() throws IOException, InterruptedException {
        // 先进行初始化
        this.beforeTest();
    }


    public void beforeTest() throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new  ChromeDriver();
        File file = new File("cookies.yaml");
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
        driver.get("https://work.weixin.qq.com/wework_admin/frame");
        // 判断本地是否存有cookie
        if(!file.exists()){
            GetCookie.getWeiXinCookie(driver);
        }
        // 获取本地cookie
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        TypeReference<List<HashMap<String,Object>>> typeReference = new TypeReference<List<HashMap<String,Object>>>(){};
        List<HashMap<String,Object>> cookies =mapper.readValue(file, typeReference);
        // 设置到网页中
        cookies.forEach(cookieMap ->{
            driver.manage().addCookie(new Cookie(cookieMap.get("name").toString(),cookieMap.get("value").toString()));
        });
        // 刷新页面
        driver.navigate().refresh();
        driver.manage().window().maximize();
    }


    public ContactPage getContactPage() throws IOException, InterruptedException {
        new IndexPage(driver).clickContactBtn();
        //传递selenium的driver给另外一个PO
        //po原则4 跳转或者进入新页面使用返回新的po来模拟
        return new ContactPage(driver);
    }


}
