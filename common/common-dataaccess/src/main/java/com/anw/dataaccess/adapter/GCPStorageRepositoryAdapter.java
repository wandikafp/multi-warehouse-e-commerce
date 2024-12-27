package com.anw.dataaccess.adapter;

import com.anw.dataaccess.config.GCPConfig;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class GCPStorageRepositoryAdapter {
    private final GCPConfig gcpConfig;
    public String uploadFile(byte[] fileData, String fileName, String contentType) throws Exception {
        try{

            log.debug("Start file uploading process on GCS");

            InputStream inputStream = new ClassPathResource(gcpConfig.getConfigFile()).getInputStream();

            StorageOptions options = StorageOptions.newBuilder().setProjectId(gcpConfig.getProjectId())
                    .setCredentials(GoogleCredentials.fromStream(inputStream)).build();

            Storage storage = options.getService();
            Bucket bucket = storage.get(gcpConfig.getBucketId(),Storage.BucketGetOption.fields());

            String id = UUID.randomUUID().toString();
            Blob blob = bucket.create(gcpConfig.getDirectoryName() + "/" + id + "-" + fileName, fileData, contentType);

            if(blob != null) {
                log.debug("File successfully uploaded to GCP Storage");
                return blob.getMediaLink();
            }

        } catch (Exception e) {
            log.error("An error occurred while uploading data. Exception: ", e);
            throw new Exception("An error occurred while storing data to GCP Storage");
        }
        log.error("An error occurred while uploading data. Exception: blob is null");
        throw new Exception("An error occurred while storing data to GCP Storage");
    }

//    private File convertFile(MultipartFile file) {
//
//        try{
//            if(file.getOriginalFilename() == null){
//                throw new BadRequestException("Original file name is null");
//            }
//            File convertedFile = new File(file.getOriginalFilename());
//            FileOutputStream outputStream = new FileOutputStream(convertedFile);
//            outputStream.write(file.getBytes());
//            outputStream.close();
//            log.debug("Converting multipart file : {}", convertedFile);
//            return convertedFile;
//        }catch (Exception e){
//            throw new FileWriteException("An error has occurred while converting the file");
//        }
//    }

//    private String checkFileExtension(String fileName) {
//        if(fileName != null && fileName.contains(".")){
//            String[] extensionList = {".png", ".jpeg", ".pdf", ".doc", ".mp3"};
//
//            for(String extension: extensionList) {
//                if (fileName.endsWith(extension)) {
//                    log.debug("Accepted file type : {}", extension);
//                    return extension;
//                }
//            }
//        }
//        log.error("Not a permitted file type");
//        throw new InvalidFileTypeException("Not a permitted file type");
//    }
}
