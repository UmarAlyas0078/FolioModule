package com.codesorbit.foliomodule;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.folioreader.Config;
import com.folioreader.FolioReader;
import com.folioreader.model.HighLight;
import com.folioreader.model.locators.ReadLocator;
import com.folioreader.ui.base.OnSaveHighlight;
import com.folioreader.util.AppUtil;
import com.folioreader.util.OnHighlightListener;
import com.folioreader.util.ReadLocatorListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnHighlightListener, ReadLocatorListener, FolioReader.OnClosedListener {
    FolioReader folioReader;
    ArrayList<HighLight> highLightslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // getHighlightAndSave();
        folioReader = FolioReader.get()
                .setOnHighlightListener(this)
                .setReadLocatorListener(this)
                .setOnClosedListener(this);
       // ReadLocator readLocator = getLastReadLocator();
        findViewById(R.id.btn_assest).setOnClickListener(v -> {

            Config config = AppUtil.getSavedConfig(getApplicationContext());
            if (config == null)
                config = new Config();
            config.setAllowedDirection(Config.AllowedDirection.VERTICAL_AND_HORIZONTAL);
           // folioReader.setReadLocator(readLocator);
            folioReader.setConfig(config, true)
                    .openBook("file:///android_asset/TheSilverChair.epub");
        });
    }

    private ReadLocator getLastReadLocator() {
        String jsonString = loadAssetTextAsString("Locators/LastReadLocators/last_read_locator_1.json");
        return ReadLocator.fromJson(jsonString);
    }

    private void getHighlightAndSave() {
        new Thread(() -> {
            highLightslist = null;
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                highLightslist = objectMapper.readValue(
                        loadAssetTextAsString("highlights/highlights_data.json"), new TypeReference<List<HighlightData>>() {
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (highLightslist == null) {
                folioReader.saveReceivedHighLights(highLightslist, new OnSaveHighlight() {
                    @Override
                    public void onFinished() {
                        Log.i("LOG_TAG", "-> saveReadLocator -> " + highLightslist);
                    }
                });
            }
        }).start();
    }

    private String loadAssetTextAsString(String name) {
        BufferedReader in = null;
        try {
            StringBuilder buf = new StringBuilder();
            InputStream is = getAssets().open(name);
            in = new BufferedReader(new InputStreamReader(is));

            String str;
            boolean isFirst = true;
            while ((str = in.readLine()) != null) {
                if (isFirst)
                    isFirst = false;
                else
                    buf.append('\n');
                buf.append(str);
            }
            return buf.toString();
        } catch (IOException e) {
            Log.e("HomeActivity", "Error opening asset " + name);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    Log.e("HomeActivity", "Error closing asset " + name);
                }
            }
        }
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FolioReader.clear();
    }

    @Override
    public void onFolioReaderClosed() {
        Log.v("LOG_TAG", "-> onFolioReaderClosed");
    }

    @Override
    public void onHighlight(HighLight highlight, HighLight.HighLightAction type) {
        Toast.makeText(this,
                "highlight id = " + highlight.getUUID() + " type = " + type,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void saveReadLocator(ReadLocator readLocator) {
        Log.i("LOG_TAG", "-> saveReadLocator -> " + readLocator.toJson());
    }
}