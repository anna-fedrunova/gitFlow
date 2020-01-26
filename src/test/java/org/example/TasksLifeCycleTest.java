package org.example;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.example.pages.TodoMVCPage.*;

public class TasksLifeCycleTest extends BaseTestWithScreenshots {
    @BeforeClass
    public void openApp() {
        open("https://todomvc4tasj.herokuapp.com/#/");
        sleep(1000);
    }

    @Test
    public void smokeToDoMVC () {

        add("1", "2", "3", "4");
        assertTasksAre("1", "2", "3", "4");
        assertItemsLeft(4);

        toggle("2");
        assertItemsLeft(3);

        openActive();
        Assert.assertEquals(url(), "https://todomvc4tasj.herokuapp.com/#/active");
        assertVisibleTasksAre("1", "3", "4");


        edit("4", " edited");
        assertItemsLeft(3);

        toggle("4 edited");
        assertVisibleTasksAre("1", "3");
        assertItemsLeft(2);

        refresh();
        sleep(1000);

        toggleAll();
        tasks.filter(visible).shouldBe(empty);
        assertItemsLeft(0);

        openCompleted();
        assertVisibleTasksAre("1", "2", "3", "4 edited");

        clearCompleted();
        tasks.shouldBe(empty);
    }

}