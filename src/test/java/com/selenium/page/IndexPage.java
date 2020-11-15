package com.selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IndexPage extends BasePage {

    public IndexPage(WebDriver driver) {
        super(driver);
    }
    // 添加成员
    private By addMemberButton = new By.ByXPath("//a[@node-type='addmember']");
    private By clickContact = new By.ById("menu_contacts");
    public void clickAddMemberButton(){
        clickElement(addMemberButton);
    }
    public void clickContactBtn(){
        clickElement(clickContact);
    }
}
