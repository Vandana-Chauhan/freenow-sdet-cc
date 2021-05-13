package com.freenow.ste.cc.test.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/com/freenow/ste/cc/features", glue = {
		"com/freenow/ste/cc/step/definations" }, plugin = "json:target/freenowTestReport.json")
public class TestRunner {

}
