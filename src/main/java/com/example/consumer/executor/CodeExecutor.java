package com.example.consumer.executor;

import com.example.consumer.TemplateManager;
import com.example.consumer.domain.model.Language;
import com.example.consumer.domain.model.LanguageTemplate;
import com.example.consumer.dto.CodeRequest;
import com.example.consumer.dto.CodeResponse;
import com.example.consumer.dto.Template;
import com.example.consumer.fileManager.CustomFileWriter;
import com.example.consumer.service.LanguageService;
import com.example.consumer.service.LanguageTemplateService;
import com.example.consumer.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class CodeExecutor {

    @Autowired
    RedisService redisService;

    @Autowired
    CustomFileWriter customFileWriter;
    private final String testExecutionDirName = "/testExec/";
    private final String executionDirName = "/exec/";

    private final LanguageService languageService;
    private final LanguageTemplateService languageTemplateService;

    public CodeExecutor(LanguageService languageService, LanguageTemplateService languageTemplateService) {
        this.languageService = languageService;
        this.languageTemplateService = languageTemplateService;
    }

    public CodeResponse executeCode(CodeRequest codeRequest) throws IOException, InterruptedException {

        File tmpDir = createDir(codeRequest);

        if (tmpDir == null){
            return null;
        }

        Language language = languageService.getLanguageByName(codeRequest.getLanguage());
        if( language == null){
            return null;
        }

        LanguageTemplate template = languageTemplateService.getTemplateByLanguageId(language.getId());

        File fileToExecute = customFileWriter.rewriteFile(template.getFile(), codeRequest.getCode(), tmpDir, language);

        Map<String, ArrayList<String>> result = execute(fileToExecute, language, tmpDir);
        fileToExecute.delete();
        tmpDir.delete();

        return new CodeResponse(codeRequest.getId(), result.get("outputs"), result.get("errors"), "done");
    }

    private Map<String, ArrayList<String>> execute(File fileToExecute, Language language, File dir) throws IOException {
        Process process = null;

        Map<String, ArrayList<String>> result = new HashMap<>();
        if(Objects.equals(language.getName(), "c")){
            System.out.println("COMMMMMMENCE");
            process = Runtime.getRuntime().exec("firejail gcc -o " + dir.getAbsolutePath() + "/exec " + fileToExecute.getAbsolutePath() );
            result = ProcessManager.getProcessResult(process);
            System.out.println("ERREUR SIZE" + result.get("errors").size());
            System.out.println("OUTPU SIZE" + result.get("outputs").size());
            if( result.get("errors").size() == 1) {
                process = Runtime.getRuntime().exec("firejail " + dir.getAbsolutePath() + "/exec ");
                result.get("outputs").addAll(ProcessManager.getProcessResult(process).get("outputs"));
                result.get("errors").addAll(ProcessManager.getProcessResult(process).get("errors"));
            }
            return result;
        } else if (Objects.equals(language.getName(), "javascript")){
            process = Runtime.getRuntime().exec("firejail node " + fileToExecute.getAbsolutePath() );
            result = ProcessManager.getProcessResult(process);
        } else if (Objects.equals(language.getName(), "python")){
            process = Runtime.getRuntime().exec("firejail python3 " + fileToExecute.getAbsolutePath() );
            result = ProcessManager.getProcessResult(process);
        }
        return result;
    }

    private java.io.File createDir(CodeRequest codeRequest) throws InterruptedException {
        String directoryPath;
        if( codeRequest.isTest() == true){
            directoryPath = testExecutionDirName;
        } else {
            directoryPath = executionDirName;
        }
        java.io.File tmpDir = new java.io.File(directoryPath + codeRequest.getId());
        if( !tmpDir.mkdir()){
            return null;
        }
        return tmpDir;

    }


}
