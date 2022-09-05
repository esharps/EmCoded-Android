package com.esharps.emcoded.data;

import com.esharps.emcoded.model.CodeLine;
import com.esharps.emcoded.controller.AppController;

import android.util.Log;

import java.util.ArrayList;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * A CodeProject is multiple lines of code written in a designated programming language
 */
public class CodeProject {

    //Name of the project
    String projectName;
    // Storing each line of code in the code editor as an item in an ArrayList
    ArrayList<CodeLine> linesOfCodeList = new ArrayList<>();
    // Project's programming language
    String codeLanguage;
    JSONObject apiResponse;
    String replitUrl = "https://eval-backend.emmy-sharp.repl.co/exec";

    // Default constructor for CodeProject
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

    public String getCodeLanguage() {
        return codeLanguage;
    }

    /**
     * Use a dropdown box in the UI to select the programming language
     * for this specific CodeProject
     * @param codeLanguage
     */
    public void setCodeLanguage(String codeLanguage) {
        this.codeLanguage = codeLanguage;
    }

    /**
     * We want to submit whatever is in the EditText to the Web API
     * Return the API response as a String
     */
    public String submitCode(String language, String line) {

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
                //Log.d("Replit Response", apiResponse.toString());
                try {
                    Log.d("Replit Response", apiResponse.toString());
                } catch (Exception e) {
                    Log.d("API callback error", e.getMessage());
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
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

        if(apiResponse == null || apiResponse.toString().isEmpty()) {
            return "Unknown submission error";
        } else {
            return apiResponse.toString();
        }

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
