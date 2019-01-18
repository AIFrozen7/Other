package com.skr.myproject.bean;

import java.io.Serializable;
import java.util.List;

public class CircleBean implements Serializable {

    /**
     * result : [{"commodityId":1,"content":"1547098591047494","createTime":1547149096000,"greatNum":6,"headPic":"http://172.17.8.100/images/small/default/user.jpg","id":279,"image":"","nickName":"啊","userId":494,"whetherGreat":2},{"commodityId":1,"content":"7","createTime":1547142937000,"greatNum":6,"headPic":"http://172.17.8.100/images/small/default/user.jpg","id":278,"image":"http://172.17.8.100/images/small/circle_pic/2019-01-10/5209420190110115537.jpg","nickName":"1212121212","userId":163,"whetherGreat":2},{"commodityId":1,"content":"1234","createTime":1547142790000,"greatNum":6,"headPic":"http://172.17.8.100/images/small/default/user.jpg","id":277,"image":"http://172.17.8.100/images/small/circle_pic/2019-01-10/8590220190110115310.jpg","nickName":"5l_8DK61","userId":1105,"whetherGreat":2},{"commodityId":1,"content":"12345","createTime":1547142786000,"greatNum":1,"headPic":"http://172.17.8.100/images/small/default/user.jpg","id":276,"image":"http://172.17.8.100/images/small/circle_pic/2019-01-10/8092320190110115306.jpg","nickName":"5l_8DK61","userId":1105,"whetherGreat":2},{"commodityId":1,"content":"123456","createTime":1547142780000,"greatNum":1,"headPic":"http://172.17.8.100/images/small/default/user.jpg","id":275,"image":"http://172.17.8.100/images/small/circle_pic/2019-01-10/3809120190110115300.jpg","nickName":"5l_8DK61","userId":1105,"whetherGreat":2}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * commodityId : 1
         * content : 1547098591047494
         * createTime : 1547149096000
         * greatNum : 6
         * headPic : http://172.17.8.100/images/small/default/user.jpg
         * id : 279
         * image : 
         * nickName : 啊
         * userId : 494
         * whetherGreat : 2
         */

        private String commodityId;
        private String content;
        private long createTime;
        private String greatNum;
        private String headPic;
        private String id;
        private String image;
        private String nickName;
        private String userId;
        private String whetherGreat;

        public String getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(String commodityId) {
            this.commodityId = commodityId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getGreatNum() {
            return greatNum;
        }

        public void setGreatNum(String greatNum) {
            this.greatNum = greatNum;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getWhetherGreat() {
            return whetherGreat;
        }

        public void setWhetherGreat(String whetherGreat) {
            this.whetherGreat = whetherGreat;
        }
    }
}
