package org.example;

import com.codeborne.selenide.Screenshots;
import com.google.common.io.Files;
import io.qameta.allure.Attachment;
import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.IOException;

public class BaseTestWithScreenshots {
    @AfterMethod
    public void takeScreenshot() throws IOException {
        screenshot();
    }


    @Attachment(type = "image/png")
    public byte[] screenshot() throws IOException {
        File screenshot = Screenshots.takeScreenShotAsFile();
        return screenshot == null ? null : Files.toByteArray(screenshot);
    }
}