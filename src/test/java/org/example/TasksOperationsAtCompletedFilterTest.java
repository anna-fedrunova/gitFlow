package org.example;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static org.example.pages.TodoMVCPage.*;

public class TasksOperationsAtCompletedFilterTest {
    @Test
    public void addTest() {
        add("1", "2");
        tasks.findBy(visible).shouldBe(empty);
        assertItemsLeft(2);
        openActive();
        assertVisibleTasksAre("1", "2");
    }

    @Test
    public void addTest() {
        add("1");
        tasks.findBy(visible).shouldBe(empty);
        assertItemsLeft(1);
        openActive();
        assertVisibleTasksAre("1");
    }
}
