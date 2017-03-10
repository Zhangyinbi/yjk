package com.example.yangbang.miowner.entity;

import java.util.List;

/**
 * 评价实体类
 *
 * @FileName: com.example.yangbang.miowner.entity.Comment.java
 * @author: Yangbang
 * @date: 2016-06-27 17:42
 */
public class Comment {

    /**
     * status_name : 项目经理
     * review_list : [{"id":"7","score":"2","content":"好的就觉得","addtime":"2016-06-29 12:02:13","imgs":[{"id":"44251","small":"/images/thumb_imgs/20160629/smallorigin14671729332891.jpg","normal":"/images/thumb_imgs/20160629/normalorigin14671729332891.jpg"}]}]
     */

    private String status_name;
    /**
     * id : 7
     * score : 2
     * content : 好的就觉得
     * addtime : 2016-06-29 12:02:13
     * imgs : [{"id":"44251","small":"/images/thumb_imgs/20160629/smallorigin14671729332891.jpg","normal":"/images/thumb_imgs/20160629/normalorigin14671729332891.jpg"}]
     */

    private List<ReviewListBean> review_list;

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }

    public List<ReviewListBean> getReview_list() {
        return review_list;
    }

    public void setReview_list(List<ReviewListBean> review_list) {
        this.review_list = review_list;
    }

    public static class ReviewListBean {
        private String id;
        private String score;
        private String content;
        private String addtime;
        /**
         * id : 44251
         * small : /images/thumb_imgs/20160629/smallorigin14671729332891.jpg
         * normal : /images/thumb_imgs/20160629/normalorigin14671729332891.jpg
         */

        private List<ImgsBean> imgs;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
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
}
