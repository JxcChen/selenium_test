package com.selenium.test;

import com.selenium.page.ContactPage;
import com.selenium.page.MainPage;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ContactTest extends BaseTest {

    @Test
    void successAddMemberTest() throws IOException, InterruptedException {
        // 获取添加成员页面对象
        MainPage mainPage = new MainPage();
        ContactPage contactPage = mainPage.getContactPage();
        ContactPage successAddMember = contactPage.addMemberSuccess("小明", "JJ_02", "12132132121");

    }

    //    todo: 部门新建 部门搜索 部门的更新 部门内添加成员 导入成员
    @Test
    void addDepartmentTest() throws IOException, InterruptedException {
        MainPage mainPage = new MainPage();
        ContactPage contactPage = mainPage.getContactPage();
        ContactPage successAddDepartment = contactPage.addDepartment("研发部", "CHenTester");
    }

    @Test
    void searchDepartmentTest() throws IOException, InterruptedException {
        MainPage mainPage = new MainPage();
        ContactPage contactPage = mainPage.getContactPage();
        ContactPage successAddDepartment = contactPage.searchDepartment("研发部");
    }

}
