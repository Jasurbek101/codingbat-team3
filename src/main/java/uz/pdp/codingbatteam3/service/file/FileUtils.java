package uz.pdp.codingbatteam3.service.file;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.codingbatteam3.common.exception.FileUploadException;
import uz.pdp.codingbatteam3.entity.UserEntity;
import uz.pdp.codingbatteam3.entity.model.DTO.UserRegisterDTO;

import java.io.*;

@Component
public class FileUtils {
    private final String basePath = "src/main/resources/static/";
    private final String defaultLogo = "default.png";

    public void saveLogo(UserEntity userEntity, MultipartFile logoFile) {
        try {
            if (logoFile.isEmpty()) {
                setDefaultLogo(userEntity);
            } else {
                File file = new File(basePath + logoFile.getOriginalFilename());
                if (!file.exists()) file.createNewFile();
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(logoFile.getBytes());
                userEntity.setLogoUrl(basePath + logoFile.getOriginalFilename());
                closeConnections(fos);
            }
        } catch (Exception e) {
            throw new FileUploadException("file don't uploaded");
        }
    }

    private void setDefaultLogo(UserEntity userEntity) {
        userEntity.setLogoUrl(basePath + defaultLogo);
    }
    private void closeConnections(FileOutputStream fos) {
        try {
            fos.close();
        } catch (Exception e) {
            throw new FileUploadException("file don't uploaded");
        }
    }

}
