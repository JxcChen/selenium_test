package com.selenium.page;

import com.selenium.utils.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    public WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver=driver;
    }
    public BasePage(){}
    /**
     * 等待元素可见
     * @param by 元素定位表达是
     * @return 所定位元素
     */
    public WebElement waitElementVisible(By by){
        WebDriverWait webDriverWait = new WebDriverWait(driver, Constant.WAIT_TIMEOUT);
        return webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    /**
     * 等待元素可被点击
     * @param by 元素定位表达是
     * @return
     */
    public WebElement waitElementClickable(By by){
        WebDriverWait webDriverWait = new WebDriverWait(driver, Constant.WAIT_TIMEOUT);
        return webDriverWait.until(ExpectedConditions.elementToBeClickable(by));
    }


    /**
     * 输入数据
     * @param by 元素by对象
     * @param inputData 输入的数据
     */
    public void sendData(By by,String inputData){
        waitElementVisible(by).sendKeys(inputData);
    }

    /**
     * 输入按键操作
     */
    public void typeKey(By by, Keys keys){
        waitElementVisible(by).sendKeys(keys);
    }

    /**
     * 点击元素的
     * @param by
     */
    public void clickElement(By by){
        waitElementClickable(by).click();
    }

    /**
     * 获取元素文本值
     * @param by
     */
    public String getElementText(By by){
        //TODO - 统一日志处理
        String text = waitElementVisible(by).getText();
        return text;
    }

    /**
     * 获取元素属性值
     * @param by 元素定位表达是
     * @param attributeName  元素的属性名
     */
    public String getElementAttribute(By by, String attributeName){
        String value = waitElementVisible(by).getAttribute(attributeName);
        return value;
    }

    /**
     * 元素是否可见
     * @param by
     * @return
     */
    public Boolean isElementDisplayed(By by){
        return waitElementVisible(by).isDisplayed();
    }


}
