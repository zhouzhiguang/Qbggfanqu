package com.qbgg.cenglaicengqu.session.viewholder;

import android.widget.ImageView;
import android.widget.TextView;

import com.netease.nim.uikit.session.viewholder.MsgViewHolderBase;
import com.qbgg.cenglaicengqu.R;


public class MsgViewHolderAVChat extends MsgViewHolderBase {

    private ImageView typeImage;
    private TextView statusLabel;

    @Override
    protected int getContentResId() {
        return R.layout.nim_message_item_avchat;
    }

    @Override
    protected void inflateContentView() {
        typeImage = findViewById(R.id.message_item_avchat_type_img);
        statusLabel = findViewById(R.id.message_item_avchat_state);
    }

    @Override
    protected void bindContentView() {
        if (message.getAttachment() == null) {
            return;
        }

        layoutByDirection();

        refreshContent();
    }

    private void layoutByDirection() {
//        AVChatAttachment attachment = (AVChatAttachment) message.getAttachment();
//
//        if (isReceivedMessage()) {
//            if (attachment.getType() == AVChatType.AUDIO) {
//                typeImage.setImageResource(R.mipmap.avchat_left_type_audio);
//            } else {
//                typeImage.setImageResource(R.mipmap.avchat_left_type_video);
//            }
//            statusLabel.setTextColor(ContextCompat.getColor(context,R.color.color_grey_999999));
//        } else {
//            if (attachment.getType() == AVChatType.AUDIO) {
//                typeImage.setImageResource(R.mipmap.avchat_right_type_audio);
//            } else {
//                typeImage.setImageResource(R.mipmap.avchat_right_type_video);
//            }
//            statusLabel.setTextColor(Color.WHITE);
//        }
    }

    private void refreshContent() {
//        AVChatAttachment attachment = (AVChatAttachment) message.getAttachment();
//
//        String textString = "";
//        switch (attachment.getState()) {
//        case Success: //成功接听
//            textString = TimeUtil.secToTime(attachment.getDuration());
//            break;
//        case Missed: //未接听
//        case Rejected: //主动拒绝
//            textString = context.getString(R.string.avchat_no_pick_up);
//            break;
//        default:
//            break;
        }

     //   statusLabel.setText(textString);
    //}
}
