package com.syhelper.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.syhelper.R;
import com.syhelper.adapter.CommonAdapter;
import com.syhelper.adapter.ViewHolder;
import com.syhelper.tool.L;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.nereo.multi_image_selector.MultiImageSelector;

public class PublishPhotoActivity extends BaseActivity {

    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_ok)
    TextView tvOk;
    @BindView(R.id.tv_add_photo)
    TextView tvAddPhoto;
    @BindView(R.id.et_publish_text)
    EditText etPublishText;
    @BindView(R.id.gv_image_result)
    GridView gvImageResult;

    ArrayList<String> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_photo);
        ButterKnife.bind(this);
        tvBack.setVisibility(View.VISIBLE);
        tvTitle.setVisibility(View.VISIBLE);
        tvTitle.setText("发布照片");
        tvOk.setVisibility(View.VISIBLE);
        tvOk.setText("发表");


        mData = new ArrayList<>();
        mAdapter = new MyImageAdapter(mContext, mData, R.layout.selecter_image_item);
        gvImageResult.setAdapter(mAdapter);
        gvImageResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pickImage();
            }
        });

        initAddPhotoState();
    }

    MyImageAdapter mAdapter;

    class MyImageAdapter extends CommonAdapter<String> {

        int mGridWidth;

        public MyImageAdapter(Context context, List<String> data, int layoutId) {
            super(context, data, layoutId);

            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            int width = 0;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
                Point size = new Point();
                wm.getDefaultDisplay().getSize(size);
                width = size.x;
            } else {
                width = wm.getDefaultDisplay().getWidth();
            }
            mGridWidth = width / 3;
        }

        @Override
        public void convert(ViewHolder holder, String s) {

            ImageView imageView = holder.getView(R.id.iv_item);
            // 显示图片
            Picasso.with(mContext)
                    .load(new File(s))
                    .placeholder(R.drawable.mis_default_error)
                    .resize(mGridWidth, mGridWidth)
                    .centerCrop()
                    .into(imageView);
        }
    }


    private void pickImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN // Permission was added in API Level 16
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE,
                    getString(R.string.mis_permission_rationale),
                    REQUEST_STORAGE_READ_ACCESS_PERMISSION);
        } else {
            int maxNum = 9;
            MultiImageSelector selector = MultiImageSelector.create(mContext);
            selector.showCamera(true);
            selector.count(maxNum);
            selector.multi();
            selector.origin(mData);
            selector.start(mContext, REQUEST_IMAGE);
        }
    }


    private static final int REQUEST_IMAGE = 2;
    protected static final int REQUEST_STORAGE_READ_ACCESS_PERMISSION = 101;

    private void requestPermission(final String permission, String rationale, final int requestCode) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.mis_permission_dialog_title)
                    .setMessage(rationale)
                    .setPositiveButton(R.string.mis_permission_dialog_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(mContext, new String[]{permission}, requestCode);
                        }
                    })
                    .setNegativeButton(R.string.mis_permission_dialog_cancel, null)
                    .create().show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_STORAGE_READ_ACCESS_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                pickImage();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE && resultCode == RESULT_OK) {
            L.e("===" + Thread.currentThread().getName());
            ArrayList<String> tempData = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
            mData.clear();
            mData.addAll(tempData);
            initAddPhotoState();
            mAdapter.notifyDataSetChanged();
        }
    }
    private void initAddPhotoState(){
        if (mData.size() !=9) tvAddPhoto.setVisibility(View.VISIBLE);
        else tvAddPhoto.setVisibility(View.GONE);
    }

    @OnClick({R.id.tv_back, R.id.tv_ok, R.id.tv_add_photo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                onBackPressed();
                break;
            case R.id.tv_ok:
                break;
            case R.id.tv_add_photo:
                pickImage();
                break;
        }
    }
}
