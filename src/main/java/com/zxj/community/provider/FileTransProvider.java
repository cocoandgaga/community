package com.zxj.community.provider;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.OSSObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;

@Service
public class FileTransProvider {
    // Endpoint以杭州为例，其它Region请按实际情况填写。
    @Value("${aliyun.oss.endpoint}")
    private  String endpoint;
    @Value("${aliyun.oss.accessKeyId}")
    private  String accessKeyId ;
    @Value("${aliyun.oss.accessKeySecret}")
    private  String accessKeySecret ;
    @Value("${aliyun.oss.bucketName}")
    private  String bucketName;

    /**
     * markdown上传文件
     * @param inputStream 上传文件流
     * @param fileName  文件名
     * @return  上传到oos随机产生的文件名字
     */
    public String upload(InputStream inputStream, String fileName) {

        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        if(suffixName.isBlank())
            return null;
        // 生成上传文件名
        String finalFileName = UUID.randomUUID() + "" + suffixName;
        System.out.println(finalFileName);
        String objectName =  "imags/" + finalFileName;

        // 创建PutObjectRequest对象。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 上传文件。
        ossClient.putObject(bucketName,objectName,inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();

        return objectName;

    }

    public void download() throws IOException {


        String objectName = "<yourObjectName>";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
        OSSObject ossObject = ossClient.getObject(bucketName, objectName);

        // 读取文件内容。
        System.out.println("Object content:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(ossObject.getObjectContent()));
        while (true) {
            String line = reader.readLine();
            if (line == null) break;

            System.out.println("\n" + line);
        }
        // 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
        reader.close();

        // 关闭OSSClient。
        ossClient.shutdown();

    }




}