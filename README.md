# java-selenium-cucumber demo project

The test run is scheduled to be started at 09:00 UTC on every Monday. The badge is presented the last state of the run. [![Java CI with Maven](https://github.com/lvasileva/java-selenium-cucumber/actions/workflows/maven.yml/badge.svg?branch=main)](https://github.com/lvasileva/java-selenium-cucumber/actions/workflows/maven.yml)

Here is the project of cucumber features running tests for the demo site at: https://www.saucedemo.com/


It contains **Login feature**, testing different users login action, and the **BuyItems feture** with the following tasks:
1. As a standard_user add all items to the cart and check if user sees 6 items total
2. As a standard_user add all items to the cart, and proceeds to the checkout, and verifies that the total value of items is $140.34
3. As a performance_glitch_user add all items to the cart, remove all items of type T-Shirt from the shopping cart, and continue to the checkout. Verify that the total value of items is $105.80. Then repeat this test, but this time remove the all items of type Backpack and verify that the total value of items is $107.95
