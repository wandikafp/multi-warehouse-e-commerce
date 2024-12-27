package com.anw.user.service.dataaccess.user.adapter;

import com.anw.dataaccess.adapter.GCPStorageRepositoryAdapter;
import com.anw.user.service.domain.exception.UserDomainException;
import com.anw.user.service.domain.ports.output.repository.FileUploadRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileRepositoryImpl implements FileUploadRepository {
    private final GCPStorageRepositoryAdapter gcpStorageRepositoryAdapter;
    @Override
    public String uploadFile(byte[] file, String fileName, String contentType) {
        try {
            return gcpStorageRepositoryAdapter.uploadFile(file, fileName, contentType);
        } catch (Exception ex) {
            throw new UserDomainException(ex.getMessage());
        }
    }
}
