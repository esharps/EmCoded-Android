package com.esharps.emcoded.data;
import com.esharps.emcoded.model.CodeLine;

import com.android.volley.RequestQueue;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import android.util.Log;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A CodeProject is multiple lines of code written in a designated programming language
 * TODO: In the future a CodeProject could consist of multiple files
 */
public class CodeProject {

    //Name of the project
    String projectName;

    // Storing each line of code in the code editor as an item in an ArrayList
    ArrayList<CodeLine> linesOfCodeList = new ArrayList<>();

    // Programming language used in editor
    String codeLanguage;

    // Variables for Volley API request
    JSONObject apiResponse;
    String replitUrl = "https://eval-backend.emmy-sharp.repl.co/exec";
    String responseString = "";

    RequestQueue queue;

    // Default constructor for empty CodeProject
    public CodeProject() {
    }

    // TODO: This constructor would be used to set up multiple programming projects
    public CodeProject(String codeLanguage, String projectName) {
        this.codeLanguage = codeLanguage;
        this.projectName = projectName;
    }

    //TODO: This constructor would be used to pull in an existing programming project
    //TODO: into the code editor UI
    public CodeProject(String codeLanguage, String projectName, ArrayList<CodeLine> linesOfCodeList){
        this.codeLanguage = codeLanguage;
        this.projectName = projectName;
        this.linesOfCodeList = linesOfCodeList;
    }

    //TODO: Useful for a "LOAD" type of action
    public ArrayList<CodeLine> getLinesOfCodeList(String name) {
        return linesOfCodeList;
    }

    //TODO: This method would be great for a "SAVE" action
    public void setLinesOfCodeList(ArrayList<CodeLine> linesOfCodeList) {
        this.linesOfCodeList = linesOfCodeList;
    }

    //TODO: This method could be used in the future
    public String getCodeLanguage() {
        return codeLanguage;
    }


    //TODO Use a dropdown box in the UI to select the programming language
    public void setCodeLanguage(String codeLanguage) {
        this.codeLanguage = codeLanguage;
    }

    /**
     * We want to submit whatever is in the EditText to the Web API
     * Return the API response as a String
     */
    public String submitCode(String language, String line, RequestQueue myQueue) {

        final JSONObject[] tempResponse = {new JSONObject()};
        JSONObject jsonParams = new JSONObject();

        try {
            jsonParams.put("language", language);
            jsonParams.put("command", line);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT,
                replitUrl, jsonParams, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                apiResponse = response;
                try {
                    responseString = apiResponse.toString();
                    Log.d("Replit Response", apiResponse.toString());
                } catch (Exception e) {
                    Log.d("API callback error", e.getMessage());
                }

                if(responseString == null || responseString.isEmpty()) {
                    responseString = "Unknown network submission error";
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error response", error.toString());
                    }
                }
        );
        myQueue.add(jsonObjectRequest);
        //AppController.getInstance().addToRequestQueue(jsonObjectRequest);

        return responseString;
    }

    /**
     * Only used if we are passing in a single line of code
     * @param singleLine
     */
    public void setCodeLine(CodeLine singleLine){
        linesOfCodeList.add(singleLine);
    }
    //TODO: This would allow submission of multiple lines of code from editor
    public void submitCodeLines(String language, ArrayList<CodeLine> lineList) {

    }

    //TODO: Provide the ability to save your project to device locally
    public void saveProject() {

    }

    //TODO: Load an existing project that has been cached locally to device
    public CodeProject getProject(String projectName) {
        CodeProject myExistingProject = null;
        return myExistingProject;
    }

}
