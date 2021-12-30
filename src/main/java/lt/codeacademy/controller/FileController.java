package lt.codeacademy.controller;

import static lt.codeacademy.ApiPath.*;

import lt.codeacademy.entity.File;
import lt.codeacademy.service.FileService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping(FILES)
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(BLOBS + PRODUCT)
    public void saveFileAsBlob(@RequestParam MultipartFile multipartFile, @PathVariable(ID_VARIABLE) UUID productId) {
        fileService.saveFileInDbAsBlob(productId, multipartFile);
    }

    @GetMapping(GET_BLOB)
    public ResponseEntity<Resource> getFileById(@PathVariable(ID_VARIABLE) UUID id) {
        File file = fileService.getFileById(id);

        Resource resource = new ByteArrayResource(file.getBytes());

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(file.getMediaType()))
                .headers(getHeaders(file.getFileName()))
                .body(resource);
    }

    private HttpHeaders getHeaders(String fileName) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        return headers;
    }

}
