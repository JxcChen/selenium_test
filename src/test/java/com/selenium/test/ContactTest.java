package com.selenium.test;

import com.selenium.page.ContactPage;
import com.selenium.page.MainPage;
import com.selenium.utils.Constant;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

public class ContactTest extends BaseTest {

    public static MainPage mainPage ;
    public static ContactPage contactPage;

    // 清楚测试添加的数据
    @BeforeAll
    static void clearTestData() throws IOException, InterruptedException {
        // 删除测试数据 部门+人员
        mainPage = new MainPage();
        contactPage = mainPage.getContactPage();
        contactPage.clearAll("测试部");
        mainPage.teardown();
    }


    @BeforeEach
    public void init() throws Exception {
        mainPage = new MainPage();
        contactPage = mainPage.getContactPage();
    }
    // 添加成员
    @ParameterizedTest
    @MethodSource("MemberData")
    void successAddMemberTest(String name,String acctId,String phone) throws IOException, InterruptedException {

        ContactPage successAddMember = contactPage.addMemberSuccess(name, acctId, phone);
        // 断言查看是否添加成功
        Assertions.assertEquals("保存成功",successAddMember.getAddMemberTips());

    }

    // 添加部门
    @Test
    void addDepartmentTest() throws IOException, InterruptedException {
        ContactPage successAddDepartment = contactPage.addDepartment("测试部", "研发部");
    }
    // 部门搜索
    @Test
    void searchDepartmentTest() throws IOException, InterruptedException {
        ContactPage successSearch = contactPage.searchDepartment("测试部");
        Assertions.assertTrue(successSearch.getSearchResult().contains("测试部"));
    }
    // 部门改名
    @Test
    void changeDepartmentTest() throws InterruptedException {
        Assertions.assertTrue(contactPage.changeDepartment("测试部","研发部门").getSearchResult().contains("研发部"));
    }

    // 使用文件批量导入成员
    @Test
    void batchAddMemberTest() throws InterruptedException {
        ContactPage successAddPage = ContactTest.contactPage.batchAddMember(Constant.MEMBER_DATA_FILE);
        Assertions.assertTrue(successAddPage.getFileImporterBody().contains("导入成功"));
    }


    // 添加成员使用的数据
    public static Stream<Arguments> MemberData() {
        return Stream.of(
                Arguments.arguments("周杰伦", "JJ_10","12121221211"),
                Arguments.arguments("林俊杰", "JJ_11","12121221212"),
                Arguments.arguments("王力宏", "JJ_12","12121221213"));
    }

   @AfterEach
    public void teardown(){
        mainPage.teardown();
    }
}
