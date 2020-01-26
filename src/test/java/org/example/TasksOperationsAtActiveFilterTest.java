package org.example;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static org.example.pages.TodoMVCPage.*;

public class TasksOperationsAtActiveFilterTest extends BaseTestWithScreenshots {
    @BeforeClass
    public void switchToActive() {
        open("https://todomvc4tasj.herokuapp.com/#/active");
        sleep(1000);
    }

    @Test
    public void addTest() {
        clearBrowserLocalStorage();
        refresh();
        add("1", "2", "3", "4");
        assertVisibleTasksAre("1", "2", "3", "4");
        assertItemsLeft(4);
    }

    @Test
    public void deleteTest () {
        given("1", "2", "3");
        delete("2");
        assertVisibleTasksAre("1", "3");
        assertItemsLeft(2);
    }

    @Test
    public void abortEditTest () {
        given("1", "2");
        abortEdit("2");
        assertVisibleTasksAre("1", "2");
        assertItemsLeft(2);
    }

    @Test
    public void toggleTest () {
        given("1", "2", "3");
        toggle("2");
        assertVisibleTasksAre("1", "3");
        assertItemsLeft(2);
    }

}