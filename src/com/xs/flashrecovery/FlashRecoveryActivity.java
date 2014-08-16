package com.xs.flashrecovery;

import java.io.IOException;

import com.xs.flashrecovery.tools.*;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.widget.Toast;

public class FlashRecoveryActivity extends PreferenceActivity {

	private static final String FLASHCHINESERECOVERY = "flash_chinese_recovery";
	private static final String FLASHENGLISHRECOVERY = "flash_english_recovery";
	private static final String REBOOTRECOVERY = "reboot_recovery";

	private Preference mFlashChineseRecovery;
	private Preference mFlashEnglishRecovery;
	private Preference mRebootRecovery;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.activity_flash_recovery);

		mFlashChineseRecovery = (Preference) findPreference(FLASHCHINESERECOVERY);
		mFlashEnglishRecovery = (Preference) findPreference(FLASHENGLISHRECOVERY);
		mRebootRecovery = (Preference) findPreference(REBOOTRECOVERY);
	}
	
	public void onStart(){
		super.onStart();
		
		// 在程序启动时复制数据到SD卡
		AssetCopyer asset = new AssetCopyer(getBaseContext());
		try {
			asset.copy();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
			Preference preference) {
		if (preference == mRebootRecovery) {
			new AlertDialog.Builder(FlashRecoveryActivity.this)
			.setTitle("确定？")
			.setMessage("您确定重启进入刷机模式？")
			.setNegativeButton("是",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							RootCmd.RunRootCmd("reboot recovery");
						}
					}).setPositiveButton("否", null).show();
		}
		
		if (preference == mFlashChineseRecovery) {
							RootCmd.RunRootCmd("dd if=/sdcard/Android/data/com.xs.flashrecovery/files/Coolpad_F1_6050_20140816_Chinese.img of=/dev/recovery");
							Toast.makeText(this, "刷入中文Recovery成功", 2000).show();
			}

		if (preference == mFlashEnglishRecovery) {
							RootCmd.RunRootCmd("dd if=/sdcard/Android/data/com.xs.flashrecovery/files/Coolpad_F1_6050_20140816_English.img of=/dev/recovery");
							Toast.makeText(this, "刷入英文Recovery成功", 2000).show();
			}

		return false;
	}

}
