package by.epam.webpharmacy.tests;

import by.epam.webpharmacy.managers.MessageManager;

import java.util.ResourceBundle;

public class TestBundle {
    public static void main(String[] args) {
        System.out.println(MessageManager.RU.getProperty("local.message.login.error"));
    }
}
