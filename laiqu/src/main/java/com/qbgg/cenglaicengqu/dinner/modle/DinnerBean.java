package com.qbgg.cenglaicengqu.dinner.modle;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 *饭局实体类
 */

public class DinnerBean implements Parcelable {
    private String headimge;//头像
    private String accounts;//帐号
    private String nickname;//昵称
    private String accounts_grade;//会员等级
    private String dinner_state;//饭局状态
    private String dinner_introduce;//饭局介绍
    private List<String> pictures;//饭局介绍照片
    private String dinner_location;//饭局发布位置
    private String dinner_reserve;//饭局已经预定了多少
    private String dinner_release_time;//饭局发布时间
    private String dinner_read;//阅读数
    private String dinner_comment;//评论数
    private String dinner_like;//饭局喜欢数

    public DinnerBean() {
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DinnerBean that = (DinnerBean) o;

        if (headimge != null ? !headimge.equals(that.headimge) : that.headimge != null)
            return false;
        if (accounts != null ? !accounts.equals(that.accounts) : that.accounts != null)
            return false;
        if (nickname != null ? !nickname.equals(that.nickname) : that.nickname != null)
            return false;
        if (accounts_grade != null ? !accounts_grade.equals(that.accounts_grade) : that.accounts_grade != null)
            return false;
        if (dinner_state != null ? !dinner_state.equals(that.dinner_state) : that.dinner_state != null)
            return false;
        if (dinner_introduce != null ? !dinner_introduce.equals(that.dinner_introduce) : that.dinner_introduce != null)
            return false;
        if (pictures != null ? !pictures.equals(that.pictures) : that.pictures != null)
            return false;
        if (dinner_location != null ? !dinner_location.equals(that.dinner_location) : that.dinner_location != null)
            return false;
        if (dinner_reserve != null ? !dinner_reserve.equals(that.dinner_reserve) : that.dinner_reserve != null)
            return false;
        if (dinner_release_time != null ? !dinner_release_time.equals(that.dinner_release_time) : that.dinner_release_time != null)
            return false;
        if (dinner_read != null ? !dinner_read.equals(that.dinner_read) : that.dinner_read != null)
            return false;
        if (dinner_comment != null ? !dinner_comment.equals(that.dinner_comment) : that.dinner_comment != null)
            return false;
        return dinner_like != null ? dinner_like.equals(that.dinner_like) : that.dinner_like == null;

    }

    @Override
    public int hashCode() {
        int result = headimge != null ? headimge.hashCode() : 0;
        result = 31 * result + (accounts != null ? accounts.hashCode() : 0);
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (accounts_grade != null ? accounts_grade.hashCode() : 0);
        result = 31 * result + (dinner_state != null ? dinner_state.hashCode() : 0);
        result = 31 * result + (dinner_introduce != null ? dinner_introduce.hashCode() : 0);
        result = 31 * result + (pictures != null ? pictures.hashCode() : 0);
        result = 31 * result + (dinner_location != null ? dinner_location.hashCode() : 0);
        result = 31 * result + (dinner_reserve != null ? dinner_reserve.hashCode() : 0);
        result = 31 * result + (dinner_release_time != null ? dinner_release_time.hashCode() : 0);
        result = 31 * result + (dinner_read != null ? dinner_read.hashCode() : 0);
        result = 31 * result + (dinner_comment != null ? dinner_comment.hashCode() : 0);
        result = 31 * result + (dinner_like != null ? dinner_like.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DinnerBean{" +
                "headimge='" + headimge + '\'' +
                ", accounts='" + accounts + '\'' +
                ", nickname='" + nickname + '\'' +
                ", accounts_grade='" + accounts_grade + '\'' +
                ", dinner_state='" + dinner_state + '\'' +
                ", dinner_introduce='" + dinner_introduce + '\'' +
                ", pictures=" + pictures +
                ", dinner_location='" + dinner_location + '\'' +
                ", dinner_reserve='" + dinner_reserve + '\'' +
                ", dinner_release_time='" + dinner_release_time + '\'' +
                ", dinner_read='" + dinner_read + '\'' +
                ", dinner_comment='" + dinner_comment + '\'' +
                ", dinner_like='" + dinner_like + '\'' +
                '}';
    }

    public String getHeadimge() {
        return headimge;
    }

    public void setHeadimge(String headimge) {
        this.headimge = headimge;
    }

    public String getAccounts() {
        return accounts;
    }

    public void setAccounts(String accounts) {
        this.accounts = accounts;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAccounts_grade() {
        return accounts_grade;
    }

    public void setAccounts_grade(String accounts_grade) {
        this.accounts_grade = accounts_grade;
    }

    public String getDinner_state() {
        return dinner_state;
    }

    public void setDinner_state(String dinner_state) {
        this.dinner_state = dinner_state;
    }

    public String getDinner_introduce() {
        return dinner_introduce;
    }

    public void setDinner_introduce(String dinner_introduce) {
        this.dinner_introduce = dinner_introduce;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    public String getDinner_location() {
        return dinner_location;
    }

    public void setDinner_location(String dinner_location) {
        this.dinner_location = dinner_location;
    }

    public String getDinner_release_time() {
        return dinner_release_time;
    }

    public void setDinner_release_time(String dinner_release_time) {
        this.dinner_release_time = dinner_release_time;
    }

    public String getDinner_reserve() {
        return dinner_reserve;
    }

    public void setDinner_reserve(String dinner_reserve) {
        this.dinner_reserve = dinner_reserve;
    }

    public String getDinner_read() {
        return dinner_read;
    }

    public void setDinner_read(String dinner_read) {
        this.dinner_read = dinner_read;
    }

    public String getDinner_like() {
        return dinner_like;
    }

    public void setDinner_like(String dinner_like) {
        this.dinner_like = dinner_like;
    }

    public String getDinner_comment() {
        return dinner_comment;
    }

    public void setDinner_comment(String dinner_comment) {
        this.dinner_comment = dinner_comment;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.headimge);
        dest.writeString(this.accounts);
        dest.writeString(this.nickname);
        dest.writeString(this.accounts_grade);
        dest.writeString(this.dinner_state);
        dest.writeString(this.dinner_introduce);
        dest.writeStringList(this.pictures);
        dest.writeString(this.dinner_location);
        dest.writeString(this.dinner_reserve);
        dest.writeString(this.dinner_release_time);
        dest.writeString(this.dinner_read);
        dest.writeString(this.dinner_comment);
        dest.writeString(this.dinner_like);
    }

    protected DinnerBean(Parcel in) {
        this.headimge = in.readString();
        this.accounts = in.readString();
        this.nickname = in.readString();
        this.accounts_grade = in.readString();
        this.dinner_state = in.readString();
        this.dinner_introduce = in.readString();
        this.pictures = in.createStringArrayList();
        this.dinner_location = in.readString();
        this.dinner_reserve = in.readString();
        this.dinner_release_time = in.readString();
        this.dinner_read = in.readString();
        this.dinner_comment = in.readString();
        this.dinner_like = in.readString();
    }

    public static final Parcelable.Creator<DinnerBean> CREATOR = new Parcelable.Creator<DinnerBean>() {
        public DinnerBean createFromParcel(Parcel source) {
            return new DinnerBean(source);
        }

        public DinnerBean[] newArray(int size) {
            return new DinnerBean[size];
        }
    };
}
