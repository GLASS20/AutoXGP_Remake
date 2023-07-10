package me.liycxc.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * This file is part of AutoXGP Remake project.
 * Copyright 2023 Liycxc
 * All Rights Reserved.
 *
 * @author Liycxc
 * @date: 2023-07-09
 * @time: 15:40
 */
public class Waiter {
    public static WebDriverWait getWaiter(WebDriver driver) {
        return  new WebDriverWait(driver, Duration.ofSeconds(20));
    }
}
