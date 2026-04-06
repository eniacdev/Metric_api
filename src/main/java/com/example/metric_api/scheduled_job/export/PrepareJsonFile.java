package com.example.metric_api.scheduled_job.export;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.metric_api.model.SystemLogDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PrepareJsonFile {
	
	private static final Logger log = LoggerFactory.getLogger(PrepareJsonFile.class);
	
	public boolean writeJsonFile(SystemLogDto metric) {
		
		try {
		
		log.warn("The json file is being preparing.");
			
		ObjectMapper mapper = new ObjectMapper();
		
		LocalDate date = LocalDate.now();
		String year = String.valueOf(date.getYear());
		String month = String.format("%02d", date.getMonthValue());
		
		String fileName = date.toString() + ".json";
		
		Path directoryPath = Paths.get("/MetricsLog", year, month);
		Path filePath = directoryPath.resolve(fileName);
		
		Files.createDirectories(directoryPath);
		
		mapper.writerWithDefaultPrettyPrinter().writeValue(filePath.toFile(), metric);	
		
		log.info("file is ready.");
		
		return true;
		
		}catch (Exception e) {
			log.error("Json dosyası yazılamadı: {}", e.getMessage());
		    return false;
		}
		
	}
}
