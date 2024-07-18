package likelion.summer.welches.commons.config;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.GroupGrantee;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.Permission;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RequiredArgsConstructor
@Component
public class ImageUploader {
    private final AmazonS3 amazonS3;
    private final AmazonConfig amazonConfig;

    @Transactional
    public String toUpload(MultipartFile multipartFile) {

        String objectName =  System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
        String uploadedImageUrl = "";
        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(multipartFile.getSize());
            objectMetadata.setContentType(multipartFile.getContentType());
            amazonS3.putObject(amazonConfig.getBucketName(), objectName, multipartFile.getInputStream(), objectMetadata);

            uploadedImageUrl = String.format("%s/%s/%s", amazonConfig.getEndPoint(), amazonConfig.getBucketName(), URLEncoder.encode(objectName, "UTF-8").replace("+", "%20"));
            AccessControlList accessControlList = amazonS3.getObjectAcl(amazonConfig.getBucketName(), objectName);
            accessControlList.grantPermission(GroupGrantee.AllUsers, Permission.Read);

            amazonS3.setObjectAcl(amazonConfig.getBucketName(), objectName, accessControlList);



        } catch (Exception e) {
            return null;
        }

        return uploadedImageUrl;

    }
}
