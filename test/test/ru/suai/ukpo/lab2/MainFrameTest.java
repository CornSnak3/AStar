/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.ru.suai.ukpo.lab2;

import static org.assertj.core.api.Assertions.assertThat;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JListFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.suai.ukpo.lab2.MainFrame;

public class MainFrameTest {
    private FrameFixture window;
    
    @BeforeClass
    public static void setUpOnce() {
        
    }
    
  @Before
  public void setUp() {
    MainFrame frame = GuiActionRunner.execute(() -> new MainFrame());
    window = new FrameFixture(frame);
    window.show(); // shows the frame to test
  }
  
  @Test
  public void initComponentsTest() {
      window.textBox("name").requireText("");
      window.textBox("latitude").requireText("");
      window.textBox("longitude").requireText("");
      window.textBox("routeTextField").requireText("");
      window.list("addList").requireItemCount(8);
      window.list("addList").requireItemCount(8);
      window.list("addList").requireItemCount(8);
  }
  
  @Test
  public void findExistingRoute() {
      window.list("findFromList").selectItem("Лондон");
      window.list("findToList").selectItem("Лос-Анджелес");
      window.button("findButton").click();
      window.textBox("routeTextField").requireText("Лондон Москва Лос-Анджелес ");
  }
  
  @Test
  public void findNonExistingRoute() {
      window.list("findFromList").selectItem("Лондон");
      window.list("findToList").selectItem("Северный полюс");
      window.button("findButton").click();
      window.textBox("routeTextField").requireText("Маршрута не найдено" );
  }
  
  @Test
  public void addNewEntryAndFindRoute() {
      window.textBox("name").enterText("Тест");
      window.textBox("latitude").enterText("11");
      window.textBox("longitude").enterText("2");
      window.list("addList").selectItems("Санкт-Петербург", "Москва");
      window.button("addButton").click();
      assertThat(window.list("findFromList").item("Тест").value()).isEqualTo("Тест");
      window.list("findFromList").selectItem("Тест");
      window.list("findToList").selectItem("Москва");
      window.button("findButton").click();
      window.textBox("routeTextField").requireText("Тест Санкт-Петербург Москва ");
  }
  
  @Test
  public void addNewEntryAndNotFindRoute() {
      window.textBox("name").enterText("Тест");
      window.textBox("latitude").enterText("11");
      window.textBox("longitude").enterText("21");
      window.list("addList").selectItems("Санкт-Петербург", "Москва");
      window.button("addButton").click();
      assertThat(window.list("findFromList").item("Тест").value()).isEqualTo("Тест");
      window.list("findFromList").selectItem("Тест");
      window.list("findToList").selectItem("Северный полюс");
      window.button("findButton").click();
      window.textBox("routeTextField").requireText("Маршрута не найдено");
  }
  
  @After
  public void tearDown() {
    window.cleanUp();
  }
}
