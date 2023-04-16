package com.common.project.minio.entity;

import lombok.Data;

import java.io.Serializable;
/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: hsw
 * Date: 2022/04/12/10:10
 */


@Data
public class MinIoUploadResDTO implements Serializable {

    private static final long serialVersionUID = 475040120689218785L;
    private String minFileName;
    private String minFileUrl;

    public MinIoUploadResDTO(String minFileName,String minFileUrl) {
        this.minFileName = minFileName;
        this.minFileUrl = minFileUrl;
    }
}