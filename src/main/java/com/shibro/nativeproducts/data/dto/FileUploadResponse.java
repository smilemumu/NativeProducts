package com.shibro.nativeproducts.data.dto;

import lombok.Data;

import java.util.List;

@Data
public class FileUploadResponse {
    private List<FileUploadResponseItem> uploadResults;
}
