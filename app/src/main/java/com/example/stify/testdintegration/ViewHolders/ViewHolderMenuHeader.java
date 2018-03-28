package com.example.stify.testdintegration.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.stify.testdintegration.R;

/**
 * Created by stify on 14/03/2018.
 */

public class ViewHolderMenuHeader extends RecyclerView.ViewHolder {
    private TextView _header;

    public ViewHolderMenuHeader(View view) {
        super(view);
        _header = view.findViewById(R.id.cellHeader);
    }

    public void render(String category) {
        _header.setText(category);
    }
}
