package org.example.pages;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

public  class TodoMVCPage {
    public static ElementsCollection tasks = $$("#todo-list li");

    @Step
    public static void clearCompleted() { $("#clear-completed").click(); }

    @Step
    public static  void add(String... taskTexts) {
        for(String text: taskTexts) {
            $("#new-todo").shouldBe(enabled).setValue(text);
            $("#new-todo").pressEnter();
        }
    }

    @Step
    public static  void edit (String taskText, String editedText) {
        tasks.find(exactText(taskText)).find("label").doubleClick();
        tasks.find(cssClass("editing")).find(".edit").shouldBe(enabled).sendKeys(editedText, Keys.ENTER);
    }

    @Step
    public static  void abortEdit (String taskText) {
        tasks.find(exactText(taskText)).doubleClick();
        tasks.find(cssClass("editing")).find(".edit").shouldBe(enabled).sendKeys(Keys.ESCAPE);
    }

    @Step
    public static void editToEmptyString (String taskText) {
        tasks.find(exactText(taskText)).doubleClick();
        tasks.find(cssClass("editing")).find(".edit").shouldBe(enabled).sendKeys(Keys.COMMAND, "a");
    }
    @Step
    public static  void delete(String taskText) { tasks.find(exactText(taskText)).hover().find(".destroy").click(); }

    @Step
    public static  void assertTasksAre(String... taskTexts) { tasks.shouldHave(exactTexts(taskTexts)); }

    @Step
    public static  void assertVisibleTasksAre(String ...taskTexts) { tasks.filter(visible).shouldHave(exactTexts(taskTexts)); }

    @Step
    public static  void toggle(String taskText) { tasks.find(exactText(taskText)).find(".toggle").click(); }

    @Step
    public static  void toggleAll() { $("#toggle-all").click(); }

    @Step
    public static  void openActive() { $$("#filters li").find(exactText("Active")).click(); }

    @Step
    public static  void openCompleted() { $$("#filters li").find(exactText("Completed")).click(); }

    public static  int getCounter() {
        return Integer.parseInt($("#todo-count strong").getText());
    }

    @Step
    public static  void assertItemsLeft(int tasksNumber) {
        Assert.assertEquals(getCounter(), tasksNumber);
    }


    public static  void given(String... taskTexts) {
        StringBuilder codeString = new StringBuilder();
        codeString.append("localStorage.setItem(\"todos-troopjs\", \"[");
        for (String text : taskTexts) {
            codeString.append("{\\\"completed\\\":false, \\\"title\\\":\\\"")
                    .append(text)
                    .append("\\\"},");
        }
        codeString.deleteCharAt(codeString.length()-1);
        codeString.append("]\")");
        String jsCode = codeString.toString();
        executeJavaScript(jsCode);
        refresh();
        //        System.out.println(jsCode);
//        //localStorage.setItem("todos-troopjs", "[{\"completed\":false, \"title\":\"new task\"}]")
    }
}
