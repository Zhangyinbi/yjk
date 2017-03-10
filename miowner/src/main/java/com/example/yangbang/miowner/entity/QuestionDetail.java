package com.example.yangbang.miowner.entity;

import java.util.List;

/**
 * 问题详情信息实体类
 *
 * @FileName: com.example.yangbang.miowner.entity.QuestionDetail.java
 * @author: Yangbang
 * @date: 2016-06-28 21:31
 */
public class QuestionDetail {

    /**
     * status : 4
     * content : regvresgbvrtdsbrfdg
     * imgs : [{"id":"44198","small":"/images/thumb_imgs/20160624/small14667406897887.jpg","normal":"/images/thumb_imgs/20160624/normal14667406897887.jpg"}]
     * addtime : 2016-06-24 11:58:10
     * allot_time_manager :
     * expected_processing_time :
     * act_end_time :
     * role_name : 待下发
     * com_type : 设计师
     */

    private String status;
    private String content;
    private String addtime;
    private String allot_time_manager;
    private String expected_processing_time;
    private String act_end_time;
    private String role_name;
    private String com_type;
    /**
     * id : 44198
     * small : /images/thumb_imgs/20160624/small14667406897887.jpg
     * normal : /images/thumb_imgs/20160624/normal14667406897887.jpg
     */

    private List<ImgsBean> imgs;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getAllot_time_manager() {
        return allot_time_manager;
    }

    public void setAllot_time_manager(String allot_time_manager) {
        this.allot_time_manager = allot_time_manager;
    }

    public String getExpected_processing_time() {
        return expected_processing_time;
    }

    public void setExpected_processing_time(String expected_processing_time) {
        this.expected_processing_time = expected_processing_time;
    }

    public String getAct_end_time() {
        return act_end_time;
    }

    public void setAct_end_time(String act_end_time) {
        this.act_end_time = act_end_time;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getCom_type() {
        return com_type;
    }

    public void setCom_type(String com_type) {
        this.com_type = com_type;
    }

    public List<ImgsBean> getImgs() {
        return imgs;
    }

    public void setImgs(List<ImgsBean> imgs) {
        this.imgs = imgs;
    }

    public static class ImgsBean {
        private String id;
        private String small;
        private String normal;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getNormal() {
            return normal;
        }

        public void setNormal(String normal) {
            this.normal = normal;
        }
    }
}
