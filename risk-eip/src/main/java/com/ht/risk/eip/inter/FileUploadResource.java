package com.ht.risk.eip.inter;

import com.ht.risk.api.model.eip.FaceCompareDtoOut;
import com.ht.ussp.core.Result;
import feign.Param;
import feign.RequestLine;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;


public interface FileUploadResource {
//    @RequestLine("POST /file")
//    String fileUpload(@Param("file") MultipartFile file, @Param("usage") String usage, @Param("sync") boolean sync);

    @RequestLine("POST /eip/st/faceCompare")
    public Result<FaceCompareDtoOut> faceCompare(@Param("idNumber") String idNumber,
                                                 @Param( "name" ) String name,
                                                 @Param( "selfieUrl" ) String selfieUrl,
                                                 @Param( "selfieImageId" ) String selfieImageId,
                                                 @Param( "selfieAutoRotate" ) String selfieAutoRotate,
                                                 @Param("file") MultipartFile selfieFile);
}
