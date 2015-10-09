package com.rhcloud.msdm.conference.utils;


import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUploader {

    private String pathToBufferDir;

    public FileUploader(String pathToBufferDir) {
        this.pathToBufferDir = pathToBufferDir;
    }


    public UploadStatus upload(MultipartFile file, String fileName) {

        BufferedOutputStream stream = null;

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                File new_file = new File(getBufferDir().getAbsolutePath() + File.separator + fileName + ".jpg");
                stream = new BufferedOutputStream(new FileOutputStream(new_file));
                stream.write(bytes);
                stream.flush();

            } catch (MaxUploadSizeExceededException e){
                e.printStackTrace();
                return UploadStatus.MAXIMUM_UPLOAD_SIZE_EXCEEDED;

            } catch (IOException e) {
                e.printStackTrace();
                return UploadStatus.IO_ERROR;
            }
            finally {
                try {
                    if (stream != null) {
                        stream.close();
                    }
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
            return UploadStatus.SUCCESS;
        }

        return UploadStatus.FILE_IS_EMPTY;
    }


    //schedule
    private void cleanBufferDir() {
        getBufferDir().delete();
    }


    private File getBufferDir() {
        File dir = new File(pathToBufferDir);

        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }
}
