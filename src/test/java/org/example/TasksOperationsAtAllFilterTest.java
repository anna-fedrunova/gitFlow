package org.example;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static org.example.pages.TodoMVCPage.*;

public class TasksOperationsAtAllFilterTest extends BaseTestWithScreenshots {
    @BeforeClass
    public void openApp() {
        open("https://todomvc4tasj.herokuapp.com/#/");
        sleep(1000);
    }

    @Test
    public void editTest() {
        given("1", "2", "3");
        edit("2", " edited");
        assertTasksAre("1", "2 edited", "3");
        assertItemsLeft(3);
    }

    @Test
    public void deleteTest () {
        given("1", "2", "3");
        delete("2");
        assertTasksAre("1", "3");
        assertItemsLeft(2);
    }


    @Test
    public void abortEditTest () {
        given("1", "2", "3");
        abortEdit("3");
        assertTasksAre("1", "2", "3");
        assertItemsLeft(3);
    }


    @Test
    public void toggleAllTest () {
        given("1", "2", "3");
        toggleAll();
        assertItemsLeft(0);
    }
}
