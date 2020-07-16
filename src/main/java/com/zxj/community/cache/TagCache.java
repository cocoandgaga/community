package com.zxj.community.cache;

import com.zxj.community.dto.TagDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TagCache {
    public static List<TagDTO> get() {
        List<TagDTO> tagDTOS = new ArrayList<>();

        TagDTO front = new TagDTO();
        front.setCategoryName("前端语言");
        front.setTags(Arrays.asList("javascript", "vue.js", "css", "html", "html5", "node.js", "react.js", "jquery", "css3", "es6", "typescript", "chrome", "npm", "bootstrap", "sass", "less", "chrome-devtools", "angular", "firefox", "coffeescript", "safari", "postman", "postcss", "fiddler", "yarn", "webkit", "firebug", "edge"));
        tagDTOS.add(front);

        TagDTO behind = new TagDTO();
        behind.setCategoryName("后端语言");
        behind.setTags(Arrays.asList("php", "java", "node.js", "python", "c++", "c", "golang", "spring", "springboot", "django", "flask", "c#", "swoole", "ruby", "asp.net", "ruby-on-rails", "scala", "rust", "lavarel", "python"));
        tagDTOS.add(behind);

        TagDTO mobile = new TagDTO();
        mobile.setCategoryName("移动端");
        mobile.setTags(Arrays.asList("java", "android", "ios", "objective-c", "小程序", "react-native", "swift", "xcode", "android-studio", "webapp", "flutter", "kotlin"));
        tagDTOS.add(mobile);

        TagDTO database = new TagDTO();
        database.setCategoryName("数据库");
        database.setTags(Arrays.asList("mysql", "redis", "mongodb", "sql", "json", "elasticsearch", "nosql", "memcached", "postgresql", "sqlite", "mariadb"));
        tagDTOS.add(database);

        TagDTO devOps = new TagDTO();
        devOps.setCategoryName("运维");
        devOps.setTags(Arrays.asList("linux", "nginx", "docker", "apache", "centos", "ubuntu", "服务器", "运维", "负载均衡", "ssh", "容器", "vagrant", "jenkins", "devops", "debian", "fabric"));
        tagDTOS.add(devOps);

        TagDTO ai = new TagDTO();
        ai.setCategoryName("人工智能");
        ai.setTags(Arrays.asList("算法机器", "学习人工", "智能深度学习", "数据挖掘", "tensorflow", "神经网络", "图像识别", "人脸识别", "自然语言处理", "机器人", "pytorch", "自动驾驶"));
        tagDTOS.add(ai);

        TagDTO tool = new TagDTO();
        tool.setCategoryName("工具");
        tool.setTags(Arrays.asList("git", "github", "macos", "visual-studio-code", "windows", "vim", "sublime-text", "intellij-idea", "phostorm", "eclipse", "webstorm", "编辑器", "svn", "visual-studio", "pycharm", "emacs"));
        tagDTOS.add(tool);

        return tagDTOS;
    }

    public static String isValid(String tags){
        String[] split = StringUtils.split(tags, ",");
        List<TagDTO> tagDTOS = get();

        List<String> tagList = tagDTOS.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        String invalid = Arrays.stream(split).filter(t -> StringUtils.isBlank(t) || tagList.contains(t)==false).collect(Collectors.joining(","));

        return invalid;

    }

}