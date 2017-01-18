package com.fanqu.dinner.modle;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

/**
 * 评论实体类
 */

public class CommentEntity implements Parcelable {
    private String commentid;//评论id
    private String commentpersonid;//评论人的id
    private String commentpersonheadimageurl;//头像地址
    private String commentpersonnicemane;//评论人的名字
    private String dinnerpartyid;//评论的饭局id
    private String dinnerpartygrade;//评分
    private String commenttext;
    private String commentdate;//评论的日期
    private String[] commentimageurl;//图片的URL
    private String commentsetmeal;//评论那个套餐来的

    public CommentEntity() {

    }

    public CommentEntity(String commentid, String commentsetmeal, String[] commentimageurl, String commentdate, String commenttext, String dinnerpartygrade, String dinnerpartyid, String commentpersonnicemane, String commentpersonheadimageurl, String commentpersonid) {
        this.commentid = commentid;
        this.commentsetmeal = commentsetmeal;
        this.commentimageurl = commentimageurl;
        this.commentdate = commentdate;
        this.commenttext = commenttext;
        this.dinnerpartygrade = dinnerpartygrade;
        this.dinnerpartyid = dinnerpartyid;
        this.commentpersonnicemane = commentpersonnicemane;
        this.commentpersonheadimageurl = commentpersonheadimageurl;
        this.commentpersonid = commentpersonid;
    }


    @Override
    public String toString() {
        return "CommentEntity{" +
                "commentid='" + commentid + '\'' +
                ", commentpersonid='" + commentpersonid + '\'' +
                ", commentpersonheadimageurl='" + commentpersonheadimageurl + '\'' +
                ", commentpersonnicemane='" + commentpersonnicemane + '\'' +
                ", dinnerpartyid='" + dinnerpartyid + '\'' +
                ", dinnerpartygrade='" + dinnerpartygrade + '\'' +
                ", commenttext='" + commenttext + '\'' +
                ", commentdate='" + commentdate + '\'' +
                ", commentimageurl=" + Arrays.toString(commentimageurl) +
                ", commentsetmeal='" + commentsetmeal + '\'' +
                '}';
    }

    public String getCommentpersonheadimageurl() {
        return commentpersonheadimageurl;
    }

    public void setCommentpersonheadimageurl(String commentpersonheadimageurl) {
        this.commentpersonheadimageurl = commentpersonheadimageurl;
    }

    public String getCommentid() {
        return commentid;
    }

    public void setCommentid(String commentid) {
        this.commentid = commentid;
    }

    public String getCommentpersonid() {
        return commentpersonid;
    }

    public void setCommentpersonid(String commentpersonid) {
        this.commentpersonid = commentpersonid;
    }

    public String getCommentpersonnicemane() {
        return commentpersonnicemane;
    }

    public void setCommentpersonnicemane(String commentpersonnicemane) {
        this.commentpersonnicemane = commentpersonnicemane;
    }

    public String getDinnerpartyid() {
        return dinnerpartyid;
    }

    public void setDinnerpartyid(String dinnerpartyid) {
        this.dinnerpartyid = dinnerpartyid;
    }

    public String getDinnerpartygrade() {
        return dinnerpartygrade;
    }

    public void setDinnerpartygrade(String dinnerpartygrade) {
        this.dinnerpartygrade = dinnerpartygrade;
    }

    public String getCommenttext() {
        return commenttext;
    }

    public void setCommenttext(String commenttext) {
        this.commenttext = commenttext;
    }

    public String getCommentdate() {
        return commentdate;
    }

    public void setCommentdate(String commentdate) {
        this.commentdate = commentdate;
    }

    public String[] getCommentimageurl() {
        return commentimageurl;
    }

    public void setCommentimageurl(String[] commentimageurl) {
        this.commentimageurl = commentimageurl;
    }

    public String getCommentsetmeal() {
        return commentsetmeal;
    }

    public void setCommentsetmeal(String commentsetmeal) {
        this.commentsetmeal = commentsetmeal;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.commentid);
        dest.writeString(this.commentpersonid);
        dest.writeString(this.commentpersonheadimageurl);
        dest.writeString(this.commentpersonnicemane);
        dest.writeString(this.dinnerpartyid);
        dest.writeString(this.dinnerpartygrade);
        dest.writeString(this.commenttext);
        dest.writeString(this.commentdate);
        dest.writeStringArray(this.commentimageurl);
        dest.writeString(this.commentsetmeal);
    }

    protected CommentEntity(Parcel in) {
        this.commentid = in.readString();
        this.commentpersonid = in.readString();
        this.commentpersonheadimageurl = in.readString();
        this.commentpersonnicemane = in.readString();
        this.dinnerpartyid = in.readString();
        this.dinnerpartygrade = in.readString();
        this.commenttext = in.readString();
        this.commentdate = in.readString();
        this.commentimageurl = in.createStringArray();
        this.commentsetmeal = in.readString();
    }

    public static final Creator<CommentEntity> CREATOR = new Creator<CommentEntity>() {
        public CommentEntity createFromParcel(Parcel source) {
            return new CommentEntity(source);
        }

        public CommentEntity[] newArray(int size) {
            return new CommentEntity[size];
        }
    };
}
