package com.kklimkovtests;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import java.io.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@Owner("KKlimkov")
@Layer("IDE")
@Feature("BaseObjects")
@TestMethodOrder(OrderAnnotation.class)

public class TestCreateProject {

    @BeforeAll
    static void setUp() throws InterruptedException, IOException {
        //IDESteps.StartDriver();
        IDESteps.LaunchAPP("Root","10");
     // for presentation   IDESteps.ChangeLanguageKeybord("en");
    }
    String host = System.getProperty("HostIP");

    @DisplayName("Запуск среды разработки и проверка процесса")
    @Test
    @Story("BaseObjects Pump")
    @Tags({@Tag("IDE"),@Tag("Launch")})
    @Order(1)
    public void LaunchIDE() throws InterruptedException, IOException {
        IDESteps.LaunchAPP("C:\\Program Files\\MPSSoft\\MasterSCADA 4D 1.2\\bin\\ProjectEditor.exe","15");
        IDESteps.GetProcessExistence("ProjectEditor",true);
    }

    @DisplayName("Создание нового проекта")
    @Test
    @Story("BaseObjects Pump")
    @Tags({@Tag("IDE"),@Tag("Project")})
    @Order(2)
    public void Create() throws InterruptedException {
        IDESteps.CreateProject("Тестовый проект", 20000);
    }

    @DisplayName("Создание АРМа, окна и параметра")
    @Test
    @Story("BaseObjects Pump")
    @Tags({@Tag("IDE"),@Tag("ARM")})
    @Order(3)
    public void AddParamAndWindow() throws InterruptedException {
        IDESteps.TreeMode("Usual");
        IDESteps.AddARM();
        IDESteps.AddWindow("АРМ 1");
        IDESteps.AddParam("АРМ 1","BOOL");
        IDESteps.DragAndDropElementWithAction("XPath","//*[contains(@Name, 'Система.АРМ 1.Параметры.Параметр 1')]","AccessibilityId","MainTabControl", "ARROW_DOWN",5);
    }

    @DisplayName("Создание экземпляра объекта и перенос на стартовое окно")
    @Test
    @Story("BaseObjects Pump")
    @Tags({@Tag("IDE"),@Tag("Instance")})
    @Order(4)
    public void DragLibraryPump() throws InterruptedException {
        IDESteps.GetLibraryObject("BaseObjects");
        IDESteps.DragAndDropElement("XPath","//*[contains(@Name, 'Библиотеки.BaseObjects.Объекты.Насос')]","Name","'Объекты' (Id=64 Тип=MasterSCADA.Objects)");
        IDESteps.DragAndDropElementWithAction("XPath","//*[contains(@Name, 'Объекты.Насос 1')]","AccessibilityId", "MainTabControl", "ARROW_UP",5);
    }

    @DisplayName("Связь управляющего выхода насоса и параметра объекта")
    @Test
    @Story("BaseObjects Pump")
    @Tags({@Tag("IDE"),@Tag("Link")})
    @Order(5)
    public void LinkPumpWithParam() throws InterruptedException {
        IDESteps.DoubleClickTreeElement("//*[contains(@Name, 'Объекты.Насос 1')]");
        IDESteps.DragAndDropElement("Name", "УпрВыход", "XPath", "//*[contains(@Name, 'Система.АРМ 1.Параметры.Параметр 1')]");
    }

    @DisplayName("Получение id элементов для следующих тестов")
    @Test
    @Story("BaseObjects Pump")
    @Tags({@Tag("IDE"),@Tag("ID")})
    @Order(6)
    public void GetIdElements() throws InterruptedException {
        IDESteps.TreeMode("Full");
        IDESteps.OpenElementInNewBranch("//*[contains(@Name, 'Система.АРМ 1.Графический интерфейс.Окна.Окно 1')]");
        IDESteps.DoubleClickTreeElementName("Схема");
        IDESteps.WriteIdByPath("//*[contains(@Name, 'Система.АРМ 1.Параметры.Параметр 1')]","C:\\Users\\Public\\Autotests\\Data.csv", false,false);
        IDESteps.WriteIdByPath("//*[contains(@Name, 'Объекты.Насос 1')]","C:\\Users\\Public\\Autotests\\Data.csv", true,false);
        IDESteps.WriteIdByPath("//*[contains(@Name, 'Система.АРМ 1.Графический интерфейс.Окна.Окно 1.Схема.Насос горизонт 1')]","C:\\Users\\Public\\Autotests\\Data.csv",true,true);
    }

    @DisplayName("Смена ip и запуск проекта")
    @Test
    @Story("BaseObjects Pump")
    @Tags({@Tag("IDE"),@Tag("IP")})
    @Order(7)
    public void ChangeIpAndLaunch() throws InterruptedException {
      //for presentation  IDESteps.DoubleClickTreeElement("//*[contains(@Name, 'Система.АРМ 1.Службы')]");
        // for presentation   IDESteps.OpenNextTreeElementByArrow("//*[contains(@Name, 'Система.АРМ 1.Службы.Межузловая связь')]");
        //for presentation  IDESteps.DoubleClickTreeElement("//*[contains(@Name, 'Система.АРМ 1.Службы.Межузловая связь.Настройки')]");
        //for presentation  IDESteps.DoubleClickTreeElementName("IP адрес");
        //for presentation  IDESteps.SetValueInDialogWindow("127.0.0.1",host);
        IDESteps.SaveProject();
        IDESteps.RunRT();
        IDESteps.SendNewRTFiles();
    }

  /*  @DisplayName("Закрытие проекта")
    @Test
    @Story("BaseObjects Pump")
    @Tags({@Tag("IDE"),@Tag("Close")})
    @Order(8)
        public void СloseProject() throws InterruptedException {
            IDESteps.CloseProject();
            IDESteps.CloseProject();
        }

     @AfterAll
       static void Stop() throws InterruptedException {
           IDESteps.StopDriver();
    }
    */
}