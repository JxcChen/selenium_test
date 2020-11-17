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

public class MainPage extends BasePage{

    public MainPage() throws IOException, InterruptedException {
        // 先进行初始化
        this.beforeTest();
    }

    /**
     * 初始化驱动 并进行扫码登录企业微信
     * @throws IOException
     * @throws InterruptedException
     */
    public void beforeTest() throws IOException, InterruptedException {
        // 初始化驱动
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver2.exe");
        driver = new  ChromeDriver();
        File file = new File("cookies.yaml");
        driver.get("https://work.weixin.qq.com/wework_admin/frame");
        driver.manage().window().maximize();

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

    }

    /**
     * 获取通讯录页面
     * @return 通讯录页面
     * @throws IOException
     * @throws InterruptedException
     */
    public ContactPage getContactPage() throws IOException, InterruptedException {
        new IndexPage(driver).clickContactBtn();
        return new ContactPage(driver);
    }


    public void teardown() {
        driver.quit();
    }


}
