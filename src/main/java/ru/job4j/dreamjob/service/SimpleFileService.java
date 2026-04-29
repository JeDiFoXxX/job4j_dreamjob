package ru.job4j.dreamjob.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.dto.FileDto;
import ru.job4j.dreamjob.model.File;
import ru.job4j.dreamjob.repository.FileRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.UUID;

@Service
public class SimpleFileService implements FileService<File, FileDto> {

    private final FileRepository fileRepository;
    private final String storageDirectory;

    public SimpleFileService(FileRepository fileRepository, String storageDirectory) {
        this.fileRepository = fileRepository;
        this.storageDirectory = storageDirectory;
    }

    @Override
    public File save(FileDto fileDto) {
        var path = getNewFilePath(fileDto.getName());
        writeFileBytes(path, fileDto.getContent());
        return fileRepository.save(new File(fileDto.getName(), path));
    }

    @Override
    public Optional<FileDto> getFileById(int id) {
        var fileOptional = fileRepository.findById(id);
        if (fileOptional.isEmpty()) {
            return Optional.empty();
        }
        var content = readFileAsBytes(fileOptional.get().getPath());
        return Optional.of(new FileDto(fileOptional.get().getName(), content));
    }

    @Override
    public void deleteById(int id) {
        var fileOptional = fileRepository.findById(id);
        if (fileOptional.isPresent()) {
            deleteFile(fileOptional.get().getPath());
            fileRepository.deleteById(id);
        }
    }

    @Override
    public boolean update(int id, FileDto fileDto) {
        boolean rsl = false;
        var fileOptional = fileRepository.findById(id);
        if (fileOptional.isPresent()) {
            deleteFile(fileOptional.get().getPath());
            var path = getNewFilePath(fileDto.getName());
            writeFileBytes(path, fileDto.getContent());
            File newFile = new File(fileDto.getName(), path);
            newFile.setId(id);
            rsl = fileRepository.update(newFile);
        }
        return rsl;
    }

    private String getNewFilePath(String sourceName) {
        return storageDirectory + java.io.File.separator + UUID.randomUUID() + sourceName;
    }

    private void writeFileBytes(String path, byte[] content) {
        try {
            Files.write(Path.of(path), content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] readFileAsBytes(String path) {
        try {
            return Files.readAllBytes(Path.of(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteFile(String path) {
        try {
            Files.deleteIfExists(Path.of(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
