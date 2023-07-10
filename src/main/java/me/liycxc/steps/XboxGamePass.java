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
import java.util.Random;

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

            while (true) {
                try {
                    // 获取头像以登录Xbox
                    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='mectrl_headerPicture']")));

                    // 点击头像
                    element.click();

                    break;
                } catch (Exception exception) {
                    exception.printStackTrace();
                    driver.navigate().refresh();
                    System.out.println("Try again...");
                }

                if (!"https://www.xbox.com/zh-HK/xbox-game-pass/pc-game-pass?xr=shellnav".equalsIgnoreCase(driver.getCurrentUrl())) {
                    driver.get("https://www.xbox.com/zh-HK/xbox-game-pass/pc-game-pass?xr=shellnav");
                }
            }

            int index = 1;
            do {

                Thread.sleep(500);

                try {
                    // 查找名字
                    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='create-account-gamertag-suggestion-" + index + "']")));

                    // 点击名字
                    element.click();

                    // 等待名字检测完成
                    Thread.sleep(2500);

                    // 名字合法
                    if (driver.getPageSource().contains("<label for=\"create-account-gamertag-input\" dir=\"ltr\" id=\"create-account-gamertag-detail\" aria-live=\"assertive\">This will be your public name in the Xbox online community.</label>")) {
                        break;
                    } else {
                        System.out.println("[警告] Xbox 档案名不合法！");
                        if (index > 3) {
                            throw new Exception("Xbox档案名没一个合法");
                        }
                        index++;
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            } while (true);

            Thread.sleep(500);

            // 查找LET‘S GO
            element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='inline-continue-control']")));

            // 点击LET'S GO
            element.click();

            // 等待网页加载
            Thread.sleep(2500);

            // 获取JavaScript跳转链接
            int counter = 0;
            while (true) {
                try {
                    element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='JavaScript:void(0);' and @role='button' and @class='c-call-to-action c-glyph xbstorebuy xbstoreDynAdd storeDynAdded']")));
                    break;
                } catch (Exception exception) {
                    driver.navigate().refresh();
                    counter++;
                }
                if (counter > 10) {
                    throw new Exception("等待链接过久");
                }
                Thread.sleep(500);
            }

            // 点击跳转链接
            element.click();

            Thread.sleep(500);

            // 查找下一步
            element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@aria-label='下一步']")));

            // 点击下一步
            element.click();

            // 等待网页加载
            Thread.sleep(2500);

            // 转入iframe
            while (true) {
                try {
                    driver.switchTo().frame("purchase-sdk-hosted-iframe");
                    break;
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }

            Thread.sleep(1000);

            // 查找下一步
            element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='下一步']")));

            Thread.sleep(500);

            // 点击下一步
            element.click();

            Thread.sleep(500);

            // 查找eWallet 付款方式
            element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='displayId_ewallet']")));

            // 点击eWallet 付款方式
            element.click();

            Thread.sleep(500);

            // 查找Alipay
            element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@aria-label='Alipay']")));

            // 点击Alipay
            element.click();

            Thread.sleep(500);

            // 进入 新增您的支付寶帳戶 页面
            // 查找下一步
            element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@aria-label='下一步']")));

            // 点击下一步
            element.click();

            Thread.sleep(500);

            // 进入 以您的支付寶電子錢包掃描 QR 代碼。 页面
            // 查找 登录支付宝 超链接
            element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='登入支付寶']")));

            // 跳转支付宝
            element.click();

            // 等待新建标签页
            Thread.sleep(1000);

            // 切换标签页
            driver.switchTo().defaultContent();
            driver.switchTo().window(driver.getWindowHandles().stream().toList().get(1));

            // 查找 支付宝支付密码输入框
            element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='password' and @id='payPassword_rsainput']")));

            // 模拟输入
            for (char c : Main.ALIPAY_KEY.toCharArray()) {
                int delay = new Random().nextInt(200) + 50;
                // 模拟输入延迟
                Thread.sleep(delay);

                // 输入字符
                element.sendKeys(String.valueOf(c));
            }

            // 输入回车
            element.sendKeys(Keys.ENTER);

            // 等待网页完成
            Thread.sleep(2500);

            // 切换标签页
            driver.switchTo().window(driver.getWindowHandles().stream().toList().get(0));

            // 切换至iframe
            driver.switchTo().frame("purchase-sdk-hosted-iframe");

            // 点击至到绑定成功
            WebDriverWait driverWait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
            do {
                try {
                    // 查找 繼續
                    element = driverWait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@aria-label='繼續']")));
                    element.click();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                try {
                    // 查找 繼續
                    element = driverWait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='下一步']")));
                    element.click();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                Thread.sleep(1000);
            } while (!driver.getPageSource().contains("我們需要您的設定檔地址"));

            // 进入地址输入页面
            // 查找城市
            WebElement city = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='city']")));

            // 清空城市
            city.clear();

            Thread.sleep(200);

            // 输入城市
            city.sendKeys("Shanghai");

            Thread.sleep(500);

            // 查找 地址1
            WebElement address = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='address_line1']")));

            // 清空地址
            address.clear();

            Thread.sleep(200);

            // 输入地址
            address.sendKeys("Xuhui District");

            Thread.sleep(500);

            // 查找存储
            element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@aria-label='儲存']")));

            // 点击存储
            element.click();

            // 等待页面加载完成
            Thread.sleep(1500);

            // 寻找 订阅
            element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='訂閱']")));

            // 点击 订阅
            element.click();

            // 切出iframe
            driver.switchTo().defaultContent();

            // 等待页面刷新完成
            Thread.sleep(3000);

            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }
}
