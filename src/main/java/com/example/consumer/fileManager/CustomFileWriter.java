package com.example.consumer.fileManager;

import com.example.consumer.domain.model.Language;
import com.example.consumer.dto.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class CustomFileWriter {

    @Autowired
    Environment env;

    public void copyTemplateToRunDirectory(String fileExtension) throws IOException {
        Path copyFileDestination = Paths.get( env.getProperty("cust.runDirectory.path") + "\\template." + fileExtension );
        Path fileCopied = Paths.get( env.getProperty("cust.template.path") + "\\template." + fileExtension );
        Files.copy(fileCopied, copyFileDestination, StandardCopyOption.REPLACE_EXISTING);
    }

    public File rewriteFile(byte[] template, String code, File dir, Language language) throws IOException {
        System.out.println("/////////////////7");

        Charset charset = StandardCharsets.UTF_8;
        String content = new String(template, StandardCharsets.UTF_8);

        content = content.replaceAll(language.getStrToReplace(), code);
        File fileCreated = new File(dir.getAbsolutePath() + "/execute." + language.getExtension());
        FileWriter fileWriter = new FileWriter(fileCreated);
        fileWriter.write(content);
        fileWriter.close();
        System.out.println("/////////////////8");

        return fileCreated;
    }

    public void deleteExecutedFile(){

    }

}
