package com.selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class ContactPage extends BasePage {

    public ContactPage(WebDriver driver) {
        super(driver);
    }

    //private By addMember = new By.ByXPath("//div[@class='ww_operationBar']/a[text()='添加成员']");
    /*
     清楚数据定位
     */
    // 人员全选框
    private By allMemberCheck = new By.ByCssSelector(".member_colRight_memberTable_th_Checkbox .ww_checkbox");
    private By delete = new By.ByLinkText("删除");
    private By deleteMemberConfirm = new By.ByLinkText("确认");
    private By deleteDepartmentConfirm = new By.ByXPath("//a[text()='确定']");
    private By afterDeleteMemberMesg = new By.ByLinkText(" 当前部门无任何成员 ");
    // 部门后的小控件按钮
    private By departmentSetting = new By.ByXPath("//a[text()='测试部']/span");
    /*
     添加部门定位
     */
    private By addMember = new By.ByLinkText("添加成员");
    private By add = new By.ByLinkText("添加");
    private By addDepartment = new By.ByLinkText("添加部门");
    private By departmentName = new By.ByName("name");
    private By parentDepartment = new By.ByLinkText("选择所属部门");
    private By addDepartmentConfirm = new By.ByLinkText("确定");

    /*
     搜索成员、部门定位
     */
    private By memberSearchInput = new By.ById("memberSearchInput");
    private By searchResult = new By.ByCssSelector(".member_colLeft_bottom");

    /*
     更新部门定位符
     */
    private By changeDepartmentName = new By.ByLinkText("修改名称");
    // 修改部门名称输入框
    private By departmentNameInput = new By.ByCssSelector("[name='name']");
    private By save = new By.ByLinkText("保存");

    /*
     批量导入成员坐标
     */
    private By batchAddMemberBtn = new By.ByLinkText("批量导入/导出");
    private By fileImportBtn = new By.ByLinkText("文件导入");
    private By uploadInputMask = new By.ByCssSelector(".ww_fileImporter_fileContainer_uploadInputMask");
    // 导入按钮
    private By importBtn = new By.ByCssSelector(".ww_fileImporter_submit");
    // 导入成功后页面显示
    private By fileImporterBody = new By.ByCssSelector(".ww_fileImporter_body");
    // 添加成功提示
    private By addMemberTips = new By.ByXPath("//div[contains(@class,'ww_tip')]");

    /**
     * 清除测试数据
     * @param departmentName 部门名称
     */
    public void clearAll(String departmentName) throws InterruptedException {
        Actions actions = new Actions(driver);
        // 等待搜索页面加载  点击一下因网页加载速度可能有异常 这里确保正常选中部门 需要点击两下
        clickElement(new By.ByXPath("//a[text()='"+departmentName+"']"));
        clickElement(new By.ByXPath("//a[text()='"+departmentName+"']"));
        waitElementVisible(new By.ByXPath("//span[@id='party_name'][text()='测试部']"));
        // 1、对部门成员进行删除
        clickElement(allMemberCheck);
        clickElement(delete);
        actions.sendKeys(Keys.ENTER).perform();
       // 2、将部门进行删除
        // 先等待人员删除后的页面加载完成
        waitElementVisible(afterDeleteMemberMesg);
        clickElement(departmentSetting);
        clickElement(delete);
        Thread.sleep(1000);
        actions.sendKeys(Keys.ENTER).perform();
    }

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

    public String getAddMemberTips(){
        return getElementText(addMemberTips);
    }


    /**
     * 添加部门
     * @param departmentName 新增部门名称
     * @param parentDepartmentName 父级部门名称
     * @return 添加部门后的页面
     */
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

    /**
     * 搜索部门
     * @param departmentName 部门名称
     * @return 搜索后的页面
     */
    public ContactPage searchDepartment(String departmentName) {
        sendData(memberSearchInput,departmentName);
        return this;
    }

    /**
     * 获取搜索后的搜索面板 用于搜索断言
     * @return
     */
    public String getSearchResult(){
        clickElement(memberSearchInput);
        return getElementText(searchResult);
    }

    /**
     * 修改部门名称
     * @param oleDepartmentName 原部门名
     * @param newDepartmentName 新部门名
     * @return 修改后的页面
     * @throws InterruptedException
     */
    public ContactPage changeDepartment(String oleDepartmentName,String newDepartmentName) throws InterruptedException {
        // 点击修改部门入口 点击一次可能出现因网页加载出现异常
        clickElement(new By.ByXPath("//a[text()='"+oleDepartmentName+"']"));
        clickElement(new By.ByXPath("//a[text()='"+oleDepartmentName+"']"));
        // 点击修改名称
        clickElement(changeDepartmentName);
        // 输入新部门名称
        sendData(departmentNameInput,newDepartmentName);
        // 点击保存
        clickElement(save);
        return this;
    }

    /**
     * 用文件批量导入成员
     * @param filePath 文件路径
     * @return
     * @throws InterruptedException
     */
    public ContactPage batchAddMember(String filePath) throws InterruptedException {
        // 点击批量导入或到处
        clickElement(batchAddMemberBtn);
        // 点击文件导入
        clickElement(fileImportBtn);
        // 上传文件
        sendData(uploadInputMask,filePath);
        // 点击上传
        clickElement(importBtn);
        // 等待页面
        Thread.sleep(5000);
        return this;
    }

    /**
     * 获取批量导入成功后的页面信息
     * @return
     */
    public String getFileImporterBody(){
        return getElementText(fileImporterBody);
    }
}
