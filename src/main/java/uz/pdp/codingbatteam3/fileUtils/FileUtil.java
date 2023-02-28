package uz.pdp.codingbatteam3.fileUtils;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.codingbatteam3.entity.UserEntity;

import java.io.File;
import java.io.FileOutputStream;
@Component
public class FileUtil {
    private final String basePath = "/assets/";
    private final String defaultLogo = "default.png";

    public UserEntity saveLogo(UserEntity userEntity, MultipartFile logoFile) throws FileUploadException {
        try {
            if (logoFile.isEmpty()) {
               return setDefaultLogo(userEntity);
            } else {
               final String localePath = "src/main/resources/static/assets/";
                File file = new File(localePath + logoFile.getOriginalFilename());
                if (!file.exists()) file.createNewFile();
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(logoFile.getBytes());
                userEntity.setLogoUrl(basePath + logoFile.getOriginalFilename());
                fos.close();
                return userEntity;
            }
        } catch (Exception e) {
            throw new FileUploadException("file don't uploaded");
        }
    }

    private UserEntity setDefaultLogo(UserEntity userEntity) {
        userEntity.setLogoUrl(basePath+defaultLogo);
       return userEntity;
    }
}
