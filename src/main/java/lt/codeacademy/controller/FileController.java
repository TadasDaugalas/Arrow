package lt.codeacademy.controller;

import io.swagger.annotations.Api;

import static lt.codeacademy.ApiPath.*;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lt.codeacademy.entity.File;
import lt.codeacademy.service.FileService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(FILES)
@Api(tags = "Upload/Download files")
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @ApiOperation(value = "Isaugoti faila", tags = "saveFile", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Failas Isaugotas"),
            @ApiResponse(code = 403, message = "Nera teisiu"),
            @ApiResponse(code = 401, message = "Neprisijunges"),
            @ApiResponse(code = 404, message = "Failas nerastas")
    }
    )
    @PostMapping(BLOBS+PRODUCT)
    public void saveFileAsBlob(@RequestParam MultipartFile multipartFile,@PathVariable(ID_VARIABLE) UUID productId) {
        fileService.saveFileInDbAsBlob(productId,multipartFile);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<File> getAllPic(){
        return fileService.getFiles();
    }

    @ApiOperation(value = "Parsisiusti faila pagal varda", tags = "downloadFile", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Failas Isaugotas"),
            @ApiResponse(code = 403, message = "Nera teisiu"),
            @ApiResponse(code = 401, message = "Neprisijunges"),
            @ApiResponse(code = 404, message = "Failas nerastas")
    })
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
