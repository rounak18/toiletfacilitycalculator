# Test automation for MBIE Toilet Calculator

This Automation Project is developed using TDD (Test Driven Development), Java as programing language, Maven as build automation tool and open source libraries like Selenium, TestNG, PdfComparator, ExtentReport etc..

Please read the below note to successfully setup the project on your machine.

If you are working behind a proxy, ensure you work through the next section 'Dealing with Proxies'.   

##### Note: Test Automation project currently only support browser Firefox and OS Windows, support for other browser and os not fully implemented in project.
## Prerequisite to Run project
* Installed Java 1.8.** JDK
* Installed Apace Maven 3.5.***
* Installed Eclipse or IntelliJ IDE
* Set Environment Variables for Java Home, Maven Home and Path variable for the same

* Open Eclipse and Import Project as an 'Existing Maven Projects'

## Dealing with HTTP and HTTPS Proxies
If working behind the proxy, then you will need to manage some proxy configuration as specified below, for Maven, Service Testing, and Eclipse.

### Maven
update settings.xml in maven .m2 folder with proxy details.

### UI Testing
For an initial example, see `config.properies`, in the root directory, and update the `proxyrequired` parameters as required. You must set `proxyrequired = true` to use HTTP and HTTPS format of proxy configuration. Also update details of proxyHost, proxyPort, proxyUser, proxyPassword. 

### With Eclipse
`Eclipse > Window > Preferences > General > Network Connections`

* Add Manual for Http and Https: 
  * host: proxyhost
  * port: proxyport
  * authentication: required
  * proxy bypass: [hostsToBypass]
 
## To Execute Tests
###  From terminal go to project directory and run command
* Run Command **mvn clean test -Denvironment={env-name} -Dsurefire.suiteXmlFiles=testsuite-executor/{env-name}.xml**
* e.g: **mvn clean test -Denvironment=dev -Dsurefire.suiteXmlFiles=testsuite-executor/dev.xml**
  
### From an IDE (e.g. Eclipse)
* Right click on pom.xml and Run As **Maven Build** with goals **mvn clean test -Denvironment={env-name} -Dsurefire.suiteXmlFiles=testsuite-executor/{env-name}.xml**

## To Check Test Report
* After Test Run you can see Test Report inside **target** folder with name **MBIE_Toilet_Facility_Calculator_Test_Automation_Report_{env-name}.html**

## To Check Test Logs
* Test execution log4j log file is available in project root folder with name **CalculatorTestAutomationLogs.out**

## To Change Test Environment
* To change test environment, go to `config.properies`, in the root directory, and update the same.
 
## To Change Base URL
* To update Base URL in project, go to `config.properies`, in the root directory, and update the same against **{env-name}.url**.

## To update TestData
* To update TestData in project, go to the src\main\java\govt\mbie\calculator\testdata directory, and update the ***.json** file related to environment.

## To update testng xml
* To update testng.xml in project, go to **testsuite-executor\{env-name}.xml** file.
