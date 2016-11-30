package com.qbgg.cenglaicengqu.session.action;

import android.content.Intent;

import com.netease.nim.uikit.session.actions.BaseAction;
import com.netease.nim.uikit.session.constant.RequestCode;
import com.qbgg.cenglaicengqu.R;

/**
 * 发送文件
 */
public class FileAction extends BaseAction {

    public FileAction() {
        super(R.drawable.message_plus_file_selector, R.string.input_panel_file);
    }

    /**
     * **********************文件************************
     */
    private void chooseFile() {
        //FileBrowserActivity.startActivityForResult(getActivity(), makeRequestCode(RequestCode.GET_LOCAL_FILE));
    }

    @Override
    public void onClick() {
        chooseFile();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RequestCode.GET_LOCAL_FILE) {
            //   String path = data.getStringExtra(FileBrowserActivity.EXTRA_DATA_PATH);
            //            File file = new File(path);
//            IMMessage message = MessageBuilder.createFileMessage(getAccount(), getSessionType(), file, file.getName());
//            sendMessage(message);
        }
    }
}
