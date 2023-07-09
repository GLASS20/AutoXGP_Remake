package me.liycxc.utils;

import org.openqa.selenium.firefox.FirefoxDriver;
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
    public static WebDriverWait getWaiter(FirefoxDriver driver) {
        return  new WebDriverWait(driver, Duration.ofSeconds(60),  Duration.ofMillis(500));
    }
}
