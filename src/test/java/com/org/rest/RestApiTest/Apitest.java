package com.org.rest.RestApiTest;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Apitest {
	ExtentHtmlReporter htmlReporter;
	ExtentTest test;
	ExtentReports extent;

	@BeforeTest
	public void bt() {
		htmlReporter = new ExtentHtmlReporter("Report.html");
		htmlReporter.config().setTheme(Theme.DARK);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}

	@Test
	public void resttesting() {
		try {

			test = extent.createTest("Rest API Test");

			test.info("Automation Initiated");
			Response xyz = RestAssured.given().baseUri("http://dummy.restapiexample.com/api/v1/employees")
					.contentType("application/json").when().get();

			System.out.println("*************Response***************");
			String response = xyz.getBody().asString();
			System.out.println(response);

			System.out.println("*************Status Code***************");
			int httpstatus = xyz.statusCode();
			System.out.println(httpstatus);

			System.out.println("*************Time Taken***************");
			long timetaken = xyz.getTime();
			System.out.println(timetaken);
			System.out.println("*************End Test***************");

			test.pass("Rest API Automation Successfull");
		} catch (Exception e) {
			test.fail("Rest API Automation Failed");
		}
	}

	@AfterTest
	public void at() {
		test.info("Automation Terminated");
		extent.flush();
	}
}
