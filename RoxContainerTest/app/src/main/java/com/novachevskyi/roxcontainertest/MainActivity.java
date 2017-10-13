package com.novachevskyi.roxcontainertest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.rollout.android.Rox;
import io.rollout.client.RoxOptions;

public class MainActivity extends AppCompatActivity {

    private TextView flagValueTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        flagValueTextView = (TextView) findViewById(R.id.flagValue);

        Button setButton = (Button) findViewById(R.id.set);
        Button updateButton = (Button) findViewById(R.id.update);

        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setRoxUidProperty();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean flagValue = getRoxFlagValue();
                flagValueTextView.setText(String.valueOf(flagValue));
            }
        });

        setupRox();
    }

    private void setupRox() {
        Rox.register(new FeatureFlags());

        RoxOptions options = new RoxOptions.Builder()
                .withVerboseLevel(RoxOptions.VerboseLevel.VERBOSE_LEVEL_DEBUG)
                .build();

        Rox.setup(this.getApplication(), options);
    }

    private void setRoxUidProperty() {
        Rox.setCustomStringProperty("uid", "466664644630110208");

        Rox.sync();
        Rox.unfreeze();
    }

    private boolean getRoxFlagValue() {
        FeatureFlags flags = (FeatureFlags) Rox.getContainer(FeatureFlags.class);

        return flags.featureFlagsTestView3.isEnabled();
    }
}
