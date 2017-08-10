package com.os.police.module.mine.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.os.police.R;
import com.os.police.base.BaseActivity;
import com.os.police.okhttp.OKManager;
import com.os.police.utils.AppManager;
import com.os.police.utils.AppToastMgr;
import com.os.police.utils.UrlUtil;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

import static com.os.police.utils.AppApplicationMgr.getVersionName;


/**
 * Created by LSH on 2017/7/27.
 * 意见反馈
 */
public class FeedBackActivity extends BaseActivity {

    @BindView(R.id.btn_detail_back)
    ImageView btnDetailBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.contact_way)
    TextView contactWay;
    @BindView(R.id.et_feedback)
    EditText etFeedback;
    @BindView(R.id.et_contact)
    EditText etContact;

    private String url = UrlUtil.http("api/IdeaManage/InsertIdea");
    private OKManager okManager = OKManager.getInstance();
    private HashMap<String, String> mForm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
    }

    @Override
    protected void initView() {
        tvSubmit.setVisibility(View.VISIBLE);
        btnDetailBack.setVisibility(View.VISIBLE);
        tvTitle.setText(R.string.feed_back);
    }

    @Override
    protected void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_feed_back;
    }

    @OnClick({R.id.btn_detail_back, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_detail_back:
                finish();
                break;
            case R.id.tv_submit:

                if (TextUtils.isEmpty(etFeedback.getText().toString().trim())) {
                    Toast.makeText(this, "请输入反馈问题", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(etContact.getText().toString())) {
                    Toast.makeText(this, "请输入联系方式", Toast.LENGTH_SHORT).show();
                    return;
                }
                mForm = new HashMap<>();
                mForm.put("UserGuid", "");
                mForm.put("UserName", "");
                mForm.put("DataSource", "1");
                mForm.put("AreaId", "OceanSoft");
                mForm.put("EncryptPass", "123456");
                mForm.put("ContactWay", etContact.getText().toString());
                mForm.put("Content", etFeedback.getText().toString());
                mForm.put("mobiletype", android.os.Build.MODEL);
                mForm.put("systemtype", android.os.Build.VERSION.RELEASE);
                mForm.put("apptype", String.valueOf(getVersionName(getApplicationContext())));

                okManager.sendComplexForm(url, mForm, new OKManager.Func4() {
                    @Override
                    public <T> T onResponse(JSONObject t) {
                        JSONObject object = t;
                        try {
                            String msg = object.getString("msg");
                            boolean success = object.getBoolean("succ");
                            if (success) {
                                AppToastMgr.ToastShortCenter(getApplicationContext(), msg);
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }

                    @Override
                    public void onFailed(String error) {
                        AppToastMgr.ToastShortCenter(getApplicationContext(), "网络错误");
                    }
                });
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
