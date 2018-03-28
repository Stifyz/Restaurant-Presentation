package com.example.stify.testdintegration;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.stify.testdintegration.ViewHolders.ViewHolderMenuCell;
import com.example.stify.testdintegration.ViewHolders.ViewHolderMenuFooter;
import com.example.stify.testdintegration.ViewHolders.ViewHolderMenuHeader;
import com.truizlop.sectionedrecyclerview.SectionedRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MenuListAdapter extends SectionedRecyclerViewAdapter<ViewHolderMenuHeader, ViewHolderMenuCell, ViewHolderMenuFooter> {
    List<List<MenuCell>> _sections = new ArrayList<List<MenuCell>>();
    Context _context;

    public MenuListAdapter(Context context) {
        _context = context;
    }

    public void add(MenuCell cell) {
        if (_sections.size() == 0 || cell.getCategory() != _sections.get(_sections.size() - 1).get(0).getCategory()) {
            _sections.add(new ArrayList<MenuCell>());
            _sections.get(_sections.size() - 1).add(cell);
        }
        else
            _sections.get(_sections.size() - 1).add(cell);
    }

    @Override
    protected int getItemCountForSection(int section) {
        Log.d("Section", _sections.get(section).get(0).getCategory() + " " + String.valueOf(section));
        Log.d("Item in section", String.valueOf(_sections.get(section).size()));
        return _sections.get(section).size();
    }

    @Override
    protected int getSectionCount() {
        return _sections.size();
    }

    @Override
    protected boolean hasFooterInSection(int section) {
        return false;
    }

    protected LayoutInflater getLayoutInflater(){
        return LayoutInflater.from(_context);
    }

    @Override
    protected ViewHolderMenuHeader onCreateSectionHeaderViewHolder(ViewGroup parent, int viewType) {
        View view = getLayoutInflater().inflate(R.layout.item_menucellheader, parent, false);
        return new ViewHolderMenuHeader(view);
    }

    @Override
    protected ViewHolderMenuFooter onCreateSectionFooterViewHolder(ViewGroup parent, int viewType) {
        View view = getLayoutInflater().inflate(R.layout.item_menucellfooter, parent, false);
        return new ViewHolderMenuFooter(view);
    }

    @Override
    protected ViewHolderMenuCell onCreateItemViewHolder(ViewGroup parent, int viewType) {
        View view = getLayoutInflater().inflate(R.layout.item_menucell, parent, false);
        return new ViewHolderMenuCell(view);
    }

    @Override
    protected void onBindSectionHeaderViewHolder(ViewHolderMenuHeader holder, int section) {
        holder.render(_sections.get(section).get(0).getCategory());
    }

    @Override
    protected void onBindSectionFooterViewHolder(ViewHolderMenuFooter holder, int section) {
        holder.render("");
    }

    @Override
    protected void onBindItemViewHolder(ViewHolderMenuCell holder, int section, int position) {
        Log.d("----------------", "---------------------------");
        Log.d("Section : ", String.valueOf(section));
        Log.d("NumberOfSections : ", String.valueOf(_sections.size()));
        Log.d("Position : ", String.valueOf(position));
        Log.d("SectionSize : ", String.valueOf(_sections.get(section).size()));
        Log.d("----------------", "---------------------------");
        holder.render(_sections.get(section).get(position));
    }

}
