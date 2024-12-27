package com.anw.user.service.domain.ports.output.repository;

public interface FileUploadRepository {
    String uploadFile(byte[] file, String fileName, String contentType);
}
