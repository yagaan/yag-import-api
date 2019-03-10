package com.yagaan.report.documentation;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

public class ScannerDocumentation {
	public static ScannerDocumentation fromJson(File file) throws JsonSyntaxException, IOException {
		return new Gson().fromJson(FileUtils.readFileToString(file,StandardCharsets.UTF_8), ScannerDocumentation.class);
	}
	private String title;
	private String language;
	private String version;

	private Map<String, String> checkers;

	public ScannerDocumentation(String title, String version, String language) {
		this.checkers = new HashMap<>();
		this.language = language;
		this.version = version;
		this.title = title;
	}

	public void set(String name, String doc) {
		this.checkers.put(name, doc);
	}

	public String getTitle() {
		return title;
	}

	public String getLanguage() {
		return language;
	}

	public String getVersion() {
		return version;
	}
	
	public String getDocumentation(String name) {
		return this.checkers.get(name);
	}

	public Map<String, String> getCheckers() {
		return checkers;
	}

	public String toJson() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(this);
	}

}
