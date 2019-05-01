package com.wuhulala.spring.crawler;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 功能
 *
 * @author xueah20964 2019/5/1 Create 1.0  <br>
 * @version 1.0
 */
public class HtmlScreenshot {

    public static void main(String[] args) throws Exception {
        String url = "https://echarts.baidu.com/examples/editor.html?c=line-simple";
        Integer sleepTime = 5 * 1000;// 截图等待时间
        String targetPath = "D:/chromescreenshot/" + System.currentTimeMillis() + ".jpg";// 生成图片文件路径
        // 设置是否启用headless模式
        System.setProperty("java.awt.headless", "true");
        // 加载谷歌浏览器驱动
        String driverPath = "D:/tools/google/chromedriver.exe";
        ChromeOptions option = new ChromeOptions();
        option.addArguments("disable-infobars");
        option.addArguments("--no-sandbox");
        option.addArguments("--headless");
        option.addArguments("--disable-dev-shm-usage");
        option.addArguments("--window-size=1920,1080");
        option.setExperimentalOption("useAutomationExtension", false);
        option.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");

        System.setProperty("webdriver.chrome.driver", driverPath);
        WebDriver driver = new ChromeDriver(option);
        driver.manage().window().maximize();
        driver.get(url);
        Thread.sleep(sleepTime);
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        // 全屏
        FileUtils.copyFile(srcFile, new File(targetPath));
        System.out.println("===>截图完成...正在同步数据" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//        robot.delay(3000);
        driver.quit();
    }
}
