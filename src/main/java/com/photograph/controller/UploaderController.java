package com.photograph.controller;

import com.photograph.pojo.ImgsUri;
import com.photograph.pojo.UserRelease;
import com.photograph.pojo.UserTag;
import com.photograph.service.ReleaseService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

/**
 * Created by Eminem on 2018/1/24.
 */
@Controller
public class UploaderController {

    private final static Logger logger = LoggerFactory.getLogger(UploaderController.class);

    //文件上传路径
    private String uploadFolderImgae = "F:\\ACCP\\Photograph\\src\\main\\resources\\uploadImage";
    private String uploadFolderPicture = "F:\\ACCP\\Photograph\\src\\main\\resources\\uploadImage\\picture";

    //加载静态资源
    private final ResourceLoader resourceLoader;

    @Autowired
    public UploaderController(ResourceLoader resourceLoader){
        this.resourceLoader = resourceLoader;
    }

    @Autowired
    private ReleaseService releaseService;

    /*BASE64Encoder encoder = new BASE64Encoder();
        for (int i = 0; i < file.length; i++) {
        try {
            String base = encoder.encode(file[i].getBytes());
            System.out.println("\n\n\n"+encoder.encode(file[i].getBytes()));
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] bytes = decoder.decodeBuffer(base);

            for (int j = 0; j < bytes.length; j++) {
                if (bytes[i] < 0){
                    bytes[i] += 256;
                }
            }

            OutputStream out = new FileOutputStream("C:\\Users\\Eminem\\Desktop\\Painter\\Java\\6.jpg");
            out.write(bytes);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    int i = 0; //由于webUploader插件是以队列方式上传文件，所以该方法会请求多次，定义此变量记录请求次数
    int urid = 0; //保存一个urid

    /**
     * 作品发布
     * @param uname 作者名字
     * @param title 标题
     * @param contents 介绍
     * @param watt 属性
     * @param tag 标签
     * @param protection 是否开启保护 default:close
     * @param file 文件
     * @return
     */
    @ResponseBody
    @PostMapping("/webUploader")
    public String webUploader(String uname,
                              String title,
                              String contents,
                              String watt,
                              String tag[],
                              String protection,
                              @RequestParam(value = "file",required = false) MultipartFile[] file){

        UserRelease userRelease = new UserRelease();
        ImgsUri imgsUri = new ImgsUri();
        List<UserTag> userTags = new ArrayList<UserTag>();
        ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(tag));
        List<ImgsUri> imgsUris = new ArrayList<ImgsUri>();

        //用户没有设置权限则为close
        if(protection==null){
            protection = "close";
        }

        userRelease.setUname(uname);
        userRelease.setTitle(title);
//        if (contents!=null){
//        }
        userRelease.setContents(contents);
        userRelease.setWatt(watt);
        userRelease.setProtection(protection);


        i +=+1;
        System.out.println("i:"+i);
        if (i<2){
            releaseService.addRelease(userRelease);
            urid = userRelease.getId();
            for (int j = 0; j < arrayList.size(); j++) {
                System.out.println(arrayList.get(j));
                UserTag userTag = new UserTag();
                userTag.setTagname(arrayList.get(j));
                userTag.setUrid(urid);
                userTag.setUname(uname);
                userTags.add(j,userTag);
            }
            releaseService.addTag(userTags);
        }

        imgsUri.setUrid(urid);

        for (int i = 0; i < file.length; i++) {

            if (file != null && !file[i].isEmpty()){
                try {
                    //对文件进行重命名避免文件重名
                    String random = UUID.randomUUID().toString().replaceAll("-","")+".png";
                    File dir = new File(uploadFolderImgae,random);
                    FileUtils.copyInputStreamToFile(file[i].getInputStream(),dir);
                    if (!dir.exists()){
                        dir.mkdir();
                    }
                    imgsUri.setImgName(random);
                    imgsUri.setImgUrl("http://localhost:8081/picture/"+random);
                    imgsUri.setUname(uname);
                    imgsUris.add(i,imgsUri);
                    releaseService.addImgs(imgsUris);
                    file[i].transferTo(dir);
                    return "{\"success\": \"1\"}";
                }catch (IOException e){
                    logger.error("数据上传失败："+e);
                    return "{\"success\": \"2\"}";
                }
            }
        }

        logger.error("请求参数不完整");
        return "{\"success\": \"3\"}";
    }

    @ResponseBody
    @PostMapping(value = "/touxiang")
    public void touXiang(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("hello:"+file.getOriginalFilename());
        InputStream is = file.getInputStream();
        byte[] bytes = FileCopyUtils.copyToByteArray(is);
        is = file.getInputStream();
        String fileName = UUID.randomUUID().toString().replaceAll("-","")+".png";
        File dir = new File(uploadFolderPicture,fileName);
        FileUtils.copyInputStreamToFile(file.getInputStream(),dir);
        file.transferTo(dir);
    }

    /**
     * 显示图片
     * @param filename 图片名称
     * @return
     */
    @GetMapping(value = "/picture/{filename:.+}")
    @ResponseBody
    public ResponseEntity<?> image(@PathVariable String filename){
        try {
            //响应实体
            return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(uploadFolderImgae,filename).toString()));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/image/{filename:.+}")
    @ResponseBody
    public ResponseEntity<?> picture(@PathVariable String filename){
        try {
            //响应实体
            return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(uploadFolderPicture,filename).toString()));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}