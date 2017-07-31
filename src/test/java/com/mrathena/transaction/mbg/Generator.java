package com.mrathena.transaction.mbg;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

public class Generator {

	public static void main(String[] args) {
		generate();
	}

	public static void generate() {
		System.out.println("MyBatis Generator start work");
		List<String> warnings = new ArrayList<String>();
		try {
			String path = "/src/test/resources";
			String file = "/mbg/config.xml";
			String configFilePath = System.getProperty("user.dir").concat(path).concat(file);
//			System.out.println("Config.xml Path: " + configFilePath);
			boolean overwrite = true;
			File configFile = new File(configFilePath);
			ConfigurationParser parser = new ConfigurationParser(warnings);
			Configuration config = parser.parseConfiguration(configFile);
			DefaultShellCallback callback = new DefaultShellCallback(overwrite);
			MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
			myBatisGenerator.generate(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (String warning : warnings) {
			System.out.println(warning);
		}
		System.out.println("MyBatis Generator finfish work");
	}

}
