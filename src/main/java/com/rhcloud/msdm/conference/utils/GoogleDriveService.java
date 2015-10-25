package com.rhcloud.msdm.conference.utils;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.util.Arrays;

public class GoogleDriveService {

    private final String serviceAccountId;
    private final String pathToServiceAccountPrivateKeyFromP12File;
    private String appName;


    private String pathToBufferDir;

    public GoogleDriveService(String serviceAccountId, String pathToServiceAccountPrivateKeyFromP12File, String appName, String pathToBufferDir) {
        this.serviceAccountId = serviceAccountId;
        this.pathToServiceAccountPrivateKeyFromP12File = pathToServiceAccountPrivateKeyFromP12File;
        this.appName = appName;
        this.pathToBufferDir = pathToBufferDir;
    }

    private GoogleCredential generateCredential() throws GeneralSecurityException, IOException {

        HttpTransport httpTransport = new NetHttpTransport();
        JsonFactory jsonFactory = new JacksonFactory();

        return new GoogleCredential.Builder()
                .setTransport(httpTransport)
                .setJsonFactory(jsonFactory)
                .setServiceAccountId(serviceAccountId)
                .setServiceAccountScopes(Arrays.asList(DriveScopes.DRIVE))
                .setServiceAccountPrivateKeyFromP12File(new java.io.File(pathToServiceAccountPrivateKeyFromP12File))
                .build();
    }


    private Drive getService(GoogleCredential credential) {

        HttpTransport httpTransport = new NetHttpTransport();
        JsonFactory jsonFactory = new JacksonFactory();

        return new Drive.Builder(httpTransport, jsonFactory, null)
                .setHttpRequestInitializer(credential)
                .setApplicationName(appName)
                .build();
    }


    public void upload(java.io.File fileContent) throws GeneralSecurityException, IOException {

        this.clearDuplicatedFiles(fileContent.getName());

        File body = new File();
        body.setTitle(fileContent.getName());
        body.setMimeType("image/jpeg");

        FileContent mediaContent = new FileContent("image/jpeg", fileContent);
        this.getService(this.generateCredential()).files().insert(body, mediaContent).execute();
    }


    private void clearDuplicatedFiles(String fileName) throws GeneralSecurityException, IOException {

        Drive service = this.getService(this.generateCredential());

        for (File file : service.files().list().execute().getItems()) {
            if (fileName.equals(this.getNameFileWithOutMimeType(file.getTitle()))) {
                Drive.Files.Delete request = service.files().delete(file.getId());
                request.execute();
                break;
            }
        }
    }


    public boolean download(String fileName) throws GeneralSecurityException, IOException {

        Drive service = getService(generateCredential());

        for (File file : service.files().list().execute().getItems()) {
            if (fileName.equals(this.getNameFileWithOutMimeType(file.getTitle()))) {
                try (OutputStream outputStream = new FileOutputStream(pathToBufferDir + java.io.File.separator + file.getTitle())) {

                    Drive.Files.Get request = service.files().get(file.getId());
                    request.executeMediaAndDownloadTo(outputStream);
                    return true;
                }
            }
        }
        return false;
    }

    private String getNameFileWithOutMimeType(String fileName){

        int pos = fileName.indexOf(".");
        if(pos != -1){
            return fileName.substring(0, pos);
        }
        return null;
    }


    public String getAppName() {
        return appName;
    }


    public void setAppName(String appName) {
        this.appName = appName;
    }


    public String getPathToBufferDir() {
        return pathToBufferDir;
    }

    public void setPathToBufferDir(String pathToBufferDir) {
        this.pathToBufferDir = pathToBufferDir;
    }
}
