package me.liycxc.steps;

import me.liycxc.Main;
import me.liycxc.driver.Driver;
import me.liycxc.driver.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

/**
 * This file is part of AutoXGP Remake project.
 * Copyright 2023 Liycxc
 * All Rights Reserved.
 *
 * @author Liycxc
 * @date: 2023-07-09
 * @time: 12:56
 */
public class XboxGamePass {
    public static boolean xboxGamePass() {
        try {
            // 获取浏览器
            WebDriver driver = Driver.getDriver();

            // 获取WebDriverWait
            WebDriverWait driverWait = Waiter.getWaiter(driver);

            driver.navigate().to(Main.XGP_URL);

            Thread.sleep(1500);

            WebElement element;

            driver.findElement(By.xpath("//button[@aria-label='加入 PC Game Pass。每月 HK$29.00']")).click();

            WebElement createProfileButton = driver.findElement(By.xpath("//button[@id='inline-continue-control']"));
            while (!createProfileButton.isEnabled()) {
                driver.findElement(By.xpath("//button[@id='create-account-gamertag-suggestion-1']")).click();
                Thread.sleep(1000);
            }
            createProfileButton.click();

            System.out.println("档案创建成功");

            driver.findElement(By.xpath("//button[@aria-label='加入 PC Game Pass。每月 HK$29.00']")).click();

            // 等待网页加载
            Thread.sleep(2500);

            // 转入iframe
            driver.switchTo().frame("purchase-sdk-hosted-iframe");

            // 查找下一步
            element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(@class, 'optionContainer')]")));

            // 点击下一步
            element.click();

            Thread.sleep(1000);

            // 查找eWallet 付款方式
            element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='displayId_ewallet']")));

            // 点击eWallet 付款方式
            element.click();

            Thread.sleep(500);

            // 查找Alipay
            element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='displayId_ewallet_alipay_billing_agreement']")));

            // 点击Alipay
            element.click();

            Thread.sleep(500);

            // 进入 新增您的支付寶帳戶 页面
            // 查找下一步
            element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='pidlddc-button-saveNextButton']")));

            // 点击下一步
            element.click();

            Thread.sleep(500);

            // 进入 以您的支付寶電子錢包掃描 QR 代碼。 页面
            // 查找 登录支付宝 超链接
            element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='登入支付寶']")));

            // 跳转支付宝
            element.click();

            System.out.println("开始登录支付宝");
            Set<String> windowHandles = driver.getWindowHandles();
            driver.switchTo().window(windowHandles.toArray()[1].toString());

            driver.switchTo().frame(driver.findElement(By.xpath("//iframe")));
            driver.findElement(By.xpath("//a[@title='扫码登录']")).click();
            driver.findElement(By.xpath("//input[@seed='authcenter-input-account']")).sendKeys(Main.ALIPAY_USER);
            driver.findElement(By.xpath("//input[@id='password_rsainput']")).sendKeys(Main.ALIPAY_PWD);

            driver.findElement(By.xpath("//input[@type='submit']")).sendKeys(Keys.ENTER);
            driver.findElement(By.xpath("//input[@id='payPassword_rsainput']")).sendKeys(Main.ALIPAY_KEY);
            driver.findElement(By.xpath("//button[@type='submit']")).click();
            driver.findElement(By.xpath("//h2[text()='準備好了嗎？']"));
            System.out.println("支付宝登录成功");
            driver.switchTo().parentFrame();

            driver.switchTo().window(windowHandles.toArray()[0].toString());
            driver.switchTo().parentFrame();
            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title=\"Purchase Frame\"]")));

            // 点击至到绑定成功
            WebDriverWait driverWait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
            do {
                try {
                    // 查找 繼續
                    element = driverWait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='pidlddc-button-alipayContinueButton']")));
                    element.click();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                Thread.sleep(1000);
            } while (!driver.getPageSource().contains("我們需要您的設定檔地址"));

            // 进入地址输入页面
            // 查找城市
            WebElement city = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='city']")));

            Thread.sleep(200);

            // 输入城市
            city.sendKeys("Shanghai");

            Thread.sleep(500);

            // 查找 地址1
            WebElement address = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='address_line1']")));

            Thread.sleep(200);

            // 输入地址
            address.sendKeys("Xuhui District");

            Thread.sleep(500);

            // 查找存储
            element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@value='儲存']")));

            // 点击存储
            element.click();

            System.out.println("支付宝付款方式已添加");

            // 等待页面加载完成
            Thread.sleep(1500);

            // 寻找 订阅
            element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='訂閱']")));

            // 点击 订阅
            element.click();

            // 切出iframe
            driver.switchTo().defaultContent();
            driver.switchTo().parentFrame();

            driver.findElement(By.xpath("//p[contains(@class, 'ThankYouPage')]"));

            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }
}
