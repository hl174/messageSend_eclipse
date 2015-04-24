package com.example.message;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	private EditText et_number;
	private EditText et_content;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//设置主面板
		setContentView(R.layout.activity_main);
		et_number = (EditText) findViewById(R.id.et_number);
		et_content = (EditText) findViewById(R.id.et_content);

		// 找到发送按钮
		Button bt_send = (Button) findViewById(R.id.bt_send);
		bt_send.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_send:
         String content= et_content.getText().toString().trim();
         String number= et_number.getText().toString().trim();
		if(TextUtils.isEmpty(content)||TextUtils.isEmpty(number)){
			Toast.makeText(this, "电话号码或者内容不能为空", Toast.LENGTH_SHORT).show();
			return ;
		}else{
			 //没有new,default得到一个实例
			SmsManager smsManager=SmsManager.getDefault();
		ArrayList<String> contents=smsManager.divideMessage(content);
		for (String string : contents) {
			//发送一个纯文本短信
			smsManager.sendTextMessage(number, null, string, null, null);
		}
			
		break;
		}
         
		default:
			break;
		}
	}

}
