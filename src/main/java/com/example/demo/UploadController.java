package com.example.demo;

import org.springframework.http.*;
import org.springframework.util.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;

import java.io.*;
import java.nio.file.*;

//@RestController
public class UploadController {
//  private static final String UPLOAD_DIR = System.getenv("RENDER") != null ? "/opt/render/.render/upload/images/" : "upload/images/";
  private static final String UPLOAD_DIR = "/opt/render/.render/upload/images/";

  @PostMapping("/upload")
  public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
    File dir = new File(UPLOAD_DIR);
    if (!dir.exists())
      dir.mkdirs();
    String filename = file.getOriginalFilename();
    File targetFile = new File(dir, filename);
    try {
      file.transferTo(targetFile);
      return ResponseEntity.ok("파일 저장 성공: " + targetFile.getAbsolutePath());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return ResponseEntity.status(HttpStatus.CONFLICT).build();
  }

  @GetMapping("/image/{filename}")
  public ResponseEntity<byte[]> getImage(@PathVariable String filename) throws IOException {
    File file = new File(UPLOAD_DIR + filename);

    if (!file.exists()) {
      return ResponseEntity.notFound().build();
    }

    // 4. 파일을 byte 배열로 읽기
    byte[] fileBytes = FileCopyUtils.copyToByteArray(file);

    // 5. MIME 타입 설정 (예: image/jpeg)
    String mimeType = Files.probeContentType(file.toPath());

    return ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType)).body(fileBytes);
  }
}
