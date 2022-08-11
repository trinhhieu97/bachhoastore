/**
 * @copyright Morisawa, all rights reserved.
 */

package vn.com.store.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import vn.com.store.dtos.FileDTO;
import vn.com.store.exception.SystemException;
import vn.com.store.services.IUserFileService;
import vn.com.store.utils.CommonUtils;

import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;

@RestController
@RequestMapping("/api/v1/files")
@AllArgsConstructor
public class UserFileController {

    private IUserFileService userFileService;

    @GetMapping("/{fileId}/download")
    public ResponseEntity<byte[]> download(@PathVariable String fileId) {
        FileDTO fileDTO = userFileService.getFileByFileId(fileId);
        byte[] bytes = null;
        try (InputStream input = userFileService.downloadFile(fileId);) {
            bytes = CommonUtils.toByteArray(input);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Content-Disposition",
                    "attachment; filename=" + fileDTO.getFileName());
            return new ResponseEntity<byte[]>(bytes, httpHeaders, HttpStatus.OK);
        }
        catch (IOException e) {
            throw new SystemException("Download file error!");
        }
    }

    @GetMapping("/{fileId}")
    public ModelAndView getFile(@PathVariable String fileId, ModelMap mode ) {
        FileDTO fileDTO = userFileService.getFileByFileId(fileId);
        String redirectUrl = "404";
        if(fileDTO == null){
            redirectUrl = fileDTO.getUrl();
        }
        return new ModelAndView("redirect:/"+ redirectUrl, mode);
    }

    @PostMapping("/upload")
    public @ResponseBody
    FileDTO upload(
            @RequestParam("file") MultipartFile uploadFile,
            @RequestParam("fileType") Integer fileType, Principal principal) {
        try (InputStream input = uploadFile.getInputStream()) {
            String fileName = uploadFile.getOriginalFilename();
            FileDTO fileDTO = userFileService.uploadFile(principal.getName(), fileType, fileName, input);
            return fileDTO;
        }
        catch (IOException e) {
            throw new SystemException(e.getMessage());
        }
    }


    @PostMapping(value = "/delete")
    public @ResponseBody ResponseEntity<String> delete(
            @RequestParam(name = "id") String id) {
        userFileService.deleteFile(id);
        return new ResponseEntity("success", HttpStatus.OK);
    }
}
