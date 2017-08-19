package controller;

import dao.UserMapper;
import dto.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * Created by p on 2017/8/17.
 */
@RestController
@RequestMapping(value = "/set")
@CrossOrigin
public class SettingController {

    @Autowired
    private UserMapper userMapper;

    /**
     * 设置头像
     * @param avatar 图片信息
     * @return 返回信息，以及用户验证信息
     */
    @RequestMapping(value = "/setAvatar", method = RequestMethod.POST)
    public Response signIn(@RequestParam MultipartFile avatar,HttpServletRequest request){
        // 保存相对路径到数据库 图片写入服务器
        try {
            if (avatar != null && !avatar.isEmpty()) {
                // 获取图片的文件名
                String fileName = avatar.getOriginalFilename();
                // 获取图片的扩展名
                String extensionName = fileName
                        .substring(fileName.lastIndexOf(".") + 1);
                // 新的图片文件名 = 获取时间戳+"."图片扩展名
                String newFileName = String.valueOf(request.getAttribute("phone"))
                        + "." + extensionName;
                File file = new File(request.getSession().getServletContext().getRealPath("/avatar"),newFileName);
                System.out.println(file.getAbsolutePath());
                if (!file.exists()) {
                    file.mkdirs();
                }
                System.out.println(file.getPath());
                avatar.transferTo(file);
                String url = "http://104.236.132.15:8081/CitiCup/avatar/" + newFileName;
                userMapper.updateAvatar(String.valueOf(request.getAttribute("phone")),url);
                return new Response<String>().SUCCESS(url);
            }
            else {
                return Response.NO_FILE;
            }
        }
        catch (IOException e){
            e.printStackTrace();
            return Response.UPLOAD_FAIL;
        }
    }
}
