# Selenium Playground

## About
Sample project using Selenium 3.

## Prerequisites

* Maven 3+
* Java 8
* Any of:
  * geckodriver + Firefox
  * chromedriver + Chrome

## How to run
From root project, to run browser tests in:
* Chrome, run: ``mvn clean verify -Dbrowser=cr|chrome -Dwebdriver.chrome.driver=/path/to/driver``
* Firefox, run: ``mvn clean verify [-Dbrowser=ff|firefox] -Dwebdriver.gecko.driver=/path/to/driver``

(This will also run all unit tests in ``framework`` module.)