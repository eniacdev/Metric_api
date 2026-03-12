package com.example.HomeServerAPI.scheduled_job;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import org.springframework.stereotype.Service;
import com.example.HomeServerAPI.model.SystemLogDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PrepareJsonFile {
	
	public boolean writeJsonFile(SystemLogDto metric) {
		
		try {
		
		System.out.println("dosya yazılmaya başlandı");
			
		ObjectMapper mapper = new ObjectMapper();
		
		LocalDate date = LocalDate.now();
		String year = String.valueOf(date.getYear());
		
		//güncel tarihi json dosyasının ismi olarak yazılır.
		String fileName = date.toString() + ".json";
		
		//geçerli dosya yolu (path). dosya yolu istenildiği gibi değiştirilebilir, kullanım amacına bağlı.
		Path directoryPath = Paths.get("/MetricsLog", year);
		Path filePath = directoryPath.resolve(fileName);
		
		//dosya yolunu (path'i) oluşturur.
		Files.createDirectories(directoryPath);
		
		//json dosyanına metrikleri kaydeder.
		mapper.writerWithDefaultPrettyPrinter().writeValue(filePath.toFile(), metric);	
		
		System.out.println("dosya yazma bitti");
		
		return true;
		
		}catch (Exception e) {
			System.out.println("dosya yazılırken bir hata oluştu: " + e);
			return false;
		}
		
	}
}
