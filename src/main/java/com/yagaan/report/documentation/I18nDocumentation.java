package com.yagaan.report.documentation;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.io.FilenameUtils;

public class I18nDocumentation {
	private String defaultLanguage;
	private Map<String,Map<String, ScannerDocumentation>> documentations;
	
	public I18nDocumentation(String defaultLanguage,File i18nDirectory) throws IOException {
		super();
		this.defaultLanguage = defaultLanguage;
		this.documentations = new HashMap<>();
		for(File lang : i18nDirectory.listFiles()) {
			if(lang.isDirectory()) {
				for(File scanner : lang.listFiles()) {
					if(!scanner.isDirectory()) {
						String scannerName = FilenameUtils.getBaseName(scanner.getName());
						ScannerDocumentation fromJson = ScannerDocumentation.fromJson(scanner);
						Map<String, ScannerDocumentation> docForScanner = documentations.get(scannerName);
						if(docForScanner==null) {
							docForScanner  = new HashMap<>();
							documentations.put(scannerName, docForScanner);
						}
						docForScanner.put(fromJson.getLanguage(), fromJson);
					}
					
				}
				
			}
		}
	}

	public Optional<String> get(String scanner,String lang, String checker) {
		Map<String, ScannerDocumentation> scannerDocs = documentations.get(scanner);
		if(scannerDocs==null) {
			throw new IllegalStateException("No such scanner: "+scanner);
		}
		ScannerDocumentation docOfLang = scannerDocs.get(lang);
		if(docOfLang==null) {
			throw new IllegalStateException("No such lang: "+lang);
		}
		String doc = docOfLang.getDocumentation(checker);
		if(doc==null) {
			//look into default lang
			doc = scannerDocs.get(defaultLanguage).getDocumentation(checker);
		}
		return Optional.ofNullable(doc);
	}
}
