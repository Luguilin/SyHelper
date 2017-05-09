package com.syhelper.tool;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.syhelper.R;

/**
 * Created by LGL on 2016/12/6.
 */

public class LLoadingDialog {

    private Dialog loadingDialog;
    private ViewGroup mViewGroup;
    private boolean isComplete = false;

    public LLoadingDialog(Context context, @LayoutRes int layout_id) {
        loadingDialog = new Dialog(context, R.style.LoadingDialog);
        mViewGroup = (ViewGroup) LayoutInflater.from(context).inflate(layout_id, null, false);
        loadingDialog.addContentView(mViewGroup, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    private void setCancelable(boolean cancelable) {
        if (!cancelable && loadingDialog != null) {
            loadingDialog.setCanceledOnTouchOutside(false);
//            loadingDialog.setCancelable(false);
        } else if (loadingDialog != null) {
            loadingDialog.setCanceledOnTouchOutside(true);
            loadingDialog.setCancelable(true);
        }
        loadingDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                mViewGroup.findViewById(R.id.complete).setVisibility(View.GONE);
                mViewGroup.findViewById(R.id.failure).setVisibility(View.GONE);
                mViewGroup.findViewById(R.id.loading).setVisibility(View.VISIBLE);
                if (dismissListener != null) dismissListener.onDismiss(dialog, isComplete);
            }
        });
    }

    public void callBack(boolean callback_able){
        loadingDialog.setCancelable(callback_able);
    }

    public void showLoading(boolean cancelable) {
        if (loadingDialog == null) return;
        if (loadingDialog.isShowing()) loadingDialog.dismiss();
        setCancelable(cancelable);
        loadingDialog.show();
    }

    public void showLoading() {
        isComplete = false;
        if (loadingDialog == null) return;
        if (loadingDialog.isShowing()) loadingDialog.dismiss();
        loadingDialog.show();
    }

    LoadingOnDismissListener dismissListener;

    public interface LoadingOnDismissListener {
        void onDismiss(DialogInterface dialog, boolean isComplete);
    }

    public void setOnDismissListener(LoadingOnDismissListener dismissListener) {
        this.dismissListener = dismissListener;
    }

    public void setComplete() {
        isComplete = true;
        mViewGroup.findViewById(R.id.loading).setVisibility(View.GONE);
        mViewGroup.findViewById(R.id.failure).setVisibility(View.GONE);
        mViewGroup.findViewById(R.id.complete).setVisibility(View.VISIBLE);
        countDownTimer.start();
    }

    public void setFailure() {
        mViewGroup.findViewById(R.id.loading).setVisibility(View.GONE);
        mViewGroup.findViewById(R.id.complete).setVisibility(View.GONE);
        mViewGroup.findViewById(R.id.failure).setVisibility(View.VISIBLE);

        countDownTimer.start();
    }

    public void dismiss() {
        if (loadingDialog == null) return;
        loadingDialog.dismiss();
    }

    CountDownTimer countDownTimer = new CountDownTimer(1500, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
        }

        @Override
        public void onFinish() {
            if (loadingDialog != null) loadingDialog.dismiss();
        }
    };

}
