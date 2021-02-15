package io.github.lsposed.lspd.service;

import android.os.IBinder;
import android.util.Log;

import de.robv.android.xposed.XposedBridge;
import io.github.xposed.xposedservice.IXposedService;

import static io.github.lsposed.lspd.service.Service.TAG;

public class LSPosedService extends IXposedService.Stub {

    public LSPosedService() {
        BridgeService.send(this, new BridgeService.Listener() {
            @Override
            public void onSystemServerRestarted() {
                Log.w(TAG, "system restarted...");
            }

            @Override
            public void onResponseFromBridgeService(boolean response) {
                if (response) {
                    Log.i(TAG, "sent service to bridge");
                } else {
                    Log.w(TAG, "no response from bridge");
                }
            }
        });
    }

    @Override
    public IBinder asBinder() {
        return this;
    }

    @Override
    public int getVersion() {
        return XposedBridge.getXposedVersion();
    }
}