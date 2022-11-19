package com.diakem.dexcomg7logger.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.diakem.dexcomg7logger.database.DebugDatabase;
import com.diakem.dexcomg7logger.database.DebugEntity;

public class DebuggerReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        DebugEntity debugEntity = new DebugEntity();
        debugEntity.message = String.valueOf(intent.getStringExtra("message"));
        debugEntity.creation = System.currentTimeMillis();

        DebugDatabase.getInstance(context).getDebugDao().insertMessages(debugEntity);
    }
}
