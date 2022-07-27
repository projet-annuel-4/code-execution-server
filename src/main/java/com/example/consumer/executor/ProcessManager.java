package com.example.consumer.executor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProcessManager {

    public static Map<String, ArrayList<String>> getProcessResult(Process process) throws IOException {
        Map<String, ArrayList<String>> result = new HashMap<String, ArrayList<String>>();
        result.put("outputs", getOutputs(process));
        for( int i = 0; i < result.get("outputs").size(); i ++){
            System.out.println(result.get("outputs").get(i));
        }
        result.put("errors", getErrors(process));
        for( int i = 0; i < result.get("errors").size(); i ++){
            System.out.println(result.get("errors").get(i));
        }

        return result;
    }
    private static ArrayList<String> getOutputs(Process process) throws IOException {
        BufferedReader output = new BufferedReader(new InputStreamReader(process.getInputStream()));
        ArrayList<String> outputs = new ArrayList<>();
        String outputLine;
        while ( (outputLine = output.readLine()) != null){
            System.out.println( "123" );
            System.out.println(outputLine + " ///// " );

                outputs.add(outputLine);

        }

        return outputs;
    }

    private static ArrayList<String> getErrors(Process process) throws IOException {
        BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        ArrayList<String> errors = new ArrayList<>();
        String errorLine ;
        while ( (errorLine = error.readLine()) != null){
            System.out.println( "123" );
            System.out.println(errorLine + " ///// " );
                errors.add(errorLine);
        }

        return errors;
    }
}
