package com.example.stify.testdintegration.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.stify.testdintegration.R;

/**
 * Created by stify on 14/03/2018.
 */

public class ViewHolderMenuFooter extends RecyclerView.ViewHolder {
    private TextView _footer;

    public ViewHolderMenuFooter(View view) {
        super(view);
        _footer = view.findViewById(R.id.cellFooter);
    }

    public void render(String footer) {
        _footer.setText(footer);

    }
}

