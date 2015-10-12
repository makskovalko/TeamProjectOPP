package com.rhcloud.msdm.conference.utils;


import com.rhcloud.msdm.conference.utils.exeptions.BufferDirIsNotDirectoryException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@EnableScheduling
public class FileUploader {

    private GoogleDriveService googleDriveService;

    private String pathToBufferDir;

    public List<String> allowedMimeTypes;

    public FileUploader(String pathToBufferDir, List<String> allowedMimeTypes) {
        this.pathToBufferDir = pathToBufferDir;
        this.allowedMimeTypes = allowedMimeTypes;
    }

    public FileUploader(String pathToBufferDir, List<String> allowedMimeTypes, GoogleDriveService googleDriveService) {
        this.pathToBufferDir = pathToBufferDir;
        this.googleDriveService = googleDriveService;
        this.allowedMimeTypes = allowedMimeTypes;
    }

    public UploadStatus upload(MultipartFile file, String fileName) throws BufferDirIsNotDirectoryException {

        this.deleteDuplicatedFiles(fileName);

        String MIMEtype = this.getMIMEtype(file);

        if (MIMEtype == null) return UploadStatus.NOT_VALID_FORMAT_FILE;

        if (!file.isEmpty()) {
            File new_file = new File(getBufferDir().getAbsolutePath() + File.separator + fileName + MIMEtype);

            try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new_file))) {

                byte[] bytes = file.getBytes();
                stream.write(bytes);
                stream.flush();

            } catch (MaxUploadSizeExceededException e) {

                return UploadStatus.MAXIMUM_UPLOAD_SIZE_EXCEEDED;

            } catch (BufferDirIsNotDirectoryException e) {

                throw new BufferDirIsNotDirectoryException();
            } catch (IOException e) {

                return UploadStatus.IO_ERROR;
            }

            return UploadStatus.SUCCESS;
        }

        return UploadStatus.FILE_IS_EMPTY;
    }


    public UploadStatus uploadToLocalMachineAndGoogleDrive(MultipartFile file, String fileName) throws BufferDirIsNotDirectoryException, GeneralSecurityException, IOException {

        UploadStatus status = this.upload(file, fileName);

        File fileContent = fileExists(fileName);
        googleDriveService.upload(fileContent);

        return status;
    }

    private void deleteDuplicatedFiles(String fileName) throws BufferDirIsNotDirectoryException {

        File[] files = null;
        if ((files = this.getBufferDir().listFiles()) != null) {

            for (File file : files) {
                if (fileName.equals(this.getNameFileWithOutMimeType(file.getName()))) {
                    file.delete();
                    break;
                }
            }
        }

    }

    private String getNameFileWithOutMimeType(String fileName) {

        int pos = fileName.indexOf(".");
        if (pos != -1) {
            return fileName.substring(0, pos);
        }
        return null;
    }




    @Scheduled(fixedDelay=60 * 1000 * 60 * 24)
    public void cleanBufferDir() {
        try {
            File[] files = null;
            if ((files = this.getBufferDir().listFiles()) != null) {

                for (File file : files) {
                    file.delete();
                }
            }
        } catch (BufferDirIsNotDirectoryException e) {
            e.printStackTrace();
        }
    }


    private File getBufferDir() throws BufferDirIsNotDirectoryException {
        File dir = new File(pathToBufferDir);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        if (dir.isDirectory()) {
            return dir;
        } else
            throw new BufferDirIsNotDirectoryException("Checked you path! Make sure that at the end of the path is folder. ");
    }


    private String getMIMEtype(MultipartFile file) {

        String originalFileName = file.getOriginalFilename();
        int pos = originalFileName.indexOf(".");

        if (pos != -1) {
            return originalFileName.substring(pos, originalFileName.length());
        }

        return null;
    }


    public File fileExists(String fileName) throws BufferDirIsNotDirectoryException {

        for (String mimeType : allowedMimeTypes) {
            File file;
            if ((file = new File(getBufferDir().getAbsolutePath() + File.separator + fileName + mimeType)).exists()) {
                return file;
            }
        }

        return null;
    }

    public GoogleDriveService getGoogleDriveService() {
        return googleDriveService;
    }

    public void setGoogleDriveService(GoogleDriveService googleDriveService) {
        this.googleDriveService = googleDriveService;
    }

    public String getPathToBufferDir() {
        return pathToBufferDir;
    }

    public void setPathToBufferDir(String pathToBufferDir) {
        this.pathToBufferDir = pathToBufferDir;
    }
}