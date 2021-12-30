package lt.codeacademy.service;

import lombok.extern.slf4j.Slf4j;
import lt.codeacademy.entity.File;
import lt.codeacademy.exception.FileException;
import lt.codeacademy.repository.FileRepository;
import lt.codeacademy.repository.ProductRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@Slf4j
public class FileService {
    private static final int MAX_SIZE = 10000000;
    private final Set<String> types;
    private final FileRepository fileRepository;
    private final ProductRepository productRepository;

    public FileService(FileRepository fileRepository, ProductRepository productRepository) {
        this.fileRepository = fileRepository;
        this.productRepository = productRepository;
        types = Set.of(MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE);
    }

    private void validateFile(MultipartFile multipartFile) {
        if (multipartFile.getSize() > MAX_SIZE) {
            throw new FileException(String.format("File size %s is too big", multipartFile.getSize()));
        }

        if (!types.contains(multipartFile.getContentType())) {
            throw new FileException(String.format("Content type %s not allowed", multipartFile.getContentType()));
        }

    }

    public void saveFileInDbAsBlob(UUID id, MultipartFile multipartFile) {
        validateFile(multipartFile);
        try {
            File file = new File(multipartFile.getOriginalFilename(), multipartFile.getSize(), multipartFile.getContentType(), multipartFile.getBytes());
            file.setProduct(productRepository.getById(id));
            file = fileRepository.save(file);
            fileRepository.save(file);
        } catch (Exception e) {
            log.error("Cannot save file", e);
            throw new FileException(String.format("Cannot save %s file", multipartFile.getOriginalFilename()));
        }
    }

    public File getFileById(UUID id) {
        return fileRepository.findById(id).orElseThrow(()
                -> new FileException(String.format("Cannot find file by %s UUID", id)));
    }
}


