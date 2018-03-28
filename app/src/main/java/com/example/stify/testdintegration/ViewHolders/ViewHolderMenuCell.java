package com.example.stify.testdintegration.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.stify.testdintegration.MenuCell;
import com.example.stify.testdintegration.R;

public class ViewHolderMenuCell extends RecyclerView.ViewHolder {
    private TextView _name, _price, _describe;

    public ViewHolderMenuCell(View view) {
        super(view);
        _name = view.findViewById(R.id.cellName);
        _price = view.findViewById(R.id.cellPrice);
        _describe = view.findViewById(R.id.cellDescribe);
    }

    public void render(MenuCell menuCell) {
        _name.setText(menuCell.getName());

        String priceStr = String.format("%.2f", menuCell.getPrice());
        priceStr = priceStr.replace('.', ',');
        priceStr += " €";
        _price.setText(priceStr);
        _describe.setText(menuCell.getDescribe());
    }
}
