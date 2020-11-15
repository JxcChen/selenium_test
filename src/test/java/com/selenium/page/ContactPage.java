package com.selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactPage extends BasePage {

    public ContactPage(WebDriver driver) {
        super(driver);
    }

    //private By addMember = new By.ByXPath("//div[@class='ww_operationBar']/a[text()='添加成员']");
    // 添加部门定位
    private By addMember = new By.ByLinkText("添加成员");
    private By add = new By.ByLinkText("添加");
    private By addDepartment = new By.ByLinkText("添加部门");
    private By departmentName = new By.ByName("name");
    private By parentDepartment = new By.ByLinkText("选择所属部门");
    private By addDepartmentConfirm = new By.ByLinkText("确定");
    // 搜索成员、部门定位
    private By memberSearchInput = new By.ById("memberSearchInput");
    /**
     * 添加从成员方法
     * @param name 用户名
     * @param AcctId 账号
     * @param phone 手机号
     * @return 添加后的页面
     * @throws InterruptedException
     */
    public ContactPage addMemberSuccess(String name,String AcctId,String phone) throws InterruptedException {
        // 进入添加成员页面
        clickElement(addMember);
        AddMemberPage addMember = new AddMemberPage(driver);
        // 输入姓名
        addMember.sendName(name);
        // 输入别名
        // addMember.sendTagName("James");
        // 输入账号
        addMember.sendAcctId(AcctId);
        // 性别选中男
        // addMember.chooseSex("男");
        // 输入手机
        addMember.sendPhone(phone);
        // 输入座机
        // addMember.sendTelephone("25252525");
        // 输入邮箱
        // addMember.sendMail("252524612@qq.com");
        // 输入地址
        // addMember.sendAddress("深圳");
        // 输入职务
        // addMember.sendTitle("测试开发");
        // 点击添加并保存
        addMember.saveAndAdd();
        return this;
    }


    public ContactPage addDepartment(String departmentName,String parentDepartmentName){
        // 点击添加部门
        clickElement(add);
        clickElement(addDepartment);
        sendData(this.departmentName,departmentName);
        // 点击所属部门
        clickElement(parentDepartment);
        // 选择父部门
        clickElement(By.xpath("//div[@id='__dialog__MNDialog__']//a[text()='"+parentDepartmentName+"']"));
        clickElement(addDepartmentConfirm);
        return this;
    }

    public ContactPage searchDepartment(String departmentName) {
        sendData(memberSearchInput,departmentName);
        return this;
    }
}
